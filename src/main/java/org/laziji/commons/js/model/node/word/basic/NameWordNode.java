package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;

public class NameWordNode extends BaseNode implements BasicWordNode {

    private TokenUnit name;

    public NameWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (name == null && unit.getToken() == Token.NAME) {
            this.name = unit;
            return this;
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return name != null;
    }

    @Override
    public String toString(int depth, boolean start) {
        return name.getValue();
    }
}
