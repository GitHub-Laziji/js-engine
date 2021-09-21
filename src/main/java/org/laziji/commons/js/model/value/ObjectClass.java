package org.laziji.commons.js.model.value;

public class ObjectClass extends InternalFunction {

    public ObjectClass() {
        super(null);
    }

    @Override
    public Value getPrototype() {
        return Top.getObjectPrototype();
    }

}
