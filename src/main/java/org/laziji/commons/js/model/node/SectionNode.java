package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.List;

/**
 * a=1,b=2,c=3;
 */
public class SectionNode extends BaseNode {

    private List<SentenceNode> sentences= new ArrayList<>();
    private boolean done = false;

    public SectionNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
