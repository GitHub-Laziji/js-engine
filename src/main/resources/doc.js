import "asad";
import a from "asd";
import *    as ss from "asdas";
import   { asdas,sadd,dsd as dd } from "asdas";
const ContextmenuConstructor = Vue.extend(Contextmenu);
Vue.component(COMPONENT_NAME, Submenu);

/*@param Vue*/
function install(Vue) {
    let lastInstance = null;
    const
        ContextmenuProxy = function (options) {
        let instance =
            new ContextmenuConstructor();
        instance.items = options.items;
//             注解注解注解注解注解注解注解注解注解
        instance.position.x
            = options.x || 0;

        /*asd
         asdasd
         asdasd
         */
            /*
            asda
            */
        instance.position.y
            = options.y || 0;
        if (options.event) {
            instance.position.x
                = options.event
                .clientX;
            instance.position.y
                = options.event.clientY;
        }
        instance.customClass = options.customClass;
        options.minWidth && (instance.style.minWidth = options.minWidth);
        options.zIndex && (instance.style.zIndex = options.zIndex);
        ContextmenuProxy
            .destroy();
        lastInstance
            = instance;
        instance
            .$mount();
    };
    ContextmenuProxy.destroy = function () {
        if (lastInstance) {
            lastInstance.$destroy();
            lastInstance = null;
        }
        return;
    };
    Vue.prototype.$contextmenu = ContextmenuProxy;
}
const JSON= {
  a:123,
  "asd":"sad",
  [dd]:"asdas",
    1:21323,
    func,
    b:33
};
if (window && window.Vue) {
    install(window.Vue);
} while(i--){let a=0;break;}

function asdasdsa(){
    let i=1;
    i++;
    i--;
    -i++;
    ++i;
    --i;
    - ++i;

    const arr = ["asdas,232",21321,{}];
    const func4=(a,b)=>{return a+b;
    };
    const func5=()=>{return a+b;
    };
    const func6=a=>a+1;
    function func7(){}

    do{
        break;
    }while(true);

    if(a+b){c++;
    }else{  b++;
    }

    if(a+b){
        c++;
    }else if(true){ b++;
    }
    let a,b,c,d=a+n,e=1+2
    +(3 *4)
    a+=a+b+c;
    a+(b=c)+d;
    a=b=c=d+asd;
    for(let i=0;i<100;i++){break;
    }

    for(;;){}

    for(a of [1,2,3]){break;
    }

    for(let a   of  [1,2,3] ){}

    for(let a   in  [1,2,3] ){};
    return 1+2;
}
export let asd=213,b=2;

export default {
    install:install
};

function f() {};

//a