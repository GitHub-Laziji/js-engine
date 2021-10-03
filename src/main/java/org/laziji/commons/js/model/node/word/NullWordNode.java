package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.JsNull;

import java.util.Collections;
import java.util.Set;

public class NullWordNode extends BaseUnitNode implements WordNode {

    public NullWordNode(Node parent) {
        super(parent);
    }

    @Override
    public JsValue run(Contexts manager) {
        return new JsNull();
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.NULL);
    }

}