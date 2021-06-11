package org.laziji.commons.js.model.node.word.complex;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;

public class ProxyComplexWordNode extends BaseProxyNode<ComplexWordNode> implements ComplexWordNode {

    public ProxyComplexWordNode(Node parent) {
        super(parent);
        addProxyItem(new FunctionWordNode(null));
        addProxyItem(new LambdaWordNode(null));
        addProxyItem(new ClassWordNode(null));
    }

}
