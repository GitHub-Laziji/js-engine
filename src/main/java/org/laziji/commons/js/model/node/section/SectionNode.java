package org.laziji.commons.js.model.node.section;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.ParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ProxyParagraphNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.ArrayList;
import java.util.List;

/**
 * a=1,b=2,c=3;
 * a=1,b=2,c=3;
 */
public class SectionNode extends BaseNode {

    private List<ParagraphNode> paragraphs = new ArrayList<>();
    private boolean end;

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
        if (paragraphs.get(paragraphs.size() - 1).isDone() && unit.getToken() == Token.COMMA) {
            ProxyParagraphNode paragraph = new ProxyParagraphNode(this);
            this.paragraphs.add(paragraph);
            return paragraph.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return paragraphs.size() == 1 || paragraphs.get(paragraphs.size() - 1).isDone() && end;
    }

}
