package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;
import org.laziji.commons.js.model.node.word.WordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class SmallBracketWordNode extends BasePlanNode implements WordNode {

    public SmallBracketWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_OPEN),
                (self, pre) -> new ValueParagraphNode(this),
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_CLOSE)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s%s", current[0].toString(depth, start), current[1].toString(depth, false),
                current[2].toString(depth, false));
    }

}
