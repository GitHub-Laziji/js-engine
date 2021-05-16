package org.laziji.commons;

import org.junit.Test;
import org.laziji.commons.js.consts.Token;

public class RegText {

    @Test
    public void test() {
        System.out.println(Token.REMARK.match("/*sasdasd\nasdsaddasd"));
        System.out.println(Token.CONST.match("const aaa"));
        System.out.println(Token.CONST.match("const$aaa"));
        System.out.println(Token.CONST.match("const"));
        System.out.println(Token.REMARK_OF_LINE.match("//asdasd\nasdsad"));
        System.out.println(Token.REMARK_OF_LINE.match("//s"));
        System.out.println(Token.STRING.match("\"asd\\\"sad\"asdsad"));
    }
}
