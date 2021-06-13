package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;

public class ProxyParagraphNode extends BaseProxyNode<ParagraphNode> implements ParagraphNode {

    public ProxyParagraphNode(Node parent) {
        super(parent);
        addProxyItem(new BigBracketParagraphNode(null));
        addProxyItem(new DefinedParagraphNode(null));
        addProxyItem(new ValueParagraphNode(null));
        addProxyItem(new EmptyParagraphNode(null));
    }

    @Override
    public boolean shouldEndFlag() {
        return getSelf().shouldEndFlag();
    }
}
