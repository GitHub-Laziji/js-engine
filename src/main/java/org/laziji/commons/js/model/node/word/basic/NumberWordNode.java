package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;

public class NumberWordNode extends BaseNode implements BasicWordNode {

    private TokenUnit number;

    public NumberWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (number == null && unit.getToken() == Token.NUMBER) {
            this.number = unit;
            return this;
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return number != null;
    }

    @Override
    public String toString(int depth, boolean start) {
        return number.getValue();
    }
}
