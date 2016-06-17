module.exports = function($scope, ProfileService) {
    $scope.requestsMade = 0;
    $scope.possibleRequests = 0;
    $scope.lastRequests = [];
    $scope.lastRequestsOffset = 0;
    $scope.furtherRequests = true;
    $scope.paymentModels = [];
    $scope.apiKey = "";
    $scope.currentModel = { "modelId": -1 };

    $scope.loadLastRequests = function() {
        ProfileService.getLastRequests($scope.lastRequestsOffset, function(requests) {
            if (requests.length === 0) {
                $scope.furtherRequests = false;
            }
            requests.forEach(function(r) {
                $scope.lastRequests.push(r);
            });
            $scope.lastRequestsOffset += requests.length;
        }, function(error) {
            alert("ERROR: " + JSON.stringify(error));
        });
    };

    $scope.loadLastRequests();

    ProfileService.getPaymentModels(function(models) {
        $scope.paymentModels = models;
    }, function(error) {
        alert("ERROR: " + JSON.stringify(error));
    });

    $scope.changeModel = function(model) {
        ProfileService.changePaymentModel(model,
            function(success) {
                $scope.currentModel = model;
                getRequestDetails();
            },
            function(error) {
                alert("ERROR: " + JSON.stringify(error));
            }
        );
    };

    ProfileService.getProfile(function(profile) {
        $scope.apiKey = profile.apiKey;
        $scope.currentModel = profile.paymentModel;
    }, function(error) {
        alert("ERROR: " + JSON.stringify(error));
    });

    function getRequestDetails() {
        ProfileService.getRequestDetails(function(details) {
            $scope.requestsMade = details.madeRequests;
            $scope.possibleRequests = details.possibleRequests;
        }, function(error) {
            alert("ERROR: " + JSON.stringify(error));
        });
    }

    getRequestDetails();
};