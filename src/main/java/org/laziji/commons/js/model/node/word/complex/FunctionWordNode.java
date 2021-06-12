package org.laziji.commons.js.model.node.word.complex;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.FunctionParamsInternalNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;

public class FunctionWordNode extends BaseNode implements ComplexWordNode {

    private TokenUnit function;
    private NameWordNode name;
    private FunctionParamsInternalNode params;
    private BigBracketParagraphNode content;

    public FunctionWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (function == null) {
            if (unit.getToken() != Token.FUNCTION) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            this.function = unit;
            return this;
        }
        if (params == null) {
            if (unit.getToken() == Token.NAME) {
                name = new NameWordNode(this);
                name.append(unit);
                params = new FunctionParamsInternalNode(this);
                return params.init();
            }
            params = new FunctionParamsInternalNode(this);
            return params.init().append(unit);
        }
        if (params.isDone() && content == null) {
            content = new BigBracketParagraphNode(this);
            return content.init().append(unit);
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return function != null && (name == null || name.isDone()) && params != null
                && params.isDone() && content != null && content.isDone();
    }

    @Override
    public String toString(int depth) {
        return String.format("%s %s%s %s", function.getValue(), name == null ? "" : name, params, content);
    }
}
