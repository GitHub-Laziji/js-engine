package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.RunException;

public class StringPrototype extends StringValue {

    {
        addProperty("substring", new InternalFunction((caller, manager, arguments) -> {
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
        }), ObjectPropertyType.READ_ONLY);
    }

    public StringPrototype() {
        super("");
    }

    @Override
    public Value getProto() {
        return Top.getObjectPrototype();
    }
}
