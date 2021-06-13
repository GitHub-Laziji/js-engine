package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.WordNode;
import org.laziji.commons.js.model.node.word.basic.*;
import org.laziji.commons.js.model.node.word.complex.ProxyComplexWordNode;

public class OperatorWordNameInternalNode extends BaseProxyNode<WordNode> implements InternalNode {

    public OperatorWordNameInternalNode(Node parent) {
        super(parent);
        addProxyItem(new ProxyComplexWordNode(null));
        addProxyItem(new SmallBracketWordNode(null));
        addProxyItem(new StringWordNode(null));
        addProxyItem(new NameWordNode(null));
        addProxyItem(new ArrayWordNode(null));
        addProxyItem(new ObjectWordNode(null));
        addProxyItem(new CallWordNode(null));
    }
}
