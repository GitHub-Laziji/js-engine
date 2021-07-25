package org.laziji.commons.js.model.value;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.manager.ScriptManager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class SystemModuleValue extends ModuleValue {

    {
        setDefaultExportValue(new InternalFunction((caller, manager, arguments) -> {
            if (arguments.size() < 1) {
                throw new RunException();
            }
            return new StringValue("SystemFunction: " + arguments.get(0).toString());
        }));

        addExportValue("setTimeout", new InternalFunction((caller, manager, arguments) -> {
            if (arguments.size() < 2
                    || !(arguments.get(0) instanceof FunctionValue)
                    || !(arguments.get(1) instanceof NumberValue)) {
                throw new RunException();
            }
            String id = UUID.randomUUID().toString();
            manager.addDelayMacroTaskId(id);
            ScriptManager subManager = manager.fork();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        manager.addMacroTask(() ->
                                ((FunctionValue) arguments.get(0)).call(null, subManager, new ArrayList<>()));
                        manager.deleteDelayMacroTaskId(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, (long) ((NumberValue) arguments.get(1)).getValue());
            return new StringValue(id);
        }));

        addExportValue("print", new InternalFunction((caller, manager, arguments) -> {
            if (arguments.size() < 1) {
                throw new RunException();
            }
            System.out.println(arguments.get(0).toString());
            return new UndefinedValue();
        }));
    }
}
