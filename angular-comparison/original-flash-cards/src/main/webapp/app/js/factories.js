/*
#Description:	Definition of all factories for Flashcards application
#Author:		Marc Baur, Adrian Herzog
*/

Flashcards.factory('CardDeckService', function($resource) {
	var cardDeckService = $resource('/flashcards/api/carddecks/:cardDeckID', {}, {});

	cardDeckService.findAllCardDecks = function() {
		return cardDeckService.query();
	};
	cardDeckService.findCardDeckByID = function(id) {
		return cardDeckService.get({ cardDeckID : id });
	};

	return cardDeckService;
});

Flashcards.factory('CardService', function($resource) {
	var cardService = $resource('/flashcards/api/cards/:cardID/:cardDeckRestriction/:cardDeckID', {}, {});

	cardService.findAllCardDecks = function() {
		return cardService.query();
	};

	cardService.findCardsByCardDeckID = function(id) {
		return cardService.query({ cardDeckRestriction : 'carddeck', cardDeckID : id});
	};

	cardService.findCardById = function(id) {
		return cardDecardServiceckService.get({ cardID : id });
	};

	return cardService;
});