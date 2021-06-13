package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.CallNameInternalNode;
import org.laziji.commons.js.model.node.internal.ProxyCallParamsInternalNode;

import java.util.ArrayList;
import java.util.List;

public class CallWordNode extends BaseNode implements BasicWordNode {

    private CallNameInternalNode name;
    private List<ProxyCallParamsInternalNode> nodes = new ArrayList<>();

    public CallWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        name = new CallNameInternalNode(this);
        return name.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (!name.isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (nodes.size() == 0) {
            ProxyCallParamsInternalNode node = new ProxyCallParamsInternalNode(this);
            nodes.add(node);
            return node.init().append(unit);
        }
        if (isDone()) {
            try {
                new ProxyCallParamsInternalNode(null).init().append(unit);
                ProxyCallParamsInternalNode node = new ProxyCallParamsInternalNode(this);
                nodes.add(node);
                return node.init().append(unit);
            } catch (Exception ignored) {

            }
            if (getParent() != null) {
                return getParent().append(unit);
            }
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return name != null && name.isDone() && nodes.get(nodes.size() - 1).isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", name.toString(depth, start), nodesJoin(nodes, "", false, depth, start));
    }
}
