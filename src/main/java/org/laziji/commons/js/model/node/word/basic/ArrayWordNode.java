package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseBracketNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;

public class ArrayWordNode extends BaseBracketNode<ValueParagraphNode> implements BasicWordNode {

    public ArrayWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected Token getOpenBracket() {
        return Token.BRACKET_MID_OPEN;
    }

    @Override
    protected Token getCloseBracket() {
        return Token.BRACKET_MID_CLOSE;
    }

    @Override
    protected ValueParagraphNode getContentNode() {
        return new ValueParagraphNode(this);
    }
}
