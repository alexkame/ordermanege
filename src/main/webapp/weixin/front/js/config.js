var host = 'http://127.0.0.1:8080/';
var rootPath = 'ordermanage/';

//管理员登陆
var adminLoginUrl=host+rootPath+'weixin/adminUser/login';
//管理员是否登陆
var isAdminLoginUrl=host+rootPath+'weixin/adminUser/islogin';
//获取订单
var orderTableUrl=host+rootPath+'weixin/weixinOrder/findALlByUser';
//管理员获取未完成订单
var undoneOrderUrl=host+rootPath+'weixin/weixinOrder/admin/undoneOrder';
//获取已完成订单
var doneOrderUrl=host+rootPath+'weixin/weixinOrder/admin/doneOrder';
//根据ID删除订单
var deleteOrderByIdUrl=host+rootPath+'weixin/weixinOrder/admin/delete';

//获取客户信息
var getCustmerUrl=host+rootPath+'weixin/weixinCustomer/admin/findALl';

//根据ID获取客户信息
var getCustmerByIdUrl=host+rootPath+'weixin/weixinCustomer/admin/findById';
//根据ID获取客户信息
var saveCustmerByAdminUrl=host+rootPath+'weixin/weixinCustomer/admin/save';
//根据ID删除客户信息
var deleteCustmerByIdUrl=host+rootPath+'weixin/weixinCustomer/admin/delete';

//获取员工信息
var getEmployListUrl=host+rootPath+'weixin/adminUser/findALl';
//保存员工信息
var saveAdminUserUrl=host+rootPath+'weixin/adminUser/save';
//删除员工信息
var deleteAdminUserUrl=host+rootPath+'weixin/adminUser/delete';
//根据ID获取客户信息
var getEmployByIdUrl=host+rootPath+'weixin/adminUser/findById';

function getWebRootPath() {
    var webroot = document.location.href;
    webroot = webroot.substring(webroot.indexOf('//') + 2, webroot.length);
    webroot = webroot.substring(webroot.indexOf('/') + 1, webroot.length);
    webroot = webroot.substring(0, webroot.indexOf('/'));
    var rootpath = "/" + webroot;
    return rootpath;
}
