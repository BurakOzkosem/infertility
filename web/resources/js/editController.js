/**
 * Created by user on 02.01.2015.
 */
function EditCtrl($scope, $routeParams, Restangular) {

    $scope.state = {
        model: {
            id: '',
            ontologyId: '',

            ontologyTermPrimaryIdentifier: '',
            ontologyTermName: '',
            subjectPrimaryIdentifier: '',
            subjectSymbol: '',
            subjectName: '',
            subjectDsc: '',
            subjectChromosomeName: '',
            evidenceBaseAnnotationsSubjectBackgroundName: '',
            evidenceBaseAnnotationsSubjectZygosity: '',
            publicationId: '',
            publicationDoi: ''
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
//            $scope.state.model.id = result.id;
//            $scope.state.model.ontologyId = result.ontologyTermEdit.id;
//
//            $scope.state.model.ontologyTermPrimaryIdentifier = result.ontologyTermEdit.primaryIdentifier;
//            $scope.state.model.ontologyTermName = result.ontologyTermEdit.name;
//            $scope.state.model.subjectPrimaryIdentifier = result.subjectEdit.primaryIdentifier;
//            $scope.state.model.subjectSymbol = result.subjectEdit.symbol;
//            $scope.state.model.subjectName = result.subjectEdit.name;
//            $scope.state.model.subjectDsc = result.subjectEdit.dsc;
//            $scope.state.model.subjectChromosomeName = result.subjectEdit.chromosomeName;
//            $scope.state.model.evidenceBaseAnnotationsSubjectBackgroundName = result.evidenceEdit.baseAnnotationsSubjectBackgroundName;
//            $scope.state.model.evidenceBaseAnnotationsSubjectZygosity = result.evidenceEdit.baseAnnotationsSubjectZygosity;
//            $scope.state.model.publicationId = result.evidenceEdit.publicationEdit.id;
//            $scope.state.model.publicationDoi = result.evidenceEdit.publicationEdit.doi;
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
