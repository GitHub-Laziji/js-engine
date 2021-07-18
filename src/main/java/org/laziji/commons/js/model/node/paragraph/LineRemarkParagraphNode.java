package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.value.Value;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class LineRemarkParagraphNode extends BasePlanNode implements ParagraphNode {

    public LineRemarkParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        return null;
    }

    @Override
    public boolean shouldEndFlag() {
        return false;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Collections.singletonList(
                (self, pre) -> new UnitNode(self, Token.REMARK_OF_LINE)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s// %s", getTabString(depth, start),
                current[0].toString(0, false).substring(2).trim());
    }
}
