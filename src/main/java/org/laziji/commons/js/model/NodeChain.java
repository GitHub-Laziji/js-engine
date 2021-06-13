package org.laziji.commons.js.model;

public class NodeChain {

    private NodeChain(Builder builder) {

    }

    public static class Builder {

        public NodeChain build() {
            return new NodeChain(this);
        }
    }
}
