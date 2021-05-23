package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public class TokenNode extends BaseNode {


    public TokenNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
