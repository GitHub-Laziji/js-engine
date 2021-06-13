package org.laziji.commons.js.model.node;

import java.util.List;

public class ProxyNode<T extends Node> extends BaseProxyNode<T> {

    public ProxyNode(Node parent, List<T> nodes) {
        super(parent);
        for (T node : nodes) {
            addProxyItem(node);
        }
    }
}
