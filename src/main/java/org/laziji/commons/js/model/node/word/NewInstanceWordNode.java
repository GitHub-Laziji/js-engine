package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.CallFunctionParamsInternalNode;
import org.laziji.commons.js.model.node.internal.CallMemberNameInternalNode;
import org.laziji.commons.js.model.node.internal.CallNameInternalNode;
import org.laziji.commons.js.model.node.internal.CallObjectParamsInternalNode;
import org.laziji.commons.js.model.value.StringValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class NewInstanceWordNode extends BasePlanNode implements WordNode {

    public NewInstanceWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Contexts manager) throws Exception {
        return new StringValue("[TODO]");
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s %s%s", current[0].toString(depth, start), current[1].toString(depth, false), current[2].toString(depth, false));
    }


    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(self, Token.NEW),
                (self, pre) -> new CallNameInternalNode(self),
                (self, pre) -> new ListNode<>(
                        self,
                        (self2, pre2) -> new PlanNode(self2,
                                (self3, pre3) -> new ListNode<>(
                                        self3,
                                        (subSelf, o) -> new ProxyNode<>(subSelf, new CallMemberNameInternalNode(null), new CallObjectParamsInternalNode(null)),
                                        true
                                ),
                                (self3, pre3) -> new CallFunctionParamsInternalNode(self)
                        ),
                        false
                )

        );
    }
}