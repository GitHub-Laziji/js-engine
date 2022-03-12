package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.JsValue;

public class EmptyNode extends BaseNode {

    public EmptyNode(Node parent) {
        super(parent);
    }

    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        return null;
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public String toString(int depth, boolean start) {
        return "";
    }
}
