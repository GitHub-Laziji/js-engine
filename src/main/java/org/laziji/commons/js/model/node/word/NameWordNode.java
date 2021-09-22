package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.Set;

public class NameWordNode extends BaseUnitNode implements VariableWordNode {

    public NameWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Contexts contexts) throws Exception {
        String name = getUnit().getValue();
        return contexts.getProperty(name);
    }

    @Override
    public Value assignment(Contexts contexts, Value value) throws Exception {
        String name = getUnit().getValue();
        return contexts.addProperty(name, value);
    }

    public void define(Contexts contexts, Value value, Context.ContextPropertyType type) throws Exception {
        String name = getUnit().getValue();
        contexts.addProperty(name, value, type);
    }

    public String getName() {
        return getUnit().getValue();
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.NAME);
    }
}
