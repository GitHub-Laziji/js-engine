package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.paragraph.BigBracketParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ValueParagraphNode;
import org.laziji.commons.js.model.node.word.SmallBracketWordNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class IfInternalNode extends BasePlanNode implements InternalNode {

    public IfInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.IF),
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_OPEN),
                (self, pre) -> new ValueParagraphNode(this),
                (self, pre) -> new UnitNode(this, Token.BRACKET_SML_CLOSE),
                (self, pre) -> new BigBracketParagraphNode(this)
        );
    }

    public Value runExp(ScriptManager manager) throws Exception {
        return current[2].run(manager);
    }

    public Value runBody(ScriptManager manager) throws Exception {
        return current[4].run(manager);
    }

    @Override
    protected String getStringFormat() {
        return "%s%s%s%s %s";
    }


}
