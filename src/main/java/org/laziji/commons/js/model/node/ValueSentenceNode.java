package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class ValueSentenceNode extends ParagraphNode {

    private Queue<Node> proxyNodes = new LinkedList<>();

    public ValueSentenceNode(Node parent) {
        super(parent);
        proxyNodes.add(new FunctionValueSentenceNode(null));
        proxyNodes.add(new LambdaValueSentenceNode(null));
        proxyNodes.add(new ClassValueSentenceNode(null));
        proxyNodes.add(new FormulaValueSentenceNode(null));
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (isDone()) {
            return getParent().append(unit);
        }
        Node backup = null;
        for (int i = proxyNodes.size(); !proxyNodes.isEmpty() && i >= 0; i--) {
            Node node = proxyNodes.poll();
            try {
                proxyNodes.add(node.append(unit));
            } catch (Exception e) {
                if (node.isDone()) {
                    backup = node;
                }
            }
        }
        if (proxyNodes.isEmpty()) {
            proxyNodes.add(backup);
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return proxyNodes.size() == 1 && proxyNodes.peek().isDone();
    }


}
