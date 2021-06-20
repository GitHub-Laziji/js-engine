package org.laziji.commons.js.model.node;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.value.Value;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
    public void setParent(Node parent) {
        this.parent = parent;
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

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s[NYS-%s]", getTabString(depth, start), getClass().getSimpleName());
    }

    @Override
    public Value run(Stack<Context> contexts) throws Exception {
        throw new RunException("Not yet supported.");
    }

    protected String getTabString(int depth) {
        StringBuilder tabSb = new StringBuilder();
        for (int i = depth * 2; i > 0; i--) {
            tabSb.append(' ');
        }
        return tabSb.toString();
    }

    protected <T> T last(List<T> list) {
        return list.get(list.size() - 1);
    }

    protected String getTabString(int depth, boolean start) {
        return start ? getTabString(depth) : "";
    }

    protected String nodesJoin(Collection<? extends Node> nodes, String separator, boolean wrap, int depth, boolean start) {
        if (nodes == null || nodes.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<? extends Node> iterator = nodes.iterator();
        if (iterator.hasNext()) {
            sb.append(iterator.next().toString(depth, start));
        }
        while (iterator.hasNext()) {
            sb.append(separator);
            if (wrap) {
                sb.append('\n').append(iterator.next().toString(depth, true));
            } else {
                sb.append(iterator.next().toString(depth, false));
            }
        }
        return sb.toString();
    }
}
