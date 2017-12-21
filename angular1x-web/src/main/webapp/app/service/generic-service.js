(function() {


	var genericServices = angular.module('genericServices', []);


	/**
	 * 	畫面service
	 */
	genericServices.factory('ajaxService', 
							['$http', 'uiService', function($http, uiService) {
								
		var post = function(url, param, successCallBack, errorCallBack){
			uiService.block();
			$http.post(url, param)
			.success(function(data, status, headers, config) {
				uiService.unblock();
				if(successCallBack){
					successCallBack(data, status, headers, config);
				}
			})
			.error(function(data, status, headers, config) {
				uiService.unblock();
				if(errorCallBack){
					errorCallBack(data, status, headers, config);
				}
			});
		}
		
		return {
			post : function(url, param, successCallBack, errorCallBack) {
				post(url, param, successCallBack, errorCallBack);
			},
		};
	
	}]);
	
	
	/**
	 * 	畫面service
	 */
	genericServices.factory('uiService', 
							[function() {
		window.blockCount = 0;
		
		var block = function(){
			window.blockCount++;
			$.blockUI({
				message : '<img src="system-asset/img/loading-circular.gif" align="absmiddle">',
				css : {
					border : 'none',
					padding : '2px',
					backgroundColor : 'none'
				},
				overlayCSS : {
					backgroundColor : '#000',
					opacity : 0.1,
					cursor : 'wait'
				},
				baseZ: 9999,
			});
		};
		
		var unblock = function(){
			window.blockCount--;
			if(window.blockCount == 0){
				$.unblockUI({
					onUnblock : function() {
						
					}
				});
			}
		};
		
		return {
			//鎖定畫面
			block : function() {
				block();
			},
			//解鎖畫面
			unblock : function() {
				unblock();
			}
		};
	
	}]);
	
	/**
	 *  modal
	 */
	genericServices.factory('modalService', [function() {
		
		var open = function(modalId){
			$('#' + modalId).modal('show');
		};	
		
		var close = function(modalId){
			$('#' + modalId).modal('hide');
		};
		
		return {
			open : function(modalId) {
				return open(modalId);
			},
			close : function(modalId) {
				return close(modalId);
			},
		};
	
	}]);	
	
	/**
	 *  alert msg
	 */
	genericServices.factory('msgService', [function() {

		//http://bootstrap-notify.remabledesigns.com/
		
		var alert = function(title, msg, type){
			$.notify({
				title: "<div><strong>"+ title +"</strong></div>",
				message: msg,
			},{
				newest_on_top: true, //從上面新增
				type: type,
				z_index: 9999,
				timer: 1000,
				animate: {
					enter: 'animated rollIn',
					exit: 'animated rollOut'
				},
				template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
					'<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
					'<span data-notify="icon"></span> ' +
					'<span data-notify="title">{1}</span> ' +
					'<span data-notify="message">{2}</span>' +
					'<div class="progress" data-notify="progressbar">' +
						'<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
					'</div>' +
					'<a href="{3}" target="{4}" data-notify="url"></a>' +
				'</div>' 
			});
			
		};
		
		return {
			info : function(title, msg) {
				alert(title, msg, "info");
			},
			success : function(title, msg) {
				alert(title, msg, "success");
			},
			error : function(title, msg) {
				alert(title, msg, "danger");
			},
		};
	
	}]);
	
}).call(this);