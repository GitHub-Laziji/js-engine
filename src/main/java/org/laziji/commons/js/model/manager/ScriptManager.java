package org.laziji.commons.js.model.manager;

import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.InstanceContext;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.*;
import org.laziji.commons.js.util.TokenUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ScriptManager {

    private final Map<String, ModuleValue> internalModules = new HashMap<>();
    private final Map<String, ModuleValue> externalMudules = new HashMap<>();
    private final boolean strict;

    private final ObjectValue global;
    private final ObjectClass objectClass;
    private final ArrayClass arrayClass;
    private final FunctionClass functionClass;
    private final StringClass stringClass;
    private final NumberClass numberClass;

    private final Stack<Context> contexts;

    private String scriptFilePath;

    public ScriptManager(boolean strict) {
        this.strict = strict;
        functionClass = new FunctionClass();
        objectClass = new ObjectClass();
        arrayClass = new ArrayClass();
        stringClass = new StringClass();
        numberClass = new NumberClass();
        global = new ObjectValue();

        functionClass.setInstanceClass(functionClass);
        objectClass.setInstanceClass(functionClass);
        arrayClass.setInstanceClass(functionClass);
        stringClass.setInstanceClass(functionClass);
        numberClass.setInstanceClass(functionClass);
        global.setInstanceClass(objectClass);

        contexts = new Stack<>();
        contexts.push(new InstanceContext(global));
    }

    public void addInternalModules(String name, ModuleValue module) {
        internalModules.put(name, module);
    }

    public void run(String text) throws Exception {
        compile(text).run(this);
    }

    public void run(DocNode doc) throws Exception {
        doc.run(this);
    }

    public DocNode compile(String text) throws Exception {
        NodeConfiguration configuration = new NodeConfiguration();
        configuration.setStrict(strict);
        DocNode doc = new DocNode(configuration);
        List<Node.TokenUnit> tokens = TokenUtils.parseTextToTokens(text);
        Node p = doc;
        for (Node.TokenUnit token : tokens) {
//            System.out.println(JSON.toJSONString(token) + " " + p.getSelf().getClass().getSimpleName());
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

    public ObjectValue getGlobal() {
        return global;
    }

    public ObjectClass getObjectClass() {
        return objectClass;
    }

    public ArrayClass getArrayClass() {
        return arrayClass;
    }

    public FunctionClass getFunctionClass() {
        return functionClass;
    }

    public StringClass getStringClass() {
        return stringClass;
    }

    public NumberClass getNumberClass() {
        return numberClass;
    }

    public ModuleValue getModule(String name) throws RunException {
        if (internalModules.containsKey(name)) {
            return internalModules.get(name);
        }
        // TODO
        throw new RunException("Module not find");
    }

    public String getScriptFilePath() {
        return scriptFilePath;
    }

    public void setScriptFilePath(String scriptFilePath) {
        this.scriptFilePath = scriptFilePath;
    }

    public boolean isMain() {
        return scriptFilePath == null;
    }
}
