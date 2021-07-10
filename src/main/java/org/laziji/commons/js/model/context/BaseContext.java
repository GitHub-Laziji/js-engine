package org.laziji.commons.js.model.context;

import com.alibaba.fastjson.JSON;
import org.laziji.commons.js.exception.ReferenceException;
import org.laziji.commons.js.exception.SyntaxException;
import org.laziji.commons.js.exception.TypeException;
import org.laziji.commons.js.model.context.name.Name;
import org.laziji.commons.js.model.value.Value;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseContext implements Context {

    protected Map<String, Entry> context;
    protected boolean close = false;

    public BaseContext() {
        this.context = new ConcurrentHashMap<>();
    }

    @Override
    public void defined(Name name, Value value) throws SyntaxException {
        if (this.context.containsKey(name.getName())) {
            throw new SyntaxException("Identifier '%s' has already been declared", name.getName());
        }
        if (!name.isVariable() && value == null) {
            throw new SyntaxException("Missing initializer in const declaration");
        }
        this.context.put(name.getName(), new Entry(this, name, value));
    }

    @Override
    public void put(String name, Value value) throws ReferenceException, TypeException {
        Entry item = this.context.get(name);
        if (item == null) {
            throw new ReferenceException("%s is not defined", name);
        }
        if (!item.getName().isVariable()) {
            throw new TypeException("Assignment to constant variable");
        }
        item.setValue(value);
    }

    @Override
    public Value get(String name) {
        Entry item = this.context.get(name);
        if (item == null) {
            return null;
        }
        return item.getValue();
    }

    @Override
    public Entry getEntry(String name) throws ReferenceException {
        Entry item = this.context.get(name);
        if (item == null) {
            throw new ReferenceException("%s is not defined", name);
        }
        return item;
    }

    @Override
    public boolean isClose() {
        return close;
    }

    @Override
    public void close() {
        this.close = true;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(context, true);
    }

}
