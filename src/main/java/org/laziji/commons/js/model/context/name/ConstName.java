package org.laziji.commons.js.model.context.name;

public class ConstName extends BaseName {

    public ConstName(String name) {
        super(name);
    }

    @Override
    public boolean isVariable() {
        return false;
    }
}
