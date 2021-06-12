package org.laziji.commons.js.model.node.internal;

import com.google.common.base.Joiner;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.ArrayList;
import java.util.List;

public class ObjectContentInternalNode extends BaseNode implements InternalNode {

    private List<ObjectContentItemInternalNode> nodes = new ArrayList<>();

    public ObjectContentInternalNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (nodes.size() == 0) {
            try {
                new ObjectContentItemInternalNode(null).init().append(unit);
                ObjectContentItemInternalNode node = new ObjectContentItemInternalNode(this);
                nodes.add(node);
                return node.init().append(unit);
            } catch (Exception ignored) {
            }
        }
        if (!isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (nodes.size() > 0 && nodes.get(nodes.size() - 1).isDone() && unit.getToken() == Token.COMMA) {
            ObjectContentItemInternalNode word = new ObjectContentItemInternalNode(this);
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
        return nodes.size() == 0 || nodes.get(nodes.size() - 1).isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return nodes.size() == 0 ? "" : Joiner.on(",\n").join(nodes);
    }

}
