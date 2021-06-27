package org.laziji.commons.js.model.node;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class PlanNode extends BasePlanNode {

    private BiFunction<Node, Node, Node>[] suppliers;

    @SafeVarargs
    public PlanNode(Node parent, BiFunction<Node, Node, Node>... suppliers) {
        super(parent);
        this.suppliers = suppliers;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(suppliers);
    }

}
