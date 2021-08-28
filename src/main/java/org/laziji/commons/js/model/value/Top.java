package org.laziji.commons.js.model.value;

public class Top {

    private static ThreadLocalTop threadLocalTop;

    public static void setThreadLocalTop(ThreadLocalTop threadLocalTop) {
        Top.threadLocalTop = threadLocalTop;
    }


    public static Object getContext() {
        return threadLocalTop.getContext();
    }


    public static Object getSecurityContext() {
        return threadLocalTop.getSecurityContext();
    }

    public static Object getSaveContext(){
        return threadLocalTop.getSaveContext();
    }

    public static void InitializeThreadLocal(){

    }
}
