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

let sum2 = 0;

for(let ii=1;ii<=10;ii=ii+1){
    sum2+=ii;
}

let i3=10;
let sum3=0;

do{
    if(i3==10){
        i3=i3-1;
        continue;
    }
    sum3+=i3;
    i3=i3-1;

    if(i3<8){
        break;
    }
}while(i3>0);


let arr = [1,2,"333aa",["a","b","c"],i3];

let arr_2=arr[3][1];

let arrLength = arr.length;

let farr= [1,3,5,7,9];
let fs = 0;
for(let fi=0;fi<farr.length;fi+=1){
    fs+=farr[fi];
}