package org.laziji.commons.js.model.context.name;

public abstract class BaseName implements Name {

    private String name;

    BaseName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
