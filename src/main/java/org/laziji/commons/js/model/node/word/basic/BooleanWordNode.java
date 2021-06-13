package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;

public class BooleanWordNode extends BaseUnitNode implements BasicWordNode {

    public BooleanWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected Token getUnit() {
        return Token.BOOLEAN;
    }
}
