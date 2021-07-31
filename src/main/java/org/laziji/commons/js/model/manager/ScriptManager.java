package org.laziji.commons.js.model.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.context.InstanceContext;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.*;
import org.laziji.commons.js.util.TokenUtils;

import java.util.*;

public class ScriptManager {

    private final Queue<Runner> macroTasks;
    private final Set<String> delayMacroTaskIds;
    private final Queue<Runner> microTasks;
    private final Map<String, ModuleValue> internalModules;
    private final Map<String, ModuleValue> externalMudules;
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

        macroTasks = new LinkedList<>();
        delayMacroTaskIds = new HashSet<>();
        microTasks = new LinkedList<>();
        internalModules = new HashMap<>();
        externalMudules = new HashMap<>();

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

    public ScriptManager(ScriptManager manager) {
        this.strict = manager.strict;

        macroTasks = manager.macroTasks;
        delayMacroTaskIds = manager.delayMacroTaskIds;
        microTasks = manager.microTasks;
        internalModules = manager.internalModules;
        externalMudules = manager.externalMudules;

        functionClass = manager.functionClass;
        objectClass = manager.objectClass;
        arrayClass = manager.arrayClass;
        stringClass = manager.stringClass;
        numberClass = manager.numberClass;
        global = manager.global;


        contexts = new Stack<>();
        contexts.addAll(manager.getContexts());
    }

    public void addInternalModules(String name, ModuleValue module) {
        internalModules.put(name, module);
    }

    public synchronized void addMacroTask(Runner runner) {
        macroTasks.add(runner);
        notifyAll();
    }

    public synchronized void addDelayMacroTaskId(String id) {
        delayMacroTaskIds.add(id);
    }

    public synchronized void deleteDelayMacroTaskId(String id) {
        delayMacroTaskIds.remove(id);
    }

    public synchronized void addMicroTask(Runner runner) {
        microTasks.add(runner);
    }

    public void eval(String text) throws Exception {
        eval(compile(text));
    }

    public void eval(DocNode doc) {
        addMacroTask(() -> doc.run(this));
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

    public void loop() throws Exception {
        while (true) {
            synchronized (this) {
                while (!macroTasks.isEmpty()) {
                    macroTasks.poll().run();
                }
                while (!microTasks.isEmpty()) {
                    microTasks.poll().run();
                }
                if (delayMacroTaskIds.isEmpty()) {
                    break;
                }
                wait();
            }
        }
    }

    public Stack<Context> getContexts() {
        return contexts;
    }

    public NumberValue createNumberValue(double value) {
        NumberValue numberValue = new NumberValue(value);
        numberValue.setInstanceClass(numberClass);
        return numberValue;
    }

    public StringValue createStringValue(String value) {
        StringValue stringValue = new StringValue(value);
        stringValue.setInstanceClass(stringClass);
        return stringValue;
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

    public ScriptManager fork() {
        return new ScriptManager(this);
    }

    @FunctionalInterface
    public interface Runner {

        void run() throws Exception;
    }
}
