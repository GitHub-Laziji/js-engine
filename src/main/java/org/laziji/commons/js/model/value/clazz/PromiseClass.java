package org.laziji.commons.js.model.value.clazz;

import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.object.JsNumberObject;
import org.laziji.commons.js.model.value.object.JsObject;

public class PromiseClass extends InternalFunction {

    public PromiseClass() {
        super((caller, args) -> {
            return null;
        });
    }

    @Override
    protected JsObject initPrototype() {
        return new PromisePrototype();
    }

    public static class PromisePrototype extends JsObject {

    }
}
