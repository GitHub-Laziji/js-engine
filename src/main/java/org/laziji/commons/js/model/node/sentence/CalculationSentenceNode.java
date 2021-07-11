package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;
import org.laziji.commons.js.model.ScriptManager;
import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.value.Value;

/**
 * a+b+c
 */
public class CalculationSentenceNode extends BaseListNode<Node> implements SentenceNode {

    private static final Token[] tokens = new Token[]{
            Token.ADD, Token.SUB, Token.MUL, Token.DIV, Token.MOD,
            Token.AND, Token.OR,
            Token.EQUAL, Token.ABS_EQUAL, Token.UNEQUAL, Token.ABS_UNEQUAL,
            Token.GT, Token.GT_EQUAL, Token.LT, Token.LT_EQUAL,
            Token.BIT_AND, Token.BIT_OR, Token.BIT_XOR
    };

    public CalculationSentenceNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        return calc(manager, 0, nodes.size());
    }

    private Value calc(ScriptManager manager, int start, int end) throws Exception {
        if (start + 1 == end) {
            return nodes.get(start).run(manager);
        }
        for (Token token : tokens) {
            for (int i = start; i < end - 1; i++) {
                UnitNode op = (UnitNode) separators.get(i);
                Token operator = op.getUnit().getToken();
                if (operator != token) {
                    continue;
                }
                return calc(manager, start, i + 1).binaryOperation(operator, calc(manager, i + 1, end));
            }
        }
        throw new OperationException();
    }

    @Override
    protected WordNode getNextNode() {
        return new ProxyWordNode(this);
    }

    @Override
    protected Node getNextSeparator() {
        return new UnitNode(this, tokens);
    }

    @Override
    protected String getSeparatorFormat() {
        return " %s ";
    }

}
