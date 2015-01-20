/**
 * Created by user on 02.01.2015.
 */
function EditCtrl($scope, $routeParams, Restangular) {

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
            searchGeneResponseList: []
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
    }

    $scope.show = function(id) {
        Restangular.one("gene/showDetails", id).get().then(function(result) {
            $scope.state.model = result;

//            var sfPhenotypeIdList = [];
//            var sfPhenotypeList = [];
//            var sfWithTextList = [];
//
//            var phenotypeIdList = [];
//            var phenotypeList = [];
//
//            var bckgroundList = [];
//            var zygosityList = [];
//            var pubmedIdList = [];
//            var doiList = [];
//
//            for(var i=0; i < $scope.state.model.sequenceFeatureEditList.length; i++) {
//                if(!sfPhenotypeIdList.contains($scope.state.model.sequenceFeatureEditList[i].ontologyTermId)) {
//                    sfPhenotypeIdList.push($scope.state.model.sequenceFeatureEditList[i].ontologyTermId)
//                    sfPhenotypeList.push({
//                        id: $scope.state.model.sequenceFeatureEditList[i].ontologyTermId,
//                        name: $scope.state.model.sequenceFeatureEditList[i].ontologyTermName
//                    });
//                }
//
//                if(!sfWithTextList.contains($scope.state.model.sequenceFeatureEditList[i].evidenceWithText)) {
//                    sfWithTextList.push($scope.state.model.sequenceFeatureEditList[i].evidenceWithText);
//                }
//            }
//
//            $scope.state.model.sfPhenotypeList = sfPhenotypeList;
//
//            for(var i=0; i < $scope.state.model.searchGeneResponseList.length; i++) {
//                if(!phenotypeIdList.contains($scope.state.model.searchGeneResponseList[i].ontologyTermPrimaryIdentifier)) {
//                    phenotypeIdList.push($scope.state.model.searchGeneResponseList[i].ontologyTermPrimaryIdentifier)
//                    phenotypeList.push({
//                        id: $scope.state.model.searchGeneResponseList[i].ontologyTermPrimaryIdentifier,
//                        name: $scope.state.model.searchGeneResponseList[i].ontologyTermName
//                    });
//                    phenotypes
//                }
//
//                if(!bckgroundList.contains($scope.state.model.searchGeneResponseList[i].evidenceBaseAnnotationsSubjectBackgroundName)) {
//                    bckgroundList.push($scope.state.model.searchGeneResponseList[i].evidenceBaseAnnotationsSubjectBackgroundName);
//                }
//                if(!zygosityList.contains($scope.state.model.searchGeneResponseList[i].evidenceBaseAnnotationsSubjectZygosity)) {
//                    zygosityList.push($scope.state.model.searchGeneResponseList[i].evidenceBaseAnnotationsSubjectZygosity);
//                }
//                if(!pubmedIdList.contains($scope.state.model.searchGeneResponseList[i].publicationId)) {
//                    pubmedIdList.push($scope.state.model.searchGeneResponseList[i].publicationId);
//                }
//                if(!doiList.contains($scope.state.model.searchGeneResponseList[i].publicationDoi)) {
//                    doiList.push($scope.state.model.searchGeneResponseList[i].publicationDoi);
//                }
//
//                $scope.state.model.phenotypeList = phenotypeList;
//                $scope.state.model.bckgroundList = bckgroundList;
//                $scope.state.model.zygosityList = zygosityList;
//                $scope.state.model.pubmedIdList = pubmedIdList;
//                $scope.state.model.doiList = doiList;
//            }
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
