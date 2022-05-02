import {readFileSync,readFile,print} from 'sys';

// print(readFileSync("./README.md"));

readFile("./README.md").then(data=>{
    print("aaa");
    print(data);
});
print("hhh");