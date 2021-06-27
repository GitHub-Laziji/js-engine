package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.context.Context;

import java.util.Stack;

public interface VariableWordNode extends WordNode {

    Context.Entry getPosition(Stack<Context> contexts) throws Exception;
}