package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;

public class BreakParagraphNode extends BaseNode implements ParagraphNode {

    private TokenUnit unit;

    public BreakParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (this.unit == null) {
            if (unit.getToken() != Token.BREAK) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            this.unit = unit;
            return this;
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return unit != null;
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", start ? getTabString(depth) : "", unit.getValue());
    }
}