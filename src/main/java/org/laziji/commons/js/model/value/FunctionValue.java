package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.context.FunctionContext;

import java.util.List;
import java.util.function.Function;

public class FunctionValue extends ObjectValue {

    private List<Param> params;
    private Executor executor;
    private boolean function;

    {
        addInternalProperty("prototype", this::getPrototype);
    }

    public FunctionValue(List<Param> params, Executor executor, boolean function) {
        this.params = params;
        this.executor = executor;
        this.function = function;
    }

    public Value call(ObjectValue caller, Contexts manager, List<Value> arguments) throws Exception {
        FunctionContext context;
        if (function) {
            context = new FunctionContext(caller == null ? Top.getGlobal() : caller);
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

    public Value getPrototype() {
        return UndefinedValue.getInstance();
    }

    @Override
    public Value getProto() {
        return Top.getFunctionPrototype();
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
