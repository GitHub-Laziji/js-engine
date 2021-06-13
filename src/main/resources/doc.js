
const ContextmenuConstructor = Vue.extend(Contextmenu);
Vue.component(COMPONENT_NAME, Submenu);

function install(Vue) {
    let lastInstance = null;
    const ContextmenuProxy = function (options) {
        let instance =    new ContextmenuConstructor();
        instance.items = options.items;
        instance.position.x = options.x || 0;
        instance.position.y = options.y || 0;
        if (options.event) {
            instance.position.x = options.event.clientX;
            instance.position.y = options.event.clientY;
        }
        instance.customClass = options.customClass;
        options.minWidth && (instance.style.minWidth = options.minWidth);
        options.zIndex && (instance.style.zIndex = options.zIndex);
        ContextmenuProxy.destroy();
        lastInstance = instance;
        instance.$mount();
    };
    ContextmenuProxy.destroy = function () {
        if (lastInstance) {
            lastInstance.$destroy();
            lastInstance = null;
        }
    };
    Vue.prototype.$contextmenu = ContextmenuProxy;
}
const JSON= {
  a:123,
  "asd":"sad",
  [dd]:"asdas",
    1:21323
};
if (window && window.Vue) {
    install(window.Vue);
}
