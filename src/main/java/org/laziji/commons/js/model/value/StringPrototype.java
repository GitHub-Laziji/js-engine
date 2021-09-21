package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.util.RuntimeUtils;

import java.util.List;

public class StringPrototype extends StringValue {

    {
        addInternalProperty("substring", this::substring);
        addInternalProperty("indexOf", this::indexOf);
    }

    public StringPrototype() {
        super("");
    }

    @Override
    public Value getProto() {
        return Top.getObjectPrototype();
    }

    @Override
    public String toString() {
        return "StringPrototype";
    }

    private StringValue substring(ObjectValue caller, Contexts contexts, List<Value> arguments) throws RunException {
        if (arguments.size() < 1) {
            throw new RunException();
        }
        if (!(caller.getProto() instanceof StringValue)) {
            throw new RunException();
        }
        if (arguments.size() == 1) {
            return new StringValue(caller.toString().substring(arguments.get(0).toNumber().intValue()));
        }
        return new StringValue(caller.toString().substring(
                arguments.get(0).toNumber().intValue(),
                arguments.get(1).toNumber().intValue()
        ));
    }

    private NumberValue indexOf(ObjectValue caller, Contexts contexts, List<Value> arguments) throws RunException {
        if (arguments.size() < 1) {
            throw new RunException();
        }
        if (!(caller.getProto() instanceof StringValue)) {
            throw new RunException();
        }
        return new NumberValue(caller.toString().indexOf(arguments.get(0).toString()));
    }
}
