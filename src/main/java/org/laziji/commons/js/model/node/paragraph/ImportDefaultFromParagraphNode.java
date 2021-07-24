package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.name.LetName;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.node.word.StringWordNode;
import org.laziji.commons.js.model.value.ModuleValue;
import org.laziji.commons.js.model.value.UndefinedValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ImportDefaultFromParagraphNode extends BasePlanNode implements ParagraphNode {

    public ImportDefaultFromParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        String variableName = ((NameWordNode) current[1]).getName();
        String moduleName = ((StringWordNode) current[3]).run(manager).toString();
        System.out.println(moduleName);
        ModuleValue module = manager.getModule(moduleName);
        System.out.println(module.getClass().getName());
        if (module.getDefaultExportValue() != null) {
            manager.getContexts().peek().defined(new LetName(variableName), module.getDefaultExportValue());
        } else {
            manager.getContexts().peek().defined(new LetName(variableName), new UndefinedValue());
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

