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

<body ng-app="oddsApp" class="container-fluid" ng-controller="oddsController">
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
    <div class="alert alert-success" role="alert" ng-model="total">共查出比赛{{total}}条，单击条目任意区域可以选取，比赛已按match
        code排序，在search框中输入match code可进行筛选，
        <a class="btn btn-info btn-xs" ng-click="oneKeySelect()" ng-bind="oneKeySelectLabel" role="button"></a> 未导入比赛
    </div>
    <div ng-show="loading">正在加载数据...</div>
    <ul class="list-group">
        <li class="list-group-item" ng-repeat="odds in oddsList | filter:filterMatchCode | orderBy:orderProp">
            <h3>
					<span class="label label-info">
						{{odds.matchCode}} | {{odds.matchDate}} | {{odds.matchLeague}} | {{odds.homeTeam}} VS {{odds.awayTeam}} | 让球 {{odds.handicapLine}} | 比分 {{odds.score}} | 进行时间 {{odds.durationTime}}
					</span>
                <span class="label label-danger">{{odds.absenceState}}</span>
                <span class="label label-danger">{{odds.state}}</span>
                <button type="button" class="btn btn-success btn-sm" ng-click="refreshOdds(odds)"
                        id="refreshOddsBtn{{odds.id}}">Refresh
                </button>
                <button type="button" class="btn btn-success btn-sm" ng-click="calcSupAndTtg(odds)"
                        id="calcSupAndTtg{{odds.id}}">Calc SUP/TTG
                </button>
                <span class="label label-info" id="supAndTtg{{odds.id}}"></span>
            </h3>
            <table class="table table-bordered" ng-class='{chosen: odds.choose}' ng-click="chooseOdds(odds)"
                   id="{{odds.id}}">
                <tr>
                    <th ng-class="{different: odds.different}">HAD</th>
                    <th ng-class="{different: odds.different}">HHAD</th>
                    <th ng-class="{different: odds.different}">HDC</th>
                    <th ng-class="{different: odds.different}">HILO</th>
                </tr>
                <tr>
                    <td>
                        <table class="table">
                            <tr>
                                <th>#</th>
                                <th>官</th>
                                <th>彩</th>
                                <th>利记</th>
                                <th>金宝博</th>
                            </tr>
                            <tr>
                                <td>H</td>
                                <td>{{odds.sportteryHadH}}</td>
                                <td>{{odds.win310HadH}}</td>
                                <td>{{odds.lijiHadH}}</td>
                                <td>{{odds.jinbaoboHadH}}</td>
                            </tr>
                            <tr>
                                <td>D</td>
                                <td>{{odds.sportteryHadD}}</td>
                                <td>{{odds.win310HadD}}</td>
                                <td>{{odds.lijiHadD}}</td>
                                <td>{{odds.jinbaoboHadD}}</td>
                            </tr>
                            <tr>
                                <td>A</td>
                                <td>{{odds.sportteryHadA}}</td>
                                <td>{{odds.win310HadA}}</td>
                                <td>{{odds.lijiHadA}}</td>
                                <td>{{odds.jinbaoboHadA}}</td>
                            </tr>
                            <tr>
                                <td>M</td>
                                <td>{{odds.sportteryHadMargin}}</td>
                                <td>{{odds.win310HadMargin}}</td>
                                <td>{{odds.lijiHadMargin}}</td>
                                <td>{{odds.jinbaoboHadMargin}}</td>
                            </tr>
                            <tr>
                                <td>U</td>
                                <td>{{odds.sportteryHadUpdate}}</td>
                                <td>{{odds.win310HadUpdate}}</td>
                                <td>{{odds.lijiHadUpdate}}</td>
                                <td>{{odds.jinbaoboHadUpdate}}</td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table class="table">
                            <tr>
                                <th>#</th>
                                <th>官</th>
                                <th>彩</th>
                            </tr>
                            <tr>
                                <td>H</td>
                                <td>{{odds.sportteryHhadH}}</td>
                                <td>{{odds.win310HhadH}}</td>
                            </tr>
                            <tr>
                                <td>D</td>
                                <td>{{odds.sportteryHhadD}}</td>
                                <td>{{odds.win310HhadD}}</td>
                            </tr>
                            <tr>
                                <td>A</td>
                                <td>{{odds.sportteryHhadA}}</td>
                                <td>{{odds.win310HhadA}}</td>
                            </tr>
                            <tr>
                                <td>M</td>
                                <td>{{odds.sportteryHhadMargin}}</td>
                                <td>{{odds.win310HhadMargin}}</td>
                            </tr>
                            <tr>
                                <td>U</td>
                                <td>{{odds.sportteryHhadUpdate}}</td>
                                <td>{{odds.win310HhadUpdate}}</td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table class="table">
                            <tr>
                                <th>#</th>
                                <th>利记</th>
                                <th>金宝博</th>
                            </tr>
                            <tr>
                                <td>Home</td>
                                <td>{{odds.lijiHdcHome}}</td>
                                <td>{{odds.jinbaoboHdcHome}}</td>
                            </tr>
                            <tr>
                                <td>Line</td>
                                <td>{{odds.lijiHdcLine}}</td>
                                <td>{{odds.jinbaoboHdcLine}}</td>
                            </tr>
                            <tr>
                                <td>Away</td>
                                <td>{{odds.lijiHdcAway}}</td>
                                <td>{{odds.jinbaoboHdcAway}}</td>
                            </tr>
                            <tr>
                                <td>M</td>
                                <td>{{odds.lijiHdcMargin}}</td>
                                <td>{{odds.jinbaoboHdcMargin}}</td>
                            </tr>
                            <tr>
                                <td>L</td>
                                <td>{{odds.lijiHdcUpdate}}</td>
                                <td>{{odds.jinbaoboHdcUpdate}}</td>
                            </tr>
                        </table>
                    </td>
                    <td>
                        <table class="table">
                            <tr>
                                <th>#</th>
                                <th>利记</th>
                                <th>金宝博</th>
                            </tr>
                            <tr>
                                <td>High</td>
                                <td>{{odds.lijiHiloH}}</td>
                                <td>{{odds.jinbaoboHiloH}}</td>
                            </tr>
                            <tr>
                                <td>Line</td>
                                <td>{{odds.lijiHiloLine}}</td>
                                <td>{{odds.jinbaoboHiloLine}}</td>
                            </tr>
                            <tr>
                                <td>Low</td>
                                <td>{{odds.lijiHiloL}}</td>
                                <td>{{odds.jinbaoboHiloL}}</td>
                            </tr>
                            <tr>
                                <td>M</td>
                                <td>{{odds.lijiHiloMargin}}</td>
                                <td>{{odds.jinbaoboHiloMargin}}</td>
                            </tr>
                            <tr>
                                <td>U</td>
                                <td>{{odds.lijiHiloUpdate}}</td>
                                <td>{{odds.jinbaoboHiloUpdate}}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </li>
    </ul>
