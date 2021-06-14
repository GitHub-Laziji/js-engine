package org.laziji.commons.js.model.node.word.complex;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.internal.FunctionParamsInternalNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class LambdaWordNode extends BasePlanNode implements ComplexWordNode {

    public LambdaWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new FunctionParamsInternalNode(this),
                () -> new UnitNode(this, Token.LAMBDA),
                () -> new BigBracketParagraphNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s %s";
    }
}
