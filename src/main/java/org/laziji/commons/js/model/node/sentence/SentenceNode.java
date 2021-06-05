package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.WordNode;

import java.util.ArrayList;
import java.util.List;

/**
 * a+b+c
 */
public class SentenceNode extends BaseNode {


    private List<Node> nodes = new ArrayList<>();

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
        return null;
    }

    @Override
    public boolean isDone() {
        Node node = nodes.get(nodes.size() - 1);
        return node instanceof WordNode && node.isDone();
    }
}
