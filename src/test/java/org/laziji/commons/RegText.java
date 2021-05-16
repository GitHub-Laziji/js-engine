package org.laziji.commons;

import org.junit.Test;
import org.laziji.commons.js.consts.Token;

public class RegText {

    @Test
    public void test() {
        System.out.println(Token.REMARK.match("/*sasdasd\nasdsaddasd"));
    }
}
