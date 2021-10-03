package org.laziji.commons.js.model.node;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.manager.NodeConfiguration;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.value.JsValue;

public interface Node {

    NodeConfiguration getConfiguration();

    void setConfiguration(NodeConfiguration configuration);

    Node getParent();

    void setParent(Node parent);

    Node getSelf();

    Node append(TokenUnit unit) throws Exception;

    boolean isDone();


    String toString(int depth, boolean start);

    JsValue run(Contexts manager) throws Exception;

    class TokenUnit {

        private Token token;
        private String value;

        public TokenUnit(Token token, String value) {
            this.token = token;
            this.value = value;
        }

        public Token getToken() {
            return token;
        }

        public void setToken(Token token) {
            this.token = token;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
