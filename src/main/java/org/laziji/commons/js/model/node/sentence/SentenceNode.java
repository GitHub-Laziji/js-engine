package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.WordNode;

import java.util.ArrayList;
import java.util.List;

/**
 * a+b+c
 */
public class SentenceNode extends BaseNode {


    private List<WordNode> nodes = new ArrayList<>();
    private List<TokenUnit> tokens = new ArrayList<>();

    public SentenceNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        ProxyWordNode word = new ProxyWordNode(this);
        this.nodes.add(word);
        return word.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (!isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        switch (unit.getToken()) {
            case SELF_ADD_BY:
            case SELF_SUB_BY:
            case SELF_AND_BY:
            case SELF_OR_BY:
            case SELF_BIT_AND_BY:
            case SELF_BIT_OR_BY:
            case SELF_BIT_XOR_BY:
            case SELF_DIV_BY:
            case SELF_MOD_BY:
            case SELF_MUL_BY:
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case MOD:
            case AND:
            case OR:
            case EQUAL:
            case ABS_EQUAL:
            case UNEQUAL:
            case ABS_UNEQUAL:
            case GT:
            case GT_EQUAL:
            case LT:
            case LT_EQUAL:
            case BIT_AND:
            case BIT_OR:
            case BIT_XOR:
            case ASSIGNMENT:
                tokens.add(unit);
                ProxyWordNode word = new ProxyWordNode(this);
                this.nodes.add(word);
                return word.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return nodes.get(nodes.size() - 1).isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        StringBuilder sb = new StringBuilder();
        sb.append(nodes.get(0).toString(depth, start));
        for (int i = 1; i < nodes.size(); i++) {
            sb.append(' ').append(tokens.get(i - 1).getValue())
                    .append(' ').append(nodes.get(i).toString(depth, false));
        }
        return sb.toString();
    }
}
