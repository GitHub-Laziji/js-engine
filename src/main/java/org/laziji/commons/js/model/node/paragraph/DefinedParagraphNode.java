package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.internal.DefinedItemInternalNode;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class DefinedParagraphNode extends BasePlanNode implements ParagraphNode {

    public DefinedParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        UnitNode definedNode = (UnitNode) current[0];
        DefinedItemInternalNode itemNode = (DefinedItemInternalNode) current[1];
        itemNode.run(manager, definedNode.getUnit().getToken());
        return null;
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s %s", current[0].toString(depth, start), current[1].toString(depth, false));
    }

    @Override
    public boolean shouldEndFlag() {
        return configuration.isStrict();
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(self, Token.LET, Token.VAR, Token.CONST),
                (self, pre) -> new DefinedItemInternalNode(self)
        );
    }

}
