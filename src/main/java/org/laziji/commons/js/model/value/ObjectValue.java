package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.ObjectContext;

public class ObjectValue extends BaseValue {

    private Context context = new ObjectContext();

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
