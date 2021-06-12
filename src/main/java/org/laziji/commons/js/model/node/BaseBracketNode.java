package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

public abstract class BaseBracketNode<T extends Node> extends BaseNode {

    private TokenUnit open;
    private T node;
    private TokenUnit close;

    public BaseBracketNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (open == null) {
            if (unit.getToken() != getOpenBracket()) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            this.open = unit;
            this.node = getContentNode();
            return node.init();
        }
        if (node != null && !node.isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (close == null) {
            if (unit.getToken() != getCloseBracket()) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            this.close = unit;
            return this;
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return open != null && (node == null || node.isDone()) && close != null;
    }

    @Override
    public String toString() {
        return String.format("(%s)", node.toString());
    }

    protected abstract Token getOpenBracket();

    protected abstract Token getCloseBracket();

    protected abstract T getContentNode();
}
