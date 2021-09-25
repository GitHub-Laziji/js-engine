package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.value.FunctionValue;
import org.laziji.commons.js.model.value.ObjectValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class CallMemberNameInternalNode extends BasePlanNode implements InternalNode {

    public CallMemberNameInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.DOT),
                (self, pre) -> new NameWordNode(this)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s";
    }


    public Value run(Value pre) throws Exception {
        ObjectValue objectValue = ObjectValue.cast(pre);
        String name = current[1].toString();
        return objectValue.getProperty(name);
    }

    public Value assignment(Value pre, Value value) throws Exception {
        ObjectValue objectValue = ObjectValue.cast(pre);
        String name = current[1].toString();
        return objectValue.addProperty(name, value);
    }
}
