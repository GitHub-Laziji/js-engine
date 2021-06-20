package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class CallMemberNameInternalNode extends BasePlanNode implements InternalNode {

    public CallMemberNameInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.DOT),
                (self, pre) -> new NameWordNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s";
    }
}
