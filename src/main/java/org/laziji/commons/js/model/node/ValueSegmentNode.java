package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

public class ValueSegmentNode extends BaseSegmentNode {

    private Token end;

    public ValueSegmentNode(Node parent, Token end) {
        super(parent);
        this.end = end;
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (end != null && unit.getToken() == end) {
            if (!isDone()) {
                throw new Exception();
            }
            return getParent();
        }
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
