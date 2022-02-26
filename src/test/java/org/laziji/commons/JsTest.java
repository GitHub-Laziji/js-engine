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
        DocNode doc = Top.compile("let a=1+2,b=3,c=\"string\",d=a*(b+c/2),func=function(){};");
        System.out.println(doc.toString());
    }

    @Test
    public void docTest() throws Exception {
        System.out.println(Top.compile(IOUtils.resourceToString("/doc.js", Charsets.UTF_8)).toString());
    }


    @Test
    public void run() throws Exception {
        Top.eval(IOUtils.resourceToString("/run.js", Charsets.UTF_8));
//        node.compile("let a=123+4567,b=a+1 ,c;\nc=3;\nc+=a;c=c*(2+1);");
        System.out.println(Top.getMainContexts().getContexts().peek().toSimpleString());
        //        JsFunction func = (JsFunction) contexts.peek().get("func");
//        JsValue result = func.call(contexts, Arrays.asList(new JsNumber(9)));
//        System.out.println(result);

    }

    @Test
    public void runSort() throws Exception {
        Top.eval(IOUtils.resourceToString("/sort.js", Charsets.UTF_8));
        Top.loop();
        System.out.println(Top.getMainContexts().getContexts().peek().toSimpleString());
    }


    @Test
    public void runImport() throws Exception {
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval(IOUtils.resourceToString("/import.js", Charsets.UTF_8));
        Top.loop();
        System.out.println(Top.getMainContexts().getContexts().peek().toSimpleString());
    }

    @Test
    public void runString() throws Exception {
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval(IOUtils.resourceToString("/string.js", Charsets.UTF_8));
        Top.loop();
    }

    @Test
    public void runPromise() throws Exception {
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval(IOUtils.resourceToString("/promise.js", Charsets.UTF_8));
        Top.loop();
    }
}
