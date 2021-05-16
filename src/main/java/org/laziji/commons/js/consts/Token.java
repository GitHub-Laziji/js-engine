package org.laziji.commons.js.consts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token {
    NEWLINE("^(\\n+)[\\s\\S]*"),
    SPACE("^(\\s+)[\\s\\S]*"),
    SEMICOLON("^(;)[\\s\\S]*"),
    COLON("^(:)[\\s\\S]*"),
    QUESTION("^(\\?)[\\s\\S]*"),
    DOT("^(\\.)[\\s\\S]*"),
    BRACKET_SML_OPEN("^(\\()[\\s\\S]*"),
    BRACKET_SML_CLOSE("^(\\))[\\s\\S]*"),
    BRACKET_MID_OPEN("^(\\[)[\\s\\S]*"),
    BRACKET_MID_CLOSE("^(\\])[\\s\\S]*"),
    BRACKET_BIG_OPEN("^(\\{)[\\s\\S]*"),
    BRACKET_BIG_CLOSE("^(\\})[\\s\\S]*"),
    REMARK("^(/\\*[\\s\\S]*\\*/)[\\s\\S]*"),
    REMARK_OF_LINE("^(//.*[\\n$])[\\s\\S]*"),

    CONST(""),
    VAR(""),
    LET(""),
    FUNCTION(""),
    FOR(""),
    WHERE(""),
    DO(""),
    BREAK(""),
    RETURN(""),
    CONTINUE(""),
    SYNC(""),
    AWAIT(""),
    YIELD(""),
    NEW(""),
    IMPORT(""),
    AS(""),
    EXPORT(""),
    DEFAULT(""),
    CLASS(""),
    STATIC(""),
    PRIVATE(""),
    PUBLIC(""),
    CONSTRUCTOR(""),

    SELF_ADD(""),
    SELF_SUB(""),
    SELF_ADD_BY(""),
    SELF_SUB_BY(""),
    SELF_MUL_BY(""),
    SELF_DIV_BY(""),
    SELF_MOD_BY(""),
    SELF_AND_BY(""),
    SELF_OR_BY(""),
    SELF_BIT_AND_BY(""),
    SELF_BIT_OR_BY(""),
    SELF_BIT_XOR_BY(""),
    ADD(""),
    SUB(""),
    MUL(""),
    DIV(""),
    MOD(""),
    AND(""),
    OR(""),
    NON(""),
    EQUAL(""),
    ABS_EQUAL(""),
    UNEQUAL(""),
    ABS_UNEQUAL(""),
    BIT_AND(""),
    BIT_OR(""),
    BIT_XOR(""),
    ASSIGNMENT(""),

    STRING(""),
    NUMBER(""),
    BOOLEAN(""),
    REGEXP(""),
    UNDEFINED(""),
    NULL(""),
    NAME("");

    private Pattern reg;

    Token(String reg) {
        this.reg = Pattern.compile(reg);
    }

    public String match(String text){
        Matcher matcher = reg.matcher(text);
        return matcher.group(1);
    }

}
