package org.laziji.commons.js.model.node.word.basic;

import com.google.common.collect.ImmutableSet;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;

import java.util.Set;

public class NameWordNode extends BaseUnitNode implements BasicWordNode {

    public NameWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected Set<Token> getTokens() {
        return ImmutableSet.of(Token.NAME);
    }
}
