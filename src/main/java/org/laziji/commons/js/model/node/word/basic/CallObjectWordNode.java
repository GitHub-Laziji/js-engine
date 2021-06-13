package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.CallFunctionParamsInternalNode;
import org.laziji.commons.js.model.node.internal.CallNameInternalNode;
import org.laziji.commons.js.model.node.internal.CallObjectParamsInternalNode;

public class CallObjectWordNode extends BaseNode implements BasicWordNode {

    private CallNameInternalNode name;
    private CallObjectParamsInternalNode params;

    public CallObjectWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        name = new CallNameInternalNode(this);
        return name.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (name.isDone() && params == null) {
            params = new CallObjectParamsInternalNode(this);
            return params.init().append(unit);
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return name != null && name.isDone() && params != null && params.isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", name.toString(depth, start), params.toString(depth, false));
    }
}
