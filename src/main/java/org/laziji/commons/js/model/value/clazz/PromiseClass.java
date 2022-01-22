package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.object.JsFunction;
import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.primitive.JsUndefined;

import java.util.Arrays;
import java.util.List;

public class PromiseClass extends InternalFunction {

    public PromiseClass() {
        super((caller, args) -> {
            if (args.size() < 1 || !(args.get(0) instanceof JsFunction)) {
                throw new RunException();
            }
            ResolveFunction resolve = new ResolveFunction();
            resolve.bind(caller);
            Top.addMicroTask(()-> ((JsFunction) args.get(0)).call(Arrays.asList(resolve, null)));
            return null;
        });
    }

    @Override
    public JsValue call(List<JsValue> arguments) {
        throw new RunException();
    }

    @Override
    protected JsObject initPrototype() {
        return new PromisePrototype();
    }

    public static class PromisePrototype extends JsObject {

        {
            addInternalProperty("then", this::then);
        }

        @Override
        public JsValue getProto() {
            return Top.getObjectClass().getPrototype();
        }

        public JsValue then(JsObject caller,List<JsValue> args){
            return JsUndefined.getInstance();
        }

    }

    private static class ResolveFunction extends InternalFunction{

        public ResolveFunction() {
            super((caller, args) -> {
                caller.addProperty("result",args.get(0));
                return null;
            });
        }
    }
}
