package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class CallFunctionParamsInternalNode extends BasePlanNode implements InternalNode {

    public CallFunctionParamsInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.BRACKET_SML_OPEN),
                () -> new BracketContentInternalNode(this, false),
                () -> new UnitNode(this, Token.BRACKET_SML_CLOSE)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s%s";
    }
}
