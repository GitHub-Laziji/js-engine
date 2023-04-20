package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.StringWordNode;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.module.ModuleValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ImportParagraphNode extends BasePlanNode implements ParagraphNode {

    public ImportParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        String moduleName = ((StringWordNode) current[1]).run(manager).toString();
        ModuleValue module = Top.getModule(moduleName);
        // TODO
        return null;
    }

    @Override
    public boolean shouldEndFlag() {
        return configuration.isStrict();
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.IMPORT),
                (self, pre) -> new StringWordNode(self)
        );
    }

    @Override
    protected String getStringFormat() {
        return "%s %s";
    }
}

