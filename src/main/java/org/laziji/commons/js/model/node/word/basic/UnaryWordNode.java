package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.ProxyWordNode;

public class UnaryWordNode extends BaseNode implements BasicWordNode {

    private TokenUnit unary;
    private ProxyWordNode node;

    public UnaryWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (unary == null) {
            switch (unit.getToken()) {
                case ADD:
                case SUB:
                case NON:
                    unary = unit;
                    node = new ProxyWordNode(this);
                    return node.init();
            }
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return unary != null && node != null && node.isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        //TODO 判断是否需要空格
        return String.format("%s%s %s", start ? getTabString(depth) : "", unary.getValue(), node.toString(depth, false));
    }
}
