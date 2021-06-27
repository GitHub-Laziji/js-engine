package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.FunctionValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Stack;

public class ProxyCallParamsInternalNode extends BaseProxyNode<InternalNode> implements InternalNode {

    public ProxyCallParamsInternalNode(Node parent) {
        super(parent);
        addProxyItem(new CallFunctionParamsInternalNode(null));
        addProxyItem(new CallObjectParamsInternalNode(null));
        addProxyItem(new CallMemberNameInternalNode(null));
    }

    public Value run(Value value, Stack<Context> contexts) throws Exception {
        Node self = getSelf();
        if (self instanceof CallFunctionParamsInternalNode && value instanceof FunctionValue) {
            return ((FunctionValue) value).call(contexts, ((CallFunctionParamsInternalNode) self).getArguments(contexts));
        }
        throw new RunException();
    }
}
