package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.LoopContext;
import org.laziji.commons.js.model.context.LoopUnitContext;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class WhileParagraphNode extends BasePlanNode implements ParagraphNode {

    public WhileParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    protected String getStringFormat() {
        return "%s %s%s%s %s";
    }

    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        LoopContext context = new LoopContext();
        manager.getContexts().push(context);
        while (current[2].run(manager).toBoolean()) {
            if (context.isClose()) {
                break;
            }
            manager.getContexts().push(new LoopUnitContext());
            ((BigBracketParagraphNode) current[4]).getBody().run(manager);
            manager.getContexts().pop();
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
                (self, pre) -> new UnitNode(self, Token.WHILE),
                (self, pre) -> new UnitNode(self, Token.BRACKET_SML_OPEN),
                (self, pre) -> new ValueParagraphNode(self),
                (self, pre) -> new UnitNode(self, Token.BRACKET_SML_CLOSE),
                (self, pre) -> new BigBracketParagraphNode(self)
        );
    }
}
