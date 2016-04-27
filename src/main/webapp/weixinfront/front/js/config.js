//var host = 'http://127.0.0.1:8080/';
//var rootPath = 'ordermanage/';
//var host = 'http://jansonweixin.ngrok.cc/';
//var rootPath ='ordermanage/';
var host = 'http://www.xmdream.com.cn/';
var rootPath ='ordermanage/';

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
//根据ID作废订单
var cancelOrderByIdUrl=host+rootPath+'weixin/weixinOrder/admin/cancel';
//根据ID作废订单
var deliverOrderByIdUrl=host+rootPath+'weixin/weixinOrder/admin/deliver';
//获取根据时间所有订单明细
var getOrderByBeginAndEndUrl=host+rootPath+'weixin/weixinOrder/admin/getOrderByBeginAndEnd';

//保存订单信息
var saveOrderUrl=host+rootPath+'weixin/weixinOrder/save';
//获取订单
var getorderTableByIdUrl=host+rootPath+'weixin/weixinOrder/findById';

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
//根据ID获取员工信息
var getEmployByIdUrl=host+rootPath+'weixin/adminUser/findById';
//更新员工信息
var updateEmployUrl=host+rootPath+'weixin/adminUser/update';

//获取个人信息
var getMyInfoUrl=host+rootPath+'weixin/userInfo/myInfo';
//判断个人信息是否填满
var getMyInfoisFullUrl=host+rootPath+'weixin/userInfo/isFull';

//获取配件信息
var getPartsInfoListUrl=host+rootPath+'weixin/parts/partsInfo/list';
//保存配件信息
var savePartsInfoUrl=host+rootPath+'weixin/parts/partsInfo/save';
//删除配件信息
var deletePartsInfoUrl=host+rootPath+'weixin/parts/partsInfo/delete';
//根据ID获取配件信息
var getPartsInfoByIdUrl=host+rootPath+'weixin/parts/partsInfo/findById';
//更新配件信息
var updatePartsInfoUrl=host+rootPath+'weixin/parts/partsInfo/update';



function getWebRootPath() {
    var webroot = document.location.href;
    webroot = webroot.substring(webroot.indexOf('//') + 2, webroot.length);
    webroot = webroot.substring(webroot.indexOf('/') + 1, webroot.length);
    webroot = webroot.substring(0, webroot.indexOf('/'));
    var rootpath = "/" + webroot;
    return rootpath;
}

Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};
GetQueryString=function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
        return  unescape(r[2]);
    return null;
};
