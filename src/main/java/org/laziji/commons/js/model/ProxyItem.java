package org.laziji.commons.js.model;

import org.laziji.commons.js.model.node.Node;

public class ProxyItem<T extends Node> {

    private T root;
    private Node current;

    public ProxyItem(T root) {
        this.current = this.root = root;
    }

    public T getRoot() {
        return root;
    }

    public void setRoot(T root) {
        this.root = root;
    }

    public Node getCurrent() {
        return current;
    }

    public void setCurrent(Node current) {
        this.current = current;
    }
}
