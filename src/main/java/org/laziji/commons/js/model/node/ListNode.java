package org.laziji.commons.js.model.node;

import java.util.function.BiFunction;

// TODO  注解节点
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
        return nextNode.apply(this, null);
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
    public String toString(int depth, boolean start) {
        StringBuilder sb = new StringBuilder();
        sb.append(nodes.get(0).toString(depth, start));
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
}
