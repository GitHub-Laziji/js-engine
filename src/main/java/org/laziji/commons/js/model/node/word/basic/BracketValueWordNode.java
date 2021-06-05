package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.ValueWordNode;

public class BracketValueWordNode extends ValueWordNode {

    private TokenUnit open;
    private ValueWordNode node;
    private TokenUnit close;

    public BracketValueWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (open == null) {
            if (unit.getToken() != Token.BRACKET_SML_OPEN) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            this.open = unit;
            this.node = new ValueWordNode(this);
            return node;
        }
        if (node != null && node.isDone()) {
            if (unit.getToken() != Token.BRACKET_SML_CLOSE) {
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
        return open != null && node.isDone() && close != null;
    }
}
