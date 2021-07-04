package org.laziji.commons.js.model.value;

import java.util.List;

public class FunctionClass extends FunctionValue {

    private static final FunctionClass instance = new FunctionClass(null, null, true);

    public FunctionClass(List<Param> params, Executor executor, boolean function) {
        super(params, executor, function);
    }

    public static FunctionClass getInstance() {
        return instance;
    }


    @Override
    public ObjectValue getProto() {
        return FunctionClass.getInstance().getPrototype();
    }
}
