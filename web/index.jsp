<%--<%@ page import="java.util.Date" %>--%>
<!doctype html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html xmlns:ng="http://angularjs.org" id="ng-app" ng-app="geneSearchApp">
<head>
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
    <%--<script src="<c:url value="/resources/new/angular-locale_ru-ru.js"/>?v=<%=version%>"></script>--%>
    <script src="<c:url value="/resources/lib/underscore-min.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/ui-bootstrap-tpls.min.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/restangular.min.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/angular-resource.min.js"/>?v=<%=version%>"></script>
    <script src="<c:url value="/resources/lib/angular-route.min.js"/>?v=<%=version%>"></script>
    <%--<script src="<c:url value="/resources/new/ng-breadcrumbs.js"/>?v=<%=version%>"></script>--%>

    <%--<script src="<c:url value="/resources/lib/angular-class-extender.js"/>?v=<%=version%>"></script>--%>

    <%--<script src="<c:url value="/resources/new/angular-file-upload.js"/>?v=<%=version%>"></script>--%>

    <%--<script src="<c:url value="/resources/new/angular-cookies.js"/>?v=<%=version%>"></script>--%>
    <%--<script src="<c:url value="/resources/new/angularLocalStorage.js"/>?v=<%=version%>"></script>--%>



    <script type="text/javascript">
        var geneSearchApp = angular.module('geneSearchApp', ['ui.bootstrap', 'restangular', 'ngRoute']);

        geneSearchApp.constant('BASE_PATH', '<c:url value="/"/>');
        geneSearchApp.constant('API_END_POINT', '<c:url value="/api"/>');

        geneSearchApp.config(function (RestangularProvider, $sceProvider, API_END_POINT) {
            $sceProvider.enabled(false);
//            $parseProvider.unwrapPromises(true);
            RestangularProvider.setBaseUrl(API_END_POINT);

//            $httpProvider.interceptors.push('HttpInterceptor');

//            if (!$httpProvider.defaults.headers.get) {
//                $httpProvider.defaults.headers.get = {};
//            }
//            $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Sat, 01 Jan 2000 00:00:00 GMT';
//            $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
//            $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
        });

        geneSearchApp.config(function($routeProvider) {
            $routeProvider
                    .when('/', {
                        controller: SearchCtrl,
                        templateUrl:'page/search.html'
                    })
                    .when('/edit', {
                        controller: EditCtrl,
                        templateUrl:'page/detail.html'
                    })
                    .otherwise({
                        redirectTo:'/'
                    });
        });

        function MainCtrl() {
        }

    </script>
    <% double key = 101d;//Math.random() * 100;%>
    <script src="<c:url value="/resources/js/searchController.js"/>?v=<%=key%>"></script>
    <script src="<c:url value="/resources/js/editController.js"/>?v=<%=key%>"></script>
    <link href='<c:url value="/resources/css/bootstrap.css"/>?v=<%=version%>' rel="stylesheet">

    <!--[if lte IE 9]>
    <script src='<c:url value="/resources/lib/respond.min.js"/>?v=<%=version%>'></script>
    <![endif]-->

    <%--<link href='<c:url value="/resources/css/application.css"/>?v=<%=version%>' rel="stylesheet">--%>

</head>
<body>

<h2>    GENESEARCH PROJECT STARTED </h2>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <div ng-view></div>
        </div>
    </div>
</div>

</body>
</html>
