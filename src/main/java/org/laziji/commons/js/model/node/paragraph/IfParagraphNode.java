package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.IfInternalNode;
import org.laziji.commons.js.model.node.section.SectionNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.node.word.basic.SmallBracketWordNode;

import java.util.ArrayList;
import java.util.List;

public class IfParagraphNode extends BaseListNode<ProxyNode<Node>> implements ParagraphNode {

    public IfParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public String toString(int depth, boolean start) {
        return nodesJoin(nodes, " else ", false, depth, start);
    }

    @Override
    public boolean shouldEndFlag() {
        return false;
    }

    @Override
    protected ProxyNode<Node> getNextNode() {
        return new ProxyNode<>(this, new IfInternalNode(null), new BigBracketParagraphNode(null));
    }

    @Override
    protected Node getNextSeparator() {
        if (last(nodes).getSelf() instanceof BigBracketParagraphNode) {
            return new DenyNode(this);
        }
        return new UnitNode(this, Token.ELSE);
    }
}
