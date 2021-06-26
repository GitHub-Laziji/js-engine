package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.BasePlanNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.UnitNode;
import org.laziji.commons.js.model.node.word.ProxyWordNode;
import org.laziji.commons.js.model.node.word.WordNode;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class UnaryWordNode extends BasePlanNode implements WordNode {

    public UnaryWordNode(Node parent) {
        super(parent);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                (self, pre) -> new UnitNode(this, Token.ADD, Token.SUB, Token.NON),
                (self, pre) -> new ProxyWordNode(this)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        //TODO 判断是否需要空格
        return String.format("%s %s", current[0].toString(depth, start), current[1].toString(depth, false));
    }
}
