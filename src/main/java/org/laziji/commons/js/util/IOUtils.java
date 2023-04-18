package org.laziji.commons.js.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

public class IOUtils {

    public static String toString(InputStream input, Charset charset) throws IOException {
        StringBuilder output = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(input, charset);
        char[] buffer = new char[1024];
        int len;
        while ((len = reader.read(buffer)) > 0) {
            output.append(buffer, 0, len);
        }
        return output.toString();
    }

    public static String resourceToString(String name, Charset charset) throws IOException {
        URL resource = IOUtils.class.getResource(name);
        if (resource == null) {
            throw new IOException();
        }
        try (InputStream input = resource.openStream()) {
            return toString(input, charset);
        }
    }

}
