package org.laziji.commons.js.model.node;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListNode<T extends Node> extends BaseNode {

    protected List<T> nodes = new ArrayList<>();
    protected List<Node> separators = new ArrayList<>();

    public BaseListNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (nodes.size() <= separators.size()) {
            if (separators.size() > 0 && !last(separators).isDone()) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            if (nodes.size() == 0 && allowEmpty()) {
                if (!tryNodeAppend(unit)) {
                    if (getParent() != null) {
                        return getParent().append(unit);
                    }
                    throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
                }
            }
            T node = getNextNode();
            nodes.add(node);
            return node.append(unit);
        } else {
            if (!last(nodes).isDone()) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            Node separator = getNextSeparator();
            if (separator == null) {
                if (tryNodeAppend(unit)) {
                    separators.add(new EmptyNode(this));
                    return append(unit);
                } else {
                    if (getParent() != null) {
                        return getParent().append(unit);
                    }
                    throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
                }
            } else {
                if (!trySeparatorAppend(unit)) {
                    if (getParent() != null) {
                        return getParent().append(unit);
                    }
                    throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
                }
                separators.add(separator);
                return separator.append(unit);
            }
        }
    }

    @Override
    public boolean isDone() {
        return allowEmpty() && nodes.size() == 0 || nodes.size() == separators.size() + 1 && last(nodes).isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        if (nodes.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(nodes.get(0).toString(depth, start));
        String separatorFormat = getSeparatorFormat();
        for (int i = 1; i < nodes.size(); i++) {
            if (separatorFormat == null) {
                sb.append(separators.get(i - 1).toString(depth, false));
            } else {
                sb.append(String.format(separatorFormat, separators.get(i - 1).toString(depth, false)));
            }
            sb.append(nodes.get(i).toString(depth, false));
        }
        return sb.toString();
    }

    public List<T> getNodes() {
        return ImmutableList.copyOf(nodes);
    }

    protected boolean allowEmpty() {
        return false;
    }

    protected String getSeparatorFormat() {
        return null;
    }

    protected abstract T getNextNode();

    protected abstract Node getNextSeparator();

    private boolean tryNodeAppend(TokenUnit unit) {
        try {
            T temp = getNextNode();
            temp.setParent(null);
            temp.append(unit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean trySeparatorAppend(TokenUnit unit) {
        try {
            Node temp = getNextSeparator();
            temp.setParent(null);
            temp.append(unit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
