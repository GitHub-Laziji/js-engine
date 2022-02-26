import {print as p} from "sys";

let a = new Promise(function(resolve,reject){
    resolve("abc");
});

a.then(function(data){
    p(data);
});