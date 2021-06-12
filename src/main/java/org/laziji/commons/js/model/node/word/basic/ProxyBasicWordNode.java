package org.laziji.commons.js.model.node.word.basic;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;

public class ProxyBasicWordNode extends BaseProxyNode<BasicWordNode> implements BasicWordNode {

    public ProxyBasicWordNode(Node parent) {
        super(parent);
        addProxyItem(new SmallBracketWordNode(null));
        addProxyItem(new NumberWordNode(null));
        addProxyItem(new StringWordNode(null));
        addProxyItem(new NameWordNode(null));
    }

}
