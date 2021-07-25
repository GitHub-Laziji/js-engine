import sysfunc from "sys";
import {setTimeout,print as p} from "sys";

let a = sysfunc("hello");

setTimeout(function(){
    p("setTimeout delay 3000ms");
},3000);