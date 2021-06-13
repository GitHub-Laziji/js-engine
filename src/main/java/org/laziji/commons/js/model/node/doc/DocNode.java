package org.laziji.commons.js.model.node.doc;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.section.SectionNode;

public class DocNode extends BaseNode {

    private SectionNode section;
    private boolean end;

    public DocNode() {
        super(null);
    }

    @Override
    public Node init() {
        section = new SectionNode(this);
        return section.init();
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (section.isDone()) {
            if (unit.getToken() == Token.EOF) {
                end = true;
                return this;
            }
        }
        if (isDone()) {
            throw new Exception(String.format("doc over.", unit.getToken().toString()));
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));

    }

    @Override
    public boolean isDone() {
        return section.isDone() && end;
    }

    @Override
    public String toString(int depth, boolean start) {
        return section.toString(0, start);
    }
}
