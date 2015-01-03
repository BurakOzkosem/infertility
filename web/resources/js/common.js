/**
 * Created by user on 03.01.2015.
 */
angular.module('geneSearchApp', [])
.directive('gsZebraRows', function() {
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