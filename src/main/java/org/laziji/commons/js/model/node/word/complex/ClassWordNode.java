package org.laziji.commons.js.model.node.word.complex;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;

public class ClassWordNode extends BaseNode implements ComplexWordNode {

    public ClassWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        throw new Exception("Not yet supported.");
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String toString(int depth, boolean start) {
        return null;
    }
}