</div>
</body>

</html>
<script src="/spider-web/js/my-angular-config.js"></script>
<script>
    $(function () {
        $('.datepicker').datepicker({
            autoclose: true
        });
    });
    var app = angular.module("oddsApp", []);
    MY_ANGULAR_CONFIG(app);
    app.controller("oddsController", function ($scope, $http) {
        $scope.oddsList = [];
        $scope.loading = true;
        $scope.startDate = null;
        $scope.endDate = null;
        $scope.filterMatchCode = function (element) {
            if ($.trim($scope.query) == '') {
                return true;
            }
            return element.matchCode.indexOf($scope.query) != -1 ? true : false;
        };
        $scope.logout = function () {
            $http.post('logout');
        }
        $scope.defaultSeconds = 30;
        $scope.seconds = $scope.defaultSeconds;
        setInterval(function () {
            $('#refreshBtn').text('Refresh-' + --$scope.seconds);
            if ($scope.seconds == 0) {
                $scope.$apply(firstLoad);
                $scope.seconds = $scope.defaultSeconds;
            }
        }, 1000);
        var firstLoad = function () {
            $scope.loading = true;
            $("#refreshBtn").attr("disabled", true);
            $http.get('listOdds.do?startDate=' + $("#startDate").val() + "&endDate=" + $("#endDate").val())
                    .success(function (response) {
                        $.each(response, function (i, value) {
                            if (value.absenceState == "1") {
                                value.absenceState = "已导入";
                            } else {
                                value.absenceState = "";
                            }
                        });
                        $scope.oddsList = response;
                        $scope.total = $scope.oddsList.length;
                        $("#refreshBtn").attr("disabled", false);
                        $scope.loading = false;
                        $scope.seconds = $scope.defaultSeconds;
                    });
        };
        firstLoad();
        $scope.chooseOdds = function (odds) {
            if (odds.choose) {
                odds.choose = false;
            } else {
                odds.choose = true;
            }
        }
        $scope.selectAll = function () {
            $.each($scope.oddsList, function (i, value) {
                value.choose = true;
            });
        }
        $scope.deselectAll = function () {
            $.each($scope.oddsList, function (i, value) {
                value.choose = false;
            });
        }
        $scope.oneKeySelectLabel = "一键选中";
        $scope.oneKeySelect = function () {
            if (!$scope.oneKeySelected) {
                $scope.oneKeySelected = true;
                $scope.oneKeySelectLabel = "取消选中";
                $.each($scope.oddsList, function (i, value) {
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
                $.each($scope.oddsList, function (i, value) {
                    if (value.choose == true) {
                        $scope.deselectAll();
                    }
                });
            }
        }
        $scope.orderProp = 'matchCode';
        $scope.refresh = function () {
            if ($scope.startDate == null || $scope.endDate == null) {
                if (!$scope.validate($("#startDate").val(), $("#endDate").val())) {
                    return;
                }
            }
            $scope.startDate = $("#startDate").val();
            $scope.endDate = $("#endDate").val();
            $("#refreshBtn").attr("disabled", true);
            $scope.loading = true;
            $http.get('listOdds.do?startDate=' + $("#startDate").val() + "&endDate=" + $("#endDate").val())
                    .success(function (response) {
                        $.each(response, function (i, value) {
                            if (value.absenceState == "1") {
                                value.absenceState = "已导入";
                            } else {
                                value.absenceState = "";
                            }
                        });
                        $scope.oddsList = response;
                        $scope.total = response.length;
                        $("#refreshBtn").attr("disabled", false);
                        $scope.loading = false;
                    });
            $scope.seconds = $scope.defaultSeconds;
        }
        $scope.refreshOdds = function (odds) {
            $("#refreshOddsBtn" + odds.id).attr("disabled", true);
            $http.get('refreshOdds.do?matchCode=' + odds.matchCode + '&absenceState=' + odds.absenceState)
                    .success(function (response) {
                        if (response.absenceState == "1") {
                            response.absenceState = "已导入";
                        } else {
                            response.absenceState = "";
                        }
                        for (var i = 0; i < $scope.oddsList.length; i++) {
                            if (response.matchCode == $scope.oddsList[i].matchCode) {
                                $scope.oddsList[i] = response;
                            }
                        }
                        $("#refreshOddsBtn" + odds.id).attr("disabled", false);
                    });
        }
        $scope.calcSupAndTtg = function (odds) {
            $("#calcSupAndTtg" + odds.id).attr("disabled", true);
            $.ajax({
                url: 'calcSupAndTtg.do',
                data: {'oddsModel': JSON.stringify(odds)},
                type: 'get',
                cache: false,
                dataType: 'json',
                success: function (data) {
                    $("#calcSupAndTtg" + odds.id).attr("disabled", false);
                    $("#supAndTtg" + odds.id).text('');
                    $("#supAndTtg" + odds.id).text('ljSUP: ' + data.liji.sup + ', ljTTG: ' + data.liji.ttg + ' jbbSUP: ' + data.jinbaobo.sup + ', jbbTTG: ' + data.jinbaobo.ttg);
                },
                error: function (XMLHttpRequest, txtStatus, errorThrown) {
                    $("#calcSupAndTtg" + odds.id).attr("disabled", false);
                    alert("失败！");
                }
            });
//			$http.get("calcSupAndTtg.do?hdcHome=" + odds.lijiHdcHome + "&hdcLine=" + odds.lijiHdcLine + "&hdcAway=" + odds.lijiHdcAway + "&hiloHigh=" + odds.lijiHiloH + "&hiloLine=" + odds.lijiHiloLine + "&hiloLow=" + odds.lijiHiloL +"&durationTime=" + (odds.durationTime==''?'-':odds.durationTime) + "&score=" + odds.score)
//				.success(function(response) {
//					$("#calcSupAndTtg" + odds.id).attr("disabled", false);
//					$("#supAndTtg" + odds.id).text('');
//					$("#supAndTtg" + odds.id).text('SUP: '+response.sup+', TTG: '+response.ttg);
//				});
        };
        $scope.exportExcel = function () {
            var data = [];
            var match = {};
            var oddsModelHead = {};
            var sportteryAllEntity = {};
            oddsModelHead.matchDate = "match_date";
            oddsModelHead.matchCode = "match_code";
            oddsModelHead.matchLeague = "match_league";
            oddsModelHead.homeTeam = "home_team";
            oddsModelHead.awayTeam = "away_team";
            oddsModelHead.handicapLine = "handicap_line";
            oddsModelHead.sportteryHadH = "had_h";
            oddsModelHead.sportteryHadD = "had_d";
            oddsModelHead.sportteryHadA = "had_a";
            oddsModelHead.sportteryHhadH = "hhad_h";
            oddsModelHead.sportteryHhadD = "hhad_d";
            oddsModelHead.sportteryHhadA = "hhad_a";
            oddsModelHead.sportteryAllModel = sportteryAllEntity;
            oddsModelHead.sportteryAllModel.hadH = "s_hadH";
            oddsModelHead.sportteryAllModel.hadD = "s_hadD";
            oddsModelHead.sportteryAllModel.hadA = "s_hadA";
            oddsModelHead.sportteryAllModel.hhadH = "s_hhadH";
            oddsModelHead.sportteryAllModel.hhadD = "s_hhadD";
            oddsModelHead.sportteryAllModel.hhadA = "s_hhadA";
            oddsModelHead.sportteryAllModel.hhadLine = "s_hhadLine";
            oddsModelHead.sportteryAllModel.hafuHh = "s_hafuHh";
            oddsModelHead.sportteryAllModel.hafuHd = "s_hafuHd";
            oddsModelHead.sportteryAllModel.hafuHa = "s_hafuHa";
            oddsModelHead.sportteryAllModel.hafuDh = "s_hafuDh";
            oddsModelHead.sportteryAllModel.hafuDd = "s_hafuDd";
            oddsModelHead.sportteryAllModel.hafuDa = "s_hafuDa";
            oddsModelHead.sportteryAllModel.hafuAh = "s_hafuAh";
            oddsModelHead.sportteryAllModel.hafuAd = "s_hafuAd";
            oddsModelHead.sportteryAllModel.hafuAa = "s_hafuAa";
            oddsModelHead.sportteryAllModel.ttg0 = "s_ttg0";
            oddsModelHead.sportteryAllModel.ttg1 = "s_ttg1";
            oddsModelHead.sportteryAllModel.ttg2 = "s_ttg2";
            oddsModelHead.sportteryAllModel.ttg3 = "s_ttg3";
            oddsModelHead.sportteryAllModel.ttg4 = "s_ttg4";
            oddsModelHead.sportteryAllModel.ttg5 = "s_ttg5";
            oddsModelHead.sportteryAllModel.ttg6 = "s_ttg6";
            oddsModelHead.sportteryAllModel.ttg7Up = "s_ttg7Up";
            oddsModelHead.sportteryAllModel.score10 = "s_score10";
            oddsModelHead.sportteryAllModel.score20 = "s_score20";
            oddsModelHead.sportteryAllModel.score21 = "s_score21";
            oddsModelHead.sportteryAllModel.score30 = "s_score30";
            oddsModelHead.sportteryAllModel.score31 = "s_score31";
            oddsModelHead.sportteryAllModel.score32 = "s_score32";
            oddsModelHead.sportteryAllModel.score40 = "s_score40";
            oddsModelHead.sportteryAllModel.score41 = "s_score41";
            oddsModelHead.sportteryAllModel.score42 = "s_score42";
            oddsModelHead.sportteryAllModel.score50 = "s_score50";
            oddsModelHead.sportteryAllModel.score51 = "s_score51";
            oddsModelHead.sportteryAllModel.score52 = "s_score52";
            oddsModelHead.sportteryAllModel.scoreHElse = "s_scoreHElse";
            oddsModelHead.sportteryAllModel.score00 = "s_score00";
            oddsModelHead.sportteryAllModel.score11 = "s_score11";
            oddsModelHead.sportteryAllModel.score22 = "s_score22";
            oddsModelHead.sportteryAllModel.score33 = "s_score33";
            oddsModelHead.sportteryAllModel.scoreDElse = "s_scoreDElse";
            oddsModelHead.sportteryAllModel.score01 = "s_score01";
            oddsModelHead.sportteryAllModel.score02 = "s_score02";
            oddsModelHead.sportteryAllModel.score12 = "s_score12";
            oddsModelHead.sportteryAllModel.score03 = "s_score03";
            oddsModelHead.sportteryAllModel.score13 = "s_score13";
            oddsModelHead.sportteryAllModel.score23 = "s_score23";
            oddsModelHead.sportteryAllModel.score04 = "s_score04";
            oddsModelHead.sportteryAllModel.score14 = "s_score14";
            oddsModelHead.sportteryAllModel.score24 = "s_score24";
            oddsModelHead.sportteryAllModel.score05 = "s_score05";
            oddsModelHead.sportteryAllModel.score15 = "s_score15";
            oddsModelHead.sportteryAllModel.score25 = "s_score25";
            oddsModelHead.sportteryAllModel.scoreAElse = "s_scoreAElse";
            data.push(oddsModelHead);
            $.each($scope.oddsList, function (i, value) {
                if (value.choose) {
                    data.push(value);
                }
            });
            $.ajax({
                type: "POST",
                url: "matchOddsExcel.do",
                data: {
                    "oddsModels": JSON.stringify(data)
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