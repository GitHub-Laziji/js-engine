package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.object.JsFunction;
import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.primitive.JsUndefined;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PromiseClass extends InternalFunction {

    public PromiseClass() {
        super((caller, args) -> {
            throw new RunException();
        });
    }

    @Override
    public JsValue call(List<JsValue> arguments) {
        throw new RunException();
    }


    @Override
    public JsObject instantiate(List<JsValue> args) throws Exception {
        if (args.size() < 1 || !(args.get(0) instanceof JsFunction)) {
            throw new RunException();
        }
        JsPromise promise = new JsPromise((JsFunction) args.get(0), true);
        promise.execute(null);
        return promise;
    }

    @Override
    protected JsObject initPrototype() {
        return new PromisePrototype();
    }

    public static class PromisePrototype extends JsObject {

        {
            addInternalProperty("then", this::thenFunction);
            addInternalProperty("catch", this::catchFunction);
        }

        @Override
        public JsValue getProto() {
            return Top.getObjectClass().getPrototype();
        }

        public JsValue thenFunction(JsObject caller, List<JsValue> args) {
            if (args.size() < 1 || !(args.get(0) instanceof JsFunction) || !(caller instanceof JsPromise)) {
                throw new RunException();
            }
            JsPromise promise = new JsPromise((JsFunction) args.get(0), false);
            ((JsPromise) caller).addSuccess(promise);
            return promise;
        }

        public JsValue catchFunction(JsObject caller, List<JsValue> args) {
            return JsUndefined.getInstance();
        }

    }

    public static class JsPromise extends JsObject {
        private JsValue result;
        private final List<JsPromise> successNext = new ArrayList<>();
        private final List<JsPromise> errorNext = new ArrayList<>();
        private final JsFunction func;
        private final boolean head;

        public JsPromise(JsFunction func, boolean head) {
            this.func = func;
            this.head = head;
        }

        public void execute(JsValue data) {
            if (head) {
                Top.addMicroTask(() -> func.call(Arrays.asList(new ResolveFunction(this), new RejectFunction(this))));
            } else {
                Top.addMicroTask(() -> {
                    JsValue value = func.call(Collections.singletonList(data));
                    setResult(value);
                });
            }
        }

        private void setResult(JsValue result) {
            this.result = result;
            if (result instanceof JsPromise) {
                this.successNext.forEach(n -> ((JsPromise) result).addSuccess(n));
            } else {
                this.successNext.forEach(n -> n.execute(result));
            }
        }

        public void addSuccess(JsPromise promise) {
            successNext.add(promise);
            if (result != null) {
                if (result instanceof JsPromise) {
                    ((JsPromise) result).addSuccess(promise);
                } else {
                    promise.execute(result);
                }
            }
        }

        //TODO catch
    }

    private static class ResolveFunction extends InternalFunction {

        public ResolveFunction(JsPromise promise) {
            super(null);
        }
    }

    private static class RejectFunction extends InternalFunction {

        public RejectFunction(JsPromise promise) {
            super(null);
        }
    }
}
