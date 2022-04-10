package org.laziji.commons.js.app;

import org.apache.commons.io.IOUtils;
import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.module.SystemModuleValue;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public class Runner {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            throw new Exception("missing script path");
        }
        String path = args[0];
        if (!path.endsWith(".js")) {
            throw new Exception("wrong file type");
        }
        FileInputStream fis = new FileInputStream(path);
        String script = IOUtils.toString(fis, StandardCharsets.UTF_8);
        Top.init();
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval(script);
        Top.loop();
    }
}
