package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.List;

public class SegmentsNode implements Node {

    private List<SegmentNode> segments = new ArrayList<>();

    @Override
    public Node getParent() {
        return null;
    }

    @Override
    public Node append(TokenUnit tokenUnit) {
        return null;
    }

}
