package org.laziji.commons.js.model.value;

import java.util.List;

public class JsArray extends JsObject {

    public JsArray(List<JsValue> values) {
        addProperty("length", new JsNumber(values.size()));
        for (int i = 0; i < values.size(); i++) {
            addProperty(i + "", values.get(i));
        }
    }

    @Override
    public JsValue addProperty(String name, JsValue value) {
        // TODO length
        return super.addProperty(name, value);
    }

    @Override
    public String toString() {
        try {
            StringBuilder result = new StringBuilder();
            result.append("[");
            JsValue lengthValue = getProperty("length");
            if (!(lengthValue instanceof JsNumber)) {
                throw new Exception();
            }
            int length = (int) ((JsNumber) lengthValue).getValue();
            for (int i = 0; i < length; i++) {
                result.append(getProperty(i + "").toString());
                if (i != length - 1) {
                    result.append(", ");
                }
            }
            result.append("]");
            return result.toString();
        } catch (Exception ignored) {
        }
        return "[...]";
    }

}
