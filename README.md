# js-engine

解析JS代码
```java
class Test{
    public static void main(String[] args){
        String text = IOUtils.resourceToString("/doc.js", Charsets.UTF_8);
        List<TokenUnit> units = TokenUtils.parseTextToTokens(text);
        units.add(new TokenUnit(Token.EOF, null));
        DocNode node = new DocNode();
        Node p = node.init();
        for (TokenUnit unit : units) {
            p = p.append(unit);
        }
        System.out.println(node.isDone());
        System.out.println(node.toString());      
    }
}
```
