package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ValueSentenceNode extends ParagraphNode {

    private Token end;
    private List<ValueSentenceNode> proxyNodes = new ArrayList<>();

    public ValueSentenceNode(Node parent, Token end) {
        super(parent);
        this.end = end;
        proxyNodes.add(new FunctionValueSentenceNode(null));
        proxyNodes.add(new LambdaValueSentenceNode(null));
        proxyNodes.add(new ClassValueSentenceNode(null));
        proxyNodes.add(new FormulaValueSentenceNode(null));
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if(isDone()){
            return getParent().append(unit);
        }
        Iterator<ValueSentenceNode> iterator = proxyNodes.iterator();
        while (iterator.hasNext()) {
            ValueSentenceNode next = iterator.next();
            try {
                next.append(unit);
            } catch (Exception e) {
                iterator.remove();
            }
        }
        return null;
    }

    @Override
    public boolean isDone() {
        return proxyNodes.size() == 1 && proxyNodes.get(0).isDone();
    }
}
