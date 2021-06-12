package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;

public class ProxyParagraphNode extends BaseProxyNode<ParagraphNode> implements ParagraphNode {

    public ProxyParagraphNode(Node parent) {
        super(parent);
        addProxyItem(new BigBracketParagraphNode(null));
        addProxyItem(new LetParagraphNode(null));
        addProxyItem(new ValueParagraphNode(null));
    }
}
