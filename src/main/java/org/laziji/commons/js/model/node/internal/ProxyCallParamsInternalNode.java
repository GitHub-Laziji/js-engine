package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.FunctionValue;
import org.laziji.commons.js.model.value.ObjectValue;
import org.laziji.commons.js.model.value.Value;

public class ProxyCallParamsInternalNode extends BaseProxyNode<InternalNode> implements InternalNode {

    public ProxyCallParamsInternalNode(Node parent) {
        super(parent);
        addProxyItem(new CallFunctionParamsInternalNode(null));
        addProxyItem(new CallObjectParamsInternalNode(null));
        addProxyItem(new CallMemberNameInternalNode(null));
    }

    public Value run(ObjectValue caller, Value pre, Contexts manager) throws Exception {
        ObjectValue objectValue = ObjectValue.cast(pre);
        Node self = getSelf();
        if (self instanceof CallFunctionParamsInternalNode && objectValue instanceof FunctionValue) {
            return ((FunctionValue) objectValue).call(caller, ((CallFunctionParamsInternalNode) self).getArguments(manager));
        }
        String name;
        if (self instanceof CallObjectParamsInternalNode) {
            name = ((CallObjectParamsInternalNode) self).getNodes().get(1).run(manager).toString();
        } else if (self instanceof CallMemberNameInternalNode) {
            name = ((CallMemberNameInternalNode) self).getNodes().get(1).toString();
        } else {
            throw new TypeException();
        }
        return objectValue.getProperty(name);
    }

    public Value assignment(Value pre, Contexts manager, Value value) throws Exception {
        ObjectValue objectValue = ObjectValue.cast(pre);
        Node self = getSelf();
        if (self instanceof CallFunctionParamsInternalNode) {
            throw new TypeException();
        }
        String name;
        if (self instanceof CallObjectParamsInternalNode) {
            name = ((CallObjectParamsInternalNode) self).getNodes().get(1).run(manager).toString();
        } else if (self instanceof CallMemberNameInternalNode) {
            name = ((CallMemberNameInternalNode) self).getNodes().get(1).toString();
        } else {
            throw new TypeException();
        }
        return objectValue.addProperty(name, value);
    }
}
