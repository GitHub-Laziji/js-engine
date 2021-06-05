package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.sentence.SentenceNode;

import java.util.ArrayList;
import java.util.List;

public class ValueParagraphNode extends BaseNode implements ParagraphNode {

    private List<SentenceNode> sentences = new ArrayList<>();

    public ValueParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        SentenceNode sentence = new SentenceNode(this);
        sentences.add(sentence);
        return sentence.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
