/**
 * Created by user on 02.01.2015.
 */
function EditCtrl($scope, $routeParams, Restangular) {

    $scope.state = {
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
                homologueEditList: [
                    {
                        id: null,
                        primaryIdentifier: '',
                        symbol: '',
                        organismName: '',
                        type: '',
                        datasetsName: ''
                    }
                ]
            }
        }
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

    $scope.show($routeParams.id);

    $scope.save = function(id) {
        Restangular.one("gene", id).all("update").post($scope.state.model).then(function(result) {
            $scope.state.model.id = result.id;
            $scope.state.model.name = result.name;
        });
    }
}
