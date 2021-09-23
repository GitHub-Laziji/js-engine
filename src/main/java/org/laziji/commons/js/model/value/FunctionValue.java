package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.context.FunctionContext;
import org.laziji.commons.js.model.value.env.Top;

import java.util.List;
import java.util.function.Function;

public class FunctionValue extends ObjectValue {

    private Contexts contexts;
    private List<Param> params;
    private Executor executor;
    private boolean function;
    private ObjectValue prototype;

    {
        prototype = initPrototype();
        prototype.addInternalProperty("constructor", this);
        addInternalProperty("prototype", prototype);
    }

    public FunctionValue(Contexts contexts, List<Param> params, Executor executor, boolean function) {
        this.contexts = contexts;
        this.params = params;
        this.executor = executor;
        this.function = function;
    }

    protected FunctionValue() {

    }

    public Value call(ObjectValue caller, List<Value> arguments) throws Exception {
        FunctionContext context;
        if (function) {
            context = new FunctionContext(caller == null ? Top.getGlobal() : caller);
        } else {
            context = new FunctionContext(null);
        }
        contexts.getContexts().push(context);
        for (Param param : params) {
            context.addProperty(param.getName(), param.fetchValue.apply(arguments), Context.ContextPropertyType.LET);
        }
        executor.run(contexts);
        contexts.getContexts().pop();
        return context.getReturnValue();
    }

    public ObjectValue instantiate(ObjectValue caller, List<Value> arguments) throws Exception {
        // TODO
        return null;
    }

    protected ObjectValue initPrototype() {
        return new ObjectValue() {
            @Override
            public Value getProto() {
                return Top.getObjectClass().getPrototype();
            }
        };
    }

    public ObjectValue getPrototype() {
        return prototype;
    }

    @Override
    public Value getProto() {
        return Top.getFunctionClass().getPrototype();
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
        Value run(Contexts manager) throws Exception;
    }
}
