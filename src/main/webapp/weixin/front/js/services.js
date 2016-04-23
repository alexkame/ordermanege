angular.module('myApp.services', ['ngResource'])
    .service('webService',['$http',function($http){

      this.do = function(url,params){
        console.log("url:"+url);
        console.log(params);
        params = params == null ? '' : aesEncrypt(JSON.stringify(params));

        return $http({
          method : 'post',
          url : url,
          timeout : 7000,
          params : {params:params}
        });
      }
    }])
    .service('webServiceLong',['$http',function($http){

      this.do = function(url,params){
        console.log("url:"+url);
        params = params == null ? '' : aesEncrypt(JSON.stringify(params));

        return $http({
          method : 'post',
          url : url,
          timeout : 20000,
          params : {params:params}
        });
      }
    }])
.factory('Order', function($rootScope,$resource,$http,webService) {

  //获取客户订单列表数据
  var cusOrderObj=$resource('resource/order.json');
  //获取所有未完成订单列表数据
  var undoneOrderDObj=$resource('resource/order.json');
  //获取所有完成订单数据
  var doneOrderDObj=$resource('resource/order.json');
  //获取根据订单号获取订单明细
  var detailObj=$resource('resource/order.json');
  //获取所有完成订单瓦片数据汇总
  var doneOrderTotalItemObj=$resource('resource/orderTotalItems.json');
  //获取所有完成订单配件数据汇总
  var doneOrderTotalFitObj=$resource('resource/orderTotalFit.json');

  //获取配件数据
  var fitting=$resource('resource/fits.json');

  //获取客户信息
  var customerObj=$resource('resource/customer.json');

  //获取客户信息
  var employObj=$resource('resource/employ.json');
  //获取配件信息
  var fittingObj=$resource('resource/fits.json');

  return {
    //返回客户订单列表数据
    getCusOrderList:function(){
        return cusOrderObj.query();

    },
    //返回所有未完成订单列表数据
    getUndoneOrderDList:function(){
      return undoneOrderDObj.query();
    },
    //返回所有完成订单列表数据
    getDoneOrderDList:function(){
      return doneOrderDObj.query();
    },
    //返回根据订单号获取订单明细
    getDetail:function(){
      return detailObj.query();
    },
    //返回所有完成订单瓦片数据汇总
    getDoneOrderTotalItems:function(bDate,eDate){
      //bDate开始日期 eDate结束日期
      return doneOrderTotalItemObj.query();
    },
    //返回所有完成订单配件数据汇总
    getDoneOrderTotalFits:function(bDate,eDate){
      //bDate开始日期 eDate结束日期
      return doneOrderTotalFitObj.query();
    },
    //获得配件
    getFitting:function(){
      return fitting.query();
    },
    //删除订单
    remove: function(order) {
      order.splice(order.indexOf(order), 1);
    },
    //获得客户信息
    getCustmer:function(){
      return customerObj.query();
    },
    //获得员工列表信息
    getEmploy:function(){
      return employObj.query();
    },
    getFitting:function(){
      return fittingObj.query();
    },
    //
  };
});
