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

    @Override
    public String toString() {
        return toString(0, true);
    }

    protected String getTabString(int depth) {
        StringBuilder tabSb = new StringBuilder();
        for (int i = depth * 2; i > 0; i--) {
            tabSb.append(' ');
        }
        return tabSb.toString();
    }
}
