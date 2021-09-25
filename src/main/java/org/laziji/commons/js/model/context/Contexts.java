package org.laziji.commons.js.model.context;

import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Contexts {

    private final Stack<Context> contexts = new Stack<>();

    public Contexts() {
        contexts.push(new BlockContext());
    }

    private Contexts(Contexts manager) {
        contexts.addAll(manager.getContexts());
    }

    public Value addProperty(String key, Value value, Context.ContextPropertyType type) throws Exception {
        return contexts.peek().addProperty(key, value, type);
    }

    public Value addProperty(String key, Value value) throws Exception {
        for (int i = contexts.size() - 1; i >= 0; i--) {
            Context context = contexts.get(i);
            if (context.hasProperty(key)) {
                return context.addProperty(key, value);
            }
        }
        if (Top.getGlobal().hasProperty(key)) {
            return Top.getGlobal().addProperty(key, value);
        }
        throw new ReferenceException("%s is not defined", key);
    }

    public Value getProperty(String key) throws Exception {
        for (int i = contexts.size() - 1; i >= 0; i--) {
            Context context = contexts.get(i);
            if (context.hasProperty(key)) {
                return context.getProperty(key);
            }
        }
        if (Top.getGlobal().hasProperty(key)) {
            return Top.getGlobal().getProperty(key);
        }
        throw new ReferenceException("%s is not defined", key);
    }

    public Stack<Context> getContexts() {
        return contexts;
    }

    public <T extends Context> T findFirstContext(Class<T> clazz) throws Exception {
        for (int i = contexts.size() - 1; i >= 0; i--) {
            Context context = contexts.get(i);
            if (clazz.isInstance(context)) {
                return clazz.cast(context);
            }
        }
        throw new Exception();
    }

    public Contexts fork() {
        return new Contexts(this);
    }


}
