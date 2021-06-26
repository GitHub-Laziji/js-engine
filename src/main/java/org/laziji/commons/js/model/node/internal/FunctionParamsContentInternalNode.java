package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.NameWordNode;

public class FunctionParamsContentInternalNode extends BaseListNode<NameWordNode> implements InternalNode {

    public FunctionParamsContentInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected NameWordNode getNextNode() {
        return new NameWordNode(this);
    }

    @Override
    protected Node getNextSeparator() {
        return new UnitNode(this, Token.COMMA);
    }

    @Override
    protected boolean allowEmpty() {
        return true;
    }

    @Override
    protected String getSeparatorFormat() {
        return "%s ";
    }

}
