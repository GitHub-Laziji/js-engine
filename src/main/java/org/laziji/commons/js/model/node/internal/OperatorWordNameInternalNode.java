package org.laziji.commons.js.model.node.internal;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.*;
import org.laziji.commons.js.model.node.word.ClassWordNode;
import org.laziji.commons.js.model.node.word.FunctionWordNode;
import org.laziji.commons.js.model.node.word.LambdaWordNode;

public class OperatorWordNameInternalNode extends BaseProxyNode<WordNode> implements InternalNode {

    public OperatorWordNameInternalNode(Node parent) {
        super(parent);
        addProxyItem(new ClassWordNode(null));
        addProxyItem(new FunctionWordNode(null));
        addProxyItem(new LambdaWordNode(null));
        addProxyItem(new SmallBracketWordNode(null));
        addProxyItem(new StringWordNode(null));
        addProxyItem(new NameWordNode(null));
        addProxyItem(new ArrayWordNode(null));
        addProxyItem(new ObjectWordNode(null));
        addProxyItem(new CallWordNode(null));
    }
}
