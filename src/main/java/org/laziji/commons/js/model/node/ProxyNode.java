package org.laziji.commons.js.model.node;

public class ProxyNode<T extends Node> extends BaseProxyNode<T> {

    public ProxyNode(Node parent, T... nodes) {
        super(parent);
        for (T node : nodes) {
            addProxyItem(node);
        }
    }
}
