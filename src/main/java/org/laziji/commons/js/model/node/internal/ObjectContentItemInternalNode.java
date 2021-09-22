package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.sentence.ProxySentenceNode;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.node.word.NumberWordNode;
import org.laziji.commons.js.model.node.word.StringWordNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ObjectContentItemInternalNode extends BasePlanNode implements InternalNode {

    public ObjectContentItemInternalNode(Node parent) {
        super(parent);
    }


    public String getKey(Contexts manager) throws Exception {
        Node node = current[0].getSelf();
        if (node instanceof BaseUnitNode) {
            return ((BaseUnitNode) node).getUnit().getValue();
        } else {
            return ((CallObjectParamsInternalNode) node).getNodes().get(1).run(manager).toString();
        }
    }


    public Value getValue(Contexts manager) throws Exception {
        return current[2].run(manager);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new ProxyNode<>(this, new StringWordNode(null),
                        new NumberWordNode(null), new NameWordNode(null),
                        new CallObjectParamsInternalNode(null)),
                (self, pre) -> new UnitNode(this, Token.COLON),
                (self, pre) -> new ProxySentenceNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s %s";
    }
}
