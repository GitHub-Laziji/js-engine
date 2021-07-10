package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.*;

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

    public Context.Entry getPosition(Value value, Stack<Context> contexts) throws Exception {
        if (value == null) {
            throw new RunException();
        }
        if (value instanceof NullValue) {
            throw new TypeException("Cannot read property of null");
        }
        if (value instanceof UndefinedValue) {
            throw new TypeException("Cannot read property of undefined");
        }
        if (!(value instanceof ObjectValue)) {
            throw new TypeException();
        }
        Node self = getSelf();
        if (self instanceof CallFunctionParamsInternalNode) {
            throw new TypeException();
        }
        String name;
        if (self instanceof CallObjectParamsInternalNode) {
            name = ((CallObjectParamsInternalNode) self).getNodes().get(1).run(contexts).toString();
        } else {
            name = ((CallMemberNameInternalNode) self).getNodes().get(1).toString();
        }
        return ((ObjectValue) value).getContext().getEntry(name);
    }
}
