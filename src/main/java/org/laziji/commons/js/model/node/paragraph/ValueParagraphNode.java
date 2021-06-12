package org.laziji.commons.js.model.node.paragraph;

import com.google.common.base.Joiner;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.sentence.SentenceNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;

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
        if (!isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (unit.getToken() == Token.COMMA) {
            SentenceNode sentence = new SentenceNode(this);
            sentences.add(sentence);
            return sentence.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return sentences.get(sentences.size() - 1).isDone();
    }

    @Override
    public String toString() {
        return Joiner.on(",").join(sentences);
    }

    @Override
    public boolean shouldEndFlag() {
        return true;
    }
}
