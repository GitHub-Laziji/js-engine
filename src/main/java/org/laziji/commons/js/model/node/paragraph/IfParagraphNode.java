package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.internal.IfInternalNode;
import org.laziji.commons.js.model.node.section.SectionNode;
import org.laziji.commons.js.model.node.word.basic.SmallBracketWordNode;

import java.util.ArrayList;
import java.util.List;

public class IfParagraphNode extends BaseNode implements ParagraphNode {

    private List<Node> nodes = new ArrayList<>();

    public IfParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        IfInternalNode node = new IfInternalNode(this);
        nodes.add(node);
        return node.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (!isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        Node last = nodes.get(nodes.size() - 1);
        if (last.getSelf() instanceof IfInternalNode && unit.getToken() == Token.ELSE) {
            ProxyNode<Node> node = new ProxyNode<>(this,
                    new IfInternalNode(null), new BigBracketParagraphNode(null));
            nodes.add(node);
            return node.init();
        }
        if (getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return nodes.get(nodes.size() - 1).isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return nodesJoin(nodes, " else ", false, depth, start);
    }

    @Override
    public boolean shouldEndFlag() {
        return false;
    }
}
