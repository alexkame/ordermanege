// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('myApp', ['ionic','myApp.controllers','myApp.services'])
    .run(function($ionicPlatform) {
        $ionicPlatform.ready(function() {
            // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
            // for form inputs)
            if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
                cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
            }
            if (window.StatusBar) {
                // org.apache.cordova.statusbar required
                StatusBar.styleLightContent();
            }
        });
    })
    .config(function($stateProvider, $urlRouterProvider) {

        // Ionic uses AngularUI Router which uses the concept of states
        // Learn more here: https://github.com/angular-ui/ui-router
        // Set up the various states which the app can be in.
        // Each state's controller can be found in controllers.js
        console.log($stateProvider)
        $stateProvider
            //客户订单列表页
            .state('tab', {
                url: '/tab',
                // abstract:'true',
                cache:false,
                templateUrl: "templates/tabs.html",
                controller:"orderController"
            })
            //客户订单页，目前作废
            //.state('order', {
            //    url: '/order',
            //    templateUrl:"templates/order.html",
            //    controller:"orderController"
            //   /* views:{
            //        "tab.order":{
            //            templateUrl:"templates/order.html",
            //            controller:"orderController1"
            //        }
            //    }*/
            //})
            //订单新增
            .state('orderAdd',{
                url:'/orderAdd',
                cache:false,
                templateUrl:"templates/orderAdd.html",
                controller:"orderAddController"
            })
            .state('orderDetail', {
                url: '/orderDetail/:orderID',
                cache:false,
                templateUrl: 'templates/orderDetail.html',
                controller: 'orderDetailController'
            })
            //管理员登录
            .state('adminLogin', {
                url: '/admin/adminLogin',
                cache:false,
                templateUrl: 'templates/admin/adminLogin.html',
                controller: 'adminController'
            })
            //管理列表
            .state('adminList', {
                url: '/admin/adminList',
                cache:false,
                templateUrl: 'templates/admin/adminList.html',
                controller: 'adminController'
            })
            //未完成订单列表
            .state('undoneOrder', {
                url: '/admin/undoneOrder',
                cache:false,
                templateUrl: 'templates/admin/undoneOrder.html',
                controller: 'AdminOrderController'
            })
            //已完成订单列表
            .state('doneOrder', {
                url: '/admin/doneOrder',
                cache:false,
                templateUrl: 'templates/admin/doneOrder.html',
                controller: 'AdminOrderController'
            })
            .state('orderProcess', {
                url: '/orderProcess/:orderID',
                cache:false,
                templateUrl: 'templates/orderProcess.html',
                controller: 'orderDetailController'
            })

            //客户信息管理
            .state('admin/customer', {
            url: '/admin/customer',
            cache:false,
            templateUrl: 'templates/admin/customer.html',
            controller: 'cusController'
            })
            .state('admin/customerDetail', {
                url: '/admin/customerDetail/:customerID',
                cache:false,
                templateUrl: 'templates/admin/customerDetail.html',
                controller: 'cusDetailController'
            })
            .state('myInfo', {
                url: '/myInfo',
                cache:false,
                templateUrl: 'templates/myInfo.html',
                controller: 'myInfoController'
            })

            //员工信息管理
            .state('admin/employ', {
                url: '/admin/employ',
                cache:false,
                templateUrl: 'templates/admin/employ.html',
                controller: 'employController'
            })
            //员工信息详情
            .state('admin/employDetail', {
                url: '/admin/employDetail/:employID',
                cache:false,
                templateUrl: 'templates/admin/employDetail.html',
                controller: 'employDetailController'
            })

            //配件管理
            .state('admin/fitting', {
                url: '/admin/fitting',
                cache:false,
                templateUrl: 'templates/admin/fitting.html',
                controller: 'fittingController'
            })
            .state('admin/fittingDetail', {
                url: '/admin/fittingDetail/:fittingID',
                cache:false,
                templateUrl: 'templates/admin/fittingDetail.html',
                controller: 'fittingDetailController'
            })



        // if none of the above states are matched, use this as the fallback
        $urlRouterProvider.otherwise('/tab');

    });
