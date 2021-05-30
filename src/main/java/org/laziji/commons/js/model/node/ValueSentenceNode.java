package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.List;

public class ValueSentenceNode extends BaseNode {

    private List<Node> nodes = new ArrayList<>();

    public ValueSentenceNode(Node parent) {
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
