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

<body ng-app="teamApp" class="container-fluid" ng-controller="teamController">
<div class="navbar navbar-default navbar-fixed-top">
    <div class="form-inline form-center">
        <div class="form-group">
            <label for="exampleInputName2">Search Team By Anything </label>
            <input type="text" class="form-control" id="exampleInputName2" ng-model="query">
            <button type="button" class="btn btn-info" ng-click="add()">Add Match Team</button>
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
                <th>LEAGUE ID</th>
                <th>PINNACLE NAME</th>
                <th>PINNACLE NAME ENG</th>
                <th>SPORTTERY NAME</th>
                <th>SPORTTERY NAME ENG</th>
                <th>WIN310 NAME</th>
                <th>WIN310 NAME ENG</th>
                <th>SBC NAME ABBR</th>
                <th>SBC NAME ENG ABBR</th>
                <th>OPERATION</th>
            </tr>
            <tr ng-repeat="team in teamList | filter:query" id="{{team.id}}" ng-click="addMatchTeam(team)">
                <td ng-bind="team.leagueId"></td>
                <td ng-bind="team.pinnacleName"></td>
                <td ng-bind="team.pinnacleNameEng"></td>
                <td ng-bind="team.sportteryName"></td>
                <td ng-bind="team.sportteryNameEng"></td>
                <td ng-bind="team.win310Name"></td>
                <td ng-bind="team.win310NameEng"></td>
                <td ng-bind="team.teamNameAbbr"></td>
                <td ng-bind="team.teamNameEngAbbr"></td>
                <td>
                    <button type="button" class="btn btn-info" ng-click="modify(team)">Modify</button>
                </td>
            </tr>
        </table>
    </div>
    <div>
        <script type="text/ng-template" id="modal.do">
            <div class="modal-header">
                <h3 class="modal-title">球队信息</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">LEAGUE ID</label>

                        <div class="col-sm-2">
                            <input type="text" class="form-control" ng-model="team.leagueId" disabled="true">
                        </div>
                        <div class="col-sm-6">
                            <select class="form-control" ng-model="team.leagueId">
                                <option ng-repeat="league in leagueList" value = "{{league.id}}">{{league.leagueNameChinese}}-{{league.id}}</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">PINNACLE NAME</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="team.pinnacleName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">PINNACLE NAME ENG</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="team.pinnacleNameEng">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">SPORTTERY NAME</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="team.sportteryName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">SPORTTERY NAME ENG</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="team.sportteryNameEng">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">WIN310 NAME</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="team.win310Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">WIN310 NAME ENG</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="team.win310NameEng">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">SBC NAME ABBR</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="team.teamNameAbbr">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">SBC NAME ENG ABBR</label>

                        <div class="col-sm-8">
                            <input type="text" class="form-control" ng-model="team.teamNameEngAbbr">
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
<script src="../../js/my-angular-config.js"></script>
<script>
    var app = angular.module("teamApp", ['ui.bootstrap']);
    MY_ANGULAR_CONFIG(app);
    var leagueList;
    app.controller("teamController", function ($scope, $http, $modal) {
        $scope.teamList = [];
        $scope.loading = true;
        $http.get('listMatchTeam.do')
                .success(function (response) {
                    $scope.teamList = response;
                    $scope.total = $scope.teamList.length;
                    $scope.loading = false;
                });
        $http.get('listMatchLeague.do')
                .success(function (response) {
                    leagueList = response;
                });
        $scope.modify = function (team) {
            var modalInstance = $modal.open({
                animation: false,
                templateUrl: 'modal.do',
                controller: 'ModalInstanceCtrl',
                size: 'lg',
                resolve: {
                    team: function () {
                        return team;
                    }
                }
            });

            modalInstance.result.then(function () {
                team.isModifyDisable = false;
            }, function () {
                team.isModifyDisable = false;
            });
        }
        $scope.add = function () {
            var modalInstance = $modal.open({
                animation: false,
                templateUrl: 'modal.do',
                controller: 'ModalInstanceCtrl',
                size: 'lg',
                resolve: {
                    team: function () {
                        return {};
                    }
                }
            });

            modalInstance.result.then(function () {
                team.isModifyDisable = false;
            }, function () {
                team.isModifyDisable = false;
            });
        }
    });
    app.controller('ModalInstanceCtrl', function ($scope, $http, $modalInstance, team) {
        $scope.leagueList = leagueList;
        $scope.leagueId = team.leagueId;
        $scope.pinnacleName = team.pinnacleName;
        $scope.pinnacleNameEng = team.pinnacleNameEng;
        $scope.sportteryName = team.sportteryName;
        $scope.sportteryNameEng = team.sportteryNameEng;
        $scope.win310Name = team.win310Name;
        $scope.win310NameEng = team.win310NameEng;
        $scope.teamNameAbbr = team.teamNameAbbr;
        $scope.teamNameEngAbbr = team.teamNameEngAbbr;
        $scope.team = team;
        $scope.ok = function () {
            $http.post('modifyTeam.do', {"team": JSON.stringify(team)}).success(function (response) {
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
            team.pinnacleName = $scope.pinnacleName;
            team.pinnacleNameEng = $scope.pinnacleNameEng;
            team.sportteryName = $scope.sportteryName;
            team.sportteryNameEng = $scope.sportteryNameEng;
            team.win310Name = $scope.win310Name;
            team.win310NameEng = $scope.win310NameEng;
            team.teamNameAbbr = $scope.teamNameAbbr;
            team.teamNameEngAbbr = $scope.teamNameEngAbbr;
            $modalInstance.dismiss('cancel');
        };
    });

</script>