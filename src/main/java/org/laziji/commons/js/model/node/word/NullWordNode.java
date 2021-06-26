package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.value.NullValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.function.BiFunction;

public class NullWordNode extends BaseUnitNode implements WordNode {

    public NullWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Stack<Context> contexts) {
        return new NullValue();
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.NULL);
    }

}