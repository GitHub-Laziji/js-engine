package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public class ValuelessSentenceNode extends ParagraphNode {

    public ValuelessSentenceNode(Node parent) {
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
