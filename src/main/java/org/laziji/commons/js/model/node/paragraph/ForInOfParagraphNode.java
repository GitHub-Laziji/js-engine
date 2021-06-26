package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.sentence.ProxySentenceNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ForInOfParagraphNode extends BasePlanNode implements ParagraphNode {

    public ForInOfParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return false;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(self, Token.FOR),
                (self, pre) -> new UnitNode(self, Token.BRACKET_SML_OPEN),
                (self, pre) -> new ProxyNode<>(self,
                        new UnitNode(null, Token.LET, Token.VAR, Token.CONST),
                        new EmptyNode(null)),
                (self, pre) -> new NameWordNode(self),
                (self, pre) -> new UnitNode(self, Token.OF, Token.IN),
                (self, pre) -> new ProxySentenceNode(self),
                (self, pre) -> new UnitNode(self, Token.BRACKET_SML_CLOSE),
                (self, pre) -> new BigBracketParagraphNode(self)
        );
    }

    @Override
    protected String getStringFormat() {
        if (current[2].getSelf() instanceof EmptyNode) {
            return "%s %s%s%s %s %s%s %s";
        }
        return "%s %s%s %s %s %s%s %s";
    }
}
