package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.WordNode;

/**
 * a+b+c
 */
public class SentenceNode extends BaseListNode<WordNode> {

    public SentenceNode(Node parent) {
        super(parent);
    }

    @Override
    public String toString(int depth, boolean start) {
        StringBuilder sb = new StringBuilder();
        sb.append(nodes.get(0).toString(depth, start));
        for (int i = 1; i < nodes.size(); i++) {
            sb.append(' ').append(separators.get(i - 1).toString(depth, false))
                    .append(' ').append(nodes.get(i).toString(depth, false));
        }
        return sb.toString();
    }

    public Class<? extends Node> getSingleWord() throws Exception {
        if (!isDone() || nodes.size() > 1) {
            throw new Exception("");
        }
        return nodes.get(0).getSelf().getClass();
    }

    @Override
    protected WordNode getNextNode() {
        return new ProxyWordNode(this);
    }

    @Override
    protected Node getNextSeparator() {
        return new UnitNode(this, Token.SELF_ADD_BY, Token.SELF_SUB_BY, Token.SELF_AND_BY, Token.SELF_OR_BY,
                Token.SELF_BIT_AND_BY, Token.SELF_BIT_OR_BY, Token.SELF_BIT_XOR_BY, Token.SELF_DIV_BY,
                Token.SELF_MOD_BY, Token.SELF_MUL_BY, Token.ADD, Token.SUB, Token.MUL, Token.DIV, Token.MOD,
                Token.AND, Token.OR, Token.EQUAL, Token.ABS_EQUAL, Token.UNEQUAL, Token.ABS_UNEQUAL, Token.GT,
                Token.GT_EQUAL, Token.LT, Token.LT_EQUAL, Token.BIT_AND, Token.BIT_OR, Token.BIT_XOR, Token.ASSIGNMENT);
    }
}
