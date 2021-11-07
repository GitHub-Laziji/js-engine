package org.laziji.commons.js.model.node.word;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.model.context.Contexts;
import org.laziji.commons.js.model.node.BaseUnitNode;
import org.laziji.commons.js.model.node.Node;
import org.laziji.commons.js.model.value.JsStringObject;
import org.laziji.commons.js.model.value.JsValue;

import java.util.Collections;
import java.util.Set;

public class StringWordNode extends BaseUnitNode implements WordNode {

    public StringWordNode(Node parent) {
        super(parent);
    }

    @Override
    public JsValue run(Contexts manager) {
        String code = getUnit().getValue();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < code.length() - 1; i++) {
            char ch = code.charAt(i);
            if (ch != '\\') {
                sb.append(ch);
                continue;
            }
            char nextCh = code.charAt(i + 1);
            switch (nextCh) {
                case 'n':
                    sb.append('\n');
                    break;
                default:
                    sb.append(nextCh);
                    break;
            }
            i++;
        }
        return new JsStringObject(sb.toString());
    }

    @Override
    protected Set<Token> getTokens() {
        return Collections.singleton(Token.STRING);
    }
}