package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;

public class NumberWordNode extends BaseUnitNode implements BasicWordNode {

    public NumberWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected Token getToken() {
        return Token.NUMBER;
    }
}