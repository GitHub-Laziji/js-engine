package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.word.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ImportFromParagraphNode extends BasePlanNode implements ParagraphNode {

    public ImportFromParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return configuration.isStrict();
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(self, Token.IMPORT),
                (self, pre) -> new UnitNode(self, Token.BRACKET_BIG_OPEN),
                (self, pre) -> new ListNode<>(self,
                        (listNodeSelf, last) -> new ProxyNode<>(listNodeSelf,
                                new NameWordNode(null),
                                new PlanNode(null,
                                        (s, l) -> new NameWordNode(s),
                                        (s, l) -> new UnitNode(s, Token.AS),
                                        (s, l) -> new NameWordNode(s))),
                        (listNodeSelf, last) -> new UnitNode(listNodeSelf, Token.COMMA),
                        false,
                        "%s "
                ),
                (self, pre) -> new UnitNode(self, Token.BRACKET_BIG_CLOSE),
                (self, pre) -> new UnitNode(self, Token.FROM),
                (self, pre) -> new UnitNode(self, Token.STRING)
        );
    }

}

