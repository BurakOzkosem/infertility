function GeneEditCtrl($scope, $modalInstance, model, Restangular, focusInput) {

    $scope.state = {
        model : angular.isDefined(model) && model != null
            ? angular.copy(model)
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
        selected: false
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
        return $scope.state.selected == item;
    };

    $scope.setSelected = function (item) {
        if ($scope.state.selected == item) {
            //$scope.state.selected = false;
        } else {
            $scope.state.selected = item;
        }
    };

    // Load phenotypes reference values for select box
    $scope.loadReferences = function() {
        Restangular.oneUrl('gene/reference/ontologyterm').get().then(function(result) {
                $scope.reference.ontologyTerm = result;
            },
            function() { });
    };

    // Save button
    $scope.ok = function() {
        if($scope.state.model.id != null) {
            Restangular.all("gene/update").post($scope.state.model).then(function(result) {
                $modalInstance.close(true);
            }, function(error) {});
        }
        else {
            if($scope.state.model.geneAnnotationList.length == 0) {
                $scope.state.model.geneAnnotationList.push({
                    id: null,
                    ontologyTermId: null,
                    evidenceBaseAnnotationsSubjectBackgroundName: '-',
                    evidenceBaseAnnotationsSubjectZygosity: null,
                    publicationId: '-',
                    publicationDoi: null
                });
            }

            Restangular.all("gene/add").post($scope.state.model).then(function(result) {
                $modalInstance.close(true);
            }, function(error) {});
        }
    };

    // Cancel button
    $scope.cancel = function() {
        $modalInstance.close(false);
    };

    // Remove sequence feature from table button
    $scope.removeSequenceFeature = function(property) {
        var index = $scope.state.model.sequenceFeatureEditList.indexOf(property);
        if (index > -1) {
            $scope.state.model.sequenceFeatureEditList.splice(index, 1);
        }
    };

    // Remove gene annotation from table button
    $scope.removeGeneAnotation = function(property) {
        var index = $scope.state.model.geneAnnotationList.indexOf(property);
        if (index > -1) {
            $scope.state.model.geneAnnotationList.splice(index, 1);
        }
    };

    // Remove homology from table button
    $scope.removeHomology = function(property) {
        var index = $scope.state.model.homologyEditList.indexOf(property);
        if (index > -1) {
            $scope.state.model.homologyEditList.splice(index, 1);
        }
    };

    $scope.getSelectValue = function(ontologyTermId){
        return $scope.reference.ontologyTerm[ontologyTermId-1].value;
    };

    // Catching event of typing something in the last row of sequence feature table
    // Adds new row below and set focus back to edited row
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

            var element = document.activeElement;

            $scope.state.model.sequenceFeatureEditList.push($scope.newSequenceFeature);

            var lastAdded = $scope.newSequenceFeature;

            $scope.newSequenceFeature = {
                id: null,
                ontologyTermId: null,
                ontologyTermName: null,
                evidenceWithText: null
            };

            $scope.setSelected(lastAdded);
            focusInput('td1' + ($scope.state.model.sequenceFeatureEditList.length -1));
        },
        true
    );

    // Catching event of typing something in the last row of gene annotation table
    // Adds new row below and set focus back to edited row
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
            focusInput('td2' + ($scope.state.model.geneAnnotationList.length -1));
        },
        true
    );

    // Catching event of typing something in the last row of homology table
    // Adds new row below and set focus back to edited row
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
            focusInput('td3' + ($scope.state.model.homologyEditList.length -1));
        },
        true
    );

    $scope.loadReferences();
}