package org.laziji.commons.js.model.context.name;

public class VarName extends BaseName {

    VarName(String name) {
        super(name);
    }

    @Override
    public boolean isVariable() {
        return true;
    }
}
