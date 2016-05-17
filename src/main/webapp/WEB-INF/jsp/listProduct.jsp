<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <script src="js/bootstrap-datepicker.js"></script>
    <style>
        .chosen {
            background-color: gold;
        }

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

<body ng-app="productApp" class="container-fluid" ng-controller="productController">
<div class="navbar navbar-default navbar-fixed-top">
    <div class="form-inline form-center">
        <div class="form-group">
            <label for="exampleInputName2">Search</label>
            <input type="text" class="form-control" id="exampleInputName2" ng-model="query">
            <button type="button" class="btn btn-primary" ng-click="selectAll()">Select All</button>
            <button type="button" class="btn btn-primary" ng-click="deselectAll()">Deselect All</button>
            <label for="startDate">Start Date</label>
            <input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="startDate">
            <label for="endDate">End Date</label>
            <input class="datepicker" type="text" data-date-format="mm/dd/yyyy" readonly="true" id="endDate">
            <button type="button" class="btn btn-success" ng-click="refresh()" id="refreshBtn">Refresh</button>
            <button type="button" class="btn btn-info" ng-click="exportExcel()">Export Excel</button>
        </div>
    </div>
</div>
<div class="col-md-2">
    <jsp:include page="navigation.jsp"/>
</div>
<div class="col-md-10">
    <div class="alert alert-success" role="alert" ng-model="total">共查出比赛{{total}}条，单击条目任意区域可以选取，比赛已按product
        code排序，在search框中输入关键字可进行筛选，
        <a class="btn btn-info btn-xs" ng-click="oneKeySelect()" ng-bind="oneKeySelectLabel" role="button"></a> 未导入比赛
    </div>
    <div ng-show="loading">正在加载数据...</div>
    <div>
        <table class="table">
            <tr>
                <th>MATCH CODE</th>
                <th>MATCH DATE</th>
                <th>MATCH LEAGUE</th>
                <th>HOME TEAM</th>
                <th>AWAY TEAM</th>
                <th>HANDICAP LINE</th>
                <th>HAD</th>
                <th>HHAD</th>
                <th>HAFU</th>
                <th>CRS</th>
                <th>TTG</th>
                <th>ABSENCE STATE</th>
            </tr>
            <tr ng-repeat="product in productList | filter:filterMatchCode | orderBy:orderProp" id="{{product.id}}"
                ng-click="chooseProduct(product)">
                <td>{{product.matchCode}}</td>
                <td>{{product.matchDate}}</td>
                <td>{{product.matchLeague}}</td>
                <td>{{product.homeTeam}}</td>
                <td>{{product.awayTeam}}</td>
                <td>{{product.handicapLine}}</td>
                <td>{{product.had}}</td>
                <td>{{product.hhad}}</td>
                <td>{{product.hafu}}</td>
                <td>{{product.crs}}</td>
                <td>{{product.ttg}}</td>
                <td><span class="label label-danger">{{product.absenceState}}</span></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
<script src="js/my-angular-config.js"></script>
<script>
    $(function () {
        $('.datepicker').datepicker({
            autoclose: true
        });
    });
    var app = angular.module("productApp", []);
    MY_ANGULAR_CONFIG(app);
    app.controller("productController", function ($scope, $http) {
        $scope.productList = [];
        $scope.loading = true;
        $scope.filterMatchCode = function (element) {
            if ($.trim($scope.query) == '') {
                return true;
            }
            return element.matchCode.indexOf($scope.query) != -1 ? true : false;
        };
        $http.get('listProduct.do?startDate=' + $("#startDate").val() + "&endDate=" + $("#endDate").val())
                .success(function (response) {
                    $.each(response, function (i, value) {
                        if (value.absenceState == "1") {
                            value.absenceState = "已导入";
                        } else {
                            value.absenceState = "";
                        }
                    });
                    $scope.productList = response;
                    $scope.total = $scope.productList.length;
                    $scope.loading = false;
                });
        $scope.chooseProduct = function (product) {
            if (product.choose) {
                product.choose = false;
            } else {
                product.choose = true;
            }
            if (product.choose) {
                $("#" + product.id).addClass("chosen");
            } else {
                $("#" + product.id).removeClass("chosen");
            }
        }
        $scope.oneKeySelectLabel = "一键选中";
        $scope.oneKeySelect = function () {
            if (!$scope.oneKeySelected) {
                $scope.oneKeySelected = true;
                $scope.oneKeySelectLabel = "取消选中";
                $.each($scope.productList, function (i, value) {
                    if (value.choose) {
                        value.choose = false;
                    }
                    if (value.absenceState == "") {
                        $scope.chooseProduct(value);
                    }
                });
            } else {
                $scope.oneKeySelected = false;
                $scope.oneKeySelectLabel = "一键选中";
                $.each($scope.productList, function (i, value) {
                    if (value.choose == true) {
                        $scope.deselectAll();
                    }
                });
            }
        }
        $scope.selectAll = function () {
            $.each($scope.productList, function (i, value) {
                value.choose = true;
                $("#" + value.id).addClass("chosen");
            });
        }
        $scope.deselectAll = function () {
            $.each($scope.productList, function (i, value) {
                value.choose = false;
                $("#" + value.id).removeClass("chosen");
            });
        }
        $scope.orderProp = 'matchCode';
        $scope.refresh = function () {
            if (!$scope.validate($("#startDate").val(), $("#endDate").val())) {
                return;
            }
            $("#refreshBtn").attr("disabled", true);
            $scope.loading = true;
            $http.get('listProduct.do?startDate=' + $("#startDate").val() + "&endDate=" + $("#endDate").val())
                    .success(function (response) {
                        $.each(response, function (i, value) {
                            if (value.absenceState == "1") {
                                value.absenceState = "已导入";
                            } else {
                                value.absenceState = "";
                            }
                        });
                        $scope.productList = response;
                        $scope.total = response.length;
                        $("#refreshBtn").attr("disabled", false);
                        $scope.loading = false;
                    });
        }
        $scope.exportExcel = function () {
            var data = [];
            var product = {};
            var productModelHead = {};
            productModelHead.matchDate = "match_date";
            productModelHead.matchCode = "match_code";
            productModelHead.matchLeague = "match_league";
            productModelHead.homeTeam = "home_team";
            productModelHead.awayTeam = "away_team";
            productModelHead.HAD = "HAD";
            productModelHead.HHAD = "HHAD";
            productModelHead.handicapLine = "handicap_line";
            data.push(productModelHead);
            $.each($scope.productList, function (i, value) {
                if (value.choose) {
                    data.push(value);
                }
            });
            $.ajax({
                type: "POST",
                url: "matchProductExcel.do",
                data: {
                    "productModels": JSON.stringify(data)
                },
                dataType: "json",
                success: function (data) {
                    window.open("download.do?downloadname=" + data.fileName);
                },
                error: function (XMLHttpRequest, txtStatus, errorThrown) {
                    alert(XMLHttpRequest + txtStatus + errorThrown);
                }
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