(function() {
	require.config({
		waitSeconds : 60,
		baseUrl : window.contextPath,
		paths : {
			angular : "system-asset/js/angular155",
			angularRoute : "system-asset/js/angular-route155",
			jquery : "system-asset/js/jquery",
			jqueryBlockUI : "system-asset/js/jquery.blockUI",
			bootstrap : "system-asset/js/bootstrap.min",
			bootstrapNotify : "system-asset/plugins/bootstrap-notify-3.1.3/bootstrap-notify",
			bootstrapDatetimepicker : "system-asset/plugins/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker",
			bootstrapDatetimepickerZhTW : "system-asset/plugins/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-TW",
			
			common : "app/main/common",
			mainController : "app/main/main-controller",
			appMain : "app/main/appMain",
			routeServices : 'app/service/route-service',
			genericServices : 'app/service/generic-service',
			genericDirective : 'app/directive/generic-directive',
			
			raphael : "system-asset/js/plugins/morris/raphael.min",
			morris : "system-asset/js/plugins/morris/morris.min",
//			morrisData : "system-asset/js/plugins/morris/morris-data",

			jqueryFlot  : "system-asset/js/plugins/flot/jquery.flot",
			jqueryFlotTooltip  : "system-asset/js/plugins/flot/jquery.flot.tooltip.min",
			jqueryFlotResize  : "system-asset/js/plugins/flot/jquery.flot.resize",
			jqueryFlotPie  : "system-asset/js/plugins/flot/jquery.flot.pie",
//			flotData  : "system-asset/js/plugins/flot/flot-data",
		},
		shim : {
			angular : {
				deps : ['jquery'],
				exports : 'angular'
			},
			angularRoute : {
				deps : ['angular']
			},
			routeServices : {
				deps : ['angular'],
				exports : 'routeServices'
			},
			genericServices : {
				deps : [ 'angular' ],
				exports : 'genericServices'
			},
			genericDirective : {
				deps : [ 'angular' ],
				exports : 'genericDirective'
			},
			bootstrap : {
				deps : ['jquery'],
				exports : 'bootstrap'
			},
			bootstrapNotify : {
				deps : ['bootstrap'],
				exports : 'bootstrapNotify'
			},
			bootstrapDatetimepicker : {
				deps : ['bootstrap'],
				exports : 'bootstrapDatetimepicker'
			},
			bootstrapDatetimepickerZhTW : {
				deps : ['bootstrapDatetimepicker'],
				exports : 'bootstrapDatetimepickerZhTW'
			},
			morris : {
				deps : ['jquery']
			},
			jqueryFlot : {
				deps : ['jquery'],
				exports : 'jqueryFlot'
			},
			jqueryFlotTooltip : {
				deps : ['jquery','jqueryFlot'],
				exports : 'jqueryFlotTooltip'
			},
			jqueryFlotResize : {
				deps : ['jquery','jqueryFlot'],
				exports : 'jqueryFlotResize'
			},
			jqueryFlotPie : {
				deps : ['jquery','jqueryFlot'],
				exports : 'jqueryFlotPie'
			},
			jqueryBlockUI : {
				deps : ['jquery'],
				exports : 'jqueryBlockUI'
			},
		}
	});

	require(['jquery','angular', 'angularRoute', 'bootstrap', 'bootstrapNotify', 'bootstrapDatetimepicker', 'bootstrapDatetimepickerZhTW', 'raphael', 'morris',
	         'jqueryFlot','jqueryFlotTooltip','jqueryFlotResize','jqueryFlotPie','common', 'jqueryBlockUI'], function() {
		require(['appMain','routeServices','genericServices','genericDirective', 'mainController'], function() {
			angular.bootstrap(document, ['appMain']);
		});
	});
	
})(this);
