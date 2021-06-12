package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.sentence.SentenceNode;
import org.laziji.commons.js.model.node.word.basic.StringWordNode;

public class ObjectContentItemInternalNode extends BaseNode implements InternalNode {

    private StringWordNode key;
    private SentenceNode value;

    public ObjectContentItemInternalNode(Node parent) {
        super(parent);
    }

    @Override
    public Node init() {
        key= new StringWordNode(this);
        return key.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (key.isDone() && value == null) {
            if (unit.getToken() != Token.COLON) {
                throw new Exception(String.format("[%s] is not the expected token. expected [name]", unit.getToken().toString()));
            }
            this.value = new SentenceNode(this);
            return value.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token. expected [name]", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return key != null && key.isDone() && value != null && value.isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s: %s", getTabString(depth), key, value);
    }
}
