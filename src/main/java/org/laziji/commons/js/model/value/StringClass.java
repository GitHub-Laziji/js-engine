package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.RunException;

public class StringClass extends InternalFunction {

    public StringClass() {
        super(null);
    }

    {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {
        prototype = new StringValue("");
        prototype.addProperty("substring", new InternalFunction((caller, manager, arguments) -> {
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

}
