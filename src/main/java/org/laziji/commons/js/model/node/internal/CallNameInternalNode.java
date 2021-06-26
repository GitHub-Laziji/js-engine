package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.*;

public class CallNameInternalNode extends BaseProxyNode<WordNode> implements InternalNode {

    public CallNameInternalNode(Node parent) {
        super(parent);
        addProxyItem(new ArrayWordNode(null));
        addProxyItem(new NameWordNode(null));
        addProxyItem(new NumberWordNode(null));
        addProxyItem(new ObjectWordNode(null));
        addProxyItem(new SmallBracketWordNode(null));
        addProxyItem(new StringWordNode(null));
    }
}
