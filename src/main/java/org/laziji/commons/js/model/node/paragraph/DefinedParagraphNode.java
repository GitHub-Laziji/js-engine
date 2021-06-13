package org.laziji.commons.js.model.node.paragraph;

import com.sun.istack.internal.NotNull;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.DefinedInternalNode;

import java.util.ArrayList;
import java.util.List;

public class DefinedParagraphNode extends BaseNode implements ParagraphNode {

    private TokenUnit defined;
    private List<DefinedInternalNode> nodes;

    public DefinedParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(@NotNull TokenUnit unit) throws Exception {
        if (defined == null) {
            if (unit.getToken() != Token.LET && unit.getToken() != Token.VAR && unit.getToken() != Token.CONST) {
                throw new Exception(String.format("[%s] is not the expected token. expected [let.js]", unit.getToken().toString()));
            }
            defined = unit;
            nodes = new ArrayList<>();
            nodes.add(new DefinedInternalNode(this));
            return nodes.get(0).init();
        }
        if (nodes.get(nodes.size() - 1).isDone()) {
            if (unit.getToken() != Token.COMMA) {
                throw new Exception(String.format("[%s] is not the expected token. expected [,]", unit.getToken().toString()));
            }
            nodes.add(new DefinedInternalNode(this));
            return nodes.get(nodes.size() - 1).init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return defined != null && nodes.get(nodes.size() - 1).isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s %s", start ? getTabString(depth) : "",
                defined.getValue(), nodesJoin(nodes, ", ", false, depth, start));
    }


    @Override
    public boolean shouldEndFlag() {
        return true;
    }
}
