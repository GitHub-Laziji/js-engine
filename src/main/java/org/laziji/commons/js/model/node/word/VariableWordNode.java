package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.Value;

public interface VariableWordNode extends WordNode {

    Value assignment(Contexts manager, Value value) throws Exception;
}
