package org.laziji.commons.js.model.context.name;

public abstract class BaseName implements Name {

    private String name;

    public BaseName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
