package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.ProxyItem;
import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ValueSentenceNode extends ParagraphNode {

    private List<ProxyItem> proxyItems = new ArrayList<>();

    public ValueSentenceNode(Node parent) {
        super(parent);
        proxyItems.add(new ProxyItem(new FunctionValueSentenceNode(null)));
        proxyItems.add(new ProxyItem(new LambdaValueSentenceNode(null)));
        proxyItems.add(new ProxyItem(new ClassValueSentenceNode(null)));
        proxyItems.add(new ProxyItem(new BracketValueSentenceNode(null)));
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
