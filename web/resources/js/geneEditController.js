/**
 * Created by kmorozov on 21.01.2015.
 */
function GeneEditCtrl($scope, $modalInstance, model, Restangular) {

    $scope.state = {
        model : angular.isDefined(model) && model != null
            ? model
            : {
                    id: null,
                    primaryIdentifier: null,
                    symbol: null,
                    name: null,
                    dsc: null,
                    chromosome: null,
                    ncbi: null,
                    organismName: null,
                    homologyEditList: [
                    ],
                    sequenceFeatureEditList: [
                    ],
                    geneAnnotationList: [
                    ]
                },
        sequenceFeatureSelected: false
    };

    $scope.newSequenceFeature = {
        id: null,
        ontologyTermId: null,
        ontologyTermName: null,
        evidenceWithText: null
    };

    $scope.newGeneAnnotation = {
        id: null,
        ontologyTermId: null,
        evidenceBaseAnnotationsSubjectBackgroundName: null,
        evidenceBaseAnnotationsSubjectZygosity: null,
        publicationId: null,
        publicationDoi: null
    };

    $scope.newHomology = {
        id: null,
        primaryIdentifier: null,
        symbol: null,
        organismName: null,
        type: null,
        datasetsName: null
    };

    $scope.reference = {
        ontologyTerm: []
    };

    $scope.isSelected = function (item) {
        return $scope.state.sequenceFeatureSelected == item;
    };

    $scope.setSelected = function (item) {
        if ($scope.state.sequenceFeatureSelected == item) {
            //$scope.state.selected = false;
        } else {
            $scope.state.sequenceFeatureSelected = item;
        }
    };

    $scope.loadReferences = function() {
        Restangular.oneUrl('gene/reference/ontologyterm').get().then(function(result) {
                $scope.reference.ontologyTerm = result;
            },
            function() { });
    };

    $scope.ok = function() {
        if($scope.state.model.id != null) {
            Restangular.all("gene/update").post($scope.state.model).then(function(result) {
                $modalInstance.close(true);
            }, function(error) {});
        }
        else {
            Restangular.all("gene/add").post($scope.state.model).then(function(result) {
                $modalInstance.close(true);
            }, function(error) {});
        }
    };

    $scope.cancel = function() {
        $modalInstance.close(false);
    };

    $scope.$watch( 'newSequenceFeature',
        function(newValue, oldValue) {
            var hasNotNullProperty = false;
            angular.forEach(newValue, function(value, key) {
                if(value != null) {
                    hasNotNullProperty = true;
                }
            });

            if(!hasNotNullProperty) {
                return;
            }

            $scope.state.model.sequenceFeatureEditList.push($scope.newSequenceFeature);

            var lastAdded = $scope.newSequenceFeature;

            $scope.newSequenceFeature = {
                id: null,
                ontologyTermId: null,
                ontologyTermName: null,
                evidenceWithText: null
            };

            $scope.setSelected(lastAdded);
        },
        true
    );

    $scope.removeSequenceFeature = function(property) {
        var index = $scope.state.model.sequenceFeatureEditList.indexOf(property);
        if (index > -1) {
            $scope.state.model.sequenceFeatureEditList.splice(index, 1);
        }
    };
    $scope.removeGeneAnotation = function(property) {
        var index = $scope.state.model.geneAnnotationList.indexOf(property);
        if (index > -1) {
            $scope.state.model.geneAnnotationList.splice(index, 1);
        }
    };
    $scope.removeHomology = function(property) {
        var index = $scope.state.model.homologyEditList.indexOf(property);
        if (index > -1) {
            $scope.state.model.homologyEditList.splice(index, 1);
        }
    };

    $scope.$watch( 'newGeneAnnotation',
        function(newValue, oldValue) {
            var hasNotNullProperty = false;
            angular.forEach(newValue, function(value, key) {
                if(value != null) {
                    hasNotNullProperty = true;
                }
            });

            if(!hasNotNullProperty) {
                return;
            }

            $scope.state.model.geneAnnotationList.push($scope.newGeneAnnotation);

            var lastAdded = $scope.newGeneAnnotation;

            $scope.newGeneAnnotation = {
                id: null,
                ontologyTermId: null,
                evidenceBaseAnnotationsSubjectBackgroundName: null,
                evidenceBaseAnnotationsSubjectZygosity: null,
                publicationId: null,
                publicationDoi: null
            };

            $scope.setSelected(lastAdded);
        },
        true
    );

    $scope.$watch( 'newHomology',
        function(newValue, oldValue) {
            var hasNotNullProperty = false;
            angular.forEach(newValue, function(value, key) {
                if(value != null) {
                    hasNotNullProperty = true;
                }
            });

            if(!hasNotNullProperty) {
                return;
            }

            $scope.state.model.homologyEditList.push($scope.newHomology);

            var lastAdded = $scope.newHomology;

            $scope.newHomology = {
                id: null,
                primaryIdentifier: null,
                symbol: null,
                organismName: null,
                type: null,
                datasetsName: null
            };

            $scope.setSelected(lastAdded);
        },
        true
    );

    $scope.loadReferences();
}