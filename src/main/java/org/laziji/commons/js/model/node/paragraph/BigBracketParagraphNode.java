package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseBracketNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.section.SectionNode;

public class BigBracketParagraphNode extends BaseBracketNode<SectionNode> implements ParagraphNode {

    public BigBracketParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    protected Token getOpenBracket() {
        return Token.BRACKET_BIG_OPEN;
    }

    @Override
    protected Token getCloseBracket() {
        return Token.BRACKET_BIG_CLOSE;
    }

    @Override
    protected SectionNode getContentNode() {
        return new SectionNode(this);
    }


    @Override
    public boolean shouldEndFlag() {
        return false;
    }

    @Override
    public String toString(int depth) {
        return String.format("%s\n%s%s", open.getValue(), node.toString(depth+1), close.getValue());
    }
}