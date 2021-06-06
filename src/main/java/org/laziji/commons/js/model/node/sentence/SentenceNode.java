package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.mark.MarkNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.WordNode;

import java.util.ArrayList;
import java.util.List;

/**
 * a+b+c
 */
public class SentenceNode extends BaseNode {


    private List<WordNode> nodes = new ArrayList<>();
    private List<Token> tokens = new ArrayList<>();

    public SentenceNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        ProxyWordNode word = new ProxyWordNode(this);
        this.nodes.add(word);
        return word.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (!isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        switch (unit.getToken()) {
            case ADD:
            case SUB:
            case MUL:
            case DIV:
                tokens.add(unit.getToken());
                ProxyWordNode word = new ProxyWordNode(this);
                this.nodes.add(word);
                return word.init();
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
}
