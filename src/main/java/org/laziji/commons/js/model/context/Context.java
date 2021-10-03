package org.laziji.commons.js.model.context;


import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.value.JsValue;

public interface Context {

    JsValue addProperty(String key, JsValue value, ContextPropertyType type) throws Exception;

    JsValue addProperty(String key, JsValue value) throws Exception;

    JsValue getProperty(String key) throws Exception;

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
        private JsValue value;
        private ContextPropertyType type;

        public ContextProperty(String key, JsValue value, ContextPropertyType type) {
            this.key = key;
            this.value = value;
            this.type = type;
        }

        public void setValue(JsValue value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public JsValue getValue() {
            return value;
        }

        public ContextPropertyType getType() {
            return type;
        }
    }

}
