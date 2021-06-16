package org.laziji.commons.js.utils;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.node.doc.DocNode;

import java.util.List;

public class CodeUtils {

    public static String format(String text) throws Exception {
        List<TokenUnit> tokens = TokenUtils.parseTextToTokens(text);
        DocNode node = new DocNode();
        Node p = node.init();
        for (TokenUnit token : tokens) {
            System.out.println(JSON.toJSONString(token)+" "+p.getSelf().getClass().getSimpleName());
            p = p.append(token);
        }
        if (!node.isDone()) {
            throw new Exception();
        }
        return node.toString();
    }
}
