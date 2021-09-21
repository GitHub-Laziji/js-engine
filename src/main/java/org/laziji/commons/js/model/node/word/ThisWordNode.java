package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.NumberValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.Set;

public class ThisWordNode extends BaseUnitNode implements WordNode {

    public ThisWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Contexts manager) {
        return new NumberValue(Double.valueOf(getUnit().getValue()));
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.THIS);
    }

}