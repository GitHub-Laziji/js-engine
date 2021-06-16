package org.laziji.commons.js.model.node;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
// TODO lambda 传入上下文 this ,  注解节点
public class ListNode<T extends Node> extends BaseListNode<T> {

    private Supplier<T> nextNode;
    private Function<List<T>, Node> nextSeparator;
    private boolean allowEmpty;
    private String separatorFormat;

    public ListNode(Node parent, Supplier<T> nextNode, Function<List<T>, Node> nextSeparator,
                    boolean allowEmpty, String separatorFormat) {
        super(parent);
        this.nextNode = nextNode;
        this.nextSeparator = nextSeparator;
        this.allowEmpty = allowEmpty;
        this.separatorFormat = separatorFormat;
    }

    public ListNode(Node parent, Supplier<T> nextNode, boolean allowEmpty) {
        super(parent);
        this.nextNode = nextNode;
        this.allowEmpty = allowEmpty;
    }

    @Override
    protected T getNextNode() {
        T node = nextNode.get();
        node.setParent(this);
        return node;
    }

    @Override
    protected Node getNextSeparator() {
        if (nextSeparator == null) {
            return null;
        }
        Node separator = nextSeparator.apply(nodes);
        if (separator == null) {
            return null;
        }
        separator.setParent(this);
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
