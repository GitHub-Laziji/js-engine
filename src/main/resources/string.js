import {setTimeout,print as p} from "sys";
import sysfunc from "sys";

let str ="1234567890";

let str2=str.substring(5,9);

p(str.toString());

let a = sysfunc("hello".substring(1,3));

setTimeout(function(){
    p("setTimeout delay 3000ms");
},3000);

let obj = {
    a:"abc",
    1:"123",
    "asd":{a}
};