package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public interface Node {

    Node getParent();

    void setParent(Node parent);

    Node getSelf();

    Node append(TokenUnit unit) throws Exception;

    Node init();

    boolean isDone();

    String toString(int depth, boolean start);

}
