package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public class ValueParagraphNode extends ParagraphNode {

    public ValueParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
