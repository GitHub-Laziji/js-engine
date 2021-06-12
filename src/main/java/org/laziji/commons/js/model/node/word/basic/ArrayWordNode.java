package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseBracketNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.ArrayContentInternalNode;

public class ArrayWordNode extends BaseBracketNode<ArrayContentInternalNode> implements BasicWordNode {

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
    protected ArrayContentInternalNode getContentNode() {
        return new ArrayContentInternalNode(this);
    }
}
