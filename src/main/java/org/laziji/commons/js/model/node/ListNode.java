package org.laziji.commons.js.model.node;

import java.util.function.BiFunction;

public class ListNode<T extends Node> extends BaseListNode<T> {

    private BiFunction<Node, Node, T> nextNode;
    private BiFunction<Node, T, Node> nextSeparator;
    private boolean allowEmpty;
    private String separatorFormat;

    public ListNode(Node parent, BiFunction<Node, Node, T> nextNode, BiFunction<Node, T, Node> nextSeparator,
                    boolean allowEmpty, String separatorFormat) {
        super(parent);
        this.nextNode = nextNode;
        this.nextSeparator = nextSeparator;
        this.allowEmpty = allowEmpty;
        this.separatorFormat = separatorFormat;
    }

    public ListNode(Node parent, BiFunction<Node, Node, T> nextNode, boolean allowEmpty) {
        super(parent);
        this.nextNode = nextNode;
        this.allowEmpty = allowEmpty;
    }

    @Override
    protected T getNextNode() {
        return nextNode.apply(this, nodes.size() > 0 ? last(nodes) : null);
    }

    @Override
    protected Node getNextSeparator() {
        if (nextSeparator == null) {
            return null;
        }
        Node separator = nextSeparator.apply(this, nodes.size() > 0 ? last(nodes) : null);
        if (separator == null) {
            return null;
        }
        return separator;
    }

    @Override
    protected boolean allowEmpty() {
        return allowEmpty;
    }

    @Override
    public Node getSelf() {
        return super.getSelf();
    }

    @Override
    public String getSeparatorFormat() {
        return separatorFormat;
    }
}
