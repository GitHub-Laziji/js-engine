package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.primitive.JsUndefined;

import java.util.List;

public class UserFunction extends JsFunction {

    private JsObject prototype;

    public UserFunction(Contexts contexts, List<Param> params, Executor executor, boolean function) {
        super(contexts, params, executor, function);
        prototype = new JsObject() {
            @Override
            public JsValue getProto() {
                return JsUndefined.getInstance();
            }
        };
    }

    @Override
    public JsValue getProperty(String key) {
        return prototype;
    }
}
