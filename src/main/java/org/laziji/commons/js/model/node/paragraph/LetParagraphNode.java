package org.laziji.commons.js.model.node.paragraph;

import com.google.common.base.Joiner;
import com.sun.istack.internal.NotNull;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.LetInternalNode;

import java.util.ArrayList;
import java.util.List;

public class LetParagraphNode extends BaseNode implements ParagraphNode {

    private TokenUnit let;
    private List<LetInternalNode> nodes;

    public LetParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(@NotNull TokenUnit unit) throws Exception {
        if (let == null) {
            if (unit.getToken() != Token.LET) {
                throw new Exception(String.format("[%s] is not the expected token. expected [let.js]", unit.getToken().toString()));
            }
            let = unit;
            nodes = new ArrayList<>();
            nodes.add(new LetInternalNode(this));
            return nodes.get(0).init();
        }
        if (nodes.get(nodes.size() - 1).isDone()) {
            if (unit.getToken() != Token.COMMA) {
                throw new Exception(String.format("[%s] is not the expected token. expected [,]", unit.getToken().toString()));
            }
            nodes.add(new LetInternalNode(this));
            return nodes.get(nodes.size() - 1).init();
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

    @Override
    public String toString(int depth) {
        return String.format("%s %s", let.getValue(), Joiner.on(", ").join(nodes));
    }


    @Override
    public boolean shouldEndFlag() {
        return true;
    }
}
