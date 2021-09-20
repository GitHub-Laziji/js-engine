package org.laziji.commons.js.model.value;

import java.util.List;

public class UserFunction extends FunctionValue {

    public UserFunction(List<Param> params, Executor executor, boolean function) {
        super(params, executor, function);
        prototype = new ObjectValue() {
            @Override
            public ObjectValue getProto() {
                return null;
            }
        };
    }


}
