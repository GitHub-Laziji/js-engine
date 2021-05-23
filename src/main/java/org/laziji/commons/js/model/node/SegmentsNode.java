package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.List;

public class SegmentsNode extends BaseNode {

    private List<BaseSegmentNode> segments = new ArrayList<>();
    private boolean done = false;

    public SegmentsNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
