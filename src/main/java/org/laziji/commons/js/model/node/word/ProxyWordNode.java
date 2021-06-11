package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.ProxyItem;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.BaseNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.word.basic.BracketWordNode;
import org.laziji.commons.js.model.node.word.basic.NameWordNode;
import org.laziji.commons.js.model.node.word.basic.NumberWordNode;
import org.laziji.commons.js.model.node.word.basic.StringWordNode;
import org.laziji.commons.js.model.node.word.complex.ClassWordNode;
import org.laziji.commons.js.model.node.word.complex.FunctionWordNode;
import org.laziji.commons.js.model.node.word.complex.LambdaWordNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProxyWordNode extends BaseNode implements WordNode {

    private List<ProxyItem> proxyItems = new ArrayList<>();

    public ProxyWordNode(Node parent) {
        super(parent);
        proxyItems.add(new ProxyItem<WordNode>(new FunctionWordNode(null)));
        proxyItems.add(new ProxyItem<WordNode>(new LambdaWordNode(null)));
        proxyItems.add(new ProxyItem<WordNode>(new ClassWordNode(null)));
        proxyItems.add(new ProxyItem<WordNode>(new BracketWordNode(null)));
        proxyItems.add(new ProxyItem<WordNode>(new NumberWordNode(null)));
        proxyItems.add(new ProxyItem<WordNode>(new StringWordNode(null)));
        proxyItems.add(new ProxyItem<WordNode>(new NameWordNode(null)));
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (isDone()) {
            return getParent().append(unit);
        }
        ProxyItem backup = null;
        Iterator<ProxyItem> iterator = proxyItems.iterator();
        while (iterator.hasNext()) {
            ProxyItem item = iterator.next();
            try {
                item.setCurrent(item.getCurrent().append(unit));
            } catch (Exception e) {
                iterator.remove();
                if (item.getRoot().isDone()) {
                    backup = item;
                }
            }
        }
        if (proxyItems.isEmpty() && backup != null) {
            proxyItems.add(backup);
        }
        if (proxyItems.size() > 0) {
            return this;
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return proxyItems.size() == 1 && proxyItems.get(0).getRoot().isDone();
    }

    @Override
    public String toString() {
        return proxyItems.get(0).getRoot().toString();
    }
}
