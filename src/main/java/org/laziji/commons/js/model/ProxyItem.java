package org.laziji.commons.js.model;

import org.laziji.commons.js.model.node.Node;

public class ProxyItem {

    private Node root;
    private Node current;

    public ProxyItem(Node root) {
        this.root = this.current = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }
}
