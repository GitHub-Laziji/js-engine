package org.laziji.commons.js.model.context;

import org.laziji.commons.js.model.value.ObjectValue;

public interface InstanceContext extends Context {

    ObjectValue getInstance();
}
