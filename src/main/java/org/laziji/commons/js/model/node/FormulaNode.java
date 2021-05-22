package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

public class FormulaNode extends BaseNode {

    private boolean deep;

    public FormulaNode(Node parent) {
        this(parent, false);
    }

    public FormulaNode(Node parent, boolean deep) {
        super(parent);
        this.deep = deep;
    }

    @Override
    public Node append(TokenUnit unit) {
        if (deep && unit.getToken() == Token.BRACKET_SML_CLOSE) {
            return getParent();
        }
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
