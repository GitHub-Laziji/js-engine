package org.laziji.commons.js.model.node;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.TokenUnit;

import java.util.Set;

public abstract class BaseUnitNode extends BaseNode {

    private TokenUnit unit;

    public BaseUnitNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (this.unit == null && getTokens().contains(unit.getToken())) {
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

    public TokenUnit getUnit() {
        return unit;
    }

    protected abstract Set<Token> getTokens();
}
