package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.List;

public class LetNode extends BaseNode {

    private List<TokenUnit> units = new ArrayList<>();
    private FormulaNode formulaNode;

    public LetNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (units.size() == 0 && unit.getToken() == Token.LET) {
            units.add(unit);
            return this;
        }
        if (units.size() == 1 && unit.getToken() == Token.NAME) {
            units.add(unit);
            return this;
        }
        if (units.size() == 2 && unit.getToken() == Token.ASSIGNMENT) {
            units.add(unit);
            formulaNode = new FormulaNode(this);
            return formulaNode;
        }
        if (isDone() && getParent() != null) {
            getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return units.size() == 2 || units.size() == 3 && formulaNode != null && formulaNode.isDone();
    }
}
