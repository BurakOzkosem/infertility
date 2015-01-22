/**
 * Created by user on 02.01.2015.
 */
function EditCtrl($scope, $routeParams, $modal, Restangular, BASE_PATH) {

    $scope.state = {
        rdOnly: true,
        backup: {},
        functionAccordeon: { isFirstOpen: true },
        model: {
            id: null,
            primaryIdentifier: '',
            symbol: '',
            name: '',
            dsc: '',
            chromosome: '',
            ncbi: '',
            organismName: '',
            homologyEditList: [
                {
                    id: null,
                    primaryIdentifier: '',
                    symbol: '',
                    organismName: '',
                    type: '',
                    datasetsName: ''
                }
            ],
            sequenceFeatureEditList: [
                {
                    id: null,
                    ontologyTermId: '',
                    ontologyTermName: '',
                    evidenceWithText: ''
                }
            ],
            geneAnnotationList: [],
            phenotypes: '',
            chromosomes: ''
        }
    };

    $scope.sorts = {
        property: "id",
        direction: true
    };

    $scope.reference = {
        ontologyTerm: []
    };

    $scope.loadReferences = function() {
        Restangular.oneUrl('gene/reference/ontologyterm').get().then(function(result) {
                $scope.reference.ontologyTerm = result;
            },
            function() { });
    };

    Array.prototype.contains = function(obj) {
        var i = this.length;
        while (i--) {
            if (this[i] == obj) {
                return true;
            }
        }
        return false;
    };

    $scope.show = function(id) {
        Restangular.one("gene/showDetails", id).get().then(function(result) {
            $scope.state.model = result;

            var phenotypeArray = [];
            for(var i=0; i < $scope.state.model.geneAnnotationList.length; i++) {
                if($scope.state.model.geneAnnotationList[i].ontologyTermName != null
                    && $scope.state.model.geneAnnotationList[i].ontologyTermName.length > 0
                    && !phenotypeArray.contains($scope.state.model.geneAnnotationList[i].ontologyTermName)) {
                    phenotypeArray.push($scope.state.model.geneAnnotationList[i].ontologyTermName);
                }
            }
            var phenotypes = '';
            for(var i=0; i < phenotypeArray.length; i++) {
                phenotypes = phenotypes + phenotypeArray[i] + ", ";
            }
            $scope.state.model.phenotypes = phenotypes.substring(0, phenotypes.length - 2);

        });
    };

    $scope.openEdit = function() {
        var modalInstance = $modal.open({
            templateUrl: BASE_PATH+'/page/edit.html',
            controller: GeneEditCtrl,
            windowClass: 'modal-large',
            resolve: {
                model: function() {
                    return angular.isDefined($scope.state.model)  &&  $scope.state.model ? $scope.state.model : null;
                }
            },
            backdrop: 'static'
        });

        modalInstance.result.then(function(result) {
            if(result == false) {
                $scope.show($routeParams.id);
            }
        });
    };

    $scope.cancel = function() {
        $scope.state.rdOnly = true;
        $scope.state.model = angular.copy($scope.state.backup);
        $scope.state.backup = {};
    };


    $scope.getEditButtonText = function() {
        return $scope.isReadOnly() ? "Edit" : "Save";
    };

    $scope.isReadOnly = function() {
        return $scope.state.rdOnly != false;
    };

    $scope.canSort = function() {

    };

    $scope.show($routeParams.id);
}
