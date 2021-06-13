package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;

public class ReturnParagraphNode extends BaseNode implements ParagraphNode {

    private TokenUnit unit;
    private ProxyNode<ParagraphNode> node;

    public ReturnParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (this.unit == null) {
            if (unit.getToken() != Token.RETURN) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            this.unit = unit;
            node = new ProxyNode<>(this, new ValueParagraphNode(null), new EmptyParagraphNode(null));
            return node.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return unit != null && node != null && node.isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        if (node.getSelf() instanceof EmptyParagraphNode) {
            return String.format("%s%s", getTabString(depth, start), unit.getValue());
        }
        return String.format("%s%s %s", getTabString(depth, start), unit.getValue(), node.toString(depth, false));
    }
}
