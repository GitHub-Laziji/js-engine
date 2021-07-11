package org.laziji.commons.js.model.context;

import org.laziji.commons.js.model.value.GlobalValue;
import org.laziji.commons.js.model.value.ObjectValue;

public class GlobalContext extends BaseContext implements InstanceContext {

    @Override
    public ObjectValue getInstance() {
        return GlobalValue.getInstance();
    }
}
