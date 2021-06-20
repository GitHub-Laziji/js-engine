package org.laziji.commons.js.model;

import org.laziji.commons.js.constant.Token;

public class TokenUnit {

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
