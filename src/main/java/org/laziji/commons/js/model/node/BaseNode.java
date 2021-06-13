package org.laziji.commons.js.model.node;

import com.google.common.base.Joiner;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

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
