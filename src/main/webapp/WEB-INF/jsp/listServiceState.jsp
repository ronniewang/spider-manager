<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title></title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/angular.js/1.4.3/angular.min.js"></script>
    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap-tpls.min.js"></script>
    <link href="//cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <script src="/spider-web/js/bootstrap-datepicker.js"></script>
    <style>
        body {
            padding-top: 70px;
            margin: 0 auto;
        }
    </style>
</head>

<body ng-app="productApp" class="container-fluid" ng-controller="productController">
<div class="col-md-2">
    <jsp:include page="navigation.jsp"/>
</div>
<div class="col-md-10">
    <div>
        <table class="table">
            <tr>
                <th>SERVICE NAME</th>
                <th>LATEST START TIME</th>
                <th>LATEST END TIME</th>
                <th>SUCCESS</th>
                <th>PARSE TIME</th>
                <th>HISTORY</th>
            </tr>
            <%--初始化是会从后台加载数据，放入$scope.serivces中--%>
            <tr ng-repeat="service in services">
                <%--遍历$scope.serivces--%>
                <td>{{service.service}}</td>
                <%--通过过滤器修改日期的显示形式--%>
                <td>{{service.startTime | date:'MM-dd HH:mm:ss.sss'}}</td>
                <td>{{service.endTime | date:'MM-dd HH:mm:ss.sss'}}</td>
                <td>{{service.success}}</td>
                <td>{{service.endTime-service.startTime | date:'s.sss'}}s</td>
                <td>
                    <%--click时间绑定到$scope.listHistory(service)函数--%>
                    <button type="button" class="btn btn-info" ng-click="listHistory(service.service)">历史</button>
                </td>
            </tr>
        </table>
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">历史记录</h3>
            </div>
            <div class="panel-body">
                <table class="table" ng-repeat="service in history">
                    <tr>
                        <td>{{service.service}}</td>
                        <td>{{service.startTime | date:'MM-dd HH:mm:ss.sss'}}</td>
                        <td>{{service.endTime | date:'MM-dd HH:mm:ss.sss'}}</td>
                        <td>{{service.success}}</td>
                        <td>{{service.endTime-service.startTime | date:'s.sss'}}s</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>

</html>
<script>
    var app = angular.module("productApp", []);
    app.controller("productController", function ($scope, $http) {
        $scope.serivces = [];
        $scope.history = [];
        $scope.loading = true;
        $http.get('listServiceState.do')
                .success(function (response) {
                    $scope.services = response;
                });
        $scope.listHistory = function (service) {
            $http.get('listHistory.do?service=' + service + '&' + Math.random())
                    .success(function (response) {
                        $scope.history = response;
                    });
        }
    });
</script>