package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.name.ConstName;
import org.laziji.commons.js.model.context.name.LetName;
import org.laziji.commons.js.model.context.name.Name;
import org.laziji.commons.js.model.context.name.VarName;
import org.laziji.commons.js.model.node.*;
import org.laziji.commons.js.model.node.sentence.ProxySentenceNode;
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

    public Value run(Stack<Context> contexts, Token type) throws Exception {
        Context context = contexts.peek();
        for (ProxyNode<Node> node : nodes) {
            PlanNode self = (PlanNode) node.getSelf();
            List<Node> current = self.getNodes();
            NameWordNode nameNode = (NameWordNode) current.get(0);
            if (current.size() == 1) {
                context.defined(createName(type, nameNode.getUnit().getValue()), new NullValue());
                continue;
            }
            ProxySentenceNode valueNode = (ProxySentenceNode) current.get(2);
            context.defined(createName(type, nameNode.getUnit().getValue()), valueNode.run(contexts));
        }
        return null;
    }

    @Override
    protected ProxyNode<Node> getNextNode() {
        return new ProxyNode<>(this,
                new PlanNode(null, (subSelf, subPre) -> new NameWordNode(subSelf)),
                new PlanNode(null,
                        (subSelf, subPre) -> new NameWordNode(subSelf),
                        (subSelf, subPre) -> new UnitNode(subSelf, Token.ASSIGNMENT),
                        (subSelf, subPre) -> new ProxySentenceNode(subSelf)
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
    protected String getSeparatorFormat() {
        return "%s ";
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
