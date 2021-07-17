package org.laziji.commons.js.model.node;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.manager.NodeConfiguration;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.node.section.SectionNode;
import org.laziji.commons.js.model.value.Value;

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
    public Value run(ScriptManager manager) throws Exception {
        return current[0].run(manager);
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
