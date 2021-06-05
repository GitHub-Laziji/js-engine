package org.laziji.commons.js.model.node.section;

import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.ParagraphNode;

import java.util.ArrayList;
import java.util.List;

/**
 * a=1,b=2,c=3;
 * a=1,b=2,c=3;
 */
public class SectionNode extends BaseNode {

    private List<ParagraphNode> paragraphs = new ArrayList<>();
    private boolean done = false;

    public SectionNode(Node parent) {
        super(parent);
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
