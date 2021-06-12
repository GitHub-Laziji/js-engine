package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;

public class EmptyParagraphNode extends BaseNode implements ParagraphNode {

    public EmptyParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public boolean shouldEndFlag() {
        return false;
    }

    @Override
    public String toString(int depth) {
        return "";
    }
}
