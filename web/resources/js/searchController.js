function SearchCtrl($scope, $modal, Restangular, localStorageService, BASE_PATH) {

    $scope.collapsed = false;

    $scope.state = {
        geneList: [],

        popoverTemplate: '',

        checkbox: false,
        allChecked: false,
        selected: false,
        count: 0,
        page: 1,
        size: 25
    };

    $scope.reference = {
        ontologyTerm: []
    };

    $scope.sorts = {direction: 'asc', property: 'id'};

    $scope.filters = localStorageService.get('gene.filters.v1.2');

    if (!angular.isDefined($scope.filters) || $scope.filters == null) {
        $scope.filters = {
            id: '',
            ontologyTermId: '',

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
        };
    }

    // Main search function
    $scope.load = function () {
        localStorageService.set('gene.filters.v1.2', $scope.filters);

        var sorts = [{
            'direction': $scope.sorts.direction ? 'asc' : 'desc',
            'property': $scope.sorts.property
        }];

        var filters = {
            id: $scope.filters.id,
            ontologyTermId: $scope.filters.ontologyTermId,

            ontologyTermPrimaryIdentifier: $scope.filters.ontologyTermPrimaryIdentifier,
            ontologyTermName: $scope.filters.ontologyTermName,
            subjectPrimaryIdentifier:         $scope.filters.subjectPrimaryIdentifier,
            subjectSymbol:        $scope.filters.subjectSymbol,
            subjectName:        $scope.filters.subjectName,
            subjectDsc:       $scope.filters.subjectDsc,
            subjectChromosomeName:        $scope.filters.subjectChromosomeName,
            evidenceBaseAnnotationsSubjectBackgroundName:        $scope.filters.evidenceBaseAnnotationsSubjectBackgroundName,
            evidenceBaseAnnotationsSubjectZygosity:        $scope.filters.evidenceBaseAnnotationsSubjectZygosity,
            publicationId:        $scope.filters.publicationId,
            publicationDoi:        $scope.filters.publicationDoi,

            sorts: sorts,
            page: $scope.state.page,
            size: $scope.state.size
        };

        Restangular.all('gene/search').post(filters).then(function(result) {
            $scope.state.geneList = result.content;
            $scope.state.count = result.totalElements;
            $scope.state.size = result.size;
            $scope.state.totalPages = result.totalPages;
        },
        function() {

        });
    };

    // Load phenotypes reference values for select box
    $scope.loadReferences = function() {
        Restangular.oneUrl('gene/reference/ontologyterm').get().then(function(result) {
                $scope.reference.ontologyTerm = result;
            },
            function() { });
    };

    // URL for gene details link
     $scope.getDetailsUrl = function(item) {
        return "#/edit/" + item.geneId;
    };

    $scope.clearFilters = function() {
        $scope.filters = {
            id: '',
            ontologyTermId: '',

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
        };

        localStorageService.remove('gene.filters.v1.2');
        $scope.sorts = {direction: 'asc', property: 'id'};

        $scope.load();
    };

    $scope.loadReferences();

    // New gene button
    $scope.newGene = function() {
        var modalInstance = $modal.open({
            templateUrl: BASE_PATH+'/page/edit.html',
            controller: GeneEditCtrl,
            windowClass: 'modal-large',
            resolve: {
                model: function() {
                    return null;
                }
            },
            backdrop: 'static'
        });

        modalInstance.result.then(function(result) {
            if(result == false) {
                $scope.load();
            }
        });
    };

    $scope.load();

    $scope.canSort = function (property, ascending) {
        if ($scope.sorts.property == property) {
            if ($scope.sorts.direction == (ascending == 'asc')) {
                return true;
            }
        }
        return false;
    };

    $scope.setSort = function (property) {
        if ($scope.sorts.property == property) {
            $scope.sorts.direction = !$scope.sorts.direction;
        } else {
            $scope.sorts.property = property;
            $scope.sorts.direction = true;
        }
        $scope.load();
    };

    $scope.setPage = function (page) {
        $scope.state.page = page;
        $scope.load();
    };

    $scope.canEdit = function () {
        return $scope.state.selected !== false;
    };

    $scope.canDelete = function () {
        for(var i=0; i<$scope.state.geneList.length; i++) {
            if($scope.state.geneList[i].utils.selected === true) {
                return true;
            }
        }
        return false;
    };

    $scope.isSelected = function (item) {
        return $scope.state.selected == item;
    };

    $scope.setSelected = function (item) {
        if ($scope.state.selected == item) {
            $scope.state.selected = false;
        } else {
            $scope.state.selected = item;
        }
    };

    $scope.checkGt = function(itemSelected) {
        itemSelected.utils.selected = !itemSelected.utils.selected;
    };

    $scope.checkAll = function() {
        $scope.state.allChecked = !$scope.state.allChecked;
        for(var i=0; i<$scope.state.geneList.length; i++) {
            $scope.state.geneList[i].utils.selected = $scope.state.allChecked;
        }
    };


     //     --- POPOVER PART BELOW ---

    // Load data for popup
    $scope.genePrimaryIdentifier = function(item) { return item.subjectPrimaryIdentifier };
    $scope.geneSymbol = function(item) { return item.subjectSymbol };
    $scope.geneDescription = function(item) { return item.subjectDsc };
    $scope.geneNcbi = function(item) { return item.ncbi };


    $scope.createPopoverTemplate = function(model) {

        // In order to change popover width you must change 'div[title='<b>Gene brief details</b>']' style in application.css

            $scope.state.popoverTemplate =
        '<div class="row" style="width: 500px">' +
            '<div class="col-sm-12">' +
                '<div class="col-sm-11">' +
                    '<div class="row">' +
                        '<label class="control-label col-sm-5">Gene ID:</label>' +
                        '<div class="col-sm-7">' +
                            '<label class="control-label propertyDataText">' +
                                        $scope.genePrimaryIdentifier(model)    +
                            '</label>' +
                        '</div>' +
                    '</div>' +
                    '<div class="row  vspace-top-5">' +
                        '<label class="control-label col-sm-5">Gene Symbol:</label>' +
                        '<div class="col-sm-7">' +
                            '<label class="control-label propertyDataText">' +
                                        $scope.geneSymbol(model)   +
                            '</label>' +
                        '</div>' +
                    '</div>' +
                    '<div class="row  vspace-top-5">' +
                        '<label class="control-label col-sm-5">Gene description:</label>' +
                        '<div class="col-sm-7">' +
                            '<label class="control-label propertyDataText">' +
                                        $scope.geneDescription(model)   +
                            '</label>' +
                        '</div>' +
                    '</div>' +
                    '<div class="row  vspace-top-5">' +
                        '<label class="control-label col-sm-5">NCBI:</label>' +
                        '<div class="col-sm-7">' +
                            '<label class="control-label propertyDataText">' +
                                        $scope.geneNcbi(model)    +
                            '</label>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</div>' +
        '</div>';
    };
}