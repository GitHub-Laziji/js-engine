package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.RunException;

public class SystemModuleValue extends ModuleValue {

    {
        setDefaultExportValue(new InternalFunction((caller, manager, arguments) -> {
            if (arguments.size() < 1) {
                throw new RunException();
            }
            return new StringValue("SystemFunction: " + arguments.get(0).toString());
        }));
    }
}
