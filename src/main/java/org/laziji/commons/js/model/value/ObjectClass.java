package org.laziji.commons.js.model.value;

import java.util.List;

public class ObjectClass extends FunctionValue {

    private static final ObjectClass instance = new ObjectClass(null, null, true);

    public ObjectClass(List<Param> params, Executor executor, boolean function) {
        super(params, executor, function);
    }

    public static ObjectClass getInstance() {
        return instance;
    }


    @Override
    public ObjectValue getProto() {
        return ObjectClass.getInstance().getPrototype();
    }
}
