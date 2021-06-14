package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.BracketContentInternalNode;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SmallBracketWordNode extends BasePlanNode implements BasicWordNode {

    public SmallBracketWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.BRACKET_SML_OPEN),
                () -> new ValueParagraphNode(this),
                () -> new UnitNode(this, Token.BRACKET_SML_CLOSE)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s%s", current[0].toString(depth, start), current[1].toString(depth, false),
                current[2].toString(depth, false));
    }

}
