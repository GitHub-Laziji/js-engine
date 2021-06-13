package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;

public class UndefinedWordNode extends BaseNode implements BasicWordNode {

    private TokenUnit unit;

    public UndefinedWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (this.unit == null && unit.getToken() == Token.UNDEFINED) {
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
        return String.format("%s%s", start ? getTabString(depth) : "", unit.getValue());
    }
}
