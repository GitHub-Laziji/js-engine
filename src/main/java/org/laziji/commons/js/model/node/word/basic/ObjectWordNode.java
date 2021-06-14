package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.internal.ObjectContentInternalNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ObjectWordNode extends BasePlanNode implements BasicWordNode {

    public ObjectWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.BRACKET_BIG_OPEN),
                () -> new ObjectContentInternalNode(this),
                () -> new UnitNode(this, Token.BRACKET_BIG_CLOSE)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s\n%s\n%s", current[0].toString(depth, start),
                current[1].toString(depth + 1, true), current[2].toString(depth, true));
    }
}
