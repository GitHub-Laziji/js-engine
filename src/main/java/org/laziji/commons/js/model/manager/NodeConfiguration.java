package org.laziji.commons.js.model.manager;

public class NodeConfiguration {

    private boolean strict;
    private boolean safe;

    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }
}