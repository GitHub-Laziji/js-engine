package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.CallWordNode;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
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
    public Value run(Stack<Context> contexts) throws Exception {
        // TODO
        return super.run(contexts);
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
