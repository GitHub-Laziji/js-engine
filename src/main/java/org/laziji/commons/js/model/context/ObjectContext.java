package org.laziji.commons.js.model.context;

import org.laziji.commons.js.model.context.name.LetName;
import org.laziji.commons.js.model.value.UndefinedValue;
import org.laziji.commons.js.model.value.Value;

public class ObjectContext extends BaseContext {

    @Override
    public Value get(String name) {
        Entry item = this.context.get(name);
        if (item == null) {
            return new UndefinedValue();
        }
        return item.getValue();
    }

    @Override
    public Entry getEntry(String name) {
        Entry item = this.context.get(name);
        if (item != null) {
            return item;
        }
        LetName letName = new LetName(name);
        item = new Entry(this, letName, new UndefinedValue());
        this.context.put(letName.getName(), item);
        return item;
    }

}
