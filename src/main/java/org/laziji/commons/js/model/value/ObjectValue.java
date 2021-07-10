package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.BlockContext;
import org.laziji.commons.js.model.context.Context;

public class ObjectValue extends BaseValue {

    private Context context = new BlockContext();

    public Context getContext() {
        return context;
    }

    public ObjectValue getPrototype() {
        return null;
    }

    public ObjectValue getProto() {
        return ObjectClass.getInstance().getPrototype();
    }
}
