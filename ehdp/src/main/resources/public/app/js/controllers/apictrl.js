module.exports = function($scope, $routeParams, ApiService) {
    $scope.apis = [];
    $scope.apiPath = $routeParams.apiPath;
    $scope.api = {};

    ApiService.get(function(apis) {
        $scope.apis = apis;
    }, function(error) {
        alert("ERROR: " + JSON.stringify(error));
    });

    ApiService.getApi($scope.apiPath, function(api) {
        $scope.api = api;
    }, function(error) {
        alert("ERROR: " + JSON.stringify(error));
    });
};