package org.laziji.commons.js.model.node;

import com.sun.istack.internal.NotNull;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.List;

public class LetValuelessSentenceNode extends BaseNode {

    private TokenUnit let;
    private List<LetItemValuelessSentenceNode> nodes;

    public LetValuelessSentenceNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(@NotNull TokenUnit unit) throws Exception {
        if (let == null) {
            if (unit.getToken() != Token.LET) {
                throw new Exception(String.format("[%s] is not the expected token. expected [let]", unit.getToken().toString()));
            }
            let = unit;
            nodes = new ArrayList<>();
            nodes.add(new LetItemValuelessSentenceNode(this));
            return nodes.get(0);
        }
        if (nodes.get(nodes.size() - 1).isDone()) {
            if (unit.getToken() != Token.COMMA) {
                throw new Exception(String.format("[%s] is not the expected token. expected [,]", unit.getToken().toString()));
            }
            nodes.add(new LetItemValuelessSentenceNode(this));
            return nodes.get(0);
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return let != null && nodes.get(nodes.size() - 1).isDone();
    }

}
