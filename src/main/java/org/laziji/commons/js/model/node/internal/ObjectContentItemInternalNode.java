package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.sentence.SentenceNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;
import org.laziji.commons.js.model.node.word.basic.NumberWordNode;
import org.laziji.commons.js.model.node.word.basic.StringWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ObjectContentItemInternalNode extends BasePlanNode implements InternalNode {

    public ObjectContentItemInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new ProxyNode<>(this, new StringWordNode(null),
                        new NumberWordNode(null), new NameWordNode(null),
                        new CallObjectParamsInternalNode(null)),
                () -> new UnitNode(this, Token.COLON),
                () -> new SentenceNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s %s";
    }
}
