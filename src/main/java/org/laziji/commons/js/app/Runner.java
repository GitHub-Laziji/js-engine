package org.laziji.commons.js.app;

import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.module.SystemModuleValue;
import org.laziji.commons.js.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public class Runner {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            throw new Exception("missing script path");
        }
        String path = args[0];
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            file = new File(path + ".js");
        }
        if (!file.exists() || !file.isFile()) {
            throw new Exception("file not found");
        }
        String script;
        try (FileInputStream fis = new FileInputStream(file)) {
            script = IOUtils.toString(fis, StandardCharsets.UTF_8);
        }
        Top.init();
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval(script);
        Top.loop();
    }
}
