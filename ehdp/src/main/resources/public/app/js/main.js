(function () {
	'use strict';

	require('angular');
	require('angular-route');
	require('angular-animate');

	//controllers
	var apiCtrl = require("./controllers/apictrl");
	var profileCtrl = require("./controllers/profilectrl");

	//services
	var apiService = require("./services/apiserv");
	var profileService = require("./services/profileserv");

	angular.module('EHDP', ['ngRoute', 'ngAnimate'])

	.config([
		'$locationProvider',
		'$routeProvider',
		function($locationProvider, $routeProvider) {
			$locationProvider.hashPrefix('!');
			
			$routeProvider
			.when("/", {
				templateUrl: "./partials/home.html"
			})
			.when("/api/:apiPath?", {
				templateUrl: "./partials/api.html",
				controller: "ApiController"
			})
			.when("/termsofuse", {
				templateUrl: "./partials/termsofuse.html"
			})
			.when("/profile", {
				templateUrl: "./partials/profile.html",
				controller: "ProfileController"
			})
			.when("/about", {
				templateUrl: "./partials/about.html"
			})
			.otherwise({
				redirectTo: '/'
			});
		}
	])

	//controller registration
	.controller('ApiController', ['$scope', '$routeParams', "ApiService", apiCtrl])
	.controller('ProfileController', ['$scope', 'ProfileService', profileCtrl])

	//service registration
	.factory("ApiService", ['$http', apiService])
	.factory("ProfileService", ['$http', profileService]);
}());