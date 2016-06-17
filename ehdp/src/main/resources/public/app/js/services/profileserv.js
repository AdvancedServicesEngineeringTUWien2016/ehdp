module.exports = function($http) {
    return {
        getLastRequests: function(offset, success, error) {
            $http.get("/profile/requests/" + offset).success(success).error(error);
        },
        getRequestDetails: function(success, error) {
            $http.get("/profile/requests/details").success(success).error(error);
        },
        getPaymentModels: function(success, error) {
            $http.get("/profile/paymentModels").success(success).error(error);
        },
        changePaymentModel: function(model, success, error) {
            $http.post("/profile/paymentModels", model).success(success).error(error);
        },
        getProfile: function(success, error) {
            $http.get("/profile/").success(success).error(error);
        }
    };
};