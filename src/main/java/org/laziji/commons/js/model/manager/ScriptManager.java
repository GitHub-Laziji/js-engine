package org.laziji.commons.js.model.manager;

import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.exception.RunException;
import org.laziji.commons.js.model.context.BlockContext;
import org.laziji.commons.js.model.context.Context;
import org.laziji.commons.js.model.node.DocNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.ModuleValue;
import org.laziji.commons.js.util.TokenUtils;

import java.util.*;

public class ScriptManager {

    private final Stack<Context> contexts;

    private String scriptFilePath;

    public ScriptManager() {
        contexts = new Stack<>();
        contexts.push(new BlockContext());
    }

    public ScriptManager(ScriptManager manager) {
        contexts = new Stack<>();
        contexts.addAll(manager.getContexts());
    }





    public Stack<Context> getContexts() {
        return contexts;
    }

    public List<Context> getReContexts() {
        List<Context> reContexts = new ArrayList<>();
        for (int i = contexts.size() - 1; i >= 0; i--) {
            reContexts.add(contexts.get(i));
        }
        return reContexts;
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
