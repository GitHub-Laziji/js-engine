package org.laziji.commons.js.model.value;

import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.FunctionContext;

import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class FunctionValue extends BaseValue {

    private List<Param> params;
    private Function<Stack<Context>, Value> run;
    private boolean function;

    public FunctionValue(List<Param> params, Function<Stack<Context>, Value> run, boolean function) {
        this.params = params;
        this.run = run;
        this.function = function;
    }

    public Value call(Stack<Context> contexts, List<Value> arguments) throws Exception {
        FunctionContext context = new FunctionContext(function);
        contexts.push(context);
        for (Param param : params) {
            context.put(param.getName(), param.fetchValue.apply(arguments));
        }
        run.apply(contexts);
        contexts.pop();
        Value returnValue = context.getReturnValue();
        return returnValue == null ? new UndefinedValue() : returnValue;
    }

    public static class Param {
        private String name;
        private Function<List<Value>, Value> fetchValue;

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
}
