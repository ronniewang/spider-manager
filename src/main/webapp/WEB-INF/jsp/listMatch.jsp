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
    <script src="/spider-web/js/bootstrap-datepicker.js"></script>
    <script src="/spider-web/js/my-datepicker.js"></script>
    <style>
        .absence {
            text-decoration: line-through;
        }

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

<body ng-app="matchApp" class="container-fluid" ng-controller="matchController">
<div class="col-md-2">
    <jsp:include page="navigation.jsp"/>
</div>
<div class="col-md-10">
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
                <button type="button" class="btn btn-success" ng-click="refresh(true)" id="refreshBtn">Refresh</button>
                <button type="button" class="btn btn-info" ng-click="exportExcel()">Export Excel</button>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" ng-model="alertMatch"> 弹框提示未导入比赛
                    </label>
                </div>
            </div>
        </div>
    </div>
    <div class="alert alert-success" role="alert" ng-model="total">共查出比赛{{total}}条，单击条目任意区域可以选取，比赛已按match
        code排序，在search框中输入关键字可进行筛选
    </div>
    <div class="alert alert-success" role="alert" ng-model="total">
        您有<span ng-bind="unimportsNum"></span>场比赛尚未导入，分别为[<span ng-bind="unimports"></span>]，
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
                <th>ABSENCE STATE</th>
            </tr>
            <tr ng-repeat="match in matchList | filter:filterMatchCode | orderBy:orderProp" id="{{match.id}}"
                ng-click="chooseOdds(match)">
                <td>{{match.matchCode}}</td>
                <td>{{match.matchDate}}</td>
                <td>{{match.matchLeague}}</td>
                <td>{{match.homeTeam}}</td>
                <td>{{match.awayTeam}}</td>
                <td><span class="label label-danger">{{match.absenceState}}</span></td>
            </tr>
        </table>
    </div>
</div>
</body>

</html>
<script src="/spider-web/js/my-angular-config.js"></script>
<script>
    var app = angular.module("matchApp", []);
    MY_ANGULAR_CONFIG(app);
    app.controller("matchController", function ($scope, $http) {
        $scope.matchList = [];
        $scope.unimports = [];
        $scope.unimportsNum = 0;
        $scope.loading = true;
        $scope.filterMatchCode = function (element) {
            if ($.trim($scope.query) == '') {
                return true;
            }
            return element.matchCode.indexOf($scope.query) != -1 ? true : false;
        };
        $scope.oneKeySelected = false;
        $scope.oneKeySelectLabel = "一键选中";
        $scope.oneKeySelect = function () {
            if (!$scope.oneKeySelected) {
                $scope.oneKeySelected = true;
                $scope.oneKeySelectLabel = "取消选中";
                $.each($scope.matchList, function (i, value) {
                    if (value.choose) {
                        value.choose = false;
                    }
                    if (value.absenceState == "") {
                        $scope.chooseOdds(value);
                    }
                });
            } else {
                $scope.oneKeySelected = false;
                $scope.oneKeySelectLabel = "一键选中";
                $.each($scope.matchList, function (i, value) {
                    if (value.choose == true) {
                        $scope.deselectAll();
                    }
                });
            }
        }
        $scope.chooseOdds = function (match) {
            if (match.choose) {
                match.choose = false;
            } else {
                match.choose = true;
            }
            if (match.choose) {
                $("#" + match.id).addClass("chosen");
            } else {
                $("#" + match.id).removeClass("chosen");
            }
        }
        $scope.selectAll = function () {
            $.each($scope.matchList, function (i, value) {
                value.choose = true;
                $("#" + value.id).addClass("chosen");
            });
        }
        $scope.deselectAll = function () {
            $.each($scope.matchList, function (i, value) {
                value.choose = false;
                $("#" + value.id).removeClass("chosen");
            });
        }
        $scope.orderProp = 'matchCode';
        $scope.defaultSeconds = 30;
        $scope.seconds = $scope.defaultSeconds;
        $scope.refresh = function (isValidate) {
            if (isValidate) {
                if (!$scope.validate($("#startDate").val(), $("#endDate").val())) {
                    return;
                }
            }
            $("#refreshBtn").attr("disabled", true);
            $scope.loading = true;
            $http.get('listMatch.do?startDate=' + $("#startDate").val() + "&endDate=" + $("#endDate").val())
                    .success(function (response) {
                        $scope.oneKeySelectLabel = "一键选中";
                        var isAlert = false;
                        $scope.unimports = [];
                        $.each(response, function (i, value) {
                            if (value.absenceState == "1") {
                                value.absenceState = "已导入";
                            } else {
                                value.absenceState = "";
                                $scope.unimports.push(value.matchCode);
                                isAlert = true;
                            }
                        });
                        $scope.unimportsNum = $scope.unimports.length;
                        $scope.matchList = response;
                        $scope.total = response.length;
                        $("#refreshBtn").attr("disabled", false);
                        $scope.loading = false;
                        $scope.seconds = $scope.defaultSeconds;
                        if ($scope.alertMatch && isAlert) {
                            alert('您有比赛尚未导入');
                        }
                    });
        }
        setInterval(function () {
            $('#refreshBtn').text('Refresh-' + --$scope.seconds);
            if ($scope.seconds == 0) {
                $scope.$apply($scope.refresh(false));
                $scope.seconds = $scope.defaultSeconds;
            }
        }, 1000);
        $scope.refresh(false)
        $scope.alertMatch = false;
        $scope.exportExcel = function () {
            var data = [];
            var match = {};
            var matchModelHead = {};
            matchModelHead.matchDate = "match_date";
            matchModelHead.matchCode = "match_code";
            matchModelHead.matchLeague = "match_league";
            matchModelHead.homeTeam = "home_team";
            matchModelHead.awayTeam = "away_team";
            data.push(matchModelHead);
            $.each($scope.matchList, function (i, value) {
                if (value.choose) {
                    data.push(value);
                }
            });
            $.ajax({
                type: "POST",
                url: "matchExcel.do",
                data: {
                    "matchModels": JSON.stringify(data)
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