package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.EmptyParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;

public class BracketContentInternalNode extends BaseProxyNode<ParagraphNode> implements InternalNode {

    public BracketContentInternalNode(Node parent, boolean notEmpty) {
        super(parent);
        addProxyItem(new ValueParagraphNode(null));
        if (!notEmpty) {
            addProxyItem(new EmptyParagraphNode(null));
        }
    }
}
