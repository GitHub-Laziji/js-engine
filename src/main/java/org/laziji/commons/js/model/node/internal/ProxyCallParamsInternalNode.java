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
        ObjectValue objectValue = castObjectValue(value);
        Node self = getSelf();
        if (self instanceof CallFunctionParamsInternalNode && objectValue instanceof FunctionValue) {
            return ((FunctionValue) objectValue).call(contexts, ((CallFunctionParamsInternalNode) self).getArguments(contexts));
        }
        String name;
        if (self instanceof CallObjectParamsInternalNode) {
            name = ((CallObjectParamsInternalNode) self).getNodes().get(1).run(contexts).toString();
        } else if (self instanceof CallMemberNameInternalNode) {
            name = ((CallMemberNameInternalNode) self).getNodes().get(1).toString();
        } else {
            throw new TypeException();
        }
        return objectValue.getContext().get(name);
    }

    public Context.Entry getPosition(Value value, Stack<Context> contexts) throws Exception {
        ObjectValue objectValue = castObjectValue(value);
        Node self = getSelf();
        if (self instanceof CallFunctionParamsInternalNode) {
            throw new TypeException();
        }
        String name;
        if (self instanceof CallObjectParamsInternalNode) {
            name = ((CallObjectParamsInternalNode) self).getNodes().get(1).run(contexts).toString();
        } else if (self instanceof CallMemberNameInternalNode) {
            name = ((CallMemberNameInternalNode) self).getNodes().get(1).toString();
        } else {
            throw new TypeException();
        }
        return objectValue.getContext().getEntry(name);
    }

    private ObjectValue castObjectValue(Value value) throws Exception {
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
        return (ObjectValue) value;
    }
}
