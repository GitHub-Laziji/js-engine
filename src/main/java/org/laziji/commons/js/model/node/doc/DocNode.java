package org.laziji.commons.js.model.node.doc;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.section.SectionNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class DocNode extends BasePlanNode {

    public DocNode() {
        super(null);
    }

    @Override
    protected List<Supplier<Node>> getPlan() {
        return Arrays.asList(
                () -> new SectionNode(this),
                () -> new UnitNode(this, Token.EOF)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return current[0].toString(0, start);
    }
}
