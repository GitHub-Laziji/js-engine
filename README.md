# JS脚本引擎

## 生成语法树
```java
class Test{
    public static void main(String[] args){
        ScriptManager manager = new ScriptManager(true);
        DocNode doc = manager.compile("let a=1+2,b=3,c=\"string\",d=a*(b+c/2),func=function(){};");
        System.out.println(doc.toString());
    }
}
```

## 运行
```java
class Test{
    public static void main(String[] args){
        ScriptManager manager = new ScriptManager(true);
        manager.addInternalModules("sys", new SystemModuleValue());
        manager.eval(
            "import sysfunc from \"sys\";                     \n" +
            "import {setTimeout,print as p} from \"sys\";     \n" +
            "let a = sysfunc(\"hello\".substring(1,3));       \n" +
            "setTimeout(function(){                           \n" +
            "    p(\"setTimeout delay 3000ms\");              \n" +
            "},3000);                                         \n" +
            "let obj = {                                      \n" +
            "    a:\"abc\",                                   \n" +
            "    1:\"123\",                                   \n" +
            "    \"asd\":{a}                                  \n" +
            "};                                               \n"
        );
        manager.loop();
        System.out.println(manager.getContexts().peek().toSimpleString());
    }
}
```
