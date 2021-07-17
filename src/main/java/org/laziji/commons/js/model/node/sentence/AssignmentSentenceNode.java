package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.OperationException;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.CallWordNode;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.node.word.VariableWordNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * a+b+c
 */
public class AssignmentSentenceNode extends BasePlanNode implements SentenceNode {

    private static final Token[] tokens = new Token[]{
            Token.ASSIGNMENT,
            Token.SELF_ADD_BY, Token.SELF_SUB_BY, Token.SELF_MUL_BY, Token.SELF_DIV_BY, Token.SELF_MOD_BY,
            Token.SELF_OR_BY, Token.SELF_AND_BY,
            Token.SELF_BIT_OR_BY, Token.SELF_BIT_AND_BY, Token.SELF_BIT_XOR_BY
    };

    public AssignmentSentenceNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        VariableWordNode node = (VariableWordNode) current[0].getSelf();
        UnitNode op = (UnitNode) current[1];
        Context.Entry position = node.getPosition(manager);
        Value value = current[2].run(manager);
        switch (op.getUnit().getToken()) {
            case ASSIGNMENT:
                position.setValue(value);
                break;
            case SELF_ADD_BY:
                position.setValue(position.getValue().binaryOperation(Token.ADD, value));
                break;
            case SELF_SUB_BY:
                position.setValue(position.getValue().binaryOperation(Token.SUB, value));
                break;
            case SELF_MUL_BY:
                position.setValue(position.getValue().binaryOperation(Token.MUL, value));
                break;
            case SELF_DIV_BY:
                position.setValue(position.getValue().binaryOperation(Token.DIV, value));
                break;
            default:
                throw new OperationException();
        }
        return position.getValue();
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new ProxyNode<>(self, new CallWordNode(null), new NameWordNode(null)),
                (self, pre) -> new UnitNode(self, tokens),
                (self, pre) -> new ProxySentenceNode(self)
        );
    }

}
