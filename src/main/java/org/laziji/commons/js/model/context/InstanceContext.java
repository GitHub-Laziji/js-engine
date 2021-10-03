package org.laziji.commons.js.model.context;

import org.laziji.commons.js.model.value.JsObject;

public class InstanceContext extends BaseContext{

    private JsObject instance;

    public InstanceContext(JsObject instance) {
        this.instance = instance;
    }

    public JsObject getInstance() {
        return instance;
    }

}
