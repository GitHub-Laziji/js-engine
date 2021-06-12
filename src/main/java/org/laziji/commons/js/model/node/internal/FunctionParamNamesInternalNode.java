package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.ArrayList;
import java.util.List;

public class FunctionParamNamesInternalNode extends BaseNode implements InternalNode {

    private List<NameWordNode> names = new ArrayList<>();

    public FunctionParamNamesInternalNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        NameWordNode word = new NameWordNode(this);
        this.names.add(word);
        return word.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (!isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (names.get(names.size() - 1).isDone() && unit.getToken() == Token.COMMA) {
            NameWordNode word = new NameWordNode(this);
            this.names.add(word);
            return word.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return names.size() == 1 || names.get(names.size() - 1).isDone();
    }
}
