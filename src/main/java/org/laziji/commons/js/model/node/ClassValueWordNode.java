package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public class ClassValueWordNode extends ValueWordNode {

    public ClassValueWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        throw new Exception("Not yet supported.");
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
