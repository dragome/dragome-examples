/*
#Description:	Definition of all routes for Flashcards application
#Author:		Marc Baur, Adrian Herzog
*/

// Define routes
var flashcardsConfig = function($routeProvider) {
	$routeProvider.when('/lernkartei', {
		controller : 'CardDeckListController',
		templateUrl : 'partials/carddeck/carddeck-list.html',
		activeTab : 'carddecks'
	})
	.when('/lernkartei/:cardDeckID', {
		controller : 'CardDeckDetailController',
		templateUrl : 'partials/carddeck/carddeck-detail.html',
		activeTab : 'carddecks'
	})
	.when('/karten/lernkartei/:cardDeckID', {
		controller : 'CardDeckDetailController',
		templateUrl : 'partials/carddeck/carddeck-detail.html',
		activeTab : 'carddecks'
	})
	.when('/', {
		controller : 'CardDeckListController',
		templateUrl : 'partials/carddeck/carddeck-list.html',
		activeTab : 'carddecks'
	})
	.when('/hilfe', {
		templateUrl : 'partials/hilfe.html',
		activeTab : 'hilfe'
	})
	.when('/links', {
		templateUrl : 'partials/links.html',
		activeTab : 'links'
	})
	.when('/impressum', {
		templateUrl : 'partials/impressum.html',
		activeTab : 'impressum'
	})
}

// Add mapping configuration to module
Flashcards.config(flashcardsConfig);