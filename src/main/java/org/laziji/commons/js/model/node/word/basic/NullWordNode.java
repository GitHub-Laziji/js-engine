package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;

public class NullWordNode extends BaseUnitNode implements BasicWordNode {

    public NullWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected Token getUnit() {
        return Token.NULL;
    }
}