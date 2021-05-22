package org.laziji.commons.js.model.node;

public abstract class BaseNode implements Node {

    private Node parent;

    public BaseNode() {

    }

    public BaseNode(Node parent) {
        this.parent = parent;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
