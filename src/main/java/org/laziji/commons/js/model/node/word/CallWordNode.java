package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.ListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.CallNameInternalNode;
import org.laziji.commons.js.model.node.internal.ProxyCallParamsInternalNode;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.JsObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class CallWordNode extends BasePlanNode implements VariableWordNode {

    public CallWordNode(Node parent) {
        super(parent);
    }

    @Override
    public JsValue run(Contexts manager) throws Exception {
        List<ProxyCallParamsInternalNode> nodes = ((ListNode<ProxyCallParamsInternalNode>) current[1]).getNodes();
        JsValue pre = current[0].run(manager);
        JsObject caller = null;
        for (ProxyCallParamsInternalNode node : nodes) {
            JsValue tempValue = node.run(caller, pre, manager);
            caller = (JsObject) pre;
            pre = tempValue;
        }
        return pre;
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

    @Override
    public JsValue assignment(Contexts manager, JsValue value) throws Exception {
        List<ProxyCallParamsInternalNode> nodes = ((ListNode<ProxyCallParamsInternalNode>) current[1]).getNodes();
        JsValue pre = current[0].run(manager);
        JsObject caller = null;
        for (int i = 0; i < nodes.size() - 1; i++) {
            JsValue tempValue = nodes.get(i).run(caller, pre, manager);
            caller = (JsObject) pre;
            pre = tempValue;
        }
        return nodes.get(nodes.size() - 1).assignment(pre, manager, value);
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
