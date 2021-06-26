package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.basic.CallWordNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * a+b+c
 */
public class AssignmentSentenceNode extends BasePlanNode implements SentenceNode {

    public AssignmentSentenceNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new ProxyNode<>(self, new CallWordNode(null), new NameWordNode(null)),
                (self, pre) -> new UnitNode(self, Token.ASSIGNMENT),
                (self, pre) -> new ProxySentenceNode(self)
        );
    }

}
