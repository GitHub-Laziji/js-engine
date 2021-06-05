package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;

public class BracketWordNode extends BaseNode implements BasicWordNode {

    private TokenUnit open;
    private ValueParagraphNode node;
    private TokenUnit close;

    public BracketWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (open == null) {
            if (unit.getToken() != Token.BRACKET_SML_OPEN) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            this.open = unit;
            this.node = new ValueParagraphNode(this);
            return node.init();
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
