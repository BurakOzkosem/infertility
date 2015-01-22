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
                        {
                            id: null,
                            primaryIdentifier: null,
                            symbol: null,
                            organismName: null,
                            type: null,
                            datasetsName: null
                        }
                    ],
                    sequenceFeatureEditList: [
                        {
                            id: null,
                            ontologyTermId: null,
                            ontologyTermName: null,
                            evidenceWithText: null
                        }
                    ],
                    geneAnnotationList: [
                        {
                            id: null,
                            ontologyTermId: null,
                            evidenceBaseAnnotationsSubjectBackgroundName: null,
                            evidenceBaseAnnotationsSubjectZygosity: null,
                            publicationId: null,
                            publicationDoi: null
                        }
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
                $modalInstance.close();
            }, function(error) {});
        }
        else {
            Restangular.all("gene/add").post($scope.state.model).then(function(result) {
                $modalInstance.close();
            }, function(error) {});
        }
    };

    $scope.cancel = function() {
        $modalInstance.close('cancel');
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

            $scope.newSequenceFeature = {
                id: null,
                ontologyTermId: null,
                ontologyTermName: null,
                evidenceWithText: null
            };
        },
        true
    );

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

            $scope.newGeneAnnotation = {
                id: null,
                ontologyTermId: null,
                evidenceBaseAnnotationsSubjectBackgroundName: null,
                evidenceBaseAnnotationsSubjectZygosity: null,
                publicationId: null,
                publicationDoi: null
            };
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

            $scope.newHomology = {
                id: null,
                primaryIdentifier: null,
                symbol: null,
                organismName: null,
                type: null,
                datasetsName: null
            };
        },
        true
    );
}