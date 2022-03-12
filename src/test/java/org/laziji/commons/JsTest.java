package org.laziji.commons;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.value.module.SystemModuleValue;
import org.laziji.commons.js.model.value.env.Top;

public class JsTest {

    @Test
    public void test() {
        System.out.println(Token.IMPORT.match("import Vue from 'vue';\nsadasd\nimport Vue from 'vue';"));
        System.out.println(Token.REMARK.match("/*sasdasd\nasdsaddasd*/ ddd */"));
        System.out.println(Token.CONST.match("const aaa"));
        System.out.println(Token.CONST.match("const$aaa"));
        System.out.println(Token.CONST.match("const"));
        System.out.println(Token.REMARK_OF_LINE.match("//asdasd\nasdsad"));
        System.out.println(Token.REMARK_OF_LINE.match("//s"));
        System.out.println(Token.STRING.match("\"asd\\\"sad\"asdsad"));
    }

    @Test
    public void letTest() throws Exception {
        Top.init();
        DocNode doc = Top.compile("let     a=1+2,b=3,c=\"string\",d=a*(b+c/2),func=function(){};");
        System.out.println(doc);
    }

    @Test
    public void docTest() throws Exception {
        Top.init();
        System.out.println(Top.compile(IOUtils.resourceToString("/doc.js", Charsets.UTF_8)));
    }


    @Test
    public void run() throws Exception {
        Top.init();
        Top.eval("function sort(arr, i, j) {\n" +
                       "    if (i >= j) {\n" +
                       "        return;\n" +
                       "    }\n" +
                       "    let p = i, q = j;\n" +
                       "    let temp = arr[p];\n" +
                       "    while (p < q) {\n" +
                       "        while (p < q && arr[q] >= temp) {\n" +
                       "            q-=1;\n" +
                       "        }\n" +
                       "        arr[p] = arr[q];\n" +
                       "        while (p < q && arr[p] <= temp) {\n" +
                       "            p+=1;\n" +
                       "        }\n" +
                       "        arr[q] = arr[p];\n" +
                       "    }\n" +
                       "    arr[q] = temp;\n" +
                       "    sort(arr, i, q - 1);\n" +
                       "    sort(arr, q + 1, j);\n" +
                       "}\n" +
                       "\n" +
                       "let arr = [234, 57, 12, 123, 346, 1234, 2];\n" +
                       "\n" +
                       "sort(arr, 0, arr.length - 1);");
        Top.loop();
        System.out.println(Top.getThreadLocalTop().getMainContexts().getContexts().peek().toSimpleString());
    }

    @Test
    public void runSort() throws Exception {
        Top.init();
        Top.eval(IOUtils.resourceToString("/sort.js", Charsets.UTF_8));
        Top.loop();
        System.out.println(Top.getThreadLocalTop().getMainContexts().getContexts().peek().toSimpleString());
    }


    @Test
    public void runImport() throws Exception {
        Top.init();
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval(IOUtils.resourceToString("/import.js", Charsets.UTF_8));
        Top.loop();
        System.out.println(Top.getThreadLocalTop().getMainContexts().getContexts().peek().toSimpleString());
    }

    @Test
    public void runString() throws Exception {
        Top.init();
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval(IOUtils.resourceToString("/string.js", Charsets.UTF_8));
        Top.loop();
    }

    @Test
    public void runPromise() throws Exception {
        Top.init();
        Top.getThreadLocalTop().setOvertime(5000L);
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval(IOUtils.resourceToString("/promise.js", Charsets.UTF_8));
        Top.loop();
    }
}
