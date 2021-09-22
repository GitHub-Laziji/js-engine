package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Contexts;

import java.util.List;

public class UserFunction extends FunctionValue {

    private ObjectValue prototype;

    public UserFunction(Contexts contexts, List<Param> params, Executor executor, boolean function) {
        super(contexts, params, executor, function);
        prototype = new ObjectValue() {
            @Override
            public Value getProto() {
                return UndefinedValue.getInstance();
            }
        };
    }

    @Override
    public Value getProperty(String key) {
        return prototype;
    }
}
