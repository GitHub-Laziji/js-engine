package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ParagraphNode;
import org.laziji.commons.js.model.node.word.basic.SmallBracketWordNode;

public class IfInternalNode extends BaseNode implements InternalNode {

    private TokenUnit ifUnit;
    private SmallBracketWordNode exp;
    private BigBracketParagraphNode content;

    public IfInternalNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (ifUnit == null) {
            if (unit.getToken() != Token.IF) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            this.ifUnit = unit;
            exp = new SmallBracketWordNode(this);
            return exp.init();
        }
        if (exp.isDone() && content == null) {
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
        return ifUnit != null && exp != null && exp.isDone() && content != null && content.isDone();
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s%s %s", getTabString(depth, start), ifUnit.getValue(),
                exp.toString(depth, false), content.toString(depth, false));
    }

}
