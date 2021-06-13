package org.laziji.commons.js.model.node.paragraph;

import com.google.common.base.Joiner;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.sentence.SentenceNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.node.word.complex.ClassWordNode;
import org.laziji.commons.js.model.node.word.complex.FunctionWordNode;

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
    public String toString(int depth, boolean start) {
        return nodesJoin(sentences, ", ", false, depth, start);
    }

    @Override
    public boolean shouldEndFlag() {
        if (sentences.size() == 1) {
            try {
                Class<? extends Node> wordClass = sentences.get(0).getSingleWord();
                System.out.println(wordClass.getSimpleName());
                if (wordClass == FunctionWordNode.class || wordClass == ClassWordNode.class) {
                    return false;
                }
            } catch (Exception ignored) {
            }
        }
        return true;
    }
}
