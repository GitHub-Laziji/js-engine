package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.ProxyNode;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.internal.FunctionParamsInternalNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.sentence.ProxySentenceNode;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.object.JsFunction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class LambdaWordNode extends BasePlanNode implements WordNode {

    public LambdaWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected JsValue subRun(Contexts contexts) throws Exception {
        List<JsFunction.Param> params;
        Node paramsNode = current[0].getSelf();
        if (paramsNode instanceof FunctionParamsInternalNode) {
            params = ((FunctionParamsInternalNode) paramsNode).getParams();
        } else {
            params = Collections.singletonList(new JsFunction.Param(((NameWordNode) paramsNode).getName(), args -> args.get(0)));
        }
        return new JsFunction(contexts.fork(), params, cs -> current[2].run(cs), false);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new ProxyNode<>(this, new FunctionParamsInternalNode(null), new NameWordNode(null)),
                (self, pre) -> new UnitNode(this, Token.LAMBDA),
                (self, pre) -> new ProxyNode<>(this, new BigBracketParagraphNode(null), new ProxySentenceNode(null))
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s %s";
    }
}
