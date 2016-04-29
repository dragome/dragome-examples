var app = angular.module("calculatorApplication", []).config(function ($routeProvider) {

    $routeProvider.when("/home", {
        templateUrl: "app/view/home.html",
        controller: "HomeController"
    });

    $routeProvider.when("/calculator", {
        templateUrl: "app/view/calculator.html",
        controller: "CalculatorController"
    });

    $routeProvider.otherwise({
        redirectTo: "/home"
    });

});

app.controller("HomeController", function () {
});

app.controller("CalculatorController", function ($scope) {

    // calculator state
    $scope.displayValue = 0;                    //current value displayed on calculator screen
    $scope.valueA = 0;                          //first (left) value that will be used for computation
    $scope.valueB = 0;                          //second (right) value that will be used for computation
    $scope.selectedOperation = null;            //last operation selected by user
    $scope.clearValue = true;                   //should value displayed on screen be cleared after new digit pressed?


    //constants
    $scope.equalSignKey = {label: "="};

    $scope.digitKeys = [
        {label: "1", value: 1}, {label: "2", value: 2}, {label: "3", value: 3},
        {label: "4", value: 4}, {label: "5", value: 5}, {label: "6", value: 6},
        {label: "7", value: 7}, {label: "8", value: 8}, {label: "9", value: 9},
        {label: "0", value: 0}
    ];

    $scope.operationKeys = [
        {label: "/", operation: function (a, b) {return a / b}},
        {label: "*", operation: function (a, b) {return a * b}},
        {label: "+", operation: function (a, b) {return a + b}},
        {label: "-", operation: function (a, b) {return a - b}}
    ];


    // actions
    /**
     * When digit is clicked, it should be added to displayed value or replace displayed value.
     * Also new displayed value should be treated as second operation value.
     * @param digit what digit was clicked
     */
    $scope.digitClicked = function (digit) {
        if ($scope.clearValue) {
            $scope.displayValue = digit;
            $scope.clearValue = false;
        } else {
            $scope.displayValue = $scope.displayValue * 10 + digit;
        }
        $scope.valueB = $scope.displayValue
    };

    /**
     * When operation key is clicked operation should be remembered,
     * displayed value should be treated as first and second number to perform operation on
     * and next pushed digit should replace the displayed value
     * @param operation which operation was clicked
     */
    $scope.operationClicked = function (operation) {
        $scope.selectedOperation = operation;
        $scope.valueA = $scope.displayValue;
        $scope.valueB = $scope.displayValue;
        $scope.clearValue = true;
    };

    /**
     * Computes the result based on remembered operation and two values and displays the result.
     * Also next pushed digit should replace the displayed value
     * and current result should be treated as first value for next operation.
     */
    $scope.compute = function () {
        if($scope.selectedOperation!=null) {
            $scope.displayValue = Math.floor($scope.selectedOperation($scope.valueA, $scope.valueB));
            $scope.clearValue = true;
            $scope.valueA = $scope.displayValue;
        }
    }

});


































