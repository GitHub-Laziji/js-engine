import {setTimeout,print as p} from "sys";
import sysfunc from "sys";

let str ="1234567890";


String.prototype.a = "prototype_a"
String.prototype.b  = function (){
    this.abc = "abc"
    this.abc=this.abc+"---"
}
p(String.prototype)
p(String.prototype.a)
p(String.prototype.b)
p(str.a)
p(str.b)

//p(str.substring(2,5))

//p("indexOf:"+str.indexOf("56"))
p("indexOf:"+"ddd"+str.indexOf("56"))


p("constructor:"+str.constructor)

let obj = new String.prototype.b()
p(obj.abc)