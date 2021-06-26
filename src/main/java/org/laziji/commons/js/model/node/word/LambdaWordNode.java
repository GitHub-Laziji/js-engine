package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.internal.FunctionParamsInternalNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.sentence.ProxySentenceNode;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.node.word.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class LambdaWordNode extends BasePlanNode implements WordNode {

    public LambdaWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new ProxyNode<>(this, new FunctionParamsInternalNode(null), new NameWordNode(null)),
                (self, pre) -> new UnitNode(this, Token.LAMBDA),
                (self, pre) -> new ProxyNode<>(this, new BigBracketParagraphNode(null), new ProxySentenceNode(null))
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s %s";
    }
}
