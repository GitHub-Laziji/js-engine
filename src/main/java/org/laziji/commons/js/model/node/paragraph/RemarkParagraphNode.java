package org.laziji.commons.js.model.node.paragraph;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public class RemarkParagraphNode extends BasePlanNode implements ParagraphNode {

    public RemarkParagraphNode(Node parent) {
        super(parent);
    }

    @Override
    public boolean shouldEndFlag() {
        return false;
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Collections.singletonList(
                (self, pre) -> new UnitNode(self, Token.REMARK)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        String remark = current[0].toString(0, false);
        String remarkText = remark.substring(2, remark.length() - 2).trim();
        String tab = getTabString(depth, true);
        StringBuilder sb = new StringBuilder();
        sb.append(tab).append("/**\n");
        for (String line : remarkText.split("\n")) {
            sb.append(tab).append(" * ").append(line.trim()).append("\n");
        }
        sb.append(tab).append(" */");
        return sb.toString();
    }
}
