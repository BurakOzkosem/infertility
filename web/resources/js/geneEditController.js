/**
 * Created by kmorozov on 21.01.2015.
 */
function GeneEditCtrl($scope, $modalInstance, model) {

    $scope.state = {
        model : angular.isDefined(model) && model != null
            ? model
            : {
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
                    geneAnnotationList: [
                        {
                            evidenceBaseAnnotationsSubjectBackgroundName: '',
                            evidenceBaseAnnotationsSubjectZygosity: '',
                            publicationId: '',
                            publicationDoi: ''
                        }
                    ]
                },
        sequenceFeatureSelected: false
    };

    $scope.newSequenceFeature = {
        id: null,
        ontologyTermId: '',
        ontologyTermName: '',
        evidenceWithText: ''
    };

    $scope.newGeneAnnotation = {
        evidenceBaseAnnotationsSubjectBackgroundName: null,
        evidenceBaseAnnotationsSubjectZygosity: '',
        publicationId: '',
        publicationDoi: ''
    };

    $scope.newHomology = {
        id: null,
        primaryIdentifier: '',
        symbol: '',
        organismName: '',
        type: '',
        datasetsName: ''
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

    $scope.ok = function() {
        $modalInstance.close();
    };

    $scope.cancel = function() {
        $modalInstance.close('cancel');
    };

    $scope.$watch( 'newSequenceFeature',
        function() {
            $scope.state.model.sequenceFeatureEditList.push($scope.newSequenceFeature);

            $scope.newSequenceFeature = {
                id: null,
                ontologyTermId: '',
                ontologyTermName: '',
                evidenceWithText: ''
            };
        },
        true
    );

    $scope.$watch( 'newGeneAnnotation',
        function() {
            $scope.state.model.geneAnnotationList.push($scope.newGeneAnnotation);

            $scope.newGeneAnnotation = {
                evidenceBaseAnnotationsSubjectBackgroundName: null,
                evidenceBaseAnnotationsSubjectZygosity: '',
                publicationId: '',
                publicationDoi: ''
            };
        },
        true
    );

    $scope.$watch( 'newHomology',
        function() {
            $scope.state.model.homologyEditList.push($scope.newHomology);

            $scope.newHomology = {
                id: null,
                primaryIdentifier: '',
                symbol: '',
                organismName: '',
                type: '',
                datasetsName: ''
            };
        },
        true
    );
}