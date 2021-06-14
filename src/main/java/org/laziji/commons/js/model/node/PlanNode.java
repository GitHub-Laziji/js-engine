package org.laziji.commons.js.model.node;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class PlanNode extends BasePlanNode {

    private Supplier<Node>[] suppliers;

    @SafeVarargs
    public PlanNode(Node parent, Supplier<Node>... suppliers) {
        super(parent);
        this.suppliers = suppliers;
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(suppliers);
    }

}
