package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

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
        if (current[1].getSelf() instanceof EmptyParagraphNode) {
            return current[0].toString(depth, start);
        }
        return String.format("%s %s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.RETURN),
                () -> new ProxyNode<>(this, new ValueParagraphNode(null), new EmptyParagraphNode(null))
        );
    }
}
