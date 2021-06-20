package org.laziji.commons.js.model.node.internal;

import com.google.common.collect.ImmutableList;
import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.name.ConstName;
import org.laziji.commons.js.model.context.name.LetName;
import org.laziji.commons.js.model.context.name.Name;
import org.laziji.commons.js.model.context.name.VarName;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.sentence.SentenceNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;
import org.laziji.commons.js.model.value.NullValue;
import org.laziji.commons.js.model.value.NumberValue;
import org.laziji.commons.js.model.value.Value;

import java.util.List;
import java.util.Stack;

public class DefinedItemInternalNode extends BaseListNode<ProxyNode<Node>> implements InternalNode {

    public DefinedItemInternalNode(Node parent) {
        super(parent);
    }

    @Override
    protected ProxyNode<Node> getNextNode() {
        return new ProxyNode<>(this,
                new PlanNode(null, (subSelf, subPre) -> new NameWordNode(subSelf)),
                new PlanNode(null,
                        (subSelf, subPre) -> new NameWordNode(subSelf),
                        (subSelf, subPre) -> new UnitNode(subSelf, Token.ASSIGNMENT),
                        (subSelf, subPre) -> new SentenceNode(subSelf)
                )
        );
    }

    @Override
    protected Node getNextSeparator() {
        return new UnitNode(this, Token.COMMA);
    }

    @Override
    protected boolean allowEmpty() {
        return false;
    }

    @Override
    public String toString(int depth, boolean start) {
        return nodesJoin(nodes, ", ", false, depth, start);
    }

    public Value run(Stack<Context> contexts, Token type) throws Exception {
        Context context = contexts.peek();
        for (ProxyNode<Node> node : nodes) {
            PlanNode self = (PlanNode) node.getSelf();
            List<Node> current = self.getNodes();
            NameWordNode nameNode = (NameWordNode) current.get(0);
            if (current.size() == 1) {
                context.defined(createName(type, nameNode.getUnit().getValue()), NullValue.getInstance());
                continue;
            }
            SentenceNode valueNode = (SentenceNode) current.get(2);
            // TODO
            context.defined(createName(type, nameNode.getUnit().getValue()), new NumberValue(1));
        }
        return null;
    }

    private Name createName(Token type, String name) {
        if (type == Token.LET) {
            return new LetName(name);
        }
        if (type == Token.VAR) {
            return new VarName(name);
        }
        if (type == Token.CONST) {
            return new ConstName(name);
        }
        return null;
    }
}
