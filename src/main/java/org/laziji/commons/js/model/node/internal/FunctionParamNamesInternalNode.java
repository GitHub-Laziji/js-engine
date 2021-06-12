package org.laziji.commons.js.model.node.internal;

import com.google.common.base.Joiner;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

import java.util.ArrayList;
import java.util.List;

public class FunctionParamNamesInternalNode extends BaseNode implements InternalNode {

    private List<NameWordNode> names = new ArrayList<>();

    public FunctionParamNamesInternalNode(Node parent) {
        super(parent);
    }


    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (names.size() == 0) {
            try {
                new NameWordNode(null).append(unit);
                NameWordNode name = new NameWordNode(this);
                name.append(unit);
                names.add(name);
                return this;
            } catch (Exception ignored) {
            }
        }
        if (!isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (names.size() > 0 && names.get(names.size() - 1).isDone() && unit.getToken() == Token.COMMA) {
            NameWordNode word = new NameWordNode(this);
            this.names.add(word);
            return word.init();
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return names.size() == 0 || names.get(names.size() - 1).isDone();
    }

    @Override
    public String toString() {
        return names.size()==0?"": Joiner.on(",").join(names);
    }
}
