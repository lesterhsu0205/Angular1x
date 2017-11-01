(function() {
    define(function () { // AMD 寫法

		var appMain = angular.module('appMain', [ 'ngRoute','routeResolverServices','genericServices']);

		var viewPath = "view/";

		appMain.config(['$routeProvider','routeResolverProvider',
		                '$controllerProvider', '$compileProvider',
		                '$filterProvider', '$provide',
				function($routeProvider,routeResolverProvider,
						$controllerProvider, $compileProvider,
						$filterProvider, $provide) {

			appMain.register = {
				controller : $controllerProvider.register,
				directive : $compileProvider.directive,
				filter : $filterProvider.register,
				factory : $provide.factory,
				service : $provide.service
			};

			var route = routeResolverProvider.route;

			return $routeProvider
//			  .when('/blankPage', {templateUrl: 'view/example/blankPage/blankPage.html'})
//			  .when('/bootstrapElements', {templateUrl: 'view/example/bootstrapElements/bootstrapElements.html'})
//			  .when('/bootstrapGrid', {templateUrl: 'view/example/bootstrapGrid/bootstrapGrid.html'})
//			  .when('/charts', {templateUrl: 'view/example/charts/charts.html'})
//			  .when('/dashboard', route.resolve('dashboard', viewPath + 'example/dashboard/'))
//			  .when('/forms', {templateUrl: 'view/example/forms/forms.html'})
//			  .when('/tables', {templateUrl: 'view/example/tables/tables.html'})
//			  .when('/test', route.resolve('test', viewPath + 'example/test/'))
//			  .when('/test/:a', route.resolve('test', viewPath + 'example/test/'))

			  .when('/ticketDeatil/:ticketId', route.resolve('ticketDetail', viewPath + 'ticketDetail/'))
			  .when('/createTicket', route.resolve('ticketDetail', viewPath + 'ticketDetail/'))
			  .when('/queryTicket', route.resolve('queryTicket', viewPath + 'queryTicket/'))
			  .when('/userBoard', route.resolve('userBoard', viewPath + 'userBoard/'))
			  .otherwise({redirectTo : '/queryTicket' });
		}]);
		return appMain;

	});
}).call(this);