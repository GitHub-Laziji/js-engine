package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.ListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.internal.DefinedInternalNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class DefinedParagraphNode extends BasePlanNode implements ParagraphNode {

    public DefinedParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s %s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.LET, Token.VAR, Token.CONST),
                () -> new ListNode<>(
                        this,
                        () -> new DefinedInternalNode(null),
                        (o) -> new UnitNode(null, Token.COMMA),
                        false,
                        "%s "
                )
        );
    }

}
