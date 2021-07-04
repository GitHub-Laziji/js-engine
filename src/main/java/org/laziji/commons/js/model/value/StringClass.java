package org.laziji.commons.js.model.value;

import java.util.List;

public class StringClass extends FunctionValue {

    private static final StringClass instance = new StringClass(null, null, true);

    public StringClass(List<Param> params, Executor executor, boolean function) {
        super(params, executor, function);
    }

    public static StringClass getInstance() {
        return instance;
    }


    @Override
    public ObjectValue getProto() {
        return FunctionClass.getInstance().getPrototype();
    }
}
