package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.model.node.EmptyNode;
import org.laziji.commons.js.model.node.Node;

public class EmptyParagraphNode extends EmptyNode implements ParagraphNode {

    public EmptyParagraphNode(Node parent) {
        super(parent);
    }

    public boolean shouldEndFlag() {
        return false;
    }

}
