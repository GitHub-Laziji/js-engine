package org.laziji.commons.js.model.node;

import org.laziji.commons.js.constant.Token;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnitNode extends BaseUnitNode {

    private final Token[] tokens;

    public UnitNode(Node parent, Token... tokens) {
        super(parent);
        this.tokens = tokens;
    }

    @Override
    protected Set<Token> getTokens() {
        return new HashSet<>(Arrays.asList(tokens));
    }
}
