package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.node.word.StringWordNode;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.ModuleValue;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.JsUndefined;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ImportDefaultFromParagraphNode extends BasePlanNode implements ParagraphNode {

    public ImportDefaultFromParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public JsValue run(Contexts manager) throws Exception {
        String variableName = ((NameWordNode) current[1]).getName();
        String moduleName = ((StringWordNode) current[3]).run(manager).toString();
        ModuleValue module = Top.getModule(moduleName);
        if (module.getDefaultExportValue() != null) {
            manager.getContexts().peek().addProperty(variableName, module.getDefaultExportValue(), Context.ContextPropertyType.CONST);
        } else {
            manager.getContexts().peek().addProperty(variableName, new JsUndefined(), Context.ContextPropertyType.CONST);
        }
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
                (self, pre) -> new NameWordNode(this),
                (self, pre) -> new UnitNode(this, Token.FROM),
                (self, pre) -> new StringWordNode(this)
        );
    }

}

