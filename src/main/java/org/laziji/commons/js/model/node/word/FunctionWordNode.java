package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.name.LetName;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.FunctionParamsInternalNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.value.FunctionValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

public class FunctionWordNode extends BasePlanNode implements WordNode {

    public FunctionWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Stack<Context> contexts) throws Exception {
        FunctionValue value = new FunctionValue(
                ((FunctionParamsInternalNode) current[2]).getParams(),
                cs -> current[3].run(cs),
                true);
        if (current[1].getSelf() instanceof NameWordNode) {
            NameWordNode nameNode = (NameWordNode) current[1].getSelf();
            contexts.peek().defined(new LetName(nameNode.getUnit().getValue()), value);
        }
        return value;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.FUNCTION),
                (self, pre) -> new ProxyNode<Node>(this, new NameWordNode(null), new EmptyNode(null)),
                (self, pre) -> new FunctionParamsInternalNode(this),
                (self, pre) -> new BigBracketParagraphNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s%s %s";
    }
}
