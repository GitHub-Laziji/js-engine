package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public class LetItemValuelessSentenceNode extends BaseNode {

    private TokenUnit name;
    private TokenUnit assignment;
    private ValueSentenceNode node;

    public LetItemValuelessSentenceNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        return null;
    }

    @Override
    public boolean isDone() {
        return name != null && (assignment == null && node == null || assignment != null && node != null && node.isDone());
    }
}
