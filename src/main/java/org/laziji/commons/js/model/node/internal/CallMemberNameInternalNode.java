package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

public class CallMemberNameInternalNode extends BaseNode implements InternalNode {

    private TokenUnit dot;
    private NameWordNode name;

    public CallMemberNameInternalNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (dot == null) {
            if (unit.getToken() != Token.DOT) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            dot = unit;
            name = new NameWordNode(this);
            return name.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return dot != null && name != null && name.isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s%s", start ? getTabString(depth) : "", dot.getValue(), name.toString(depth, false));
    }
}
