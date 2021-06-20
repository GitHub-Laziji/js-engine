package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class ContinueParagraphNode extends BasePlanNode implements ParagraphNode {

    public ContinueParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Collections.singletonList(
                (self, pre) -> new UnitNode(this, Token.CONTINUE)
        );
    }
}
