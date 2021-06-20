package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ReturnParagraphNode extends BasePlanNode implements ParagraphNode {

    public ReturnParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    public String toString(int depth, boolean start) {
        if (current[1].getSelf() instanceof EmptyNode) {
            return current[0].toString(depth, start);
        }
        return String.format("%s %s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.RETURN),
                (self, pre) -> new ProxyNode<>(this, new ValueParagraphNode(null), new EmptyNode(null))
        );
    }
}
