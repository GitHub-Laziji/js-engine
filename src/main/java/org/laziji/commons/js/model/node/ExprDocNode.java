package org.laziji.commons.js.model.node;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.manager.NodeConfiguration;
import org.laziji.commons.js.model.node.sentence.ProxySentenceNode;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ExprDocNode extends BasePlanNode {

    public ExprDocNode(NodeConfiguration configuration) {
        super(configuration, null);
    }

    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        return current[0].run(manager);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new ProxySentenceNode(self),
                (self, pre) -> new UnitNode(self, Token.EOF)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return current[0].toString(0, start);
    }

}
