package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.CallFunctionParamsInternalNode;
import org.laziji.commons.js.model.node.internal.CallNameInternalNode;
import org.laziji.commons.js.model.node.internal.CallObjectParamsInternalNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class NewInstanceWordNode extends BasePlanNode {

    public NewInstanceWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Contexts manager) throws Exception {
        return null;
    }

//    @Override
//    public String toString(int depth, boolean start) {
//        return String.format("%s%s", current[0].toString(depth, start), current[1].toString(depth, false));
//    }


    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(self, Token.NEW),
                (self, pre) -> new CallNameInternalNode(self),
                (self, pre) -> new ListNode<>(
                        self,
                        (subSelf, o) -> new ProxyNode<>(subSelf, new CallFunctionParamsInternalNode(null), new CallObjectParamsInternalNode(null)),
                        true
                ),
                (self, pre) -> new CallFunctionParamsInternalNode(self)
        );
    }
}