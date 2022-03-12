package org.laziji.commons.js.model.node;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.manager.NodeConfiguration;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class BaseNode implements Node {

    protected NodeConfiguration configuration;
    protected Node parent;

    public BaseNode(Node parent) {
        this.parent = parent;
        if (parent != null) {
            this.configuration = parent.getConfiguration();
        }
        if (this.configuration == null) {
            this.configuration = new NodeConfiguration();
            this.configuration.setStrict(false);
        }
    }

    public BaseNode(NodeConfiguration configuration, Node parent) {
        this.configuration = configuration;
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
    public String toString() {
        return toString(0, true);
    }

    @Override
    public NodeConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void setConfiguration(NodeConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s[NYS-%s]", getTabString(depth, start), getClass().getSimpleName());
    }

    @Override
    public JsValue run(Contexts contexts) throws Exception {
        if(Top.getThreadLocalTop().isTimeout()){
            throw new RunException("Run timeout.");
        }
        return subRun(contexts);
    }

    protected JsValue subRun(Contexts contexts) throws Exception {
        throw new RunException("%s Not yet supported.", this.getClass().getSimpleName());
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
