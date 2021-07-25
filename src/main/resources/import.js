import sysfunc from "sys";
import {setTimeout,print} from "sys";

let a = sysfunc("hello");

setTimeout(function(){
    print("setTimeout delay 3000ms");
},3000);