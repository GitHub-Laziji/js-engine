package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public class ValueWordMarkNode extends BaseNode {

    private TokenUnit mark;

    public ValueWordMarkNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (mark == null) {
            switch (unit.getToken()) {
                case ADD:
                case SUB:
                case MUL:
                case DIV:
                    this.mark = unit;
                    return this;
                default:
                    throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return mark != null;
    }
}
