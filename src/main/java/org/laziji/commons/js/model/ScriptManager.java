package org.laziji.commons.js.model;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.model.context.BlockContext;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.util.TokenUtils;

import java.util.List;
import java.util.Stack;

public class ScriptManager {

    private final Stack<Context> contexts;

    public ScriptManager() {
        contexts = new Stack<>();
        contexts.push(new BlockContext());
    }

    public void run(String text) throws Exception {
        compile(text).run(contexts);
    }

    public void run(DocNode doc) throws Exception {
        doc.run(contexts);
    }

    public DocNode compile(String text) throws Exception {
        DocNode doc = new DocNode();
        List<Node.TokenUnit> tokens = TokenUtils.parseTextToTokens(text);
        Node p = doc;
        for (Node.TokenUnit token : tokens) {
            System.out.println(JSON.toJSONString(token) + " " + p.getSelf().getClass().getSimpleName());
            p = p.append(token);
        }
        if (!doc.isDone()) {
            throw new CompileException();
        }
        return doc;
    }

    public Stack<Context> getContexts() {
        return contexts;
    }
}
