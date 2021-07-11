package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.ScriptManager;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.NullValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.Set;

public class NullWordNode extends BaseUnitNode implements WordNode {

    public NullWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) {
        return new NullValue();
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.NULL);
    }

}