package org.laziji.commons.js.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token {

    EOF("\0"),

    NEWLINE("^(\\n+)[\\s\\S]*"),
    SPACE("^(\\s+)[\\s\\S]*"),
    SEMICOLON("^(;)[\\s\\S]*"),
    COLON("^(:)[\\s\\S]*"),
    QUESTION("^(\\?)[\\s\\S]*"),
    DOT("^(\\.)[\\s\\S]*"),
    COMMA("^(,)[\\s\\S]*"),
    LAMBDA("^(\\=\\>)[\\s\\S]*"),
    BRACKET_SML_OPEN("^(\\()[\\s\\S]*"),
    BRACKET_SML_CLOSE("^(\\))[\\s\\S]*"),
    BRACKET_MID_OPEN("^(\\[)[\\s\\S]*"),
    BRACKET_MID_CLOSE("^(\\])[\\s\\S]*"),
    BRACKET_BIG_OPEN("^(\\{)[\\s\\S]*"),
    BRACKET_BIG_CLOSE("^(\\})[\\s\\S]*"),
    REMARK("^(/\\*[\\s\\S]*?\\*/)[\\s\\S]*"),
    REMARK_OF_LINE("^(//.*)\\r?(\\n|$)[\\s\\S]*"),

    CONST("^(const)([^\\w\\$][\\s\\S]*|$)"),
    VAR("^(var)([^\\w\\$][\\s\\S]*|$)"),
    LET("^(let)([^\\w\\$][\\s\\S]*|$)"),
    IF("^(if)([^\\w\\$][\\s\\S]*|$)"),
    ELSE("^(else)([^\\w\\$][\\s\\S]*|$)"),
    FUNCTION("^(function)([^\\w\\$][\\s\\S]*|$)"),
    FOR("^(for)([^\\w\\$][\\s\\S]*|$)"),
    OF("^(of)([^\\w\\$][\\s\\S]*|$)"),
    IN("^(in)([^\\w\\$][\\s\\S]*|$)"),
    WHILE("^(while)([^\\w\\$][\\s\\S]*|$)"),
    DO("^(do)([^\\w\\$][\\s\\S]*|$)"),
    BREAK("^(break)([^\\w\\$][\\s\\S]*|$)"),
    RETURN("^(return)([^\\w\\$][\\s\\S]*|$)"),
    CONTINUE("^(continue)([^\\w\\$][\\s\\S]*|$)"),
    SYNC("^(sync)([^\\w\\$][\\s\\S]*|$)"),
    AWAIT("^(await)([^\\w\\$][\\s\\S]*|$)"),
    YIELD("^(yield)([^\\w\\$][\\s\\S]*|$)"),
    NEW("^(new)([^\\w\\$][\\s\\S]*|$)"),
    IMPORT("^(import)([^\\w\\$][\\s\\S]*|$)"),
    FROM("^(from)([^\\w\\$][\\s\\S]*|$)"),
    AS("^(as)([^\\w\\$][\\s\\S]*|$)"),
    EXPORT("^(export)([^\\w\\$][\\s\\S]*|$)"),
    DEFAULT("^(default)([^\\w\\$][\\s\\S]*|$)"),
    CLASS("^(class)([^\\w\\$][\\s\\S]*|$)"),
    STATIC("^(static)([^\\w\\$][\\s\\S]*|$)"),
    PRIVATE("^(private)([^\\w\\$][\\s\\S]*|$)"),
    PUBLIC("^(public)([^\\w\\$][\\s\\S]*|$)"),
//    CONSTRUCTOR("^(constructor)([^\\w\\$][\\s\\S]*|$)"),

    SELF_ADD("^(\\+\\+)[\\s\\S]*"),
    SELF_SUB("^(\\-\\-)[\\s\\S]*"),
    SELF_ADD_BY("^(\\+\\=)[\\s\\S]*"),
    SELF_SUB_BY("^(\\-\\=)[\\s\\S]*"),
    SELF_MUL_BY("^(\\*\\=)[\\s\\S]*"),
    SELF_DIV_BY("^(\\/\\=)[\\s\\S]*"),
    SELF_MOD_BY("^(\\%\\=)[\\s\\S]*"),
    SELF_AND_BY("^(\\&\\&\\=)[\\s\\S]*"),
    SELF_OR_BY("^(\\|\\|\\=)[\\s\\S]*"),
    SELF_BIT_AND_BY("^(\\&\\=)[\\s\\S]*"),
    SELF_BIT_OR_BY("^(\\|\\=)[\\s\\S]*"),
    SELF_BIT_XOR_BY("^(\\^\\=)[\\s\\S]*"),
    ADD("^(\\+)([^\\+][\\s\\S]*|$)"),
    SUB("^(\\-)([^\\-][\\s\\S]*|$)"),
    MUL("^(\\*)[\\s\\S]*"),
    DIV("^(\\/)[\\s\\S]*"),
    MOD("^(\\%)[\\s\\S]*"),
    AND("^(\\&\\&)[\\s\\S]*"),
    SHR("^(>>>)[\\s\\S]*"),
    SAR("^(>>)[\\s\\S]*"),
    SHL("^(<<)[\\s\\S]*"),
    OR("^(\\|\\|)[\\s\\S]*"),
    NON("^(\\!)[\\s\\S]*"),
    EQUAL("^(\\=\\=)([^\\=][\\s\\S]*|$)"),
    ABS_EQUAL("^(\\=\\=\\=)[\\s\\S]*"),
    UNEQUAL("^(\\!\\=)([^\\=][\\s\\S]*|$)"),
    ABS_UNEQUAL("^(\\!\\=\\=)([^\\=][\\s\\S]*|$)"),
    GT_EQUAL("^(\\>\\=)[\\s\\S]*"),
    GT("^(\\>)[\\s\\S]*"),
    LT_EQUAL("^(\\<\\=)[\\s\\S]*"),
    LT("^(\\<)[\\s\\S]*"),
    BIT_AND("^(\\&)([^\\&][\\s\\S]*|$)"),
    BIT_OR("^(\\|)([^\\|][\\s\\S]*|$)"),
    BIT_XOR("^(\\^)[\\s\\S]*"),
    ASSIGNMENT("^(\\=)([^\\=][\\s\\S]*|$)"),

    THIS("^(this)([^\\w\\$][\\s\\S]*|$)"),
    STRING("^(\"(\\\\.|[^\"])*\"|'(\\\\.|[^'])*')[\\s\\S]*"),
    NUMBER("^(\\d+(\\.\\d+)?|NaN)[\\s\\S]*"),
    BOOLEAN("^(true|false)([^\\w\\$][\\s\\S]*|$)"),
    REGEXP("^(/(\\\\.|[^/])+/[a-zA-Z]?)[\\s\\S]*"),
    UNDEFINED("^(undefined)([^\\w\\$][\\s\\S]*|$)"),
    NULL("^(null)([^\\w\\$][\\s\\S]*|$)"),
    NAME("^([a-zA-Z_\\$][\\w\\$]*)([^\\w\\$][\\s\\S]*|$)");

    private Pattern reg;

    private int precedence;

    Token(String reg, int precedence) {
        this.reg = Pattern.compile(reg);
        this.precedence = precedence;
    }

    Token(String reg) {
        this(reg, 0);
    }

    public String match(String text) {
        Matcher matcher = reg.matcher(text);
        if (!matcher.matches()) {
            return null;
        }
        return matcher.group(1);
    }

    public int getPrecedence() {
        return precedence;
    }
}
