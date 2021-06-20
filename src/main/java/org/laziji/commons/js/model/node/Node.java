package org.laziji.commons.js.model.node;

import org.laziji.commons.js.exception.OperationException;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.value.Value;

import java.util.Stack;

public interface Node {

    Node getParent();

    void setParent(Node parent);

    Node getSelf();

    Node append(TokenUnit unit) throws Exception;

    Node init();

    boolean isDone();

    String toString(int depth, boolean start);

    Value run(Stack<Context> contexts) throws RunException, OperationException;

}
