package org.laziji.commons.js.utils;

import org.laziji.commons.js.consts.Token;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.doc.DocNode;

import java.util.List;

public class CodeUtils {

    public static String format(String text) throws Exception {
        List<TokenUnit> units = TokenUtils.parseTextToTokens(text);
        units.add(new TokenUnit(Token.EOF, null));
        DocNode node = new DocNode();
        Node p = node.init();
        for (TokenUnit unit : units) {
            p = p.append(unit);
        }
        if (!node.isDone()) {
            throw new Exception();
        }
        return node.toString();
    }
}
