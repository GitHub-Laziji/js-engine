package org.laziji.commons.js.model.node.section;

import org.laziji.commons.js.model.node.BaseListNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.internal.SectionItemInternalNode;

/**
 * a=1,b=2,c=3;
 * a=1,b=2,c=3;
 */
public class SectionNode extends BaseListNode<SectionItemInternalNode> {

    public SectionNode(Node parent) {
        super(parent);
    }

    @Override
    public String toString(int depth, boolean start) {
        return nodesJoin(nodes, "", true, depth, start);
    }

    @Override
    protected SectionItemInternalNode getNextNode() {
        return new SectionItemInternalNode(this);
    }

    @Override
    protected Node getNextSeparator() {
        return null;
    }

    @Override
    protected boolean allowEmpty() {
        return true;
    }
}
