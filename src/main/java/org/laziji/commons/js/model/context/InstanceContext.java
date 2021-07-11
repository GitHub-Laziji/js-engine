package org.laziji.commons.js.model.context;

import org.laziji.commons.js.model.value.ObjectValue;

public class InstanceContext extends BaseContext{

    private ObjectValue instance;

    public InstanceContext(ObjectValue instance) {
        this.instance = instance;
    }

    public ObjectValue getInstance() {
        return instance;
    }

}
