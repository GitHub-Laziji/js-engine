package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.ObjectContext;

import java.util.HashSet;
import java.util.Set;

public class ObjectValue extends BaseValue {

    protected final Context context;
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

    public void put(String name, Value value) throws Exception {
        context.getEntry(name).setValue(value);
    }

    public Value get(String name) throws Exception {
        return get(name, new HashSet<>());
    }

    public Context.Entry getEntry(String name) throws Exception {
        return context.getEntry(name);
    }

    private Value get(String name, Set<ObjectValue> values) throws Exception {
        values.add(this);
        Value value = context.get(name);
        ObjectValue proto = getProto();
        if (value instanceof UndefinedValue && proto != null && !values.contains(proto)) {
            return proto.get(name, values);
        }
        return value;
    }

    @Override
    public String toString() {
        return context.toString();
    }
}
