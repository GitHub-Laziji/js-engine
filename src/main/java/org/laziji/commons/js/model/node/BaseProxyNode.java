package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.ProxyItem;
import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseProxyNode<T extends Node> extends BaseNode {

    private List<ProxyItem<T>> proxyItems = new ArrayList<>();

    public BaseProxyNode(Node parent) {
        super(parent);
    }

    @Override
    public Node append(TokenUnit unit) throws Exception {
        if (isDone()) {
            return getParent().append(unit);
        }
        ProxyItem<T> backup = null;
        Iterator<ProxyItem<T>> iterator = proxyItems.iterator();
        while (iterator.hasNext()) {
            ProxyItem<T> item = iterator.next();
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

    protected void addProxyItem(T node) {
        proxyItems.add(new ProxyItem<>(node));
    }
}
