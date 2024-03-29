package org.laziji.commons.js.model.context;

import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Stack;

public class Contexts {

    private final Stack<Context> contexts = new Stack<>();

    public Contexts() {
        contexts.push(new BlockContext());
    }

    private Contexts(Contexts manager) {
        contexts.addAll(manager.getContexts());
    }

    public JsValue addProperty(String key, JsValue value, Context.ContextPropertyType type) throws Exception {
        return contexts.peek().addProperty(key, value, type);
    }

    public JsValue addProperty(String key, JsValue value) throws Exception {
        for (int i = contexts.size() - 1; i >= 0; i--) {
            Context context = contexts.get(i);
            if (context.hasProperty(key)) {
                return context.addProperty(key, value);
            }
        }
        if (Top.getThreadLocalTop().getGlobal().hasProperty(key)) {
            return Top.getThreadLocalTop().getGlobal().addProperty(key, value);
        }
        throw new ReferenceException("%s is not defined", key);
    }

    public JsValue getProperty(String key) throws Exception {
        for (int i = contexts.size() - 1; i >= 0; i--) {
            Context context = contexts.get(i);
            if (context.hasProperty(key)) {
                return context.getProperty(key);
            }
        }
        if (Top.getThreadLocalTop().getGlobal().hasProperty(key)) {
            return Top.getThreadLocalTop().getGlobal().getProperty(key);
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

    public String toTopContextString() {
        return getContexts().peek().toString();
    }
}
