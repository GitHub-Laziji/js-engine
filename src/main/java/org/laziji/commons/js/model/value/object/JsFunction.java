package org.laziji.commons.js.model.value.object;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.context.FunctionContext;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;

import java.util.List;
import java.util.function.Function;

public class JsFunction extends JsObject {

    private Contexts contexts;
    private List<Param> params;
    private Executor executor;
    private boolean function;
    private JsObject prototype;

    {
        prototype = initPrototype();
        prototype.addInternalProperty("constructor", this);
        addInternalProperty("prototype", prototype);
    }

    public JsFunction(Contexts contexts, List<Param> params, Executor executor, boolean function) {
        this.contexts = contexts;
        this.params = params;
        this.executor = executor;
        this.function = function;
    }

    protected JsFunction() {

    }

    public JsValue call(JsObject caller, List<JsValue> arguments) throws Exception {
        FunctionContext context;
        if (function) {
            context = new FunctionContext(caller == null ? Top.getGlobal() : caller);
        } else {
            context = new FunctionContext(null);
        }
        contexts.getContexts().push(context);
        context.setArguments(arguments);
        for (Param param : params) {
            context.addProperty(param.getName(), param.fetchValue.apply(arguments), Context.ContextPropertyType.LET);
        }
        executor.run(contexts);
        contexts.getContexts().pop();
        return context.getReturnValue();
    }

    public JsObject instantiate(List<JsValue> arguments) throws Exception {
        JsObject obj = new JsObject();
        FunctionContext context = new FunctionContext(obj);
        contexts.getContexts().push(context);
        context.setArguments(arguments);
        for (Param param : params) {
            context.addProperty(param.getName(), param.fetchValue.apply(arguments), Context.ContextPropertyType.LET);
        }
        executor.run(contexts);
        contexts.getContexts().pop();
        return obj;
    }

    protected JsObject initPrototype() {
        return new JsObject() {
            @Override
            public JsValue getProto() {
                return Top.getObjectClass().getPrototype();
            }
        };
    }

    public JsObject getPrototype() {
        return prototype;
    }

    @Override
    public JsValue getProto() {
        return Top.getFunctionClass().getPrototype();
    }

    public static class Param {
        private String name;
        private Function<List<JsValue>, JsValue> fetchValue;

        public Param(String name, Function<List<JsValue>, JsValue> fetchValue) {
            this.name = name;
            this.fetchValue = fetchValue;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Function<List<JsValue>, JsValue> getFetchValue() {
            return fetchValue;
        }

        public void setFetchValue(Function<List<JsValue>, JsValue> fetchValue) {
            this.fetchValue = fetchValue;
        }
    }

    @FunctionalInterface
    public interface Executor {
        void run(Contexts manager) throws Exception;
    }
}
