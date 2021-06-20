package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.internal.OperatorWordNameInternalNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class BeforeOperatorWordNode extends BasePlanNode implements BasicWordNode {

    public BeforeOperatorWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.SELF_ADD, Token.SELF_SUB),
                (self, pre) -> new OperatorWordNameInternalNode(this)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

}
