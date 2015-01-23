<%--<%@ page import="java.util.Date" %>--%>
<!doctype html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="geneSearchApp">
<head>
    <%--<base href="/">--%>
    <title>GENESEARCH</title>
    <meta http-equiv="X-UA-Compatible" content="IE=8" />
    <meta name="viewport" content="width=device-width, initial-scale=0.8">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="cache-control" content="no-cache; no-store; must-revalidate">
    <meta http-equiv="Content-Type" content="html; charset=utf-8;"/>

    <% double version = 2d;%>

    <%--<script src='<c:url value="/resources/new/es5-shim.js"/>?v=<%=version%>'></script>--%>

    <!--[if lte IE 8]>
    <script>
        document.createElement('ng-include');
        document.createElement('ng-pluralize');
        document.createElement('ng-view');
        document.createElement('ng-controller');
        document.createElement('ng:include');
        document.createElement('ng:pluralize');
        document.createElement('ng:view');
        document.createElement('pagination');

    </script>
    <![endif]-->
    <!--[if lte IE 9]>
    <script src='<c:url value="/resources/lib/html5shiv.min.js"/>?v=<%=version%>'></script>
    <script src='<c:url value="/resources/lib/html5shiv-printshiv.min.js"/>?v=<%=version%>'></script>
    <![endif]-->
    <!--[if lte IE 8]>
    <script src='<c:url value="/resources/lib/json3.min.js"/>?v=<%=version%>'></script>
    <![endif]-->

    <script src="<c:url value="/resources/lib/jquery-1.11.2.min.js"/>?v=<%=version%>"></script>

    <script src="<c:url value="/resources/lib/angular.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/underscore-min.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/ui-bootstrap-tpls.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/restangular.min.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/angular-resource.min.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/angular-route.min.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/angular-local-storage.min.js"/>?v=<%=version%>"></script>

    <% double key = 101d;//Math.random() * 100;%>

    <script src="<c:url value="/resources/js/loading.js"/>?v=<%=key%>"></script>

    <script src="<c:url value="/resources/js/common.js"/>?v=<%=key%>"></script>
    <script src="<c:url value="/resources/js/searchController.js"/>?v=<%=key%>"></script>
    <script src="<c:url value="/resources/js/detailsController.js"/>?v=<%=key%>"></script>
    <script src="<c:url value="/resources/js/geneEditController.js"/>?v=<%=key%>"></script>
    <script src="<c:url value="/resources/js/downloader.js"/>?v=<%=key%>"></script>

    <link href='<c:url value="/resources/css/bootstrap.css"/>?v=<%=version%>' rel="stylesheet">
    <link href='<c:url value="/resources/css/application.css"/>?v=<%=version%>' rel="stylesheet">

    <!--[if lte IE 9]>
        <script src='<c:url value="/resources/lib/respond.min.js"/>?v=<%=version%>'></script>
    <![endif]-->

</head>
<body>

<div class="col-sm-5">
</div>

<div class="col-sm-1">
    <h1>GENESEARCH</h1>
</div>

<div class="col-sm-3">
</div>

<div class="col-sm-2" style="padding-top: 20px;">
    <a href="#/downloader">
        <i class="pull-left glyphicon glyphicon-align-justify" style="font-size: 12px"></i>
    </a>
</div>

<div class="col-sm-1">
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div ng-view></div>
        </div>
    </div>
</div>

<div id="mydiv" loading>
    <img src="/genesearch/resources/img/loading.gif" class="ajax-loader"/>
</div>

</body>
</html>
