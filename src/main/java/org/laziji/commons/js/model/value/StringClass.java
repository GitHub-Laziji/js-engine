package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.RunException;

public class StringClass extends InternalFunction {

    public StringClass() {
        super(null);
    }

    @Override
    public ObjectValue getPrototype() {
        return Top.getStringPrototype();
    }
}
