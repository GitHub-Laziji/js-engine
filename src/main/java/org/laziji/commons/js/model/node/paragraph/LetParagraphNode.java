package org.laziji.commons.js.model.node.paragraph;

import com.sun.istack.internal.NotNull;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.sentence.SentenceNode;

import java.util.ArrayList;
import java.util.List;

public class LetParagraphNode extends BaseNode implements ParagraphNode {

    private TokenUnit let;
    private List<LetItemNode> nodes;

    public LetParagraphNode(Node parent) {
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
            nodes.add(new LetItemNode(this));
            return nodes.get(0).init();
        }
        if (nodes.get(nodes.size() - 1).isDone()) {
            if (unit.getToken() != Token.COMMA) {
                throw new Exception(String.format("[%s] is not the expected token. expected [,]", unit.getToken().toString()));
            }
            nodes.add(new LetItemNode(this));
            return nodes.get(0).init();
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

    public class LetItemNode extends BaseNode {

        private TokenUnit name;
        private TokenUnit assignment;
        private SentenceNode node;

        public LetItemNode(Node parent) {
            super(parent);
        }

        @Override
        public Node append(TokenUnit unit) throws Exception {
            if (name == null) {
                if (unit.getToken() != Token.NAME) {
                    throw new Exception(String.format("[%s] is not the expected token. expected [name]", unit.getToken().toString()));
                }
                name = unit;
                return this;
            }
            if (assignment == null) {
                if (unit.getToken() != Token.ASSIGNMENT) {
                    throw new Exception(String.format("[%s] is not the expected token. expected [name]", unit.getToken().toString()));
                }
                assignment = unit;
                node = new SentenceNode(this);
                return node.init();
            }
            if (isDone() && getParent() != null) {
                return getParent().append(unit);
            }
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }

        @Override
        public boolean isDone() {
            return name != null && (assignment == null && node == null || assignment != null && node != null && node.isDone());
        }
    }

}
