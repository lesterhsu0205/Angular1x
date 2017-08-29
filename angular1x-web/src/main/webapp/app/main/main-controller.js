(function() {
	define([ 'appMain' ], function(appMain) {
		
		appMain.controller('mainController', [
                  '$scope','modalService','ajaxService','msgService', '$timeout',
          function($scope, modalService, ajaxService, msgService, $timeout) {


			$scope.modalKey = {
				modifyPassword : String("modifyPassword" + new Date().getTime()),
			};
			
			//修改密碼物件
			$scope.modifyPassword = null;
			
			
			//初始畫修改密碼
			$scope.initModifyPassword = function(){
				//清空
				$scope.modifyPassword = null;
				modalService.open($scope.modalKey.modifyPassword)
			}
			

			//確認修改密碼
			$scope.modifyPasswordConfirm = function(){

				ajaxService.post('userBoard/modifyPassword.action', 
					$scope.modifyPassword,
					function(data, status, headers, config){					
						var suc = false;
						if(!isEmpty(data)){
							if(data.suc){
								suc = true;
								msgService.success('成功', '修改密碼成功, 系統即將登出, 請使用新密碼登入');
								$timeout(function(){
									modalService.close($scope.modalKey.modifyPassword)
									location.href="logout.action"
								}, 3000)
							}
						}
						
						if(!suc){
							var msg = "修改密碼發生錯誤";
							if(!isEmpty(data)){
								if(!isEmpty(data.msg)){
									msg = data.msg;
								}
							}
							msgService.error('失敗', msg);
						}
					}, function(data, status, headers, config){
						msgService.error('修改密碼', "修改密碼錯誤");
				});
				
			}

			
		}]);
	});

}).call(this);