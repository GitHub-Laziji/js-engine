package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.value.JsObject;
import org.laziji.commons.js.model.value.JsValue;

public class ObjectContentInternalNode extends BaseListNode<ProxyNode<Node>> implements InternalNode {

    public ObjectContentInternalNode(Node parent) {
        super(parent);
    }

    @Override
    public JsValue run(Contexts manager) throws Exception {
        JsObject value = new JsObject();
        for (Node node : getNodes()) {
            node = node.getSelf();
            if (node instanceof NameWordNode) {
                NameWordNode nameNode = (NameWordNode) node;
                value.addProperty(nameNode.getName(), nameNode.run(manager));
            } else if (node instanceof ObjectContentItemInternalNode) {
                ObjectContentItemInternalNode itemNode = (ObjectContentItemInternalNode) node;
                value.addProperty(itemNode.getKey(manager), itemNode.getValue(manager));
            }
        }
        return value;
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
