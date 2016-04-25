angular.module('myApp.controllers', ['ngResource'])

    //订单
.controller('AdminOrderController',['$scope','Order','$location','$ionicSideMenuDelegate','webService','$ionicPopup',
    function($scope,Order,$location,$ionicSideMenuDelegate,webService,$ionicPopup) {

        //获取所有未完成订单数据
        webService.do(undoneOrderUrl, {})
            .success(function (data) {
                $scope.undoneOrderD=data;
            }).error(function (data, status) {
               alert("获取数据失败!");
            });

        //获取所有未完成订单数据
        webService.do(doneOrderUrl, {})
            .success(function (data) {
                $scope.doneOrderD=data;
            }).error(function (data, status) {
                alert("获取数据失败!");
           });


        //删除订单
        $scope.remove = function(order) {

            console.log(order);
            var alertPopup;
            webService.do(deleteOrderByIdUrl, {
                id:order.id
            }) .success(function (data) {
                console.log(data);
                if(data.code){
                    alertPopup = $ionicPopup.alert({
                        title: '删除成功',
                        template: '您的信息删除成功！'
                    });
                    $scope.undoneOrderD.splice($scope.undoneOrderD.indexOf(order), 1);
                }else{
                    alertPopup = $ionicPopup.alert({
                        title: '删除失败',
                        template: data.msg
                    });
                }
            }).error(function (data, status) {
                alertPopup = $ionicPopup.alert({
                    title: '删除失败',
                    template: '数据连接错误！'
                });
            });

        };

        //切换完成订单的统计及明细显示
        $scope.showList={
            list1:true,
            list2:false
        };
        $scope.button1Color_bg=" button-outline"
        $scope.showDetail=function(state){
            if(state){
                $scope.showList.list1=true;
                $scope.showList.list2=false;
                $scope.button1Color_bg=" button-outline"
                $scope.button2Color_bg=""
            }else{
                $scope.showList.list1=false;
                $scope.showList.list2=true;
                $scope.button1Color_bg=""
                $scope.button2Color_bg="button-outline";
                $scope.selectDate.bDate=new Date();
                $scope.selectDate.eDate=new Date();
            }
        };
        //改变选择框的颜色
        $scope.select1Color_bg="button-outline";
        $scope.showDate={
            selectShow:false,
            tipShow:true
        }
        $scope.selectDate={
            bDate:new Date(),
            eDate:new Date()
        };
        //获取所有订单瓦片统计
        $scope.doneOrderItemTotal=Order.getDoneOrderTotalItems($scope.selectDate.bDate,$scope.selectDate.eDate);
        //获取所有订单配件统计
        $scope.doneOrderFitTotal=Order.getDoneOrderTotalFits($scope.selectDate.bDate,$scope.selectDate.eDate);
        $scope.selectResult=function(inum){
            //初始化隐藏日期选择框
            $scope.showDate.tipShow=true;
            $scope.showDate.selectShow=false;
            //选择今天
            if(inum==1){
                $scope.select1Color_bg="button-outline";
                $scope.select2Color_bg="";
                $scope.select3Color_bg="";
                $scope.select4Color_bg="";
                $scope.selectDate.bDate=new Date();
                $scope.selectDate.eDate=new Date();
            }
            //选择周
            else if(inum==2){
                $scope.select1Color_bg="";
                $scope.select2Color_bg="button-outline";
                $scope.select3Color_bg="";
                $scope.select4Color_bg="";
                $scope.selectDate.bDate=new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate()-7);
                $scope.selectDate.eDate=new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate());
            }
            //选择月
            else if(inum==3){
                $scope.select1Color_bg="";
                $scope.select2Color_bg="";
                $scope.select3Color_bg="button-outline";
                $scope.select4Color_bg="";
                $scope.selectDate.bDate=new Date(new Date().getFullYear(),new Date().getMonth(),1);
                $scope.selectDate.eDate=new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate());
            }
            else if(inum==4){
                $scope.select1Color_bg="";
                $scope.select2Color_bg="";
                $scope.select3Color_bg="";
                $scope.select4Color_bg="button-outline";
                $scope.showDate.selectShow=false;
                console.log($scope.selectDate.eDate)
            }
            //获取所有订单瓦片统计
            $scope.doneOrderItemTotal=Order.getDoneOrderTotalItems($scope.selectDate.bDate,$scope.selectDate.eDate);
            //获取所有订单配件统计
            $scope.doneOrderFitTotal=Order.getDoneOrderTotalFits($scope.selectDate.bDate,$scope.selectDate.eDate);
        };
        //显示自定义选择界面
        $scope.selectDate=function(){
            $scope.showDate.tipShow=false;
            $scope.showDate.selectShow=true;
        };





    }])


    //订单
  .controller('orderController',['$scope','Order','$location','$ionicSideMenuDelegate','webService',
      function($scope,Order,$location,$ionicSideMenuDelegate,webService) {

      webService.do(orderTableUrl, {})
          .success(function (data) {
              $scope.data=data;
          }).error(function (data, status) {
              alert("获取数据失败!");
         });


      //删除订单
      $scope.remove = function(order) {

         var alertPopup;
          webService.do(deleteOrderByIdUrl, {
             id:order.id
          }) .success(function (data) {
              console.log(data);
              if(data.code){
                  alertPopup = $ionicPopup.alert({
                      title: '删除成功',
                      template: '您的信息删除成功！'
                  });
                  $scope.undoneOrderD.splice($scope.undoneOrderD.indexOf(order), 1);
              }else{
                  alertPopup = $ionicPopup.alert({
                      title: '删除失败',
                      template: data.msg
                  });
              }
          }).error(function (data, status) {
              alertPopup = $ionicPopup.alert({
                  title: '删除失败',
                  template: '数据连接错误！'
              });
          });

      };
      //跳转到订单新增界面
      $scope.add=function(){
        $location.path('/orderAdd');
      };
      $scope.leftMenu=function(){
          $ionicSideMenuDelegate.toggleLeft();
      };

      //判断管理员是否登录
      $scope.isAdminLogin=function(){
          webService.do(isAdminLoginUrl, {})
              .success(function (data) {
                  if(data.code==0){
                      $location.path('/admin/adminLogin');
                  }else{
                      $location.path('/admin/adminList');
                  }
              }).error(function (data, status) {
                  $location.path('/tab');
              });
      };

 }])
    //订单明细
    .controller('orderDetailController', function($scope,$location, $stateParams,$ionicModal, Order) {

        var orderID=$stateParams.orderID;
        console.log(orderID);
        //获取订单明细数据
        $scope.detail=Order.getDetail();
        //跳转到订单页面
        $scope.orderPage=function(){
            $location.path('/tab');
        };
        //调用发货对话框
        $ionicModal.fromTemplateUrl("templates/orderProcess_deliver.html",{
            scope:$scope,
            animation:"slide-in-up"
        }).then(function(modal){
            $scope.modal=modal;
        });
        //显示发货确定
        $scope.showDeliver=function(){
            $scope.modal.show();
            $scope.deliver={
                deliverdate: (new Date(new Date().getFullYear(),new Date().getMonth(),new Date().getDate(),new Date().getHours(),new Date().getMinutes()))
            }

        };
        //隐藏发货确定
        $scope.closeDeliver=function(){
            $scope.modal.hide();
        };
        //修改发货时间及状态
        $scope.addDeliver=function(){
            console.log("发货成功"+$scope.deliver.deliverDate);
            $scope.modal.hide();
        }

        //调用作废对话框
        $ionicModal.fromTemplateUrl("templates/orderProcess_cancel.html",{
            scope:$scope,
            animation:"slide-in-up"
        }).then(function(modal){
            $scope.modal2=modal;
        });
        //显示订单作废
        $scope.showCancel=function(){
            $scope.modal2.show();
            $scope.cancelObj={};
        };
        //隐藏订单作废
        $scope.closeCancel=function(){
            $scope.modal2.hide();
        };
        //订单作废，修改订单状态
        $scope.cancelOrder=function(){
            console.log("作废成功"+$scope.cancelObj.cancelReason);
            $scope.modal2.hide();
        }
    })
    //新增订单
    .controller('orderAddController',function($scope,$location,$ionicModal,$anchorScroll,Order){
     //   console.log($scope);
        //跳转到订单管理
        $scope.orderPage=function(){
            $location.path('/tab');
        };
        //调用添加瓦片的modal
        $ionicModal.fromTemplateUrl("templates/order_addItem.html",{
            scope:$scope,
            animation:"slide-in-up"
        }).then(function(modal){
            $scope.modal=modal;
        });
       //显示瓦片模板
        $scope.showItem=function(){
            $scope.modal.show();
            $scope.itemdata={};
            $scope.myColor={
                show:true
            };
            $scope.myFH={
                show:true
            };
        };
        //隐藏瓦片模板
        $scope.closeItem=function(){
            $scope.modal.hide();
        };
        //新增瓦片
        $scope.items=[];

        $scope.change=function(value){
            console.log(value)
            if(value=='color'){
                $scope.myColor=!false
            }
            if(value=='fh'){
                $scope.myFH=!false
            }
        };
        $scope.addItem=function(){
            console.log(angular.isString($scope.itemdata.nColor))
            if(!angular.isString($scope.itemdata.nColor)){
                console.log("aaa");
               $location.hash("aaa");
                $anchorScroll();
            }
            else if(!angular.isString($scope.itemdata.nTH)){
                $location.hash("bbb");
                $anchorScroll();
            }
            else{
                $scope.items.push($scope.itemdata);
                $scope.modal.hide();
            }

        };
        //调用添加配件的modal
        var fitting=$ionicModal.fromTemplateUrl("templates/order_addFitting.html",{
            scope:$scope,
            animation:"slide-in-up"
        }).then(function(modal){
            $scope.modal2=modal;
            $scope.getFitting=Order.getFitting();
            $scope.myFit;
        });
        //显示配件模板
        $scope.showFitting=function(){
            $scope.modal2.show();
            $scope.fitdata={};

        };
        //隐藏配件模板
        $scope.closeFitting=function(){
            $scope.modal2.hide();
        };
        //添加配件数据
       $scope.fits=[];
        $scope.addFitting=function(){

           // $scope.myValid=$valid;
           console.log($scope.fitdata)
            $scope.fits.push( $scope.fitdata);
            $scope.modal2.hide();
        };
        //新增订单
        $scope.addOrder=function(){

            $location.path('/tab');
        }
    })
    //管理员模块
    .controller('adminController',['$scope','$location','$ionicPopup', 'webService'
        ,function($scope,$location,$ionicPopup,webService){

        //跳转到主页
        $scope.home=function(){
            $location.path('/tab');
        };

        //登录及验证
        $scope.adminlogin=function(){
            var userName=$scope.adminlogin.userName;
            var password=$scope.adminlogin.password;
            webService.do(adminLoginUrl, {
                userName:userName,
                password:password
            }).success(function (data) {
                //账号密码验证失败，则提示用户名或者密码错误
                console.log(data);
                if(data.code==1){
                    console.log("登录成功");
                    $location.path('/admin/adminList');
                } else{
                    var message=data.message;
                    var alertPopup = $ionicPopup.alert({
                        title: '验证失败',
                        template: message
                    });
                }
            }).error(function (data, status) {
                var alertPopup = $ionicPopup.alert({
                    title: '系统出错',
                    template: '系统出错'
                });
            });
        }
    }])
    .controller('cusController',function($scope,$location,$stateParams,$state,$ionicPopup,Order, webService){
        //跳转到主页
        $scope.home=function(){
            $location.path('/tab');
        };
        //获取客户列表信息
        //$scope.customerList=Order.getCustmer();getCustmerUrl
        webService.do(getCustmerUrl, {})
            .success(function (data) {
                $scope.customerList=data;
            }).error(function (data, status) {
                alert("获取数据失败!");
           });

        //保存及校检修改客户信息
        $scope.myInfo={};

        //根据ID获取客户信息
        var customerID=$stateParams.customerID;
        //获取客户列表信息
        webService.do(getCustmerByIdUrl, {id:customerID})
            .success(function (data) {
                console.log(data);
                $scope.myInfo.id=data.id;
                $scope.myInfo.name=data.username;
                $scope.myInfo.phone=data.tel;
                $scope.myInfo.addr=data.address;

            }).error(function (data, status) {
                alert("获取数据失败!");
           });

        $scope.myValid={
            name:false,
            phone:false,
            addr:false
        };
        $scope.save=function(){
            $scope.myValid.name=!angular.isString($scope.myInfo.name);
            $scope.myValid.phone=!angular.isString($scope.myInfo.phone);
            $scope.myValid.addr=!angular.isString($scope.myInfo.addr);
            if(angular.isString($scope.myInfo.name)&&angular.isString($scope.myInfo.phone)&&angular.isString($scope.myInfo.addr)){

                var alertPopup ;
                //保存
                webService.do(saveCustmerByAdminUrl, {
                    id: $scope.myInfo.id,
                    username:$scope.myInfo.name,
                    tel:$scope.myInfo.phone,
                    address:$scope.myInfo.addr
                }) .success(function (data) {
                        console.log(data);
                        if(data.code){
                            alertPopup = $ionicPopup.alert({
                                title: '保存成功',
                                template: '您的信息保存成功！'
                            });
                            $state.go('admin/customer');
                        }else{
                            alertPopup = $ionicPopup.alert({
                                title: '保存失败',
                                template: data.msg
                            });
                        }
                    }).error(function (data, status) {
                        alertPopup = $ionicPopup.alert({
                            title: '保存失败',
                            template: '数据连接错误！'
                        });
                   });
            }
        }
        //删除配件信息
        $scope.remove=function(obj){
            console.log("这里执行删除操作");
            var alertPopup;
            //获取客户列表信息
            webService.do(deleteCustmerByIdUrl, {id:obj.id})
                .success(function (data) {
                    if(data.code){
                        alertPopup = $ionicPopup.alert({
                            title: '删除成功',
                            template: '您的信息删除成功！'
                        });
                        $scope.customerList.splice($scope.customerList.indexOf(obj), 1);
                    }else{
                        alertPopup = $ionicPopup.alert({
                            title: '删除失败',
                            template: data.msg
                        });
                    }
                }).error(function (data, status) {
                    alertPopup = $ionicPopup.alert({
                        title: '删除失败',
                        template: '数据连接失败'
                    });
                });
        }
    })

    //员工信息详情
    .controller('employDetailController',function($scope,$location,$ionicModal,$state,$ionicPopup,Order,webService,$stateParams) {

        var alertPopup;
        //保存及校检修改员工信息
        $scope.employ={};

        //获取员工信息ID
        var employID=$stateParams.employID;

        //获取员工信息
        webService.do(getEmployByIdUrl, {id:employID})
            .success(function (data) {
                console.log(1);
                console.log(data);
                $scope.employ.id=data.id;
                $scope.employ.userName=data.userName;
                $scope.employ.password=data.password;
                $scope.employ.name=data.name;
                $scope.employ.tel=data.tel;
            }).error(function (data, status) {
                return null;
          });

        //保存修改员工信息
        $scope.saveEmploy=function(){

            webService.do(updateEmployUrl, {
                id: $scope.employ.id,
                userName: $scope.employ.userName,
                password: $scope.employ.password,
                name: $scope.employ.name,
                tel: $scope.employ.tel
            }) .success(function (data) {
                    if(data.code) {
                        alertPopup = $ionicPopup.alert({
                            title: '保存成功',
                            template: '您的信息保存成功！'
                        });
                        alertPopup.then(function (res) {
                            console.log('写入保存内容' + $scope.employ);
                        });
                        $state.go('admin/employ');
                    }else{
                        alertPopup = $ionicPopup.alert({
                            title: '保存失败',
                            template: '保存失败！'
                        });
                    }
                }).error(function (data, status) {
                       alertPopup = $ionicPopup.alert({
                        title: '保存失败',
                        template: '保存失败！'
                    });
                });
        }


    })

    .controller('employController',function($scope,$location,$ionicModal,$state,$ionicPopup,Order,webService){

        //弹出框
        var alertPopup;

        //获取客户列表信息
        webService.do(getEmployListUrl, {})
            .success(function (data) {
                console.log(data);
                //获取客户列表信息
                $scope.employList=data;
            }).error(function (data, status) {
                alertPopup = $ionicPopup.alert({
                    title: '数据连接失败',
                    template: '数据连接失败'
                });
            });

        //新增客户，调用对话框
        $ionicModal.fromTemplateUrl("templates/admin/employAdd.html",{
            scope:$scope,
            animation:"slide-in-up"
        }).then(function(modal){
            $scope.modal=modal;
        });
        //显示新增界面
        $scope.showEmploy=function(){
            $scope.modal.show();
            $scope.employ={};
        };
        //关闭新增界面
        $scope.closeEmploy=function(){
            $scope.modal.hide();
        };

        //添加框隐藏
        $scope.$on('modal.hidden', function() {
            //刷新客户信息
            //获取客户列表信息
            webService.do(getEmployListUrl, {})
                .success(function (data) {
                    console.log(data);
                    //获取客户列表信息
                    $scope.employList=data;
                }).error(function (data, status) {
                alertPopup = $ionicPopup.alert({
                    title: '数据连接失败',
                    template: '数据连接失败'
                });
            });
        });

        //新增员工
        $scope.addEmploy=function(){
            //这里执行添加
            console.log("执行添加");

            var userName=$scope.employ.userName;
            var password=$scope.employ.password;
            var name=$scope.employ.name;
            var tel=$scope.employ.tel;
            //保存
            webService.do(saveAdminUserUrl, {
                userName:userName,
                password:password,
                name:name,
                tel:tel
            }) .success(function (data) {
                console.log(data);
                if(data.code){
                    alertPopup = $ionicPopup.alert({
                        title: '保存成功',
                        template: '您的信息保存成功！'
                    });
                    $scope.modal.hide();
                }else{
                    alertPopup = $ionicPopup.alert({
                        title: '保存失败',
                        template: data.msg
                    });
                }
            }).error(function (data, status) {
                alertPopup = $ionicPopup.alert({
                    title: '保存失败',
                    template: '数据连接错误！'
                });
            });
        };
        //删除员工信息
        $scope.remove=function(obj){
            console.log("这里执行删除操作")
            webService.do(deleteAdminUserUrl, {id:obj.id})
                .success(function (data) {
                    if(data.code){
                        alertPopup = $ionicPopup.alert({
                            title: '删除成功',
                            template: '您的信息删除成功！'
                        });
                        $scope.employList.splice($scope.employList.indexOf(obj), 1);
                    }else{
                        alertPopup = $ionicPopup.alert({
                            title: '删除失败',
                            template: data.msg
                        });
                    }
                }).error(function (data, status) {
                alertPopup = $ionicPopup.alert({
                    title: '删除失败',
                    template: '数据连接失败'
                });
            });
        }
    })
    .controller('fittingDetailController',function($scope,$location,$ionicModal,$ionicPopup,$stateParams,$state,Order,webService) {
        var alertPopup;
        //保存及校检修改配件信息
        $scope.Fitting={};

        //获取配件信息ID
        var fittingID=$stateParams.fittingID;

        //获取员工信息
        webService.do(getPartsInfoByIdUrl, {id:fittingID})
            .success(function (data) {
                console.log(data);
                $scope.Fitting.id=data.id;
                $scope.Fitting.name=data.name;
                $scope.Fitting.spec=data.spec;
            }).error(function (data, status) {
                return null;
            });


        //保存修改配件信息
        $scope.saveFitting=function(){

            webService.do(updatePartsInfoUrl, {
                id: $scope.Fitting.id,
                name:$scope.Fitting.name,
                spec:$scope.Fitting.spec
            }) .success(function (data) {
                if(data.code) {
                    alertPopup = $ionicPopup.alert({
                        title: '保存成功',
                        template: '您的信息保存成功！'
                    });
                    alertPopup.then(function (res) {
                        console.log('写入保存内容' + $scope.employ);
                    });
                    $state.go('admin/fitting');
                }else{
                    alertPopup = $ionicPopup.alert({
                        title: '保存失败',
                        template: '保存失败！'
                    });
                }
            }).error(function (data, status) {
                alertPopup = $ionicPopup.alert({
                    title: '保存失败',
                    template: '保存失败！'
                });
            });
        }

    })
    .controller('fittingController',function($scope,$location,$ionicModal,$ionicPopup,Order,webService){
        //保存及校检修改配件
        $scope.Fitting={};

        var alertPopup;
        //获取配件列表信息
        webService.do(getPartsInfoListUrl, {})
            .success(function (data) {
                console.log(data);
                //获取配件列表信息
                $scope.fittingList=data;
            }).error(function (data, status) {
                alertPopup = $ionicPopup.alert({
                    title: '数据连接失败',
                    template: '数据连接失败'
                });
            });

        //添加框隐藏
        $scope.$on('modal.hidden', function() {
            //刷新客户信息
            //获取配件列表信息
            webService.do(getPartsInfoListUrl, {})
                .success(function (data) {
                    console.log(data);
                    //获取配件列表信息
                    $scope.fittingList=data;
                }).error(function (data, status) {
                    alertPopup = $ionicPopup.alert({
                        title: '数据连接失败',
                        template: '数据连接失败'
                    });
                });
        });

        //新增配件，调用对话框
        $ionicModal.fromTemplateUrl("templates/admin/fittingAdd.html",{
            scope:$scope,
            animation:"slide-in-up"
        }).then(function(modal){
            $scope.modal=modal;
        });

        //显示新增界面
        $scope.showFitting=function(){
            $scope.modal.show();
            $scope.fitting={};
        };

        //关闭新增界面
        $scope.closeFitting=function(){
            $scope.modal.hide();
        };

        //新增配件
        $scope.addFitting=function(){
            //这里执行添加
            console.log("执行添加")
            var name=$scope.Fitting.name;
            var spec=$scope.Fitting.spec;
            //保存配件信息
            webService.do(savePartsInfoUrl, {
                name:name,
                spec:spec
            }) .success(function (data) {
                console.log(data);
                if(data.code){
                    alertPopup = $ionicPopup.alert({
                        title: '保存成功',
                        template: '您的信息保存成功！'
                    });
                    $scope.modal.hide();
                }else{
                    alertPopup = $ionicPopup.alert({
                        title: '保存失败',
                        template: data.msg
                    });
                }
            }).error(function (data, status) {
                alertPopup = $ionicPopup.alert({
                    title: '保存失败',
                    template: '数据连接错误！'
                });
            });
        };

        //删除配件信息
        $scope.remove=function(obj){
            console.log("这里执行删除操作")
            webService.do(deletePartsInfoUrl, {id:obj.id})
                .success(function (data) {
                    if(data.code){
                        alertPopup = $ionicPopup.alert({
                            title: '删除成功',
                            template: '您的信息删除成功！'
                        });
                        $scope.fittingList.splice($scope.fittingList.indexOf(obj), 1);
                    }else{
                        alertPopup = $ionicPopup.alert({
                            title: '删除失败',
                            template: data.msg
                        });
                    }
                }).error(function (data, status) {
                    alertPopup = $ionicPopup.alert({
                        title: '删除失败',
                        template: '数据连接失败'
                    });
                });
        }
    })




