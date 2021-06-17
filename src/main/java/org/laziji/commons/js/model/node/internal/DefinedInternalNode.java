package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class DefinedInternalNode extends BasePlanNode implements InternalNode {


    public DefinedInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Collections.singletonList(
                (self, pre) -> new UnitNode(self, Token.NAME)
        );
    }
}