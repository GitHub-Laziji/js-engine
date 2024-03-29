package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.object.JsBooleanObject;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Collections;
import java.util.Set;

public class BooleanWordNode extends BaseUnitNode implements WordNode {

    public BooleanWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected JsValue subRun(Contexts manager) {
        return new JsBooleanObject(Boolean.parseBoolean(getUnit().getValue()));
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.BOOLEAN);
    }

}
