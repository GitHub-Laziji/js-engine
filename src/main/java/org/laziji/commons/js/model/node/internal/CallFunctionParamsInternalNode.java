package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.paragraph.EmptyParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;
import org.laziji.commons.js.model.node.sentence.SentenceNode;
import org.laziji.commons.js.model.value.object.JsFunction;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.object.JsObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class CallFunctionParamsInternalNode extends BasePlanNode implements InternalNode {

    public CallFunctionParamsInternalNode(Node parent) {
        super(parent);
    }

    public List<JsValue> getArguments(Contexts manager) throws Exception {
        if (current[1].getSelf() instanceof EmptyNode) {
            return new ArrayList<>();
        }
        ValueParagraphNode argsNode = (ValueParagraphNode) current[1].getSelf();
        List<JsValue> arguments = new ArrayList<>();
        for (SentenceNode node : argsNode.getNodes()) {
            arguments.add(node.run(manager));
        }
        return arguments;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_OPEN),
                (self, pre) -> new ProxyNode<>(this, new ValueParagraphNode(null),
                        new EmptyParagraphNode(null)),
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_CLOSE)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s%s";
    }

    public JsValue run(JsObject caller, JsValue pre, Contexts contexts) throws Exception {
        JsObject objectValue = JsObject.cast(pre);
        if (objectValue instanceof JsFunction) {
            return ((JsFunction) objectValue).bind(caller).call(getArguments(contexts));
        }
        throw new TypeException();
    }

    public JsValue instantiate(JsValue pre, Contexts contexts) throws Exception {
        JsObject objectValue = JsObject.cast(pre);
        if (objectValue instanceof JsFunction) {
            return ((JsFunction) objectValue).instantiate(getArguments(contexts));
        }
        throw new TypeException();
    }

}
