package org.laziji.commons.js.model.value;

public class SystemModuleValue extends ModuleValue {

    {
        setDefaultExportValue(new InternalFunction((caller, manager, arguments) -> {
            return new StringValue("SystemFunction: " + arguments.get(0).toString());
        }));
    }
}
