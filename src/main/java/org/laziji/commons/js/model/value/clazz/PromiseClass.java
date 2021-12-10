package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.object.JsFunction;
import org.laziji.commons.js.model.value.object.JsObject;

import java.util.Arrays;

public class PromiseClass extends InternalFunction {

    public PromiseClass() {
        super((caller, args) -> {
            if (args.size() < 1 || !(args.get(0) instanceof JsFunction)) {
                throw new RunException();
            }
            JsValue resolveObj = caller.getProperty("resolve");
            JsValue rejectObj = caller.getProperty("reject");
            if (!(resolveObj instanceof JsFunction) || !(rejectObj instanceof JsFunction)) {
                throw new RunException();
            }
            JsFunction resolve = ((JsFunction) resolveObj).bind(caller);
            JsFunction reject = ((JsFunction) rejectObj).bind(caller);
            ((JsFunction) args.get(0)).call(Arrays.asList(resolve, reject));
            return null;
        });
    }

    @Override
    protected JsObject initPrototype() {
        return new PromisePrototype();
    }

    public static class PromisePrototype extends JsObject {

    }
}
