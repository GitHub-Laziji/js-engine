package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseBracketNode;
import org.laziji.commons.js.model.node.Node;

public class CallFunctionParamsInternalNode extends BaseBracketNode<BracketContentInternalNode> implements InternalNode {

    public CallFunctionParamsInternalNode(Node parent) {
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
    protected BracketContentInternalNode getContentNode() {
        return new BracketContentInternalNode(this, false);
    }
}
