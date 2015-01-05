/**
 * Created by user on 02.01.2015.
 */
function EditCtrl($scope, $routeParams, Restangular) {

    $scope.state = {
        model: {}
    };

    $scope.show = function(id) {
        Restangular.one("gene/show", id).get().then(function(result) {
            $scope.state.model.id = result.id;
            $scope.state.model.name = result.name;
        });
    };

    $scope.show($routeParams.id);

    $scope.save = function(id) {
        Restangular.one("gene", id).all("update").post($scope.state.model).then(function(result) {
            $scope.state.model.id = result.id;
            $scope.state.model.name = result.name;
        });
    }
}
