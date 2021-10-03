package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.JsValue;

public interface VariableWordNode extends WordNode {

    JsValue assignment(Contexts manager, JsValue value) throws Exception;
}
