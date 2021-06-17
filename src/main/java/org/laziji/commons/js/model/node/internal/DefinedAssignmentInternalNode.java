package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.sentence.SentenceNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class DefinedAssignmentInternalNode extends BasePlanNode implements InternalNode {


    public DefinedAssignmentInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(self, Token.NAME),
                (self, pre) -> new UnitNode(self, Token.ASSIGNMENT),
                (self, pre) -> new SentenceNode(self)
        );
    }
}