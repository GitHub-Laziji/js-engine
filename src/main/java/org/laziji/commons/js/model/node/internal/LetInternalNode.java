package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.sentence.SentenceNode;

public class LetInternalNode extends BaseNode implements InternalNode {

    private TokenUnit name;
    private TokenUnit assignment;
    private SentenceNode node;

    public LetInternalNode(Node parent) {
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
            node = new SentenceNode(this);
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

    @Override
    public String toString(int depth, boolean start) {
        if (assignment == null) {
            return name.getValue();
        }
        return String.format("%s = %s", name.getValue(), node.toString(depth, false));
    }
}