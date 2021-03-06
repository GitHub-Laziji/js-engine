package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.BlockContext;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.section.SectionNode;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class BigBracketParagraphNode extends BasePlanNode implements ParagraphNode {

    public BigBracketParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    protected JsValue subRun(Contexts manager) throws Exception {
        manager.getContexts().push(new BlockContext());
        current[1].run(manager);
        manager.getContexts().pop();
        return null;
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s\n%s\n%s", current[0].toString(depth, start),
                current[1].toString(depth + 1, true), current[2].toString(depth, true));
    }

    @Override
    public boolean shouldEndFlag() {
        return false;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.BRACKET_BIG_OPEN),
                (self, pre) -> new SectionNode(this),
                (self, pre) -> new UnitNode(this, Token.BRACKET_BIG_CLOSE)
        );
    }

    public SectionNode getBody() {
        return (SectionNode) current[1];
    }

}