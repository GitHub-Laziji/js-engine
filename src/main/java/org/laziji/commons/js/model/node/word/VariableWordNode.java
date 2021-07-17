package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.context.Context;

public interface VariableWordNode extends WordNode {

    Context.Entry getPosition(ScriptManager manager) throws Exception;
}
