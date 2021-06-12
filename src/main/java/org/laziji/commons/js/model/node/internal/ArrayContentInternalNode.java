package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.EmptyParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;

public class ArrayContentInternalNode extends BaseProxyNode<ParagraphNode> implements InternalNode{

    public ArrayContentInternalNode(Node parent) {
        super(parent);
        addProxyItem(new EmptyParagraphNode(null));
        addProxyItem(new ValueParagraphNode(null));
    }
}
