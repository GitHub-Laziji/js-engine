package org.laziji.commons.js.model.node;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.section.SectionNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DocNode extends BasePlanNode {

    private BiFunction<Node, Node, Node> supplier;

    public DocNode() {
        super(null);
        this.supplier = (self, pre) -> new SectionNode(self);
    }

    public DocNode(Function<Node, Node> content) {
        super(null);
        this.supplier = (self, pre) -> content.apply(self);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                supplier,
                (self, pre) -> new UnitNode(self, Token.EOF)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return current[0].toString(0, start);
    }
}
