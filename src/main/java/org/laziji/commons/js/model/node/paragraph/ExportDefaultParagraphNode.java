package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.section.SectionNode;
import org.laziji.commons.js.model.node.sentence.SentenceNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ExportDefaultParagraphNode extends BasePlanNode implements ParagraphNode {

    public ExportDefaultParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.EXPORT),
                () -> new UnitNode(this, Token.DEFAULT),
                () -> new SentenceNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s %s";
    }
}

