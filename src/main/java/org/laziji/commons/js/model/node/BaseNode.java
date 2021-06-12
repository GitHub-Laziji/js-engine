package org.laziji.commons.js.model.node;

public abstract class BaseNode implements Node {

    private Node parent;


    public BaseNode(Node parent) {
        this.parent = parent;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    @Override
    public Node getSelf() {
        return this;
    }

    @Override
    public Node init() {
        return this;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
