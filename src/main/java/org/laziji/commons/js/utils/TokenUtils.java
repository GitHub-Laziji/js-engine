package org.laziji.commons.js.utils;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;

import java.util.ArrayList;
import java.util.List;

public class TokenUtils {

    public static List<TokenUnit> parseTextToTokens(String text) throws Exception {
        List<TokenUnit> tokens = new ArrayList<>();
        while (!text.isEmpty()) {
            TokenUnit token = null;
            for (Token type : Token.values()) {
                String value = type.match(text);
                if (value != null) {
                    token = new TokenUnit(type, value);
                    break;
                }
            }
            if (token == null) {
                System.out.println(text);
                throw new Exception();
            }
            text = text.substring(token.getValue().length());
            if (token.getToken() == Token.SPACE) {
                continue;
            }
            tokens.add(token);
        }
        return tokens;
    }

}
