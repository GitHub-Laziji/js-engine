package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.sentence.SentenceNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

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
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.LET, Token.VAR, Token.CONST),
                (self, pre) -> new ListNode<>(
                        this,
                        (listNodeSelf, o) -> new ProxyNode<>(listNodeSelf,
                                new PlanNode(null, (subSelf, subPre) -> new UnitNode(subSelf, Token.NAME)),
                                new PlanNode(null,
                                        (subSelf, subPre) -> new UnitNode(subSelf, Token.NAME),
                                        (subSelf, subPre) -> new UnitNode(subSelf, Token.ASSIGNMENT),
                                        (subSelf, subPre) -> new SentenceNode(subSelf)
                                )
                        ),
                        (listNodeSelf, o) -> new UnitNode(listNodeSelf, Token.COMMA),
                        false,
                        "%s "
                )
        );
    }

}
