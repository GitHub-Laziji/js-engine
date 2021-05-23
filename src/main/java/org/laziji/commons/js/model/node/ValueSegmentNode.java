package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ValueSegmentNode extends SegmentNode {

    private Token end;
    private List<ValueSegmentNode> proxyNodes = new ArrayList<>();

    public ValueSegmentNode(Node parent, Token end) {
        super(parent);
        this.end = end;
        proxyNodes.add(new FunctionValueSegmentNode(null));
        proxyNodes.add(new LambdaValueSegmentNode(null));
        proxyNodes.add(new ClassValueSegmentNode(null));
        proxyNodes.add(new FormulaValueSegmentNode(null));
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if(isDone()){
            return getParent().append(unit);
        }
        Iterator<ValueSegmentNode> iterator = proxyNodes.iterator();
        while (iterator.hasNext()) {
            ValueSegmentNode next = iterator.next();
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
