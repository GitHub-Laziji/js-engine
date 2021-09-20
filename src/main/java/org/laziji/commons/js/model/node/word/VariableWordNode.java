package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.value.Value;

public interface VariableWordNode extends WordNode {

    Value assignment(ScriptManager manager, Value value) throws Exception;
}
