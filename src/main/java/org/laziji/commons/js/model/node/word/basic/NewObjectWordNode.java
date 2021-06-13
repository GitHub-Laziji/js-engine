package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.CallFunctionParamsInternalNode;
import org.laziji.commons.js.model.node.internal.FunctionParamsInternalNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.word.complex.ComplexWordNode;

public class NewObjectWordNode extends BaseNode implements BasicWordNode {

    private TokenUnit newUnit;
    private NameWordNode name;
    private CallFunctionParamsInternalNode params;

    public NewObjectWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (newUnit == null) {
            if (unit.getToken() != Token.NEW) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            this.newUnit = unit;
            name = new NameWordNode(this);
            return name.init();
        }
        if (name.isDone() && params == null) {
            params = new CallFunctionParamsInternalNode(this);
            return params.init().append(unit);
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return newUnit != null && name != null && name.isDone() && params != null
                && params.isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s %s%s", start ? getTabString(depth) : "",
                newUnit.getValue(), name.toString(depth, false), params.toString(depth, false));
    }
}

