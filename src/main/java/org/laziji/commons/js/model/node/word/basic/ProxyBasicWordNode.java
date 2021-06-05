package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;

public class ProxyBasicWordNode extends BaseNode implements BasicWordNode {

    public ProxyBasicWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
