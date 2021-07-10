package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

public class StringWordNode extends BasePlanNode implements WordNode {

    public StringWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Collections.singletonList(
                (self, pre) -> new UnitNode(this, Token.STRING)
        );
    }

    @Override
    public Value run(Stack<Context> contexts) throws Exception {
        return super.run(contexts);
    }
}