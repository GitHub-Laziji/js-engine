package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

/**
 * a=1
 */
public class SentenceNode extends BaseNode {


    public SentenceNode(Node parent) {
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
