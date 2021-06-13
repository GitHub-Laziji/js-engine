package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

public abstract class BaseBracketNode<T extends Node> extends BaseNode {

    protected TokenUnit open;
    protected T node;
    protected TokenUnit close;

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
            return this.node.init();
        }
        if (!node.isDone()) {
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
    public String toString(int depth, boolean start) {
        return String.format("%s%s%s", open.getValue(), node.toString(depth, false), close.getValue());
    }

    protected abstract Token getOpenBracket();

    protected abstract Token getCloseBracket();

    protected abstract T getContentNode();
}
