package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ImportDefaultFromParagraphNode extends BasePlanNode implements ParagraphNode {

    public ImportDefaultFromParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.IMPORT),
                (self, pre) -> new NameWordNode(this),
                (self, pre) -> new UnitNode(this, Token.FROM),
                (self, pre) -> new UnitNode(this, Token.STRING)
        );
    }

}

