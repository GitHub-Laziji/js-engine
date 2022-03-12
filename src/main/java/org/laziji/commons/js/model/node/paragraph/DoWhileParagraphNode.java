package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.LoopContext;
import org.laziji.commons.js.model.context.LoopUnitContext;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.SmallBracketWordNode;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class DoWhileParagraphNode extends BasePlanNode implements ParagraphNode {

    public DoWhileParagraphNode(Node parent) {
        super(parent);
    }


    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        LoopContext context = new LoopContext();
        manager.getContexts().push(context);
        do {
            if (context.isClose()) {
                break;
            }
            manager.getContexts().push(new LoopUnitContext());
            ((BigBracketParagraphNode) current[1]).getBody().run(manager);
            manager.getContexts().pop();
        } while (((SmallBracketWordNode) current[3]).getBody().run(manager).toBoolean());
        manager.getContexts().pop();
        return null;
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.DO),
                (self, pre) -> new BigBracketParagraphNode(this),
                (self, pre) -> new UnitNode(this, Token.WHILE),
                (self, pre) -> new SmallBracketWordNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s %s %s";
    }
}
