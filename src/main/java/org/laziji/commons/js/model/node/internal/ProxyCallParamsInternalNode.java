package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;

public class ProxyCallParamsInternalNode extends BaseProxyNode<InternalNode> implements InternalNode {

    public ProxyCallParamsInternalNode(Node parent) {
        super(parent);
        addProxyItem(new CallFunctionParamsInternalNode(null));
        addProxyItem(new CallObjectParamsInternalNode(null));
        addProxyItem(new CallMemberNameInternalNode(null));
    }
}
