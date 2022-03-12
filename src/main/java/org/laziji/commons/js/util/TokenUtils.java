package org.laziji.commons.js.util;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.model.node.Node;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TokenUtils {

    public static List<Node.TokenUnit> parseTextToTokens(String text, Set<Token> excludes) throws Exception {
        List<Node.TokenUnit> tokens = new ArrayList<>();
        while (!text.isEmpty()) {
            Node.TokenUnit token = null;
            for (Token type : Token.values()) {
                String value = type.match(text);
                if (value != null) {
                    token = new Node.TokenUnit(type, value);
                    break;
                }
            }
            if (token == null) {
                throw new Exception();
            }
            if (excludes != null && excludes.contains(token.getToken())) {
                throw new CompileException("[%s] is not allowed.", token.getToken());
            }
            text = text.substring(token.getValue().length());
            if (token.getToken() == Token.SPACE || token.getToken() == Token.NEWLINE) {
                continue;
            }
            tokens.add(token);
        }
        tokens.add(new Node.TokenUnit(Token.EOF, null));
        return tokens;
    }

    public static List<Node.TokenUnit> parseTextToTokens(String text) throws Exception {
        return parseTextToTokens(text, null);
    }

}
