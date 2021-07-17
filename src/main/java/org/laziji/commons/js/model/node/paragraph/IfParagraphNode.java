package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.IfInternalNode;
import org.laziji.commons.js.model.value.Value;

public class IfParagraphNode extends BaseListNode<ProxyNode<Node>> implements ParagraphNode {

    public IfParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        for (Node node : getNodes()) {
            node = node.getSelf();
            if (node instanceof IfInternalNode && ((IfInternalNode) node).runExp(manager).toBoolean().getValue()) {
                ((IfInternalNode) node).runBody(manager);
                break;
            } else if (node instanceof BigBracketParagraphNode) {
                node.run(manager);
                break;
            }
        }
        return null;
    }

    @Override
    protected String getSeparatorFormat() {
        return " %s ";
    }

    @Override
    public boolean shouldEndFlag() {
        return false;
    }

    @Override
    protected ProxyNode<Node> getNextNode() {
        return new ProxyNode<>(this, new IfInternalNode(null), new BigBracketParagraphNode(null));
    }

    @Override
    protected Node getNextSeparator() {
        if (last(nodes).getSelf() instanceof BigBracketParagraphNode) {
            return new DenyNode(this);
        }
        return new UnitNode(this, Token.ELSE);
    }
}
