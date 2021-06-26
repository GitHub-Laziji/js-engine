package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.Set;
import java.util.Stack;

public class NameWordNode extends BaseUnitNode implements WordNode {

    public NameWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Stack<Context> contexts) throws Exception {
        String name = getUnit().getValue();
        for (int i = 0; i < contexts.size(); i++) {
            Context context = contexts.get(i);
            Value value = context.get(name);
            if (value != null) {
                return value;
            }
        }
        throw new ReferenceException("%s is not defined", name);
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.NAME);
    }
}
