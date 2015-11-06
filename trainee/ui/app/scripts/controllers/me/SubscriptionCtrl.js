'use strict';

/*global app: false */

/**
 * The clazz controller.
 *
 */
app.controller('SubscriptionCtrl', ['$modal','$state', '$scope', function($modal, $state, $scope) {

  $scope.open = function() {
    console.log("open")
    $scope.showModal = true;
  };

  $scope.ok = function() {
    console.log("ok")
    $scope.showModal = false;
  };

  $scope.cancel = function() {
    console.log("close")
    $scope.showModal = false;
  };

}]);


