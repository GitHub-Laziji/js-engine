package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class FunctionParamsInternalNode extends BasePlanNode implements InternalNode {

    public FunctionParamsInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_OPEN),
                (self, pre) -> new FunctionParamsContentInternalNode(this),
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_CLOSE)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s%s";
    }
}
