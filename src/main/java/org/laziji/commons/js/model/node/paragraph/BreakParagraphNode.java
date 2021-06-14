package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;

public class BreakParagraphNode extends BaseUnitNode implements ParagraphNode {

    public BreakParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    protected Token getToken() {
        return Token.BREAK;
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }
}