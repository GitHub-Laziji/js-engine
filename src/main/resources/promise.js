import {setTimeout,print as p} from "sys";

let a = new Promise((resolve,reject)=>{
    p("11111")
    setTimeout(()=>{
        resolve("abc");
    },3000);
}).then(data=>{
    p(data);
});
