import {setTimeout,print as p} from "sys";
import sysfunc from "sys";

let str ="1234567890";

let str2=str.substring(5,9);

p(str.toString());

let a = sysfunc("hello".substring(1,3));


let obj = {
    a:"abc",
    1:"123",
    "asd":{a}
};

String.prototype.a = "prototype_a"

p(String.prototype)
p(String.prototype.a)
p(String.prototype.b)
p(str.a)
p(str.b)