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
    COMMA("^(,)[\\s\\S]*"),
    LAMBDA("^(\\=\\>)[\\s\\S]*"),
    BRACKET_SML_OPEN("^(\\()[\\s\\S]*"),
    BRACKET_SML_CLOSE("^(\\))[\\s\\S]*"),
    BRACKET_MID_OPEN("^(\\[)[\\s\\S]*"),
    BRACKET_MID_CLOSE("^(\\])[\\s\\S]*"),
    BRACKET_BIG_OPEN("^(\\{)[\\s\\S]*"),
    BRACKET_BIG_CLOSE("^(\\})[\\s\\S]*"),
    REMARK("^(/\\*[\\s\\S]*?\\*/)[\\s\\S]*"),
    REMARK_OF_LINE("^(//.*)[\\n$][\\s\\S]*"),

    CONST("^(const)([^\\w\\$].*|$)"),
    VAR("^(var)([^\\w\\$].*|$)"),
    LET("^(let)([^\\w\\$].*|$)"),
    FUNCTION("^(function)([^\\w\\$].*|$)"),
    FOR("^(for)([^\\w\\$].*|$)"),
    WHERE("^(where)([^\\w\\$].*|$)"),
    DO("^(do)([^\\w\\$].*|$)"),
    BREAK("^(break)([^\\w\\$].*|$)"),
    RETURN("^(return)([^\\w\\$].*|$)"),
    CONTINUE("^(continue)([^\\w\\$].*|$)"),
    SYNC("^(sync)([^\\w\\$].*|$)"),
    AWAIT("^(await)([^\\w\\$].*|$)"),
    YIELD("^(yield)([^\\w\\$].*|$)"),
    NEW("^(new)([^\\w\\$].*|$)"),
    IMPORT("^(import)([^\\w\\$].*|$)"),
    AS("^(as)([^\\w\\$].*|$)"),
    EXPORT("^(export)([^\\w\\$].*|$)"),
    DEFAULT("^(default)([^\\w\\$].*|$)"),
    CLASS("^(class)([^\\w\\$].*|$)"),
    STATIC("^(static)([^\\w\\$].*|$)"),
    PRIVATE("^(private)([^\\w\\$].*|$)"),
    PUBLIC("^(public)([^\\w\\$].*|$)"),
    CONSTRUCTOR("^(constructor)([^\\w\\$].*|$)"),

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
    ADD("^(\\+)([^\\+].*|$)"),
    SUB("^(\\-)([^\\-].*|$)"),
    MUL("^(\\*)[\\s\\S]*"),
    DIV("^(\\/)[\\s\\S]*"),
    MOD("^(\\%)[\\s\\S]*"),
    AND("^(\\&\\&)[\\s\\S]*"),
    OR("^(\\|\\|)[\\s\\S]*"),
    NON("^(\\!)[\\s\\S]*"),
    EQUAL("^(\\=\\=)([^\\=].*|$)"),
    ABS_EQUAL("^(\\=\\=\\=)[\\s\\S]*"),
    UNEQUAL("^(\\!\\=)([^\\=].*|$)"),
    ABS_UNEQUAL("^(\\!\\=\\=)([^\\=].*|$)"),
    GT("^(\\>)[\\s\\S]*"),
    GT_EQUAL("^(\\>\\=)[\\s\\S]*"),
    LT("^(\\<)[\\s\\S]*"),
    LT_EQUAL("^(\\<\\=)[\\s\\S]*"),
    BIT_AND("^(\\&)([^\\&].*|$)"),
    BIT_OR("^(\\|)([^\\|].*|$)"),
    BIT_XOR("^(\\^)[\\s\\S]*"),
    ASSIGNMENT("^(\\=)([^\\=].*|$)"),

    STRING("^(\"(\\\\.|[^\"])*\"|'(\\\\.|[^'])*')[\\s\\S]*"),
    NUMBER("^(\\d+(\\.\\d+)?)[\\s\\S]*"),
    BOOLEAN("^(true|false)([^\\w\\$].*|$)"),
    REGEXP("^(/(\\\\.|[^/])+/[a-zA-Z]?)[\\s\\S]*"),
    UNDEFINED("^(undefined)([^\\w\\$].*|$)"),
    NULL("^(null)([^\\w\\$].*|$)"),
    NAME("^([a-zA-Z_\\$][\\w\\$]*)([^\\w\\$].*|$)");

    private Pattern reg;

    Token(String reg) {
        this.reg = Pattern.compile(reg);
    }

    public String match(String text) {
        Matcher matcher = reg.matcher(text);
        if (!matcher.matches()) {
            return null;
        }
        return matcher.group(1);
    }

}
