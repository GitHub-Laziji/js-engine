package org.laziji.commons.js.util;

import org.apache.commons.lang3.StringUtils;
import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.exception.SyntaxException;
import org.laziji.commons.js.model.node.Node;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TokenUtils {

    public static List<Node.TokenUnit> parseTextToTokens(String text, Set<Token> excludes) throws Exception {
        List<Node.TokenUnit> tokens = new ArrayList<>();
        String line = null;
        int lineNum = 0;
        while (!text.isEmpty()) {
            if (line == null) {
                line = getFirstLine(text);
                lineNum++;
            }
            Node.TokenUnit token = null;
            for (Token type : Token.values()) {
                String value = type.match(text);
                if (value != null) {
                    token = new Node.TokenUnit(type, value);
                    break;
                }
            }
            if (token == null) {
                String n = getFirstLine(text);
                String pre = line.substring(0, line.length() - n.length());
                throw new SyntaxException("Invalid or unexpected token, Ln %d, Col %d\n%s%s\n%s^",
                        lineNum, pre.length() + 1,
                        pre, n, StringUtils.repeat(' ', pre.length()));
            }
            if (excludes != null && excludes.contains(token.getToken())) {
                throw new CompileException("[%s] is not allowed.", token.getToken());
            }
            text = text.substring(token.getValue().length());
            if (token.getToken() == Token.SPACE) {
                continue;
            }
            if (token.getToken() == Token.NEWLINE) {
                line = null;
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

    private static String getFirstLine(String text) {
        int i = text.indexOf("\n");
        if (i == -1) {
            return text;
        }
        return text.substring(0, i);
    }

}
