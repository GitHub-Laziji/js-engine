package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseBracketNode;
import org.laziji.commons.js.model.node.Node;

public class FunctionParamsInternalNode extends BaseBracketNode<FunctionParamsContentInternalNode> implements InternalNode {

    public FunctionParamsInternalNode(Node parent) {
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
    protected FunctionParamsContentInternalNode getContentNode() {
        return new FunctionParamsContentInternalNode(this);
    }
}
