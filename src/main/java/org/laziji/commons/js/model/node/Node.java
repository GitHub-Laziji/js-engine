package org.laziji.commons.js.model.node;

import org.laziji.commons.js.model.TokenUnit;

public interface Node {

    Node getParent();

    Node append(TokenUnit unit) throws Exception;

    boolean isDone();

}