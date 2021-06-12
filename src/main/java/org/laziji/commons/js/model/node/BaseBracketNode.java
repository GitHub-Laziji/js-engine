package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;

public abstract class BaseBracketNode extends BaseNode {
    public BaseBracketNode(Node parent) {
        super(parent);
    }

    protected abstract Token getOpenBracket();

    protected abstract Token getCloseBracket();

    protected abstract Node getContentNode();
}
