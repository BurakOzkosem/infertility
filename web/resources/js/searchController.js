/**
 * Created by user on 31.12.2014.
 */
function SearchCtrl($scope, Restangular, localStorageService) {

    $scope.collapsed = false;

    $scope.state = {
        geneList: [],

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

    $scope.filters = localStorageService.get('gene.filters.v1.1');

    if (!angular.isDefined($scope.filters) || $scope.filters == null) {
        $scope.filters = {
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
        };
    }

    $scope.load = function () {
        localStorageService.set('gene.filters.v1.1', $scope.filters);

        var sorts = [{
            'direction': $scope.sorts.direction ? 'asc' : 'desc',
            'property': $scope.sorts.property
        }];

        var filters = {
            id: $scope.filters.id,
            ontologyId: $scope.filters.ontologyId,

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

    $scope.loadReferences = function() {
        Restangular.oneUrl('gene/reference/ontologyterm').get().then(function(result) {
                $scope.reference.ontologyTerm = result;
            },
            function() { });
    };

    $scope.getMM = function() {
        Restangular.oneUrl('gene/mm').get();
    };

    $scope.getDetailsUrl = function(item) {
        return "#/edit/" + item.id;
    };

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


    $scope.clearFilters = function() {
        $scope.filters = {
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
        };

        localStorageService.remove('gene.filters.v1.1');
        $scope.sorts = {direction: 'asc', property: 'id'};
    };

    $scope.load();
}