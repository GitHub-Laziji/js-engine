package org.laziji.commons.js.util;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.value.JsValue;

import java.util.List;

public class RuntimeUtils {

    public static void argumentsValid(List<JsValue> arguments, Class... classes) throws RunException {
        if (arguments.size() < classes.length) {
            throw new RunException("must have %d parameter(s)", classes.length);
        }
        for (int i = 0; i < classes.length; i++) {
            //TODO proto
            if (!classes[i].isInstance(arguments.get(i))) {
                throw new RunException();
            }
        }
    }
}
