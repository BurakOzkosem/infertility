
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
    RestangularProvider.setBaseUrl(BASE_PATH + API_END_POINT);
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

// Execute on enter keypress event
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

// Set focus on input element
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
        "<div class=\"popover {{placement}}\" ng-class=\"{ in: isOpen(), fade: animation() }\">\n" +
        "  <div class=\"arrow\"></div>\n" +
        "\n" +
        "  <div class=\"popover-inner\">\n" +
        "      <h3 class=\"popover-title\" ng-bind-html=\"title | unsafe\" ng-show=\"title\" style=\"background-color: rgba(227, 227, 227, 0.9)\"></h3>\n" +
        "      <div class=\"popover-content\"ng-bind-html=\"content | unsafe\"></div>\n" +
        "  </div>\n" +
        "</div>\n" +
        "");
}]);

