package org.laziji.commons.js.model.node;

import org.laziji.commons.js.constant.Token;
import org.laziji.commons.js.exception.CompileException;
import org.laziji.commons.js.model.TokenUnit;
import org.laziji.commons.js.model.node.section.SectionNode;
import org.laziji.commons.js.util.TokenUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DocNode extends BasePlanNode {

    private BiFunction<Node, Node, Node> supplier;

    public DocNode() {
        super(null);
        this.supplier = (self, pre) -> new SectionNode(self);
    }

    public DocNode(Function<Node, Node> content) {
        super(null);
        this.supplier = (self, pre) -> content.apply(self);
    }

    @Override
    protected List<BiFunction<Node, Node, Node>> getPlan() {
        return Arrays.asList(
                supplier,
                (self, pre) -> new UnitNode(self, Token.EOF)
        );
    }

    @Override
    public String toString(int depth, boolean start) {
        return current[0].toString(0, start);
    }

    public void compile(String text) throws Exception {
        List<TokenUnit> tokens = TokenUtils.parseTextToTokens(text);
        Node p = this.init();
        for (TokenUnit token : tokens) {
            p = p.append(token);
        }
        if (!this.isDone()) {
            throw new CompileException();
        }
    }
}
