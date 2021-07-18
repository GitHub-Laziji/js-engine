package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.LoopUnitContext;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class ContinueParagraphNode extends BasePlanNode implements ParagraphNode {

    public ContinueParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        for (int i = manager.getContexts().size() - 1; i >= 0; i--) {
            Context context = manager.getContexts().get(i);
            context.close();
            if (context instanceof LoopUnitContext) {
                return null;
            }
        }
        throw new RunException();
    }

    @Override
    public boolean shouldEndFlag() {
        return configuration.isStrict();
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Collections.singletonList(
                (self, pre) -> new UnitNode(this, Token.CONTINUE)
        );
    }
}
