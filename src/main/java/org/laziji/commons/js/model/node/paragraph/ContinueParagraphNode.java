package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;

public class ContinueParagraphNode extends BaseUnitNode implements ParagraphNode {

    public ContinueParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    protected Token getToken() {
        return Token.CONTINUE;
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }
}
