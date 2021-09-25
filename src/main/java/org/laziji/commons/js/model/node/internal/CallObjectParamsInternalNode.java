package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;
import org.laziji.commons.js.model.value.FunctionValue;
import org.laziji.commons.js.model.value.ObjectValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class CallObjectParamsInternalNode extends BasePlanNode implements InternalNode {

    public CallObjectParamsInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.BRACKET_MID_OPEN),
                (self, pre) -> new ValueParagraphNode(this),
                (self, pre) -> new UnitNode(this, Token.BRACKET_MID_CLOSE)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s%s";
    }

    public Value run(Value pre, Contexts manager) throws Exception {
        ObjectValue objectValue = ObjectValue.cast(pre);
        String name = current[1].run(manager).toString();
        return objectValue.getProperty(name);
    }

    public Value assignment(Value pre, Contexts manager, Value value) throws Exception {
        ObjectValue objectValue = ObjectValue.cast(pre);
        String name = current[1].run(manager).toString();
        return objectValue.addProperty(name, value);
    }

}
