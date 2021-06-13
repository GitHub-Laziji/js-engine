package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseBracketNode;
import org.laziji.commons.js.model.node.Node;

public class CallObjectParamsInternalNode extends BaseBracketNode<BracketContentInternalNode> implements InternalNode {

    public CallObjectParamsInternalNode(Node parent) {
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
    protected BracketContentInternalNode getContentNode() {
        return new BracketContentInternalNode(this, true);
    }
}
