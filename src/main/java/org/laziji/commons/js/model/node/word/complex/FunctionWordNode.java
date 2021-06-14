package org.laziji.commons.js.model.node.word.complex;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.FunctionParamsInternalNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class FunctionWordNode extends BasePlanNode implements ComplexWordNode {

    public FunctionWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.FUNCTION),
                () -> new ProxyNode<Node>(this, new NameWordNode(null), new EmptyNode(null)),
                () -> new FunctionParamsInternalNode(this),
                () -> new BigBracketParagraphNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s%s %s";
    }
}
