import sysfunc from "sys";
import {setTimeout,print as p} from "sys";

let a = sysfunc("hello".substring(1,3));

setTimeout(function(){
    p("setTimeout delay 3000ms");
},3000);

let obj = {
    a:"abc",
    1:"123",
    "asd":{a}
};