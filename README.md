# JS脚本引擎
Java实现支持`ES6`语法的JS脚本引擎

## 生成语法树 并输出格式化代码
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

## 运行脚本
以下示例为运行快排算法（运行环境线程隔离）
### js脚本
```js
function sort(arr, i, j) {
    if (i >= j) {
        return;
    }
    let p = i, q = j;
    let temp = arr[p];
    while (p < q) {
        while (p < q && arr[q] >= temp) {
            q-=1;
        }
        arr[p] = arr[q];
        while (p < q && arr[p] <= temp) {
            p+=1;
        }
        arr[q] = arr[p];
    }
    arr[q] = temp;
    sort(arr, i, q - 1);
    sort(arr, q + 1, j);
}

let arr = [234, 57, 12, 123, 346, 1234, 2];

sort(arr, 0, arr.length - 1);
```

### 示例代码
```java
class Test{
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