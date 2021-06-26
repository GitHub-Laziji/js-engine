package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.value.NumberValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.Set;
import java.util.Stack;

public class NumberWordNode extends BaseUnitNode implements WordNode {

    public NumberWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Stack<Context> contexts) {
        return new NumberValue(getUnit().getValue());
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.NUMBER);
    }

}