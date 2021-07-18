let a = 123 + 4567, b = a + 1, c;
c = 3;
c += a;
c = c * (2 + 1);

function func(a,b,c) {
    return a*(b+c);
}

let re = func(2,3,4);

let str="123";

let stradd = str+"["+a+"]";

str.a="a";

let sw = 44;
let p = "["+str.a+"]";

let i=10;
let sum=0;

while(i>0){
    sum+=i;
    i=i-1;
}

if(sw==1){
    p="<<"+str.a+">>1"+sum;
}else if(sw==2){
    p="<<"+str.a+">>2"+sum;
}else{
    p="<<"+str.a+">>else"+sum;
}