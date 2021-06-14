package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

public abstract class BaseUnitNode extends BaseNode {

    private TokenUnit unit;

    public BaseUnitNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (this.unit == null && unit.getToken() == getToken()) {
            this.unit = unit;
            return this;
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return unit != null;
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", getTabString(depth, start), unit.getValue());
    }

    protected abstract Token getToken();
}
