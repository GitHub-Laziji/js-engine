package org.laziji.commons.js.model.node;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.manager.NodeConfiguration;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.section.SectionNode;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class DocNode extends BasePlanNode {

    private BiFunction<Node, Node, Node> supplier;

    public DocNode(NodeConfiguration configuration) {
        super(configuration, null);
        this.supplier = (self, pre) -> new SectionNode(self);
    }

    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        current[0].run(manager);
        // TODO export
        return null;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                supplier,
                (self, pre) -> new UnitNode(self, Token.EOF)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return current[0].toString(0, start);
    }

}
