var app = angular.module('treeApp', ['ui.bootstrap','ngSanitize','ngStomp']);
app.controller('treeController', function($scope,$timeout,TreeService,$modal,$stomp) {
	$scope.treeChecbox = true;
	/***
	* Function to get All node data.
	*/
	$scope.getAllFactories = function(){
		TreeService.getAllNodes().then(function(response){
			$timeout(function(){
				let childList = response.value;
				$scope.childList = angular.copy(childList);
			});
			
		});
	}
	$scope.getAllFactories();
	
	/**
	* Web-Socket connection
	*/
	$stomp.connect('/tree/treeApp').then(function (frame) {
        var subscription = $stomp.subscribe('/topic/getFactories', function (payload, headers, res) {
          $timeout(function(){
			  $scope.childList = angular.copy(payload);
			});
		  
        })
		// Send message
        $stomp.send('/app/treeApp', {
          priority: 9
        })
      })
	
	/***
	* Smart-Simple Toggle ng-repeat.
	*/
	$scope.toggleFilter = function(item) {
		item.toggle = angular.copy(!item.toggle);
	}
	/**
	* Fucntion to update or add Factory
	*/
	$scope.updateFactory = function(object){
		var modalOptions = {
	        templateUrl: 'resources/views/modalForm.html',
	        controller: 'modalInstanceCtrl',
	        backdrop: 'static'
	    };
	    var modalInstance = $modal.open(modalOptions);
	    modalInstance.factory = object;
	    modalInstance.result.then(function() {}, function(result) {
			$scope.updatefromAdvancedSearchFilter = undefined;
	        $scope.getAllFactories();
			$stomp.send('/app/treeApp',{
				priority: 9
			});
		});
	}
	$scope.$watch('updatefromAdvancedSearchFilter', function(value) {
		if(value){
			_index =  $scope.indexOfObject( $scope.childList,'id' , value);
			let factory = $scope.childList[_index];
			$scope.updateFactory(factory);
			
		}
	  });
	  
	$scope.indexOfObject = function indexOfObject(array, property, value) {
		for (var i = 0; i < array.length; i++) {
			if (array[i][property] === value) return i;
		}
		return -1;
	};
	
});
