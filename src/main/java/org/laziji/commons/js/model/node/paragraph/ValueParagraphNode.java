package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.sentence.ProxySentenceNode;
import org.laziji.commons.js.model.node.word.ClassWordNode;
import org.laziji.commons.js.model.node.word.FunctionWordNode;

public class ValueParagraphNode extends BaseListNode<ProxySentenceNode> implements ParagraphNode {

    public ValueParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    protected String getSeparatorFormat() {
        return "%s ";
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
    protected ProxySentenceNode getNextNode() {
        return new ProxySentenceNode(this);
    }

    @Override
    protected Node getNextSeparator() {
        return new UnitNode(this, Token.COMMA);
    }
}
