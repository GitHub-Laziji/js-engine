package org.laziji.commons.js.app;

import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.module.SystemModuleValue;
import org.laziji.commons.js.util.IOUtils;

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
        String script;
        try (FileInputStream fis = new FileInputStream(path)) {
            script = IOUtils.toString(fis, StandardCharsets.UTF_8);
        }
        Top.init();
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval(script);
        Top.loop();
    }
}
