package org.laziji.commons.js.model.value;

import java.util.List;

public class UserFunction extends FunctionValue {

    private ObjectValue prototype;

    public UserFunction(List<Param> params, Executor executor, boolean function) {
        super(params, executor, function);
        prototype = new ObjectValue() {
            @Override
            public Value getProto() {
                return UndefinedValue.getInstance();
            }
        };
    }

    @Override
    public Value getProperty(String key) {
        return super.getProperty(key);
    }
}
