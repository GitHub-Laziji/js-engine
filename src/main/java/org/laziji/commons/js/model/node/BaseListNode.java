package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.sentence.SentenceNode;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseListNode extends BaseNode {

    protected List<Node> nodes = new ArrayList<>();
    protected List<Node> separators = new ArrayList<>();


    public BaseListNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (!isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (unit.getToken() == Token.COMMA) {
            SentenceNode sentence = new SentenceNode(this);
            nodes.add(sentence);
            return sentence.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return nodes.get(nodes.size() - 1).isDone();
    }

    protected abstract boolean allowEmpty();
}
