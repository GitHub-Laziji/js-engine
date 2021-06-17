package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ExportDefinedParagraphNode extends BasePlanNode implements ParagraphNode {

    public ExportDefinedParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.EXPORT),
                (self, pre) -> new DefinedParagraphNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s";
    }
}

