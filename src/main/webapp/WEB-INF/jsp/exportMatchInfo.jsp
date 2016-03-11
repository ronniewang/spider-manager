<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/angular.js/1.4.3/angular.min.js"></script>
    <script src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap.min.js"></script>
    <script src="//cdn.bootcss.com/angular-ui-bootstrap/0.13.2/ui-bootstrap-tpls.min.js"></script>
    <link href="//cdn.bootcss.com/bootstrap-datepicker/1.4.0/css/bootstrap-datepicker3.min.css" rel="stylesheet">
    <script src="//cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
    <!--<script src="js/bootstrap-datepicker.js"></script>-->
    <script src="/spider-web/js/my-datepicker.js"></script>
    <style>
        body {
            padding-top: 70px;
            margin: 0 auto;
        }

        .form-center {
            text-align: center;
            padding-top: 10px;
        }
    </style>
</head>

<body ng-app="matchApp" class="container-fluid" ng-controller="matchController">
<div class="col-md-2">
    <jsp:include page="navigation.jsp"/>
</div>
<div class="col-md-10">
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="form-inline form-center">
            <div class="form-group">
                <label for="singleSelect">League</label>
                <select class="form-control" name="singleSelect" id="singleSelect" ng-model="league">
                    <option value="">—请选择—</option>
                    <option ng-repeat="l in leagues" value="{{l}}">{{l}}</option>
                </select>
                <label for="startDate">Start Date</label>
                <input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="startDate">
                <label for="endDate">End Date</label>
                <input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="endDate">
                <button type="button" class="btn btn-info" ng-click="listMatchByLeague()">ExportMatch</button>
                <button type="button" class="btn btn-info" ng-click="matchOddsExcelByLeague()">ExportOdds</button>
                <button type="button" class="btn btn-info" ng-click="matchStatisticExcelByLeague()">ExportStatistics</button>
            </div>
        </div>
    </div>
    <div class="jumbotron">
        <h1>Hello, world!</h1>

        <p>我暂时就是个凑数的，页面啥也没有显得不好看</p>

        <p><a class="btn btn-primary btn-lg" href="javascript:alert('啥也没有啦，点我也没用')" role="button">Learn more</a></p>
    </div>
</div>
</body>

</html>
<script src="/spider-web/js/my-angular-config.js"></script>
<script>
    var app = angular.module("matchApp", []);
    MY_ANGULAR_CONFIG(app);
    app.controller("matchController", function ($scope, $http) {
        $scope.leagues = [];
        $scope.league = '';
        $scope.matchInfos = [];
        $scope.filterMatchCode = function (element) {
            if ($.trim($scope.query) == '') {
                return true;
            }
            return element.matchCode.indexOf($scope.query) != -1 ? true : false;
        };
        $http.get('list310Leagues.do')
                .success(function (response) {
                    $scope.leagues = response;
                });

        $scope.listMatchByLeague = function () {
            if (!$scope.validate($("#startDate").val(), $('#endDate').val())) {
                return;
            }
            $http.post('listMatchByLeague.do',
                    {
                        league: $scope.league,
                        startDate: $("#startDate").val(),
                        endDate: $('#endDate').val()
                    }).success(function (response) {
                        window.open("download.do?downloadname=" + response.fileName);
                    });
        }
        $scope.matchOddsExcelByLeague = function () {
            if (!$scope.validate($("#startDate").val(), $('#endDate').val())) {
                return;
            }
            $http.post('matchOddsExcelByLeague.do',
                    {
                        league: $scope.league,
                        startDate: $("#startDate").val(),
                        endDate: $('#endDate').val()
                    }).success(function (response) {
                        window.open("download.do?downloadname=" + response.fileName);
                    });
        }
        $scope.matchStatisticExcelByLeague = function () {
            if (!$scope.validate($("#startDate").val(), $('#endDate').val())) {
                return;
            }
            $http.post('matchStatisticExcelByLeague.do',
                    {
                        league: $scope.league,
                        startDate: $("#startDate").val(),
                        endDate: $('#endDate').val()
                    }).success(function (response) {
                        window.open("download.do?downloadname=" + response.fileName);
                    });
        }
        $scope.validate = function (start, end) {
            if (start == "") {
                alert("start date can not be null");
                return false;
            }
            if (end == "") {
                alert("end date can not be null");
                return false;
            }
            if (end.substring(6, 10) - start.substring(6, 10) < 0) {
                alert("end date should be greater than start date");
                return false;
            }
            if (end.substring(6, 10) - start.substring(6, 10) == 0 && end.substring(0, 2) - start.substring(0, 2) < 0) {
                alert("end date should be greater than start date");
                return false;
            }
            if (end.substring(6, 10) - start.substring(6, 10) == 0 && end.substring(0, 2) - start.substring(0, 2) == 0 && end.substring(3, 5) - start.substring(3, 5) < 0) {
                alert("end date should be greater than start date");
                return false;
            }
            return true;
        }
    });

</script>