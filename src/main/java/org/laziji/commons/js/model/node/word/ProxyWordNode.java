package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.node.BaseProxyNode;
import org.laziji.commons.js.model.node.Node;

public class ProxyWordNode extends BaseProxyNode<WordNode> implements WordNode {

    public ProxyWordNode(Node parent) {
        super(parent);
        addProxyItem(new SmallBracketWordNode(null));
        addProxyItem(new NumberWordNode(null));
        addProxyItem(new StringWordNode(null));
        addProxyItem(new BooleanWordNode(null));
        addProxyItem(new NullWordNode(null));
        addProxyItem(new UndefinedWordNode(null));
        addProxyItem(new NameWordNode(null));
        addProxyItem(new ArrayWordNode(null));
        addProxyItem(new ObjectWordNode(null));
        addProxyItem(new NewObjectWordNode(null));
        addProxyItem(new CallWordNode(null));
        addProxyItem(new UnaryWordNode(null));
        addProxyItem(new BeforeOperatorWordNode(null));
        addProxyItem(new AfterOperatorWordNode(null));
        addProxyItem(new FunctionWordNode(null));
        addProxyItem(new LambdaWordNode(null));
        addProxyItem(new ClassWordNode(null));
    }

}
