/**
 * Created by user on 03.01.2015.
 */
var geneSearchApp = angular.module('geneSearchApp', ['ui.bootstrap', 'restangular', 'ngRoute', 'LocalStorageModule', 'directive.loading']);

geneSearchApp.constant('BASE_PATH', '/');
geneSearchApp.constant('API_END_POINT', '/api');

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
})