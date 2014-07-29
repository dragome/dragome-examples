/*
#Description:	Definition of all controllers for Flashcards application
#Author:		Marc Baur, Adrian Herzog
*/

Flashcards.controller('CardDeckListController', function($scope, CardDeckService) {

	$scope.carddecks = CardDeckService.findAllCardDecks();

});

Flashcards.controller('CardDeckDetailController', function($scope, $routeParams, CardDeckService, CardService) {
	
	// counters
	$scope.currentCardArrayIndex = 0;
	$scope.percentageCompleted = 0;
	$scope.percentageCompletedStyle = getPercentageCompletedStyle(0);
	
	// get carddecks and cards from RESTful service
	$scope.carddeck = CardDeckService.findCardDeckByID($routeParams.cardDeckID);
	$scope.cards = CardService.findCardsByCardDeckID($routeParams.cardDeckID);

	// callback which sets first card after cards have been loaded
	$scope.cards.$then(function(httpResponse) {
		$scope.card = $scope.cards[0];
		$scope.answerShown = false;
	});
	
	// form action which loads next card if available
	$scope.getNextCard = function() {
		if ($scope.currentCardArrayIndex < $scope.cards.length - 1) {
			var nextCard = $scope.cards[++$scope.currentCardArrayIndex];
			$scope.card = nextCard;
			$scope.answerShown = false;
			calculatePercentageCompleted();
		} else {
			alert('Sie sind bei der letzten Frage angelangt :-)');
		}
	}
	
	// form action which loads previous card if available
	$scope.getPreviousCard = function() {
		if ($scope.currentCardArrayIndex > 0) {
			var previousCard = $scope.cards[--$scope.currentCardArrayIndex];
			$scope.card = previousCard;
			$scope.answerShown = false;
			calculatePercentageCompleted();
		} else {
			alert('Sie sind bei der ersten Frage angelangt.');
		}
	}
	
	$scope.showAnswer = function() {
		$scope.answerShown = true;
		calculatePercentageCompleted();
	}
	
	function getPercentageCompletedStyle(percentageCompleted) {
		return { width: percentageCompleted + '%' };
	}
	
	function calculatePercentageCompleted() {
		var numberOfCards = $scope.cards.length;
		
		$scope.percentageCompleted = 0;
		if(numberOfCards > 0) {
			var answerShownOffset = $scope.answerShown ? 1 : 0;
			$scope.percentageCompleted = (($scope.currentCardArrayIndex + answerShownOffset) / numberOfCards) * 100;
		}
		$scope.percentageCompletedStyle = getPercentageCompletedStyle($scope.percentageCompleted);
	}
	
});

// Controller which exposes route
Flashcards.controller('NavigationController', function($scope, $route) {
	$scope.$route = $route;
});
