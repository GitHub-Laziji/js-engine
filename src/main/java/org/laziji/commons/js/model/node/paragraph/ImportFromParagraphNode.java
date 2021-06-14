package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ImportFromParagraphNode extends BasePlanNode implements ParagraphNode {

    public ImportFromParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.IMPORT),
                () -> new NameWordNode(this),
                () -> new UnitNode(this, Token.FROM),
                () -> new UnitNode(this, Token.STRING)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s %s %s";
    }
}

