package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.FunctionContext;
import org.laziji.commons.js.model.context.name.LetName;

import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class FunctionValue extends ObjectValue {

    private List<Param> params;
    private Executor executor;
    private boolean function;
    private ObjectValue prototype;

    public FunctionValue(List<Param> params, Executor executor, boolean function) {
        this.params = params;
        this.executor = executor;
        this.function = function;
    }

    public Value call(ObjectValue caller, Stack<Context> contexts, List<Value> arguments) throws Exception {
        FunctionContext context;
        if (function) {
            context = new FunctionContext(caller == null ? GlobalValue.getInstance() : caller);
        } else {
            context = new FunctionContext(null);
        }
        contexts.push(context);
        for (Param param : params) {
            context.defined(new LetName(param.getName()), param.fetchValue.apply(arguments));
        }
        System.out.println(context);
        executor.run(contexts);
        contexts.pop();
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
        Value run(Stack<Context> contexts) throws Exception;
    }
}
