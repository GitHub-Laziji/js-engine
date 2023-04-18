package org.laziji.commons.js.util;

import org.apache.commons.io.output.StringBuilderWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class IOUtils {

    public static String toString(InputStream input, Charset charset) throws IOException {
        try (StringBuilderWriter sw = new StringBuilderWriter();) {
            InputStreamReader reader = new InputStreamReader(input, charset);
            char[] buffer = new char[1024];
            int len;
            while ((len = reader.read(buffer)) > 0) {
                sw.write(buffer, 0, len);
            }
            return sw.toString();
        }
    }

}
