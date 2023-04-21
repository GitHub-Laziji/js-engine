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
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.module.ModuleValue;
import org.laziji.commons.js.model.value.object.JsObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ImportAllAsFromParagraphNode extends BasePlanNode implements ParagraphNode {

    public ImportAllAsFromParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        String variableName = ((NameWordNode) current[3]).getName();
        String moduleName = ((StringWordNode) current[5]).run(manager).toString();
        ModuleValue module = Top.getModule(moduleName);
        JsObject all = new JsObject();
        module.getExportNames().forEach(name -> all.addProperty(name, module.getExportValue(name)));
        manager.getContexts().peek().addProperty(variableName, all, Context.ContextPropertyType.CONST);
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
                (self, pre) -> new UnitNode(this, Token.MUL),
                (self, pre) -> new UnitNode(this, Token.AS),
                (self, pre) -> new NameWordNode(this),
                (self, pre) -> new UnitNode(this, Token.FROM),
                (self, pre) -> new StringWordNode(this)
        );
    }

}

