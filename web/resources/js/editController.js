/**
 * Created by user on 02.01.2015.
 */
function EditCtrl($scope, $routeParams, Restangular) {

    $scope.state = {
        model: {}
    };

    Restangular.one("gene/show",$routeParams.id).get().then(function(result) {
        $scope.state.model.id = result.id;
        $scope.state.model.name = result.name;
    });

}