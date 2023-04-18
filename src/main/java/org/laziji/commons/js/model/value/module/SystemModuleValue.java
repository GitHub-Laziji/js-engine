package org.laziji.commons.js.model.value.module;

import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.value.InternalFunction;
import org.laziji.commons.js.model.value.JsValue;
import org.laziji.commons.js.model.value.env.ThreadLocalTop;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.object.JsFunction;
import org.laziji.commons.js.model.value.object.JsObject;
import org.laziji.commons.js.model.value.primitive.JsNumber;
import org.laziji.commons.js.model.value.primitive.JsString;
import org.laziji.commons.js.model.value.primitive.JsUndefined;
import org.laziji.commons.js.util.IOUtils;

import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.*;

public class SystemModuleValue extends ModuleValue {

    {
        setDefaultExportValue(new JsObject());
        addExportValue("readFileSync", new InternalFunction(this::readFileSyncHandler));
        addExportValue("readFile", new InternalFunction(this::readFileHandler));
        addExportValue("setTimeout", new InternalFunction(this::setTimeoutHandler));
        addExportValue("print", new InternalFunction(this::printHandler));
    }

    public JsValue readFileSyncHandler(JsObject caller, List<JsValue> arguments) {
        if (arguments.size() < 1) {
            throw new RunException();
        }
        String path = arguments.get(0).toString();
        String enc = "utf-8";
        if (arguments.size() >= 2) {
            enc = arguments.get(1).toString();
        }
        try (FileInputStream fis = new FileInputStream(path)) {
            String data = IOUtils.toString(fis, Charset.forName(enc));
            return new JsString(data);
        } catch (Exception e) {
            throw new RunException(e.getMessage());
        }
    }

    public JsValue readFileHandler(JsObject caller, List<JsValue> arguments) {
        if (arguments.size() < 1) {
            throw new RunException();
        }
        final String path = arguments.get(0).toString();
        final String enc = arguments.size() >= 2 ? arguments.get(1).toString() : "utf-8";
        InternalFunction func = new InternalFunction((ignore, args) -> {
            JsFunction resolve = (JsFunction) (args.get(0));
            JsFunction reject = (JsFunction) (args.get(1));
            String id = UUID.randomUUID().toString();
            Top.addDelayMacroTaskId(id);
            ThreadLocalTop threadLocalTop = Top.getThreadLocalTop();
            new Thread(() -> {
                Top.init(threadLocalTop);
                try (FileInputStream fis = new FileInputStream(path)) {
                    String data = IOUtils.toString(fis, Charset.forName(enc));
                    Top.deleteDelayMacroTaskId(id);
                    Top.addMacroTask(() -> resolve.call(Collections.singletonList(new JsString(data))));
                } catch (Exception e) {
                    try {
                        Top.deleteDelayMacroTaskId(id);
                        Top.addMacroTask(() -> reject.call(Collections.singletonList(new JsString(e.getMessage()))));
                    } catch (Exception ex) {
                        throw new RunException();
                    }
                }

            }).start();
            return JsUndefined.getInstance();
        });
        try {
            return Top.getThreadLocalTop().getPromiseClass().instantiate(Collections.singletonList(func));
        } catch (Exception e) {
            throw new RunException(e.getMessage());
        }
    }

    public JsValue printHandler(JsObject caller, List<JsValue> arguments) {
        if (arguments.size() < 1) {
            throw new RunException();
        }
        System.out.println(arguments.get(0).toString());
        return new JsUndefined();
    }

    public JsValue setTimeoutHandler(JsObject caller, List<JsValue> arguments) {
        if (arguments.size() < 2 || !(arguments.get(0) instanceof JsFunction) || !(arguments.get(1) instanceof JsNumber)) {
            throw new RunException();
        }
        String id = UUID.randomUUID().toString();
        Top.addDelayMacroTaskId(id);
        ThreadLocalTop threadLocalTop = Top.getThreadLocalTop();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Top.init(threadLocalTop);
                try {
                    Top.deleteDelayMacroTaskId(id);
                    Top.addMacroTask(() -> ((JsFunction) arguments.get(0)).call(new ArrayList<>()));
                } catch (Exception e) {
                    throw new RunException();
                }
            }
        }, (long) ((JsNumber) arguments.get(1)).getValue());
        return new JsString(id);
    }

}
