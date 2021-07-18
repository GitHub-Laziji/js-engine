package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.value.Value;

public class EmptyNode extends BaseNode {

    public EmptyNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
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
