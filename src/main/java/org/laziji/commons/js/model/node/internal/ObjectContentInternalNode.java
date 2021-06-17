package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

public class ObjectContentInternalNode extends BaseListNode<ProxyNode<Node>> implements InternalNode {

    public ObjectContentInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected ProxyNode<Node> getNextNode() {
        return new ProxyNode<>(this,
                new ObjectContentItemInternalNode(null),
                new NameWordNode(null)
        );
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
