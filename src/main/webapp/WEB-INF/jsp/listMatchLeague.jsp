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
<div class="navbar navbar-default navbar-fixed-top">
    <div class="form-inline form-center">
        <div class="form-group">
            <label for="exampleInputName2">Search League By Anything </label>
            <input type="text" class="form-control" id="exampleInputName2" ng-model="query">
        </div>
    </div>
</div>
<div class="col-md-2">
    <jsp:include page="navigation.jsp"/>
</div>
<div class="col-md-10">
    <div class="alert alert-success" role="alert" ng-model="total">共查出结果{{total}}条，在search框中输入关键字可进行筛选
    </div>
    <div class="alert alert-success" role="alert" ng-show="success">修改成功</div>
    <div class="alert alert-danger" role="alert" ng-show="failed">修改失败</div>
    <div ng-show="loading">正在加载数据...</div>
    <div>
        <table class="table">
            <tr>
                <th>LEAGUE NAME ENGLISH</th>
                <th>LEAGUE NAME ABBR</th>
                <th>LEAGUE NAME CHINESE</th>
                <th>SPORTTERY NAME</th>
                <th>WIN310 NAME</th>
                <th>PINNACLESPORTS NAME</th>
                <th>OPERATION</th>
            </tr>
            <tr ng-repeat="match in matchList | filter:query" id="{{match.id}}" ng-click="addMatchLeague(match)">
                <td ng-bind="match.leagueNameEnglish"></td>
                <td ng-bind="match.leagueNameAbbr"></td>
                <td ng-bind="match.leagueNameChinese"></td>
                <td ng-bind="match.sportteryName"></td>
                <td ng-bind="match.win310Name"></td>
                <td ng-bind="match.pinnaclesportsName"></td>
                <td>
                    <button type="button" class="btn btn-info" ng-click="modify(match)">Modify</button>
                </td>
            </tr>
        </table>
    </div>
    <div>
        <script type="text/ng-template" id="modal.do">
            <div class="modal-header">
                <h3 class="modal-title">联赛信息</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">LEAGUE NAME ENGLISH</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="league.leagueNameEnglish">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">LEAGUE NAME ABBR</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="league.leagueNameAbbr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">LEAGUE NAME CHINESE</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="league.leagueNameChinese">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">SPORTTERY NAME</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="league.sportteryName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">WIN310 NAME</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="league.win310Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">PINNACLESPORTS NAME</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="league.pinnaclesportsName">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" type="button" ng-click="ok()">修改</button>
                <button class="btn btn-warning" type="button" ng-click="cancel()">取消</button>
            </div>
        </script>
    </div>
</div>
</body>

</html>
<script src="/spider-web/js/my-angular-config.js"></script>
<script>
    var app = angular.module("matchApp", ['ui.bootstrap']);
    MY_ANGULAR_CONFIG(app);
    app.controller("matchController", function ($scope, $http, $modal) {
        $scope.matchList = [];
        $scope.loading = true;
        $http.get('listMatchLeague.do')
                .success(function (response) {
                    $scope.matchList = response;
                    $scope.total = $scope.matchList.length;
                    $scope.loading = false;
                });
        $scope.addMatchLeague = function (match) {
            $('#myModal').modal();
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
        $scope.refresh = function () {
            if (!$scope.validate($("#startDate").val(), $("#endDate").val())) {
                return;
            }
            $("#refreshBtn").attr("disabled", true);
            $scope.loading = true;
            $http.get('listMatchLeague.do')
                    .success(function (response) {
                        $scope.matchList = response;
                        $scope.total = response.length;
                        $("#refreshBtn").attr("disabled", false);
                        $scope.loading = false;
                    });
        }
        $scope.modify = function (league) {
            var modalInstance = $modal.open({
                animation: false,
                templateUrl: 'modal.do',
                controller: 'ModalInstanceCtrl',
                size: 'lg',
                resolve: {
                    league: function () {
                        return league;
                    }
                }
            });

            modalInstance.result.then(function () {
                league.isModifyDisable = false;
            }, function () {
                league.isModifyDisable = false;
            });
        }
    });
    app.controller('ModalInstanceCtrl', function ($scope, $http, $modalInstance, league) {
        $scope.leagueNameEnglish = league.leagueNameEnglish;
        $scope.leagueNameAbbr = league.leagueNameAbbr;
        $scope.leagueNameChinese = league.leagueNameChinese;
        $scope.sportteryName = league.sportteryName;
        $scope.win310Name = league.win310Name;
        $scope.pinnaclesportsName = league.pinnaclesportsName;
        $scope.otherName = league.otherName;
        $scope.league = league;
        $scope.ok = function () {
            $http.post('modifyLeague.do', {"league": JSON.stringify(league)}).success(function (response) {
                if (response.value == 0) {
                    alert("success")
                } else {
                    alert("failed")
                }
            }).error(function (response) {
                alert("failed")
            })
            $modalInstance.close();
        };

        $scope.cancel = function () {
            league.leagueNameEnglish = $scope.leagueNameEnglish;
            league.leagueNameAbbr = $scope.leagueNameAbbr;
            league.leagueNameChinese = $scope.leagueNameChinese;
            league.sportteryName = $scope.sportteryName;
            league.win310Name = $scope.win310Name;
            league.pinnaclesportsName = $scope.pinnaclesportsName;
            league.otherName = $scope.otherName;
            $modalInstance.dismiss('cancel');
        };
    });

</script>