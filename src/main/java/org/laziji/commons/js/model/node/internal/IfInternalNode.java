package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.word.basic.SmallBracketWordNode;

public class IfInternalNode extends BaseNode implements InternalNode {

    private Node[] plants = {
            new UnitNode(this, Token.IF),
            new SmallBracketWordNode(this),
            new BigBracketParagraphNode(this)
    };
    private Node[] process = new Node[plants.length];

    public IfInternalNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        for (int i = 0; i < plants.length; i++) {
            if (process[i] != null) {
                continue;
            }
            if (i > 0 && !process[i - 1].isDone()) {
                throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
            }
            process[i] = plants[i];
            return process[i].init().append(unit);
        }
        if (!process[plants.length - 1].isDone()) {
            throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
        }
        if (getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        for (Node node : process) {
            if (node == null || !node.isDone()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s %s", process[0].toString(depth, start),
                process[1].toString(depth, false), process[2].toString(depth, false));
    }

}
