package org.laziji.commons.js.model.node;

import com.google.common.collect.ImmutableList;
import org.laziji.commons.js.model.TokenUnit;

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
            return node.init().append(unit);
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
                return separator.init().append(unit);
            }
        }
    }

    @Override
    public boolean isDone() {
        return allowEmpty() && nodes.size() == 0 || nodes.size() == separators.size() + 1 && last(nodes).isDone();
    }

    public List<T> getNodes() {
        return ImmutableList.copyOf(nodes);
    }

    protected boolean allowEmpty() {
        return false;
    }

    protected abstract T getNextNode();

    protected abstract Node getNextSeparator();

    private boolean tryNodeAppend(TokenUnit unit) {
        try {
            T temp = getNextNode();
            temp.setParent(null);
            temp.init().append(unit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean trySeparatorAppend(TokenUnit unit) {
        try {
            Node temp = getNextSeparator();
            temp.setParent(null);
            temp.init().append(unit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
