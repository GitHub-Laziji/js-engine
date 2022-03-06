package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.clazz.NumberClass;
import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.primitive.JsNumber;
import org.laziji.commons.js.model.value.prototype.NumberPrototype;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class AfterOperatorWordNode extends BasePlanNode implements WordNode {

    public AfterOperatorWordNode(Node parent) {
        super(parent);
    }

    @Override
    public JsValue run(Contexts contexts) throws Exception {
        String name = ((NameWordNode) current[0]).getUnit().getValue();
        Token op = ((UnitNode) current[1]).getUnit().getToken();
        JsValue value = contexts.getProperty(name);
        if (value instanceof JsObject && ((JsObject) value).getProto() instanceof NumberPrototype) {
            contexts.addProperty(name, value.binaryOperation(op == Token.SELF_ADD ? Token.ADD : Token.SUB, new JsNumber(1)));
            return value;
        }
        value = JsNumber.getNanInstance();
        contexts.addProperty(name,value);
        return value;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new NameWordNode(this),
                (self, pre) -> new UnitNode(this, Token.SELF_ADD, Token.SELF_SUB)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", current[0].toString(depth, start), current[1].toString(depth, false));
    }
}
