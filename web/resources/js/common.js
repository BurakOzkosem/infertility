/**
 * Created by user on 03.01.2015.
 */
var geneSearchApp = angular.module('geneSearchApp', ['ui.bootstrap', 'restangular', 'ngRoute', 'LocalStorageModule', 'directive.loading']);

geneSearchApp.constant('BASE_PATH', '/genesearch');
geneSearchApp.constant('API_END_POINT', '/api');

geneSearchApp.filter('unsafe', ['$sce', function ($sce) {
    return function (val) {
        return $sce.trustAsHtml(val);
    };
}]);

geneSearchApp.config(function (RestangularProvider, $sceProvider, BASE_PATH, API_END_POINT) {

    $sceProvider.enabled(false);
//            $parseProvider.unwrapPromises(true);
    RestangularProvider.setBaseUrl(BASE_PATH + API_END_POINT);

//            $httpProvider.interceptors.push('HttpInterceptor');

//            if (!$httpProvider.defaults.headers.get) {
//                $httpProvider.defaults.headers.get = {};
//            }
//            $httpProvider.defaults.headers.get['If-Modified-Since'] = 'Sat, 01 Jan 2000 00:00:00 GMT';
//            $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
//            $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';
});

geneSearchApp.config(function($locationProvider, $routeProvider) {
    $routeProvider
        .when('/', {
            controller: SearchCtrl,
            templateUrl:'page/search.html'
        })
        .when('/edit/:id', {
            controller: EditCtrl,
            templateUrl:'page/detail.html'
        })
        .when('/downloader', {
            controller: DownloaderCtrl,
            templateUrl:'page/downloader.html'
        })
        .otherwise({
            redirectTo:'/'
        });
//            $locationProvider.html5Mode(true);
});

geneSearchApp.directive('gsZebraRows', function() {
    var rowColor = function(element, index, isSelected) {
        if (angular.isDefined(index) && index != null) {
            if (angular.isDefined(isSelected) && isSelected != null && isSelected === true) {
                element.addClass('active');
                element.removeClass('gs-row-default');
                element.removeClass('gs-row-hover');
            } else {
                if (index % 2 == 0) {
                    element.addClass('gs-row-default');
                    element.removeClass('active');
                    element.removeClass('gs-row-hover');
                } else {
                    element.addClass('gs-row-hover');
                    element.removeClass('gs-row-default');
                    element.removeClass('active');
                }
            }
        } else {
            element.addClass('gs-row-default');
            element.removeClass('active');
            element.removeClass('gs-row-hover');
        }
    };

    var link = function(scope, element, attrs) {
        if (angular.isDefined(attrs) && attrs != null && angular.isDefined(attrs.gsIndex)) {
            rowColor(element, attrs.gsIndex, false);
        }
    };

    return {
        link: link
    };
});

//geneSearchApp.directive('customPopover', function () {
//    return {
//        restrict: 'A',
//        template: '<span>{{label}}</span>',
//        link: function (scope, el, attrs) {
//            scope.label = attrs.popoverLabel;
//
//            $(el).popover({
//                trigger: 'click',
//                html: true,
//                content: attrs.popoverHtml,
//                placement: attrs.popoverPlacement
//            });
//        }
//    };
//});

geneSearchApp.directive('normalize', function(){
    return{
        restrict: 'A',
        link: function(scope, element, attrs){
            var id = element[0].id;
            //if(angular.isDefined(id) && id.length > 0 && id.indexOf('tdLeft') == 0) {
                element[0].style.left = 5;
            //}

            //element[0].style.left = element[0].style.left < 0 ? 5 : element[0].style.left;
        }
    }
});

geneSearchApp.directive('ngEnter', function() {
    return function(scope, element, attrs) {
        element.bind("keydown", function(event) {
            if(angular.isDefined(event) && angular.isDefined(event.which) && event.which === 13) {
                scope.$apply(function(){
                    if (angular.isDefined(attrs) && angular.isDefined(attrs.ngEnter)) {
                        scope.$eval(attrs.ngEnter, {'event': event});
                    }
                });

                if (angular.isDefined(event.preventDefault)) {
                    event.preventDefault();
                }
            }
        });
    };
});

geneSearchApp.factory('focusInput', function($timeout) {
    return function(id) {
        // timeout makes sure that is invoked after any other event has been triggered.
        // e.g. click events that need to run before the focus or
        // inputs elements that are in a disabled state but are enabled when those events
        // are triggered.
        $timeout(function() {
            var element = document.getElementById(id);
            if(element)
                element.focus();
                element.select();
        });
    };
});

angular.module("template/popover/popover.html", []).run(["$templateCache", function ($templateCache) {
    $templateCache.put("template/popover/popover.html",
        "<div class=\"popover {{placement}}\" ng-class=\"{ in: isOpen(), fade: animation() }\" normalize>\n" +
        "  <div class=\"arrow\"></div>\n" +
        "\n" +
        "  <div class=\"popover-inner\">\n" +
        "      <h3 class=\"popover-title\" ng-bind-html=\"title | unsafe\" ng-show=\"title\" style=\"background-color: rgba(227, 227, 227, 0.9)\"></h3>\n" +
        "      <div class=\"popover-content\"ng-bind-html=\"content | unsafe\"></div>\n" +
        "  </div>\n" +
        "</div>\n" +
        "");
}]);

//geneSearchApp.directive("setFocus",function(){
//
//    var link = function(scope,elem,attrs){
//
//        angular.element(elem[0]).on('blur',function(){
//            scope.$apply(function(){
//                scope.doFocus=false;
//            });
//        });
//
//        scope.$watch("doFocus",function(v){
//            if(v==true){
//                elem[0].focus();
//            }
//        });
//    };
//    return {
//        "restrict" : "A",
//        "link" : link,
//        "scope" : {doFocus : "=setFocus"}
//    };
//});
