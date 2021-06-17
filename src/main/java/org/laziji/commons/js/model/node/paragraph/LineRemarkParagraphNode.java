package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.*;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class LineRemarkParagraphNode extends BasePlanNode implements ParagraphNode {

    public LineRemarkParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return false;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Collections.singletonList(
                (self, pre) -> new UnitNode(self, Token.REMARK_OF_LINE)
        );
    }
}
