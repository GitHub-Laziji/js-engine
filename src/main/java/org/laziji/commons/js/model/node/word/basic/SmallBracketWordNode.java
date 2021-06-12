package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseBracketNode;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;

public class SmallBracketWordNode extends BaseBracketNode<ValueParagraphNode> implements BasicWordNode {

    public SmallBracketWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected Token getOpenBracket() {
        return Token.BRACKET_SML_OPEN;
    }

    @Override
    protected Token getCloseBracket() {
        return Token.BRACKET_SML_CLOSE;
    }

    @Override
    protected ValueParagraphNode getContentNode() {
        return new ValueParagraphNode(this);
    }

}
