(function() {
	define([ 'appMain' ], function(appMain) {
		var injectParams = ['$scope', '$routeParams', 'modalService','$filter','msgService', '$timeout', '$http', 'ajaxService'];
		var controller = function($scope, $routeParams, modalService,$filter,msgService, $timeout, $http, ajaxService) {
			/*
			 * start
			 */

			$scope.modalKey = {
				userView : String("userView" + new Date().getTime())
			};

			$scope.statusItems = [{
				text : "啟用",
				value : true,
			},{
				text : "停用",
				value : false,
			}]; 
			
			$scope.modalService = modalService;
			$scope.msgService = msgService;
			$scope.isEmpty = isEmpty;
			$scope.isCreate = true;
			$scope.modifyId = null;
			//查詢目前頁檔案資訊
			$scope.userInfos = null;
			$scope.userInfosPageSize = 5;
			
			$scope.queryTextByKey = function(items, value, valueKey, textKey){
				var res = "";
				if(!isEmpty(items)){
					for (var i = 0 ; i < items.length ; i++) {
						var item = items[i];
						if(item[valueKey] == value){
							res = item[textKey];
						}
					}
				}
				return res;
			}
			
			function validateForm (editData, isCreate) {
				if (isEmpty(editData) || isEmpty(editData.userName)) {
					msgService.error('錯誤', '帳號需要填寫或選擇！');
					return;
				} else if (isCreate && (isEmpty(editData) || isEmpty(editData.password))) {
					msgService.error('錯誤', '密碼需要填寫或選擇！');
					return;
				} else if (isEmpty(editData) || isEmpty(editData.name)) {
					msgService.error('錯誤', '姓名需要填寫或選擇！');
					return;
				} else if (isEmpty(editData) || isEmpty(editData.depId)) {
					msgService.error('錯誤', '部門需要填寫或選擇！');
					return;
				} else if (isEmpty(editData) || isEmpty(editData.roleId)) {
					msgService.error('錯誤', '角色需要填寫或選擇！');
					return;
				} else if (isEmpty(editData) || isEmpty(editData.status)) {
					msgService.error('錯誤', '狀態需要填寫或選擇！');
					return;
				}
				
				return true;
			}
			
			//確認編輯
			$scope.confirm = function() {
				
				if(!validateForm($scope.editData, $scope.isCreate)){
					return 
				}
				
				var info = {
					isCreate : $scope.isCreate,
					user : $scope.editData
				}
				
				if($scope.isCreate){
					edit(info, function(){
						$scope.userInfoChangeToFirstPage();
						msgService.success('成功', '人員新增成功');
						modalService.close($scope.modalKey.userView);
					});
					
				} else {
					edit(info, function(){
						$scope.refreshPage();
						msgService.success('成功', '人員更改成功');
						modalService.close($scope.modalKey.userView);
					});
				}
			}
			
			function edit(info, callBack) {
				ajaxService.post('userBoard/edit.action', 
					info,
					function(data, status, headers, config){
					
						var suc = false;
						if(!isEmpty(data)){
							if(data.suc){
								
								suc = true;
								
								if(callBack){
									callBack();
								}
							}
						}
						
						if(!suc){
							var msg = "發生未知錯誤";
							if(!isEmpty(data)){
								if(!isEmpty(data.msg)){
									msg = data.msg;
								}
							}
							msgService.error('失敗', msg);
						}
						
					}, function(data, status, headers, config){
						msgService.error('錯誤', "發生錯誤");
					}
				);
			}
			
			$scope.revertPassword = function(){
				ajaxService.post('userBoard/revertPassword.action', 
						$scope.editData,
						function(data, status, headers, config){
						
							var suc = false;
							if(!isEmpty(data)){
								if(data.suc){
									suc = true;
									msgService.success('成功', '還原預設密碼成功');
									modalService.close($scope.modalKey.userView);
								}
							}
							
							if(!suc){
								var msg = "發生未知錯誤";
								if(!isEmpty(data)){
									if(!isEmpty(data.msg)){
										msg = data.msg;
									}
								}
								msgService.error('失敗', msg);
							}
							
						}, function(data, status, headers, config){
							msgService.error('錯誤', "發生錯誤");
						}
					);
			}
			
			//點選修改
			$scope.modifyMember = function(id) {
				$scope.isCreate = false;
				$scope.modifyId = id;
				$scope.editData = null;

				if(!isEmpty($scope.queryUserInfos)){
					for (var i = $scope.queryUserInfos.length-1 ; i >= 0 ; i--) {
						if ($scope.queryUserInfos[i].id == id) {	
							$scope.editData = angular.copy($scope.queryUserInfos[i]);
							// 不能秀出轉碼過的密碼
							$scope.editData.password = null;
						}
					}
				}
				modalService.open($scope.modalKey.userView);
			}
			
			//點選新增人員
			$scope.addUser = function(){
				$scope.isCreate = true;
				$scope.editData = null;
				modalService.open($scope.modalKey.userView);
			}
			
			//重新整理此頁
			$scope.refreshPage = function(){
				$scope.userInfoChangePage($scope.pagination);
			}

			//切頁
			$scope.userInfoChangePage = function(pagination, callBack){
				$scope.pagination = pagination;
				
				$scope.userInfos = {
					totalCount : 0,
					result : [],
				};
				
					
				queryUsersByPage(pagination, function(users, totalCount){
					
					$scope.queryUserInfos = [];
					$scope.queryUserInfos = $scope.queryUserInfos.concat(angular.copy(users));
					
					if(!isEmpty($scope.queryUserInfos)){
						for(var i=0 ; i<$scope.queryUserInfos.length ; i++){
							$scope.userInfos.result.push($scope.queryUserInfos[i]);
						}
					}
					
					$scope.userInfos.totalCount = totalCount;
				});
				
				if(callBack){
					callBack();
				}
			};
			
			//切到第一頁
			$scope.userInfoChangeToFirstPage = function(){

				var pagination = {
						pageSize : $scope.userInfosPageSize,
						pageNumber : 1,
				};
				pagination.pageIdx = (pagination.pageNumber-1)*$scope.userInfosPageSize,
				
				//去後端查詢  1~$scope.userInfosPageSize比數資料, 和資料總數組合
				$scope.userInfoChangePage(pagination, function(){
					//初始化
					$timeout(function(){
						$scope.userInfoInitApi();
					}, 1000)
				});
			};
			
			// 欄位不可為空
			$scope.validate = function(columnName, ele) {
//				if (isEmpty(ele.currentTarget.value)) {
//					msgService.error('錯誤', columnName + "需要填寫或選擇！");
//					angular.element(this).focus();
//				}
			}
			
			$scope.init = function() {
				$scope.userInfoChangeToFirstPage();
			}
			
			$scope.init();
			
			function queryUsersByPage(pagination, call) {
				var res = $http.post('userBoard/init.action', pagination);
				res.success(function(data, status, headers, config) {
					var totalCount = 1;
					var users = [];
					if (!isEmpty(data)) {
						
						$scope.depItems = data.depOpts;
						$scope.roleItems = data.roleOpts;
						
						totalCount = data.cfgAuthUserCount;
						var listOfData = data.cfgAuthUsers;
						for (var i=0;i<listOfData.length;i++) {
							var user = {};
							user.id = listOfData[i].id;
							user.name = listOfData[i].name;
							user.roleId = listOfData[i].roleId;
							user.depId = listOfData[i].depId;
							user.phone = listOfData[i].phone;
							user.email = listOfData[i].email;
							user.userName = listOfData[i].userName;
							user.password = listOfData[i].password;
							user.status = listOfData[i].status;
							users.push(user);
						}
					}
					
					if(call) {
						call(users, totalCount);
					}
				});
				res.error(function(data, status, headers, config) {
					msgService.error('錯誤', "錯誤訊息 : " + JSON.stringify({data: data}));
				});
			}
			
			/*
			 * ends	
			 */	
		};
		
		controller.$inject = injectParams;
		appMain.register.controller('userBoardController', controller);
	});

}).call(this);