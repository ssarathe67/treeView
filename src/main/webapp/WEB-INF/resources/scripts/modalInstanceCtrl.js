var app = angular.module('treeApp');
app.controller('modalInstanceCtrl', function($scope,$timeout,TreeService,$modalInstance) {
	
	var _factory = angular.copy($modalInstance.factory);
	
	if(_factory){
		$scope.node = angular.copy(_factory);
		$scope.factory = angular.copy(_factory);
	}else{
		let node = {
			nodeName : undefined,
			children : undefined,
			childcount : undefined,
			upperBound : undefined,
			lowerBound : undefined
		};
		$scope.factory = angular.copy(node)
	}
	
	/**
	* function to dismiss modal instance.
	*/
	$scope.cancel = function() {
        $modalInstance.dismiss('cancel');
    };
	/**
	* Function to add/update factory.
	*/
	$scope.processFactory = function(){
		if(isValid()){
			// All user input is valid.
			let _factory = angular.copy($scope.factory);
			TreeService.addUpdateNode(_factory).then(function(response){
				$scope.cancel();
			});
		}
		
	}
	function isValid(){
		let _error = false;
		if(angular.isUndefined($scope.factory.nodeName) || angular.isUndefined($scope.factory.childcount)
			|| angular.isUndefined($scope.factory.upperBound)
			|| angular.isUndefined($scope.factory.lowerBound)){
			_error = true;
		}else if(parseInt($scope.factory.childcount)>15){
			_error = true;
			$scope.factory.childcount = undefined;
			$scope.errorMsg = "Please enter value less than or equals to 15.";
		}else if(parseInt($scope.factory.lowerBound) < parseInt($scope.factory.upperBound)){
			_error = true;
			$scope.errorMsg = "Children range from cannot be greater than range to.";
		}
		$scope.validationError = angular.copy(_error);
		return !_error;
	}
	/**
	* Function to delete factory.
	*/
	$scope.deleteFactory = function(){
		let _factory = angular.copy($scope.factory.id);
		TreeService.deleteNode(_factory).then(function(response){
			$scope.cancel();
		});
	}
	
});

