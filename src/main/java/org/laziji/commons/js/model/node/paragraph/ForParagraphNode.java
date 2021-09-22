package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.LoopContext;
import org.laziji.commons.js.model.context.LoopUnitContext;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ForParagraphNode extends BasePlanNode implements ParagraphNode {

    public ForParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Contexts manager) throws Exception {
        LoopContext context = new LoopContext();
        manager.getContexts().push(new LoopContext());
        current[2].run(manager);
        while (current[4].run(manager).toBoolean()) {
            if (context.isClose()) {
                break;
            }
            manager.getContexts().push(new LoopUnitContext());
            ((BigBracketParagraphNode) current[8]).getBody().run(manager);
            manager.getContexts().pop();
            current[6].run(manager);
        }
        manager.getContexts().pop();
        return null;
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
                        new DefinedParagraphNode(null),
                        new ValueParagraphNode(null),
                        new EmptyParagraphNode(null)),
                (self, pre) -> new UnitNode(self, Token.SEMICOLON),
                (self, pre) -> new ProxyNode<>(self,
                        new ValueParagraphNode(null),
                        new EmptyParagraphNode(null)),
                (self, pre) -> new UnitNode(self, Token.SEMICOLON),
                (self, pre) -> new ProxyNode<>(self,
                        new ValueParagraphNode(null),
                        new EmptyParagraphNode(null)),
                (self, pre) -> new UnitNode(self, Token.BRACKET_SML_CLOSE),
                (self, pre) -> new BigBracketParagraphNode(self)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s%s%s%s%s%s%s %s";
    }
}
