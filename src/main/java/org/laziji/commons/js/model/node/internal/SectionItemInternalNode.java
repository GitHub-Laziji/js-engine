package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.paragraph.ParagraphNode;
import org.laziji.commons.js.model.node.paragraph.ProxyParagraphNode;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class SectionItemInternalNode extends BasePlanNode implements InternalNode {

    public SectionItemInternalNode(Node parent) {
        super(parent);
    }

    @Override
    public Value run(ScriptManager manager) throws Exception {
        return current[0].run(manager);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new ProxyParagraphNode(self),
                (self, pre) -> {
                    Node node = pre.getSelf();
                    if (!(node instanceof ParagraphNode) || ((ParagraphNode) node).shouldEndFlag()) {
                        return new UnitNode(self, Token.SEMICOLON);
                    }
                    return new ProxyNode<>(self, new UnitNode(null, Token.SEMICOLON), new EmptyNode(null));
                }
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return String.format("%s%s", current[0].toString(depth, start),
                ((ProxyParagraphNode) current[0]).shouldEndFlag() ? ";" : "");
    }


}
