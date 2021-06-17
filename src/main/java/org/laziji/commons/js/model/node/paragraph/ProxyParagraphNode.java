package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;

public class ProxyParagraphNode extends BaseProxyNode<ParagraphNode> implements ParagraphNode {

    public ProxyParagraphNode(Node parent) {
        super(parent);
        addProxyItem(new BigBracketParagraphNode(null));
        addProxyItem(new DefinedParagraphNode(null));
        addProxyItem(new ValueParagraphNode(null));
        addProxyItem(new ReturnParagraphNode(null));
        addProxyItem(new ContinueParagraphNode(null));
        addProxyItem(new BreakParagraphNode(null));
        addProxyItem(new IfParagraphNode(null));
        addProxyItem(new WhileParagraphNode(null));
        addProxyItem(new DoWhileParagraphNode(null));
        addProxyItem(new ImportParagraphNode(null));
        addProxyItem(new ImportFromParagraphNode(null));
        addProxyItem(new ExportDefaultParagraphNode(null));
        addProxyItem(new ExportDefinedParagraphNode(null));
        addProxyItem(new EmptyParagraphNode(null));
        addProxyItem(new LineRemarkParagraphNode(null));
    }

    @Override
    public boolean shouldEndFlag() {
        Node self = getSelf();
        if (self instanceof ParagraphNode) {
            return ((ParagraphNode) self).shouldEndFlag();
        }
        return true;
    }
}
