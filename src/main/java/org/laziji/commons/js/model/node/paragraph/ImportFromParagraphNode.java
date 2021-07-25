package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.name.LetName;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.word.NameWordNode;
import org.laziji.commons.js.model.node.word.StringWordNode;
import org.laziji.commons.js.model.value.ModuleValue;
import org.laziji.commons.js.model.value.UndefinedValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class ImportFromParagraphNode extends BasePlanNode implements ParagraphNode {

    public ImportFromParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        String moduleName = ((StringWordNode) current[5]).run(manager).toString();
        ModuleValue module = manager.getModule(moduleName);
        for (Node node : ((ListNode<Node>) current[2]).getNodes()) {
            node = node.getSelf();
            String importName;
            String variableName;
            if (node instanceof NameWordNode) {
                variableName = importName = ((NameWordNode) node).getName();
            } else {
                importName = ((NameWordNode) ((PlanNode) node).getNodes().get(0)).getName();
                variableName = ((NameWordNode) ((PlanNode) node).getNodes().get(2)).getName();
            }
            Value exportValue = module.getExportValue(importName);
            if (exportValue != null) {
                manager.getContexts().peek().defined(new LetName(variableName), exportValue);
            } else {
                manager.getContexts().peek().defined(new LetName(variableName), new UndefinedValue());
            }
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
                (self, pre) -> new UnitNode(self, Token.IMPORT),
                (self, pre) -> new UnitNode(self, Token.BRACKET_BIG_OPEN),
                (self, pre) -> new ListNode<>(self,
                        (listNodeSelf, last) -> new ProxyNode<>(listNodeSelf,
                                new NameWordNode(null),
                                new PlanNode(null,
                                        (s, l) -> new NameWordNode(s),
                                        (s, l) -> new UnitNode(s, Token.AS),
                                        (s, l) -> new NameWordNode(s))),
                        (listNodeSelf, last) -> new UnitNode(listNodeSelf, Token.COMMA),
                        false,
                        "%s "
                ),
                (self, pre) -> new UnitNode(self, Token.BRACKET_BIG_CLOSE),
                (self, pre) -> new UnitNode(self, Token.FROM),
                (self, pre) -> new StringWordNode(self)
        );
    }

}

