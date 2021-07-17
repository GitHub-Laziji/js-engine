package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.paragraph.DefinedParagraphNode;

/**
 * a+b+c
 */
public class ProxySentenceNode extends BaseProxyNode<SentenceNode> implements SentenceNode {

    public ProxySentenceNode(Node parent) {
        super(parent);
        addProxyItem(new CalculationSentenceNode(null));
        addProxyItem(new AssignmentSentenceNode(null));
    }

    public Class<? extends Node> getSingleWord() throws Exception {
        Node self = getSelf();
        if (!(self instanceof CalculationSentenceNode)) {
            throw new Exception();
        }
        CalculationSentenceNode calcNode = (CalculationSentenceNode) self;
        if (!isDone() || calcNode.getNodes().size() > 1) {
            throw new Exception();
        }
        return calcNode.getNodes().get(0).getSelf().getClass();
    }

}
