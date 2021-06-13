package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.OperatorWordNameInternalNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;

public class AfterOperatorWordNode extends BaseNode implements BasicWordNode {

    private OperatorWordNameInternalNode node;
    private TokenUnit operator;

    public AfterOperatorWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        node = new OperatorWordNameInternalNode(this);
        return node.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (node.isDone() && operator == null) {
            switch (unit.getToken()) {
                case SELF_ADD:
                case SELF_SUB:
                    operator = unit;
                    return this;
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
        return operator != null && node != null && node.isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s%s", getTabString(depth, start), node.toString(depth, false), operator.getValue());
    }
}
