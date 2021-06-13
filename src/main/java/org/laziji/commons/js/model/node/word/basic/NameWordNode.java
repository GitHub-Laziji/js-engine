package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;

public class NameWordNode extends BaseUnitNode implements BasicWordNode {

    public NameWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected Token getUnit() {
        return Token.NAME;
    }
}
