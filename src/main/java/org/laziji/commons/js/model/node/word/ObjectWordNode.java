package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.internal.ObjectContentInternalNode;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ObjectWordNode extends BasePlanNode implements WordNode {

    public ObjectWordNode(Node parent) {
        super(parent);
    }

    @Override
    public JsValue run(Contexts manager) throws Exception {
        return current[1].run(manager);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.BRACKET_BIG_OPEN),
                (self, pre) -> new ObjectContentInternalNode(this),
                (self, pre) -> new UnitNode(this, Token.BRACKET_BIG_CLOSE)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s\n%s\n%s", current[0].toString(depth, start),
                current[1].toString(depth + 1, true), current[2].toString(depth, true));
    }
}
