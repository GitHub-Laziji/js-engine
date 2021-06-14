package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.word.basic.SmallBracketWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class IfInternalNode extends BasePlanNode implements InternalNode {

    public IfInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.IF),
                () -> new SmallBracketWordNode(this),
                () -> new BigBracketParagraphNode(this)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s %s", current[0].toString(depth, start),
                current[1].toString(depth, false), current[2].toString(depth, false));
    }

}
