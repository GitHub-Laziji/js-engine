package org.laziji.commons.js.model.value;

public class ThreadLocalTop {

    private Object context;
    private Object securityContext;
    private Object saveContext;

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public Object getSecurityContext() {
        return securityContext;
    }

    public void setSecurityContext(Object securityContext) {
        this.securityContext = securityContext;
    }

    public Object getSaveContext() {
        return saveContext;
    }

    public void setSaveContext(Object saveContext) {
        this.saveContext = saveContext;
    }
}
