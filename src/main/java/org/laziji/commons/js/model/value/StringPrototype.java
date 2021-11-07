package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.value.env.Top;

import java.util.List;

public class StringPrototype extends JsStringObject {

    {
        addInternalProperty("substring", this::substring);
        addInternalProperty("indexOf", this::indexOf);
    }

    public StringPrototype() {
        super("");
    }

    @Override
    public JsValue getProto() {
        return Top.getObjectClass().getPrototype();
    }

    @Override
    public String toString() {
        return "StringPrototype";
    }

    private JsStringObject substring(JsObject caller, List<JsValue> arguments) throws RunException {
        if (arguments.size() < 1) {
            throw new RunException();
        }
        if (!(caller.getProto() instanceof JsStringObject)) {
            throw new RunException();
        }
        if (arguments.size() == 1) {
            return new JsStringObject(caller.toString().substring(arguments.get(0).toNumber().intValue()));
        }
        return new JsStringObject(caller.toString().substring(
                arguments.get(0).toNumber().intValue(),
                arguments.get(1).toNumber().intValue()
        ));
    }

    private JsNumber indexOf(JsObject caller, List<JsValue> arguments) throws RunException {
        if (arguments.size() < 1) {
            throw new RunException();
        }
        if (!(caller.getProto() instanceof JsStringObject)) {
            throw new RunException();
        }
        return new JsNumber(caller.toString().indexOf(arguments.get(0).toString()));
    }
}
