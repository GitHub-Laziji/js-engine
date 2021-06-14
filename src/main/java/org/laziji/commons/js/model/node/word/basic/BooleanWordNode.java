package org.laziji.commons.js.model.node.word.basic;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;

import java.util.Set;

public class BooleanWordNode extends BaseUnitNode implements BasicWordNode {

    public BooleanWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected Set<Token> getTokens() {
        return ImmutableSet.of(Token.BOOLEAN);
    }
}
