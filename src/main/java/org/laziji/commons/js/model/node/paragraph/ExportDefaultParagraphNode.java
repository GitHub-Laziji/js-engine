package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.sentence.ProxySentenceNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ExportDefaultParagraphNode extends BasePlanNode implements ParagraphNode {

    public ExportDefaultParagraphNode(Node parent) {
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
                (self, pre) -> new UnitNode(this, Token.DEFAULT),
                (self, pre) -> new ProxySentenceNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s %s";
    }
}

