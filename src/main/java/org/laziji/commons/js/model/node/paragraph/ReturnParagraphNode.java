package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.FunctionContext;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.value.UndefinedValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ReturnParagraphNode extends BasePlanNode implements ParagraphNode {

    public ReturnParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Contexts manager) throws Exception {
        Value value = new UndefinedValue();
        if (current[1].getSelf() instanceof ValueParagraphNode) {
            value = current[1].run(manager);
        }
        for (int i = manager.getContexts().size() - 1; i >= 0; i--) {
            Context context = manager.getContexts().get(i);
            context.close();
            if (context instanceof FunctionContext) {
                ((FunctionContext) context).setReturnValue(value);
                return null;
            }
        }
        throw new RunException();
    }

    @Override
    public String toString(int depth, boolean start) {
        if (current[1].getSelf() instanceof EmptyNode) {
            return current[0].toString(depth, start);
        }
        return String.format("%s %s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

    @Override
    public boolean shouldEndFlag() {
        return configuration.isStrict();
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.RETURN),
                (self, pre) -> new ProxyNode<>(this, new ValueParagraphNode(null), new EmptyNode(null))
        );
    }
}
