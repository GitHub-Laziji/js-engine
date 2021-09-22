import {setTimeout,print as p} from "sys";
import sysfunc from "sys";

let str ="1234567890";


String.prototype.a = "prototype_a"

p(String.prototype)
p(String.prototype.a)
p(String.prototype.b)
p(str.a)
p(str.b)

p(str.substring(2,5))

p("indexOf:"+str.indexOf("56"))
p("indexOf:"+str.indexOf("ww"))


p("constructor:"+str.constructor)