package org.laziji.commons;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.DefinedParagraphNode;
import org.laziji.commons.js.utils.CodeUtils;
import org.laziji.commons.js.utils.TokenUtils;

import java.util.List;

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
        String text = "let a=1+2,b=3,c=\"string\",d=a*(b+c/2),func=function(){};";
        System.out.println(CodeUtils.format(text));
    }

    @Test
    public void docTest() throws Exception {
        String text = IOUtils.resourceToString("/doc.js", Charsets.UTF_8);
        System.out.println(CodeUtils.format(text));
    }

    @Test
    public void docTest2() throws Exception {
        List<TokenUnit> tokens = TokenUtils.parseTextToTokens("let a=123,b,c");
        DocNode node = new DocNode(DefinedParagraphNode::new);
        Node p = node.init();
        for (TokenUnit token : tokens) {
            System.out.println(JSON.toJSONString(token) + " " + p.getSelf().getClass().getSimpleName());
            p = p.append(token);
        }
        if (!node.isDone()) {
            throw new Exception();
        }
        System.out.println(node.toString());
    }
}
