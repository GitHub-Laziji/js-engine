package org.laziji.commons;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.paragraph.LetParagraphNode;
import org.laziji.commons.js.model.node.section.SectionNode;
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
        String text = "let a=1+2,b=3,c=\"string\",d=a*(b+c/2),func=function(){}";
//        System.out.println(text);
        List<TokenUnit> tokenUnits = TokenUtils.parseTextToTokens(text);
        LetParagraphNode letNode = new LetParagraphNode(null);
        Node p = letNode;
        int i = 0;
        while (i < tokenUnits.size()) {
            System.out.println(JSON.toJSONString(tokenUnits.get(i))+" "+p.getClass().getSimpleName());
            p = p.append(tokenUnits.get(i));
            i++;
        }
        System.out.println(letNode.isDone());
        System.out.println(letNode.toString());
    }

    @Test
    public void sectionTest() throws Exception {
        String text = IOUtils.resourceToString("/section.js", Charsets.UTF_8);
//        System.out.println(text);
        List<TokenUnit> tokenUnits = TokenUtils.parseTextToTokens(text);
        SectionNode node = new SectionNode(null);
        Node p = node.init();
        int i = 0;
        while (i < tokenUnits.size()) {
            System.out.println(JSON.toJSONString(tokenUnits.get(i))+" "+p.getClass().getSimpleName());
            p = p.append(tokenUnits.get(i));
            i++;
        }
        System.out.println(node.isDone());
        System.out.println(node.toString(0));
    }

}
