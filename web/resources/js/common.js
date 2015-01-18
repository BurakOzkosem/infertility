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

geneSearchApp.directive('customPopover', function () {
    return {
        restrict: 'A',
        template: '<span>{{label}}</span>',
        link: function (scope, el, attrs) {
            scope.label = attrs.popoverLabel;

            $(el).popover({
                trigger: 'click',
                html: true,
                content: attrs.popoverHtml,
                placement: attrs.popoverPlacement
            });
        }
    };
});

angular.module("template/popover/popover.html", []).run(["$templateCache", function ($templateCache) {
    $templateCache.put("template/popover/popover.html",
            "<div class=\"popover {{placement}}\" ng-class=\"{ in: isOpen(), fade: animation() }\">\n" +
            "  <div class=\"arrow\"></div>\n" +
            "\n" +
            "  <div class=\"popover-inner\">\n" +
            "      <h3 class=\"popover-title\" ng-bind-html=\"title | unsafe\" ng-show=\"title\"></h3>\n" +
            "      <div class=\"popover-content\"ng-bind-html=\"content | unsafe\"></div>\n" +
            "  </div>\n" +
            "</div>\n" +
            "");
}]);

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

//geneSearchApp.directive('popOver', function($http) {
//    return {
//        restrict: 'C',
//        link: function(scope, element, attr) {
//            var i =0;
//            element.tooltip();
//            $(element).bind('mouseover',function(e) {
//
//                $http.get("test").success(function(){
//                    attr.$set('originalTitle', "Some text "+i++);
//                    element.tooltip('show');
//                });
//            });
//        }
//    }
//});

//geneSearchApp.directive('customPopover', function($compile, $templateCache, $q, $http) {
//
//    var getTemplate = function(contentType) {
//        var def = $q.defer();
//
//        var template = '';
//        switch (contentType) {
//            case 'user':
//                template = $templateCache.get("popoverTemplate.html");
//                if (typeof template === "undefined") {
//                    $http.get("page/popoverTemplate.html")
//                        .success(function(data) {
//                            $templateCache.put("popoverTemplate.html", data);
//                            def.resolve(data);
//                        });
//                }else {
//                    def.resolve(template);
//                }
//                break;
//        }
//        return def.promise;
//    }
//    return {
//        restrict: "A",
//        link: function(scope, element, attrs) {
//            getTemplate("user").then(function(popOverContent) {
//                var options = {
//                    content: popOverContent,
//                    placement: "top",
//                    html: true,
//                    date: scope.date
//                };
//                $(element).popover(options);
//            });
//        }
//    };
//});