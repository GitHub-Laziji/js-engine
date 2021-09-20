package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.manager.ScriptManager;
import org.laziji.commons.js.model.context.FunctionContext;
import org.laziji.commons.js.model.context.name.LetName;

import java.util.List;
import java.util.function.Function;

public class FunctionValue extends ObjectValue {

    private List<Param> params;
    private Executor executor;
    private boolean function;
    protected ObjectValue prototype;

    public FunctionValue(List<Param> params, Executor executor, boolean function) {
        this.params = params;
        this.executor = executor;
        this.function = function;
        proto = Top.getFunctionPrototype();
    }

    public Value call(ObjectValue caller, ScriptManager manager, List<Value> arguments) throws Exception {
        FunctionContext context;
        if (function) {
            context = new FunctionContext(caller == null ? manager.getGlobal() : caller);
        } else {
            context = new FunctionContext(null);
        }
        manager.getContexts().push(context);
        for (Param param : params) {
            context.addProperty(param.getName(), param.fetchValue.apply(arguments), Context.ContextPropertyType.LET);
        }
        executor.run(manager);
        manager.getContexts().pop();
        return context.getReturnValue();
    }

    public void setPrototype(ObjectValue prototype) {
        this.prototype = prototype;
    }

    public ObjectValue getPrototype() {
        return prototype;
    }

    public static class Param {
        private String name;
        private Function<List<Value>, Value> fetchValue;

        public Param(String name, Function<List<Value>, Value> fetchValue) {
            this.name = name;
            this.fetchValue = fetchValue;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Function<List<Value>, Value> getFetchValue() {
            return fetchValue;
        }

        public void setFetchValue(Function<List<Value>, Value> fetchValue) {
            this.fetchValue = fetchValue;
        }
    }

    @FunctionalInterface
    public interface Executor {
        Value run(ScriptManager manager) throws Exception;
    }
}
