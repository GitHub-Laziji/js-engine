package org.laziji.commons;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.paragraph.LetParagraphNode;
import org.laziji.commons.js.utils.TokenUtils;

import java.util.List;

public class RegText {

    @Test
    public void test() {
        System.out.println(Token.IMPORT.match("import Vue from 'vue';\nsadasd\nimport Vue from 'vue';"));
        System.out.println(Token.REMARK.match("/*sasdasd\nasdsaddasd"));
        System.out.println(Token.CONST.match("const aaa"));
        System.out.println(Token.CONST.match("const$aaa"));
        System.out.println(Token.CONST.match("const"));
        System.out.println(Token.REMARK_OF_LINE.match("//asdasd\nasdsad"));
        System.out.println(Token.REMARK_OF_LINE.match("//s"));
        System.out.println(Token.STRING.match("\"asd\\\"sad\"asdsad"));
    }

    @Test
    public void astTest() throws Exception {
        String text = IOUtils.resourceToString("/ast.js", Charsets.UTF_8);
//        System.out.println(text);
        List<TokenUnit> tokenUnits = TokenUtils.parseTextToTokens(text);
        System.out.println(JSON.toJSONString(tokenUnits, true));
    }

    @Test
    public void letTest() throws Exception {
        String text = "let a=1+2,b=3;";
//        System.out.println(text);
        List<TokenUnit> tokenUnits = TokenUtils.parseTextToTokens(text);
        LetParagraphNode letNode = new LetParagraphNode(null);

        System.out.println(JSON.toJSONString(tokenUnits, true));
    }

}
