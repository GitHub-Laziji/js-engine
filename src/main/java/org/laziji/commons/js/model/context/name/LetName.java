package org.laziji.commons.js.model.context.name;

public class LetName extends BaseName {

    public LetName(String name) {
        super(name);
    }

    @Override
    public boolean isVariable() {
        return true;
    }
}
