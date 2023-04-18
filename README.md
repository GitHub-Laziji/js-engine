# JS脚本引擎
![](https://img.shields.io/github/languages/top/github-laziji/js-engine.svg?style=flat)
![](https://img.shields.io/github/stars/gitHub-laziji/js-engine.svg?style=social)

使用Java实现的JS脚本引擎

- 支持解析js脚本生成语法树
- 支持运行完整js脚本
- 支持安全模式运行单行表达式
- 支持设置超时时间

打包后命令行运行
```text
git clone https://github.com/GitHub-Laziji/js-engine.git
cd js-engine
mvn package
cd target

java -jar ./js-engine-1.0-SNAPSHOT-jar-with-dependencies.jar ./test.js
```
或者安装后直接引入依赖
```text
git clone https://github.com/GitHub-Laziji/js-engine.git
cd js-engine
mvn install
```
```xml
<dependency>
    <groupId>org.laziji.commons</groupId>
    <artifactId>js-engine</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## 1. 生成语法树 并输出格式化代码
### 示例代码
```java
class Test{
    public static void main(String[] args){
        Top.init();
        DocNode doc = Top.compile("let      a=1+2,b=3,c=\"string\",d=a*(b+c/2),func=function(){};");
        System.out.println(doc);
    }
}
```
### 输出
```text
let a = 1 + 2, b = 3, c = "string", d = a * (b + c / 2), func = function () {

}
```

## 2. 运行完整脚本
以下示例为运行快排算法（运行环境线程隔离）

### 示例代码
```java
class Test{
    /**
     * function sort(arr, i, j) {
     *   if (i >= j) {
     *     return;
     *   }
     *   let p = i, q = j;
     *   let temp = arr[p];
     *   while (p < q) {
     *     while (p < q && arr[q] >= temp) {
     *       q-=1;
     *     }
     *     arr[p] = arr[q];
     *     while (p < q && arr[p] <= temp) {
     *       p+=1;
     *     }
     *     arr[q] = arr[p];
     *   }
     *   arr[q] = temp;
     *   sort(arr, i, q - 1);
     *   sort(arr, q + 1, j);
     * }
     *
     * let arr = [234, 57, 12, 123, 346, 1234, 2];
     *
     * sort(arr, 0, arr.length - 1);
     */
    public static void main(String[] args){
        Top.init();
        Top.eval("function sort(arr, i, j) {\n" +
                "    if (i >= j) {\n" +
                "        return;\n" +
                "    }\n" +
                "    let p = i, q = j;\n" +
                "    let temp = arr[p];\n" +
                "    while (p < q) {\n" +
                "        while (p < q && arr[q] >= temp) {\n" +
                "            q-=1;\n" +
                "        }\n" +
                "        arr[p] = arr[q];\n" +
                "        while (p < q && arr[p] <= temp) {\n" +
                "            p+=1;\n" +
                "        }\n" +
                "        arr[q] = arr[p];\n" +
                "    }\n" +
                "    arr[q] = temp;\n" +
                "    sort(arr, i, q - 1);\n" +
                "    sort(arr, q + 1, j);\n" +
                "}\n" +
                "\n" +
                "let arr = [234, 57, 12, 123, 346, 1234, 2];\n" +
                "\n" +
                "sort(arr, 0, arr.length - 1);");
        Top.loop();
        System.out.println(Top.getThreadLocalTop().getMainContexts().getContexts().peek().toSimpleString());
    }
}
```

### 输出
```text
arr: [2, 12, 57, 123, 234, 346, 1234]
sort: [object Object]
```


## 3. 运行单行表达式
该模式下只支持单行表达式 并且无法使用for、while、function、lambda、import关键字

### 示例代码
```java
class Test{
    public static void main(String[] args){
        Top.init();
        System.out.println(Top.exprEval("'hello '+(1*2*3*4)"));
    }
}
```
### 输出
```text
hello 24
```



## 4. 设置超时时间
通过`Top.getThreadLocalTop().setOvertime(100L);`设置超时时间，单位毫秒

### 示例代码
```java
class Test{
    public static void main(String[] args){
        Top.init();
        Top.getThreadLocalTop().setOvertime(100L);
        Top.addInternalModules("sys", new SystemModuleValue());
        Top.eval("import { print } from \"sys\";\n" +
                "\n" +
                "let i=1;\n" +
                "while(true){\n" +
                "    print(i++);\n" +
                "}");
        Top.loop();
    }
}
```
### 输出
```text
1
2
3
...

org.laziji.commons.js.exception.RunException: Run timeout.
...
```