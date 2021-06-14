package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.ListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.CallNameInternalNode;
import org.laziji.commons.js.model.node.internal.ProxyCallParamsInternalNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class CallWordNode extends BasePlanNode implements BasicWordNode {

    public CallWordNode(Node parent) {
        super(parent);
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new CallNameInternalNode(this),
                () -> new ListNode<>(
                        this,
                        () -> new ProxyCallParamsInternalNode(null),
                        false
                )
        );
    }
}
