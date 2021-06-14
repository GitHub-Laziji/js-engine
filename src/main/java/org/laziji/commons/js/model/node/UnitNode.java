package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;

public class UnitNode extends BaseUnitNode {

    private Token token;

    public UnitNode(Node parent, Token token) {
        super(parent);
        this.token = token;
    }

    @Override
    protected Token getToken() {
        return token;
    }
}
