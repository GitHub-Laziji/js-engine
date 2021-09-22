package org.laziji.commons.js.model.context;


import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.value.Value;

public interface Context {

    Value addProperty(String key, Value value, ContextPropertyType type) throws Exception;

    Value addProperty(String key, Value value) throws Exception;

    Value getProperty(String key) throws Exception;

    boolean hasProperty(String key) throws Exception;

    void close();

    boolean isClose();

    String toSimpleString();

    enum ContextPropertyType {
        LET,
        VAR,
        CONST;

        public static ContextPropertyType match(Token token) {
            if (token == Token.CONST) {
                return CONST;
            }
            if (token == Token.VAR) {
                return VAR;
            }
            return LET;
        }
    }

    class ContextProperty {
        private String key;
        private Value value;
        private ContextPropertyType type;

        public ContextProperty(String key, Value value, ContextPropertyType type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Value getValue() {
            return value;
        }

        public ContextPropertyType getType() {
            return type;
        }
    }

}
