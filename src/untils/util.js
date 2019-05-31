//获取cookie、
export function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    console.log(document.cookie);
    if (arr = document.cookie.match(reg))
        return (arr[2]);
    else
        return null;
}

//设置cookie,增加到vue实例方便全局调用
export function setCookie (c_name, value, expiredays=7) {
    var exdate = new Date();

    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate.toGMTString());
};

//删除cookie
export function delCookie (name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
};

export function delAllCookie() {
    let keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    if(keys) {
        for(let i = keys.length - 1;i > -1; i--) {
            document.cookie = keys[i] + '=0;expires=' + new Date(0).toUTCString() + ';path=/;domain=.' + document.domain.split('.').slice(-2).join('.')
        }
    }
}

export function getUrlKey(name) {
    return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) || [, ""])[1].replace(/\+/g, '%20')) || null
}