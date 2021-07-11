package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.ObjectContext;

public class ObjectValue extends BaseValue {

    private final Context context;
    private FunctionValue instanceClass;

    public ObjectValue(FunctionClass instanceClass) {
        this.instanceClass = instanceClass;
        this.context = new ObjectContext();
    }

    public ObjectValue() {
        this.context = new ObjectContext();
    }

    public Context getContext() {
        return context;
    }

    public void setInstanceClass(FunctionValue instanceClass) {
        if (this.instanceClass != null) {
            throw new RuntimeException();
        }
        this.instanceClass = instanceClass;
    }

    public ObjectValue getInstanceClass() {
        return instanceClass;
    }

    public ObjectValue getProto() {
        if (instanceClass == null) {
            return null;
        }
        return instanceClass.getPrototype();
    }
}
