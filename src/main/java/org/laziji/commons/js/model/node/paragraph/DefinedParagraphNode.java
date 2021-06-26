package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.DefinedItemInternalNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

public class DefinedParagraphNode extends BasePlanNode implements ParagraphNode {

    public DefinedParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(Stack<Context> contexts) throws Exception {
        UnitNode definedNode = (UnitNode) current[0];
        DefinedItemInternalNode itemNode = (DefinedItemInternalNode) current[1];
        itemNode.run(contexts, definedNode.getUnit().getToken());
        return null;
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s %s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(self, Token.LET, Token.VAR, Token.CONST),
                (self, pre) -> new DefinedItemInternalNode(self)
        );
    }

}
