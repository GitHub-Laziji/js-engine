package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.ProxyItem;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.ProxyParagraphNode;
import org.laziji.commons.js.model.node.word.basic.BracketWordNode;
import org.laziji.commons.js.model.node.word.complex.ClassWordNode;
import org.laziji.commons.js.model.node.word.complex.FunctionWordNode;
import org.laziji.commons.js.model.node.word.complex.LambdaWordNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProxyWordNode extends ProxyParagraphNode {

    private List<ProxyItem> proxyItems = new ArrayList<>();

    public ProxyWordNode(Node parent) {
        super(parent);
        proxyItems.add(new ProxyItem(new FunctionWordNode(null)));
        proxyItems.add(new ProxyItem(new LambdaWordNode(null)));
        proxyItems.add(new ProxyItem(new ClassWordNode(null)));
        proxyItems.add(new ProxyItem(new BracketWordNode(null)));
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
        if (proxyItems.isEmpty()) {
            proxyItems.add(backup);
        }
        if (isDone() && getParent() != null) {
            return getParent().append(unit);
        }
        throw new Exception(String.format("[%s] is not the expected token.", unit.getToken().toString()));
    }

    @Override
    public boolean isDone() {
        return proxyItems.size() == 1 && proxyItems.get(0).getRoot().isDone();
    }


}
