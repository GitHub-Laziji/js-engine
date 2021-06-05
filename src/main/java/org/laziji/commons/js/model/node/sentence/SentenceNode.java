package org.laziji.commons.js.model.node.sentence;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.ProxyWordNode;

import java.util.ArrayList;
import java.util.List;

/**
 * a=1
 */
public class SentenceNode extends BaseNode {


    private List<Node> nodes;

    public SentenceNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        return null;
    }

    @Override
    public boolean isDone() {
        Node node = nodes.get(nodes.size() - 1);
        return node instanceof ProxyWordNode && node.isDone();
    }

    @Override
    public Node init() {
        this.nodes = new ArrayList<>();
        this.nodes.add(new ProxyWordNode(this));
        return this.nodes.get(this.nodes.size() - 1);
    }
}
