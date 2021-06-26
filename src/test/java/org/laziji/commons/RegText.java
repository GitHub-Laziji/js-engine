package org.laziji.commons;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.BlockContext;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.paragraph.DefinedParagraphNode;

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
        DocNode node = new DocNode();
        node.compile("let a=1+2,b=3,c=\"string\",d=a*(b+c/2),func=function(){};");
        System.out.println(node.toString());
    }

    @Test
    public void docTest() throws Exception {
        DocNode node = new DocNode();
        node.compile(IOUtils.resourceToString("/doc.js", Charsets.UTF_8));
        System.out.println(node.toString());
    }

    @Test
    public void docTest2() throws Exception {
        DocNode node = new DocNode(DefinedParagraphNode::new);
        node.compile("let a=123,b,c");
        System.out.println(node.toString());
    }

    @Test
    public void run() throws Exception {
        DocNode node = new DocNode(DefinedParagraphNode::new);
        node.compile("let a=123+4567,b=a+1 ,c");

        System.out.println(node.toString());

        Stack<Context> contexts = new Stack<>();
        contexts.push(new BlockContext());

        node.run(contexts);

        System.out.println(contexts.peek());
    }
}
