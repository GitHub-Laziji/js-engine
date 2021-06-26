package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.sentence.ProxySentenceNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;
import org.laziji.commons.js.model.node.word.basic.NumberWordNode;
import org.laziji.commons.js.model.node.word.basic.StringWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ObjectContentItemInternalNode extends BasePlanNode implements InternalNode {

    public ObjectContentItemInternalNode(Node parent) {
        super(parent);
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
