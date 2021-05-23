package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.List;

public class ValueSegmentNode extends SegmentNode {

    private Token end;
    private List<ValueSegmentNode> proxyNodes = new ArrayList<>();

    public ValueSegmentNode(Node parent, Token end) {
        super(parent);
        this.end = end;
        proxyNodes.add(new FunctionValueSegmentNode(this));
        proxyNodes.add(new LambdaValueSegmentNode(this));
        proxyNodes.add(new ClassValueSegmentNode(this));
        proxyNodes.add(new FormulaValueSegmentNode(this));
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (end != null && unit.getToken() == end) {
            if (!isDone()) {
                throw new Exception();
            }
            return getParent();
        }
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
