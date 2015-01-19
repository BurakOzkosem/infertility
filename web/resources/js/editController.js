/**
 * Created by user on 02.01.2015.
 */
function EditCtrl($scope, $routeParams, Restangular) {

    $scope.state = {
        rdOnly: true,
        backup: {},
        functionAccordeon: { isFirstOpen: true },
        model: {
            ontologyAnnotationEdit: {
                id: null,
                evidenceEdit: {
                    id: null,
                    publicationEdit: {
                        id: null,
                        doi: ''
                    },
                    baseAnnotationsSubjectBackgroundName: '',
                    baseAnnotationsSubjectZygosity: ''
                },
                ontologyTermEdit: {
                    id: null,
                    primaryIdentifier: ''
                },
                subjectEdit: {
                    id: null,
                    primaryIdentifier: '',
                    symbol: '',
                    chromosomeName: '',
                    name: '',
                    dsc: ''
                }
            },
            geneEdit: {
                id: null,
                primaryIdentifier: '',
                symbol: '',
                organismName: '',
                ncbi: '',
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
                ]
            }
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

    $scope.show = function(id) {
        Restangular.one("gene/showDetails", id).get().then(function(result) {
            $scope.state.model = result;
        });
    };

    $scope.update = function() {
        Restangular.all("details/update").post($scope.state.model).then(function(result) {
            $scope.state.model = result;
        });
    };

    $scope.editOrSave = function() {
        if($scope.isReadOnly()) {
            // Edit
            $scope.state.rdOnly = false;
            $scope.state.backup = angular.copy($scope.state.model);
        }
        else {
            // Save
            Restangular.all("details/save").post($scope.state.model).then(function(result) {
                $scope.state.model = result;
            });
            $scope.state.backup = {};
        }
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

//    $scope.save = function(id) {
//        Restangular.one("gene", id).all("update").post($scope.state.model).then(function(result) {
//            $scope.state.model.id = result.id;
//            $scope.state.model.name = result.name;
//        });
//    }

    $scope.show($routeParams.id);
}
