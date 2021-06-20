package org.laziji.commons.js.model.node;

import com.google.common.collect.ImmutableList;
import org.laziji.commons.js.model.TokenUnit;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public abstract class BasePlanNode extends BaseNode {

    private List<BiFunction<Node, Node, Node>> plan;
    protected Node[] current;

    public BasePlanNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (plan == null) {
            plan = getPlan();
            current = new Node[plan.size()];
        }
        for (int i = 0; i < plan.size(); i++) {
            if (current[i] != null) {
                continue;
            }
            if (i > 0 && !current[i - 1].isDone()) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            current[i] = plan.get(i).apply(this, i > 0 ? current[i - 1] : null);
            return current[i].init().append(unit);
        }
        if (!current[plan.size() - 1].isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        for (Node node : current) {
            if (node == null || !node.isDone()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(int depth, boolean start) {
        if (getStringFormat() == null) {
            return nodesJoin(Arrays.asList(current), " ", false, depth, start);
        }
        Object[] values = new Object[current.length];
        for (int i = 0; i < current.length; i++) {
            values[i] = current[i].toString(depth, i == 0 && start);
        }
        return String.format(getStringFormat(), values);
    }

    public List<Node> getNodes() {
        return ImmutableList.copyOf(current);
    }

    protected String getStringFormat() {
        return null;
    }

    protected abstract List<BiFunction<Node, Node, Node>> getPlan();

}
