(function() {
	define([ 'appMain' ], function(appMain) {
		var injectParams = ['$scope', '$routeParams', 'modalService','$filter','msgService','ajaxService','$anchorScroll'];
		var controller = function($scope, $routeParams, modalService,$filter,msgService,ajaxService,$anchorScroll) {
						
			$scope.modalService = modalService;
			$scope.msgService = msgService;
			$scope.isEmpty = isEmpty;
			
			
			//日期
			$scope.today = null;
			//案件類型
			$scope.caseTypeItems = [];
			//案件分類
			$scope.caseGroupItems = [];
			//工單狀態
			$scope.statusItems = [];
			//工單子狀態
			$scope.subStatusItems = {};
			//縣市選項
			$scope.cityItems = [];
			//鄉鎮選項
			$scope.townItems = {};

			//查詢條件
			$scope.searchParam = null;
			//是否顯示查詢結果
			$scope.showSearchRes = false;

			
			//顯示查詢結果
			$scope.ticketInfosPageSize = 5;

			$scope.ticketInfos = {
				totalCount : 0,
				result : [],
			};
			
			$scope.ticketInfoChangeToFirstPage = function(){
				//去後端查詢  1~$scope.fileInfosPageSize比數資料, 和資料總數組合
				$scope.ticketInfoChangePage({
					pageSize: $scope.ticketInfosPageSize,
					pageNumber: 1,
					pageIdx: 0
				}, function(){
					//初始畫
					$scope.ticketInfoInitApi();
				});
			};

			$scope.ticketInfoChangePage = function(pagination, callBack){
				
				
				var param = !isEmpty($scope.searchParam) ? angular.copy($scope.searchParam) : {};
				param.pagination = angular.copy(pagination);

				ajaxService.post('queryTicket/queryTicket.action', 
					param,
					function(data, status, headers, config){
						
						$scope.ticketInfos = {
							totalCount : 0,
							result : [],
						};
					
						var suc = false;
						if(!isEmpty(data)){
							if(data.suc){
								suc = true;

								$scope.ticketInfos.totalCount = data.totalCount;
								$scope.ticketInfos.result = data.result;
								
								//顯示查詢結果
								$scope.showSearchRes = true;
								
								
								if(isEmpty($scope.ticketInfos) || isEmpty($scope.ticketInfos.result)){
									msgService.info('成功', '查無資料');
								}
								
								
								if(callBack){
									callBack();
								}
							}
						}
						
						if(!suc){
							var msg = "查詢時發生錯誤";
							if(!isEmpty(data)){
								if(!isEmpty(data.code)){
									msg += ", error code :" + data.code;
								}
								if(!isEmpty(data.msg)){
									msg += ", error msg :" + data.msg;
								}
							}
							msgService.error('失敗', msg);
						}
						
					}, function(data, status, headers, config){
						msgService.error('失敗', "查詢錯誤");
				});
			};
			
			//清除
			$scope.cleanSearchParam = function(){
				//查詢條件
				$scope.searchParam = null;
				//是否顯示查詢結果
				$scope.showSearchRes = false;
			}
			
			$scope.search = function(){
				$scope.ticketInfoChangeToFirstPage();
			}

			$scope.setInit = function(data){
				//查詢條件
				$scope.searchParam = null;
				
				if(!isEmpty(data)){
					//現在時間
					if(!isEmpty(data.today)) {
						$scope.today = new Date(data.today);
					} else {
						$scope.today = new Date();
					}
					//案件類型
					$scope.caseTypeItems = data.caseTypeItems;
					//案件分類
					$scope.caseGroupItems = data.caseGroupItems;
					//工單狀態
					$scope.statusItems = data.statusItems;
					//工單子狀態
					$scope.subStatusItems = data.subStatusItems;
					//縣市
					$scope.cityItems = data.cityItems;
					//鄉鎮
					$scope.townItems = data.townItems;
				}

				//畫面到最上面
				$anchorScroll();
			}
			
			$scope.init = function(){

				ajaxService.post('queryTicket/getTicketQueryInitData.action', 
					null, 
					function(data, status, headers, config){
						var suc = false;
						
						if(!isEmpty(data)){
							if(data.suc == true){
								suc = true;
								$scope.setInit(data);
							}
						}
						
						if(!suc){
							var msg = "取得初始資料失敗";
							if(!isEmpty(data)){
								if(!isEmpty(data.code)){
									msg += ", error code :" + data.code;
								}
								if(!isEmpty(data.msg)){
									msg += ", error msg :" + data.msg;
								}
							}
						}
					}, function(data, status, headers, config){
						msgService.error('錯誤', "取得初始資料錯誤");
					})
			}
			
			$scope.init();
			
			
			/*
			 * ends	
			 */	
		};
		
		controller.$inject = injectParams;
		appMain.register.controller('queryTicketController', controller);
	});

}).call(this);