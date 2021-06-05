package org.laziji.commons.js.model.node.word.complex;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;

public class FunctionWordNode extends BaseNode implements ComplexWordNode {

    public FunctionWordNode(Node parent) {
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
}
