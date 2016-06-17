module.exports = function($http) {
    return {
        get: function(success, error) {
            $http.get("/apidata/").success(success).error(error);
        },
        getApi: function(path, success, error) {
            if (path !== undefined) {
                $http.get("/apidata/" + path).success(success).error(error);
            } else {
                var api = {};
                api.path = "APIs";
                api.description = "This page gives you an overview of all APIs that you can use. " +
                    "In order to retrieve data, you have to add your authorization header " +
                    "you can find in your profile page. The request url can be found in " +
                    "the respective page selected in the menu on the left. The request " +
                    "itself has to be preceded by the path of the particular service.";
                api.termsOfUse = "#!/termsofuse";
                api.examples = [
                    { "request" : "GET /weather/weather?q=vienna", "description" :
                        "Loads the current weather data of Vienna" },
                    { "request" : "GET /twitter/football", "description" :
                        "Loads a tweet with a hashtag \"football\"" }
                ];
                api.link = "";

                success(api);
            }
        }
    };
};