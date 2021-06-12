package org.laziji.commons.js.model.node.section;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.EmptyParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ProxyParagraphNode;

import java.util.ArrayList;
import java.util.List;

/**
 * a=1,b=2,c=3;
 * a=1,b=2,c=3;
 */
public class SectionNode extends BaseNode {

    private List<ParagraphNode> paragraphs = new ArrayList<>();

    public SectionNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        ProxyParagraphNode paragraph = new ProxyParagraphNode(this);
        this.paragraphs.add(paragraph);
        return paragraph.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (!isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        ParagraphNode last = this.paragraphs.get(this.paragraphs.size() - 1);
        if (last.isDone()) {
            if (unit.getToken() == Token.SEMICOLON) {
                ProxyParagraphNode paragraph = new ProxyParagraphNode(this);
                this.paragraphs.add(paragraph);
                return paragraph.init();
            }
            if (last.shouldEndFlag()) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            if (last.getSelf() instanceof EmptyParagraphNode) {
                if (getParent() != null) {
                    return getParent().append(unit);
                }
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            ProxyParagraphNode paragraph = new ProxyParagraphNode(this);
            this.paragraphs.add(paragraph);
            return paragraph.init().append(unit);
        }

        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return paragraphs.get(paragraphs.size() - 1).isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        StringBuilder sb = new StringBuilder();
        List<ParagraphNode> newNodes = new ArrayList<>();
        for (ParagraphNode node : paragraphs) {
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
}
