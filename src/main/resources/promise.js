import {setTimeout,print as p} from "sys";

let a = new Promise(function(resolve,reject){
    p("11111")
    setTimeout(function(){
        resolve("abc");
    },3000);
}).then(function(data){
    p(data);
});
