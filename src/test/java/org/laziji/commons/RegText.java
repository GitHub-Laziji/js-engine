package org.laziji.commons;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.ScriptManager;
import org.laziji.commons.js.model.context.BlockContext;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.paragraph.DefinedParagraphNode;
import org.laziji.commons.js.model.value.FunctionValue;
import org.laziji.commons.js.model.value.NumberValue;
import org.laziji.commons.js.model.value.Value;

import java.util.Arrays;
import java.util.Stack;

public class RegText {

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
        ScriptManager manager = new ScriptManager();
        System.out.println(manager.compile("let a=1+2,b=3,c=\"string\",d=a*(b+c/2),func=function(){};").toString());
    }

    @Test
    public void docTest() throws Exception {
        ScriptManager manager = new ScriptManager();
        System.out.println(manager.compile(IOUtils.resourceToString("/doc.js", Charsets.UTF_8)).toString());
    }


    @Test
    public void run() throws Exception {
        ScriptManager manager = new ScriptManager();
        manager.run(IOUtils.resourceToString("/run.js", Charsets.UTF_8));
//        node.compile("let a=123+4567,b=a+1 ,c;\nc=3;\nc+=a;c=c*(2+1);");
        System.out.println(manager.getContexts().peek());
//        FunctionValue func = (FunctionValue) contexts.peek().get("func");
//        Value result = func.call(contexts, Arrays.asList(new NumberValue(9)));
//        System.out.println(result);

    }
}
