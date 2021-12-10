package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.context.FunctionContext;
import org.laziji.commons.js.model.value.object.JsFunction;
import org.laziji.commons.js.model.value.object.JsObject;

import java.util.ArrayList;
import java.util.List;


public class InternalFunction extends JsFunction {

    public InternalFunction(Handler handler) {
        super(new Contexts(), new ArrayList<>(), (ctx) -> {
            FunctionContext context = ctx.findFirstContext(FunctionContext.class);
            context.setReturnValue(handler.call(context.getInstance(), context.getArguments()));
        }, true);
    }


    @FunctionalInterface
    public interface Handler {
        JsValue call(JsObject caller, List<JsValue> arguments) throws Exception;
    }


    @Override
    public String toString() {
        return "function() { [native code] }";
    }
}
