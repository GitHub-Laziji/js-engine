package org.laziji.commons.js.model.node;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.section.SectionNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

public class DocNode extends BasePlanNode {

    private BiFunction<Node, Node, Node> supplier;

    public DocNode() {
        super(null);
        this.supplier = (self, pre) -> new SectionNode(self);
    }

    @Override
    public Value run(Stack<Context> contexts) throws Exception {
        return current[0].run(contexts);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                supplier,
                (self, pre) -> new UnitNode(self, Token.EOF)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return current[0].toString(0, start);
    }

}
