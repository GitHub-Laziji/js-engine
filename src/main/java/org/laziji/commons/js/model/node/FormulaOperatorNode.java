package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public class FormulaOperatorNode extends BaseNode {

    @Override
    public Node append(TokenUnit unit) {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
