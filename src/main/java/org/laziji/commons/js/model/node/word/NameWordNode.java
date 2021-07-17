package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.model.manager.ScriptManager;
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
    public Value run(ScriptManager manager) throws Exception {
        String name = getUnit().getValue();
        for (int i = manager.getContexts().size() - 1; i >= 0; i--) {
            Value value = manager.getContexts().get(i).get(name);
            if (value != null) {
                return value;
            }
        }
        throw new ReferenceException("%s is not defined", name);
    }

    @Override
    public Context.Entry getPosition(ScriptManager manager) throws Exception {
        String name = getUnit().getValue();
        for (int i = manager.getContexts().size() - 1; i >= 0; i--) {
            Context.Entry entry = manager.getContexts().get(i).getEntry(name);
            if (entry != null) {
                return entry;
            }
        }
        throw new ReferenceException("%s is not defined", name);
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.NAME);
    }
}
