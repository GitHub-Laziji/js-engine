package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class UndefinedWordNode extends BasePlanNode implements BasicWordNode {

    public UndefinedWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Collections.singletonList(
                () -> new UnitNode(this, Token.UNDEFINED)
        );
    }
}
