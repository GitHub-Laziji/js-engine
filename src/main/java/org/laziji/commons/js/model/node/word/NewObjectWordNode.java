package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.*;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.object.JsObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class NewObjectWordNode extends BasePlanNode implements WordNode {

    public NewObjectWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        List<Node> nodes = getFlatNodes();
        JsValue pre = current[1].run(manager);
        JsObject caller = null;
        for (int i = 0; i < nodes.size() - 1; i++) {
            Node node = nodes.get(i);
            JsValue tempValue = null;
            if (node instanceof CallFunctionParamsInternalNode) {
                tempValue = ((CallFunctionParamsInternalNode) node).run(caller, pre, manager);
            }
            if (node instanceof CallMemberNameInternalNode) {
                tempValue = ((CallMemberNameInternalNode) node).run(pre);
            }
            if (node instanceof CallObjectParamsInternalNode) {
                tempValue = ((CallObjectParamsInternalNode) node).run(pre, manager);
            }
            caller = (JsObject) pre;
            pre = tempValue;
        }
        return ((CallFunctionParamsInternalNode) nodes.get(nodes.size() - 1)).instantiate(pre, manager);
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s %s%s", current[0].toString(depth, start), current[1].toString(depth, false), current[2].toString(depth, false));
    }


    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(self, Token.NEW),
                (self, pre) -> new CallNameInternalNode(self),
                (self, pre) -> new ListNode<>(
                        self,
                        (self2, pre2) -> new PlanNode(self2,
                                (self3, pre3) -> new ListNode<>(
                                        self3,
                                        (subSelf, o) -> new ProxyNode<>(subSelf, new CallMemberNameInternalNode(null), new CallObjectParamsInternalNode(null)),
                                        true
                                ),
                                (self3, pre3) -> new CallFunctionParamsInternalNode(self)
                        ),
                        false
                )

        );
    }

    private List<Node> getFlatNodes() {
        List<Node> nodes = new ArrayList<>();
        for (Node item : ((ListNode<?>) current[2]).getNodes()) {
            ListNode<?> subList = (ListNode<?>) ((PlanNode) item).getNodes().get(0);
            for (Node subItem : subList.getNodes()) {
                nodes.add(subItem.getSelf());
            }
            CallFunctionParamsInternalNode subCall = (CallFunctionParamsInternalNode) ((PlanNode) item).getNodes().get(1);
            nodes.add(subCall);
        }
        return nodes;
    }
}

