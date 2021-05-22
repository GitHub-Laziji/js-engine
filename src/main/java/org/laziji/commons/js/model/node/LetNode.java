package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.List;

public class LetNode extends BaseNode {

    private boolean done = false;
    private List<TokenUnit> units = new ArrayList<>();

    public LetNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) {
        if(units.size()==0&&unit.getToken()== Token.LET){
            units.add(unit);
        }
        if(units.size()==1&&unit.getToken()== Token.NAME){
            units.add(unit);
        }
        if(units.size()==2&&unit.getToken()== Token.NAME){
            units.add(unit);
        }
        return null;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
