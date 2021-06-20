package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.sentence.SentenceNode;
import org.laziji.commons.js.model.node.word.complex.ClassWordNode;
import org.laziji.commons.js.model.node.word.complex.FunctionWordNode;

public class ValueParagraphNode extends BaseListNode<SentenceNode> implements ParagraphNode {

    public ValueParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public String toString(int depth, boolean start) {
        return nodesJoin(nodes, ", ", false, depth, start);
    }

    @Override
    public boolean shouldEndFlag() {
        if (nodes.size() == 1) {
            try {
                Class<? extends Node> wordClass = nodes.get(0).getSingleWord();
                if (wordClass == FunctionWordNode.class || wordClass == ClassWordNode.class) {
                    return false;
                }
            } catch (Exception ignored) {
            }
        }
        return true;
    }

    @Override
    protected SentenceNode getNextNode() {
        return new SentenceNode(this);
    }

    @Override
    protected Node getNextSeparator() {
        return new UnitNode(this, Token.COMMA);
    }
}
