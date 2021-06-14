package org.laziji.commons.js.model.node;

import com.google.common.collect.ImmutableSet;
import org.laziji.commons.js.consts.Token;

import java.util.Set;

public class UnitNode extends BaseUnitNode {

    private Token[] tokens;

    public UnitNode(Node parent, Token... tokens) {
        super(parent);
        this.tokens = tokens;
    }

    @Override
    protected Set<Token> getTokens() {
        return ImmutableSet.copyOf(tokens);
    }
}
