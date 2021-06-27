package org.laziji.commons.js.model.value;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.FunctionContext;
import org.laziji.commons.js.model.context.name.LetName;

import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class FunctionValue extends BaseValue {

    private List<Param> params;
    private Executor executor;
    private boolean function;

    public FunctionValue(List<Param> params, Executor executor, boolean function) {
        this.params = params;
        this.executor = executor;
        this.function = function;
    }

    public Value call(Stack<Context> contexts, List<Value> arguments) throws Exception {
        FunctionContext context = new FunctionContext(function);
        contexts.push(context);
        for (Param param : params) {
            context.defined(new LetName(param.getName()), param.fetchValue.apply(arguments));
        }
        System.out.println(context);
        executor.run(contexts);
        contexts.pop();
        return context.getReturnValue();
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
