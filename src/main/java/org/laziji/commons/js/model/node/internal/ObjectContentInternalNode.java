package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

public class ObjectContentInternalNode extends BaseListNode<ObjectContentItemInternalNode> implements InternalNode {

    public ObjectContentInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected ObjectContentItemInternalNode getNextNode() {
        return new ObjectContentItemInternalNode(this);
    }

    @Override
    protected Node getNextSeparator() {
        return new UnitNode(this, Token.COMMA);
    }

    @Override
    protected boolean allowEmpty() {
        return true;
    }

    @Override
    public String toString(int depth, boolean start) {
        return nodesJoin(nodes, ",", true, depth, true);
    }

}
