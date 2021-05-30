package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
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
        if (name == null) {
            if (unit.getToken() != Token.NAME) {
                throw new Exception(String.format("[%s] is not the expected token. expected [name]", unit.getToken().toString()));
            }
            name = unit;
            return this;
        }
        if (assignment == null) {
            if (unit.getToken() != Token.ASSIGNMENT) {
                throw new Exception(String.format("[%s] is not the expected token. expected [name]", unit.getToken().toString()));
            }
            assignment = unit;
            node = new ValueSentenceNode(this);
            return node.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return name != null && (assignment == null && node == null || assignment != null && node != null && node.isDone());
    }
}
