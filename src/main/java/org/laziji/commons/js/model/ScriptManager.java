package org.laziji.commons.js.model;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.InstanceContext;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.*;
import org.laziji.commons.js.util.TokenUtils;

import java.util.List;
import java.util.Stack;

public class ScriptManager {

    private final GlobalValue global;
    private final ObjectClass objectClass;
    private final FunctionClass functionClass;
    private final StringClass stringClass;
    private final NumberClass numberClass;

    private final Stack<Context> contexts;

    public ScriptManager() {
        functionClass = new FunctionClass();
        objectClass = new ObjectClass();
        stringClass = new StringClass();
        numberClass = new NumberClass();
        global = new GlobalValue();

        functionClass.setInstanceClass(functionClass);
        objectClass.setInstanceClass(functionClass);
        stringClass.setInstanceClass(functionClass);
        numberClass.setInstanceClass(functionClass);
        global.setInstanceClass(objectClass);

        contexts = new Stack<>();
        contexts.push(new InstanceContext(global));
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

    public NumberValue createNumberValue(double value) {
        NumberValue numberValue = new NumberValue(value);
        numberValue.setInstanceClass(numberClass);
        return numberValue;
    }
}
