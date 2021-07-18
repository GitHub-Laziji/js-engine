package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.paragraph.EmptyParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.value.ArrayValue;
import org.laziji.commons.js.model.value.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ArrayWordNode extends BasePlanNode implements WordNode {

    public ArrayWordNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        List<Value> values = new ArrayList<>();
        Node body = current[1].getSelf();
        if (body instanceof ValueParagraphNode) {
            for (Node item : ((ValueParagraphNode) body).getNodes()) {
                values.add(item.run(manager));
            }
        }
        return new ArrayValue(values);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.BRACKET_MID_OPEN),
                (self, pre) -> new ProxyNode<>(this, new ValueParagraphNode(null),
                        new EmptyParagraphNode(null)),
                (self, pre) -> new UnitNode(this, Token.BRACKET_MID_CLOSE)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s%s", current[0].toString(depth, start), current[1].toString(depth, false),
                current[2].toString(depth, false));
    }
}
