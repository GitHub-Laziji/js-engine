package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.value.JsFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class FunctionParamsInternalNode extends BasePlanNode implements InternalNode {

    public FunctionParamsInternalNode(Node parent) {
        super(parent);
    }

    public List<JsFunction.Param> getParams() {
        FunctionParamsContentInternalNode paramsNode = (FunctionParamsContentInternalNode) current[1];
        List<NameWordNode> nodes = paramsNode.getNodes();
        List<JsFunction.Param> params = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            final int index = i;
            params.add(new JsFunction.Param(nodes.get(i).getUnit().getValue(), args -> args.get(index)));
        }
        return params;
    }


    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_OPEN),
                (self, pre) -> new FunctionParamsContentInternalNode(this),
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_CLOSE)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s%s%s";
    }
}
