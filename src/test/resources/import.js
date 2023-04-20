import * as sys from "sys";
import {setTimeout,print as p} from "sys";

sys.print("yyy");

let a = "hello".substring(1,3);

sys.setTimeout(function(){
    p("setTimeout delay 3000ms");
},3000);

let obj = {
    a:"abc",
    1:"123",
    "asd":{a}
};