package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.JsFunction;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.JsObject;

public class ProxyCallParamsInternalNode extends BaseProxyNode<InternalNode> implements InternalNode {

    public ProxyCallParamsInternalNode(Node parent) {
        super(parent);
        addProxyItem(new CallFunctionParamsInternalNode(null));
        addProxyItem(new CallObjectParamsInternalNode(null));
        addProxyItem(new CallMemberNameInternalNode(null));
    }

    public JsValue run(JsObject caller, JsValue pre, Contexts manager) throws Exception {
        JsObject objectValue = JsObject.cast(pre);
        Node self = getSelf();
        if (self instanceof CallFunctionParamsInternalNode && objectValue instanceof JsFunction) {
            return ((JsFunction) objectValue).call(caller, ((CallFunctionParamsInternalNode) self).getArguments(manager));
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

    public JsValue assignment(JsValue pre, Contexts manager, JsValue value) throws Exception {
        JsObject objectValue = JsObject.cast(pre);
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
