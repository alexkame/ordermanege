var host = 'http://127.0.0.1:8080/';
var rootPath = 'ordermanage/';

//管理员登陆
var adminLoginUrl=host+rootPath+'weixin/adminUser/login';
//管理员是否登陆
var isAdminLoginUrl=host+rootPath+'weixin/adminUser/islogin';
//获取订单
var orderTableUrl=host+rootPath+'weixin/weixinOrder/findALlByUser';
//获取订单
var undoneOrderUrl=host+rootPath+'weixin//weixinOrder/admin/undoneOrder';

function getWebRootPath() {
    var webroot = document.location.href;
    webroot = webroot.substring(webroot.indexOf('//') + 2, webroot.length);
    webroot = webroot.substring(webroot.indexOf('/') + 1, webroot.length);
    webroot = webroot.substring(0, webroot.indexOf('/'));
    var rootpath = "/" + webroot;
    return rootpath;
}
