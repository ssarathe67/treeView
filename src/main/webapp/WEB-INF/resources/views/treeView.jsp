<!DOCTYPE  html>
<html>
<head>
    <title>Tree View Example</title>
	<link rel="icon" type="image/png" sizes="16x16" href="resources/css/images/favicon-16x16.png">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/bodyStyle.css">
	<script type="text/javascript" src="resources/vendor/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="resources/vendor/angular.min.js"></script>
    <script type="text/javascript" src="resources/vendor/ui-bootstrap-tpls-0.6.0.js"></script>
    <script type="text/javascript" src="resources/vendor/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/vendor/angular-sanitize.min.js"></script>
    
</head>
<body>
    <div ng-app="treeApp">
        <div ng-controller="treeController">
			<div class="container">
				<div class="row well" id="header-div">
					<div class="col-xs-12">
						<h3>Tree View <small><i>By Saurabh Sarathe</i></small></h3>
					</div>
				</div>
				<div class="row" id="advanced-options">
					<div class="col-xs-12">
						<div class="navbar-form navbar-left">
						  <div class="float-left">
						    <label>Advanced Options :</label>
							<select class="form-control" ng-model="updatefromAdvancedSearchFilter">
								<option value="">-- Select to edit factory --</option>
								<option ng-repeat="row in childList | orderBy:'createdDate':true track by $index" value="{{row.id}}"
								 ng-model="row"
								>{{row.nodeName}}</option>
							</select>
						  </div>
						</div>
						<div class="navbar-form navbar-right">
						  <button type="button" class="btn btn-default" ng-click="treeChecbox=true">Expand All Factories</button>
						  <button type="button" class="btn btn-default" ng-click="treeChecbox=false">Collapse All Factories</button>
						  <button type="button" class="btn btn-primary" ng-click="updateFactory()">Add New Factory</button>
						</div>
					</div>
				</div>
				
				<div class="row" id="main-content">
					<div class="col-xs-3"></div>
					<div class="col-xs-6">
						<ul class="tree"> 
							<li ng-mouseenter="rootEdit=true" ng-mouseleave="rootEdit=false">
								<input type="checkbox" checked="checked" id="c1" />
								<label class="tree_label" for="c1" >Root
									<a href="" ng-show="rootEdit" ng-click="updateFactory()">Add New Factory</a>
								</label>
								<ul>
									<li ng-repeat="factory in childList | orderBy:'createdDate':true track by $index" 
												ng-mouseenter="toggleFilter(factory)" 
												ng-mouseleave="toggleFilter(factory)">
										<input type="checkbox" ng-model="treeChecbox" id="factory{{$index}}" />
										<label for="factory{{$index}}" class="tree_label">{{factory.nodeName}}
											<a href="" ng-show="factory.toggle" ng-click="updateFactory(factory);toggleFilter(factory)">edit</a>
										</label>
										<p class="rangelbl">{{factory.upperBound}}:{{factory.lowerBound}} </p>
										<ul ng-repeat="child in factory.children track by $index">
											<li>
												<span class="tree_label">{{child}}</span>
											</li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</div>
					<div class="col-xs-3"></div>
				</div>
			</div>
            
        </div>
    </div>
    <!-- Custom -->
    <script type="text/javascript" src="resources/scripts/controller.js"></script>
    <script type="text/javascript" src="resources/scripts/service.js"></script>
    <script type="text/javascript" src="resources/scripts/modalInstanceCtrl.js"></script>
    <script type="text/javascript" src="resources/vendor/ngStomp.js"></script>
</body>
</html>