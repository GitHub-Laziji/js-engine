package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class CallObjectParamsInternalNode extends BasePlanNode implements InternalNode {

    public CallObjectParamsInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new UnitNode(this, Token.BRACKET_MID_OPEN),
                () -> new BracketContentInternalNode(this, true),
                () -> new UnitNode(this, Token.BRACKET_MID_CLOSE)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s%s";
    }

}
