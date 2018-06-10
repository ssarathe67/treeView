var app = angular.module('treeApp');

app.factory('TreeService', function($http, $q) {
    return {
		getAllNodes : function(){
			req = {
                method: 'GET',
                url: 'treeview/getTreeNodes',
			}
            return $http(req).then(function(response) {
                return response.data;
            });
		},
		addUpdateNode : function(_node){
			req = {
                method: 'POST',
                url: 'treeview/addUpdateNode',
				data : _node
			}
            return $http(req).then(function(response) {
                return response.data;
            });
		},
		deleteNode : function(_nodeId){
			req = {
                method: 'POST',
                url: 'treeview/deleteNode',
				data : _nodeId
			}
            return $http(req).then(function(response) {
                return response.data;
            });
		}
        
    };
});

/****
 * Allow only numbers.
 */
app.directive('numbersOnly', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attr, ngModelCtrl) {
            function fromUser(text) {
                if (text) {
                    var transformedInput = text.replace(/[^0-9]/g, '');

                    if (transformedInput !== text) {
                        ngModelCtrl.$setViewValue(transformedInput);
                        ngModelCtrl.$render();
                    }
                    return transformedInput;
                }
                return undefined;
            }            
            ngModelCtrl.$parsers.push(fromUser);
        }
    };
});
