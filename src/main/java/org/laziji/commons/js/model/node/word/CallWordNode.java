package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.ListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.CallNameInternalNode;
import org.laziji.commons.js.model.node.internal.ProxyCallParamsInternalNode;
import org.laziji.commons.js.model.value.ObjectValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class CallWordNode extends BasePlanNode implements VariableWordNode {

    public CallWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        List<ProxyCallParamsInternalNode> nodes = ((ListNode<ProxyCallParamsInternalNode>) current[1]).getNodes();
        Value value = current[0].run(manager);
        ObjectValue caller = null;
        for (ProxyCallParamsInternalNode node : nodes) {
            Value tempValue = node.run(caller, value, manager);
            caller = (ObjectValue) value;
            value = tempValue;
        }
        return value;
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

    @Override
    public Context.Entry getPosition(ScriptManager manager) throws Exception {
        Value value = current[0].run(manager);
        List<ProxyCallParamsInternalNode> nodes = ((ListNode<ProxyCallParamsInternalNode>) current[1]).getNodes();
        for (int i = 0; i < nodes.size() - 1; i++) {
            Context.Entry position = nodes.get(i).getPosition(value, manager);
            value = position.getValue();
        }
        return nodes.get(nodes.size() - 1).getPosition(value, manager);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new CallNameInternalNode(self),
                (self, pre) -> new ListNode<>(
                        self,
                        (subSelf, o) -> new ProxyCallParamsInternalNode(subSelf),
                        false
                )
        );
    }
}
