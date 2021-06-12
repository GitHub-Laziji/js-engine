package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseBracketNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.ObjectContentInternalNode;

public class ObjectWordNode extends BaseBracketNode<ObjectContentInternalNode> implements BasicWordNode {

    public ObjectWordNode(Node parent) {
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
    protected ObjectContentInternalNode getContentNode() {
        return new ObjectContentInternalNode(this);
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s\n%s\n%s%s", start ? getTabString(depth) : "",
                open.getValue(), node.toString(depth + 1, true), getTabString(depth), close.getValue());
    }
}
