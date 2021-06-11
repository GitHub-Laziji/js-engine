package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.basic.ProxyBasicWordNode;
import org.laziji.commons.js.model.node.word.complex.ProxyComplexWordNode;

public class ProxyWordNode extends BaseProxyNode<WordNode> implements WordNode {

    public ProxyWordNode(Node parent) {
        super(parent);
        addProxyItem(new ProxyBasicWordNode(null));
        addProxyItem(new ProxyComplexWordNode(null));
    }

}
