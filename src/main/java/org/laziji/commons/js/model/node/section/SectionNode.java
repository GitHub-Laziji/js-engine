package org.laziji.commons.js.model.node.section;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.EmptyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.paragraph.EmptyParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ProxyParagraphNode;

import java.util.ArrayList;
import java.util.List;

/**
 * a=1,b=2,c=3;
 * a=1,b=2,c=3;
 */
public class SectionNode extends BaseListNode<ParagraphNode> {

    public SectionNode(Node parent) {
        super(parent);
    }

    @Override
    public String toString(int depth, boolean start) {
        StringBuilder sb = new StringBuilder();
        List<ParagraphNode> newNodes = new ArrayList<>();
        for (ParagraphNode node : nodes) {
            if (node.getSelf() instanceof EmptyParagraphNode) {
                continue;
            }
            newNodes.add(node);
        }
        for (ParagraphNode node : newNodes) {
            sb.append(node.toString(depth, true));
            if (node.shouldEndFlag()) {
                sb.append(';');
            }
            if (node != newNodes.get(newNodes.size() - 1)) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    @Override
    protected ParagraphNode getNextNode() {
        return new ProxyParagraphNode(this);
    }

    @Override
    protected Node getNextSeparator() {
        Node self = last(nodes).getSelf();
        if (!(self instanceof ParagraphNode)
                || self instanceof EmptyParagraphNode
                || ((ParagraphNode) self).shouldEndFlag()) {
            return new UnitNode(this, Token.SEMICOLON);
        }
        return new EmptyNode(this);
    }
}
