package org.laziji.commons.js.model.node;

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
                try {
                    T temp = getNextNode();
                    temp.setParent(null);
                    temp.init().append(unit);
                } catch (Exception e) {
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
            try {
                Node temp = getNextSeparator();
                temp.setParent(null);
                temp.init().append(unit);
            } catch (Exception e) {
                if (getParent() != null) {
                    return getParent().append(unit);
                }
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            Node separator = getNextSeparator();
            separators.add(separator);
            return separator.init().append(unit);
        }
    }

    @Override
    public boolean isDone() {
        return allowEmpty() && nodes.size() == 0 || nodes.size() == separators.size() + 1 && last(nodes).isDone();
    }

    protected boolean allowEmpty() {
        return false;
    }

    protected abstract T getNextNode();

    protected abstract Node getNextSeparator();
}
