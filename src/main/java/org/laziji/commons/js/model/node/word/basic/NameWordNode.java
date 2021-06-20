package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class NameWordNode extends BaseUnitNode implements BasicWordNode {

    public NameWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.NAME);
    }
}
