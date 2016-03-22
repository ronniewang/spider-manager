<%--<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<ul class="list-group">
    <div class="panel panel-default">
        <div class="panel-body">
            <b>
                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                <%--Welcome <%=SecurityContextHolder.getContext().getAuthentication().getName()%>--%>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a onclick="document.getElementById('logoutForm').submit();">退出</a>
            </b>
        </div>
    </div>
    <form type="hidden" id="logoutForm" action="/logout" method="post">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
    <a href="listMatchLeaguePage">
        <li class="list-group-item">联赛列表</li>
    </a>
    <a href="listMatchTeamPage">
        <li class="list-group-item">队名列表</li>
    </a>
    <a href="listMatchPage">
        <li class="list-group-item">赛事列表</li>
    </a>
    <a href="index">
        <li class="list-group-item">赔率信息</li>
    </a>
    <a href="listProductPage">
        <li class="list-group-item">奖池信息</li>
    </a>
    <a href="listServiceStatePage">
        <li class="list-group-item">爬虫运行信息</li>
    </a>
    <a href="exportMatchInfoPage">
        <li class="list-group-item">导出比赛信息</li>
    </a>
</ul>