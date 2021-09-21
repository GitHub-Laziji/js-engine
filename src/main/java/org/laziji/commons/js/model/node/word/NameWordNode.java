package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.UndefinedValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.Set;

public class NameWordNode extends BaseUnitNode implements VariableWordNode {

    public NameWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        String name = getUnit().getValue();
        for (Context context : manager.getReContexts()) {
            if (context.hasProperty(name)) {
                return context.getProperty(name);
            }
        }
        throw new ReferenceException("%s is not defined", name);
    }

    @Override
    public Value assignment(ScriptManager manager, Value value) throws Exception {
        String name = getUnit().getValue();
        for (Context context : manager.getReContexts()) {
            if (context.hasProperty(name)) {
                context.addProperty(name, value);
            }
        }
        throw new ReferenceException("%s is not defined", name);
    }

    public void define(ScriptManager manager, Value value, Context.ContextPropertyType type) throws Exception {
        String name = getUnit().getValue();
        manager.getContexts().peek().addProperty(name, value, type);
    }

    public String getName() {
        return getUnit().getValue();
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.NAME);
    }
}
