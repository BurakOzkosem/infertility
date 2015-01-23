function DownloaderCtrl($scope, Restangular) {

    $scope.state = {
        nextFireTime: '',
        status: ''
    };

    $scope.getNextDownloadTime = function() {
        Restangular.oneUrl('downloader/nextStartTime').get().then(function(result) {
            $scope.state.nextFireTime = result.value;
        });
    };

    $scope.getDownloadStatus = function() {
        Restangular.oneUrl('downloader/status').get().then(function(result) {
            $scope.state.status = result.value;
        });
    };

    $scope.getNextDownloadTime();
    $scope.getDownloadStatus();

    $scope.fireStart = function() {
        Restangular.oneUrl('downloader/startTask').get().then(function(result) {
            $scope.getDownloadStatus();
        });
    }
}