package org.laziji.commons.js.model.node.word.complex;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.FunctionParamsInternalNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class FunctionWordNode extends BasePlanNode implements ComplexWordNode {

    public FunctionWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.FUNCTION),
                (self, pre) -> new ProxyNode<Node>(this, new NameWordNode(null), new EmptyNode(null)),
                (self, pre) -> new FunctionParamsInternalNode(this),
                (self, pre) -> new BigBracketParagraphNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s%s %s";
    }
}
