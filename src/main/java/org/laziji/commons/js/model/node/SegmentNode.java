package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public class SegmentNode extends BaseNode{

    public SegmentNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit units) {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
