(function() {
	define([ 'appMain' ], function(appMain) {
		var injectParams = ['$scope', '$routeParams', 'modalService','$filter','msgService','$timeout','$anchorScroll', 'ajaxService', 'uiService', '$http'];
		var controller = function($scope, $routeParams, modalService, $filter, msgService, $timeout, $anchorScroll, ajaxService, uiService, $http) {
			/*
			 * start
			 */

			
			$scope.ticketId = $routeParams.ticketId;

			$scope.modalKey = {
				download : String("download" + new Date().getTime()),
				ticketHis : String("ticketHis" + new Date().getTime()),
				insertRemark : String("insertRemark" + new Date().getTime()),
				upload : String("upload" + new Date().getTime()),
			};

			//操作模式
			$scope.oprMode = {
					CREATE : {
						text : "建立工單",
						value : "CREATE",
					},
					READONLY : {
						text : "檢視工單",
						value : "READONLY",
					},
					EDIT : {
						text : "編輯工單",
						value : "EDIT",
					},
			}
			//目前操作模式
			$scope.curOprMode = $scope.oprMode.CREATE; //預設新開單
			
			$scope.modalService = modalService;
			$scope.msgService = msgService;
			$scope.isEmpty = isEmpty;
			
			$scope.today = new Date();;
			
			/*{
				//案件類型
				caseTypeItems : [],
				//案件分類
				caseGroupItems : [],
				//檔案類型
				fileTypeItems : [],
				//縣市選項
				addressCityItems : [],
				//鄉鎮選項
				addressTownItems : {},
				//工單狀態
				ticketStatusItems : [],
				//工單子狀態
				ticketSubStatusItems : {},
				//期別
				seasonItems = [],
				//年度
				yearItems = [],
			}*/
			$scope.staticData = {};
			
			//描述區塊資料
			$scope.desc = null;
			//位置區塊資料
			$scope.location = {
				allAddress : [{}],
			};
			//通知區塊資料
			$scope.notice = null;
			
			//新增備註
			$scope.remark = null;
			
			//檔案上傳
			$scope.uploadFileInfo = null;
			
			//查詢目前頁檔案資訊
			$scope.fileInfos = null;
			$scope.fileInfosPageSize = 5;
			//查詢目前頁檔案資訊
			$scope.ticketInfos = null;
			$scope.ticketInfosPageSize = 5;
			
			
			
			
			$scope.queryTicket = function(){
				$scope.ticketInfoChangeToFirstPage();
			};
			
			$scope.ticketInfoChangeToFirstPage = function(){
				//清空
				$scope.ticketInfos = {
					totalCount : 0,
					result : [],
				};
				
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

			$scope.ticketInfoChangePage = function(pageInfo, callBack){
				
				
				var param = angular.copy(pageInfo);
				param.ticketId = $scope.ticketId;

				ajaxService.post('ticketDetail/queryTicketHisStatus.action', 
						param,
						function(data, status, headers, config){
					
							var suc = false;
							if(!isEmpty(data)){
								if(data.suc){
									
									suc = true;

									$scope.ticketInfos.totalCount = data.totalCount;
									$scope.ticketInfos.result = data.result;
									
									if(callBack){
										callBack();
									}
								}
							}
							
							if(!suc){
								var msg = "讀取歷程時發生錯誤";
								if(!isEmpty(data)){
									if(!isEmpty(data.code)){
										msg += ", 錯誤代碼 :" + data.code;
									}
									if(!isEmpty(data.msg)){
										msg += ", 錯誤訊息 :" + data.msg;
									}
								}
								msgService.error('失敗', msg);
							}
							
						}, function(data, status, headers, config){
							msgService.error('失敗', "讀取歷程錯誤");
					});
			};
			
			
			
			$scope.openQueryFileInfo = function(){
				$scope.queryFileInfoParam = {
						ticketId : $scope.ticketId,
				}
				$scope.queryFileInfo();
				modalService.open($scope.modalKey.download)
			};
			
			$scope.queryFileInfo = function(){
				$scope.fileInfoChangeToFirstPage();
			}
			
			$scope.fileInfoChangeToFirstPage = function(){
				//清空
				$scope.fileInfos = {
					totalCount : 0,
					result : [],
				};
				
				//去後端查詢  1~$scope.fileInfosPageSize比數資料, 和資料總數組合
				$scope.fileInfoChangePage({
					pageSize: $scope.fileInfosPageSize,
					pageNumber: 1,
					pageIdx: 0
				}, function(){
					//初始畫
					$scope.fileInfoInitApi();
				});
			};

			$scope.fileInfoChangePage = function(pageInfo, callBack){
				
				var param = angular.copy($scope.queryFileInfoParam);
				param.pagination = angular.copy(pageInfo);

				ajaxService.post('ticketDetail/queryFileInfo.action', 
						param,
						function(data, status, headers, config){
							var suc = false;
							if(!isEmpty(data)){
								if(data.suc){
									suc = true;

									$scope.fileInfos.totalCount = data.totalCount;
									$scope.fileInfos.result = data.result;
									
									if(callBack){
										callBack();
									}
								}
							}
							
							if(!suc){
								var msg = "讀取檔案時發生錯誤";
								if(!isEmpty(data)){
									if(!isEmpty(data.code)){
										msg += ", 錯誤代碼 :" + data.code;
									}
									if(!isEmpty(data.msg)){
										msg += ", 錯誤訊息 :" + data.msg;
									}
								}
								msgService.error('失敗', msg);
							}
							
						}, function(data, status, headers, config){
							msgService.error('失敗', "讀取檔案錯誤");
					});
			};
			
			//開啟google map
			$scope.openMap = function(){
				
				//https://www.google.com/maps?q=24.197611,120.780512&ll=24.197611,120.780512&z=14
				
				var url = "https://www.google.com/maps?" +
						"q=%longitude%,%latitude%" +
						"&ll=%latitudeP%,%latitudeP%" +
						"&z=14";
				var realUrl = angular.copy(url)
				if(!isEmpty($scope.location) && 
						!isEmpty($scope.location.longitude) && 
						!isEmpty($scope.location.latitude)){
					realUrl = realUrl.replace("%longitude%", $scope.location.longitude.trim());
					realUrl = realUrl.replace("%latitude%", $scope.location.latitude.trim());
					realUrl = realUrl.replace("%latitudeP%", $scope.location.longitude.trim());
					realUrl = realUrl.replace("%latitudeP%", $scope.location.latitude.trim());
					window.open(realUrl);
				} else {
					msgService.info("提示", "請輸入正確的經緯度")
				}
			}
			
			//開啟新增備註
			$scope.addRemark = function(){
				
				$scope.remark = {
					ticketId : $scope.ticketId,
				}
				modalService.open($scope.modalKey.insertRemark)
			}
			
			//新增備註
			$scope.insertRemark = function(){
				ajaxService.post('ticketDetail/insertRemark.action', 
						$scope.remark,
						function(data, status, headers, config){
							var suc = false;
							if(!isEmpty(data)){
								if(data.suc){
									suc = true;
									msgService.success('成功', '新增備註成功');
									$scope.queryTicket(); //查詢工單歷程
									$scope.curOprMode = $scope.oprMode.READONLY; //初始為檢視模式
									modalService.close($scope.modalKey.insertRemark)
								}
							}
							
							if(!suc){
								var msg = "新增備註時發生錯誤";
								if(!isEmpty(data)){
									if(!isEmpty(data.code)){
										msg += ", 錯誤代碼 :" + data.code;
									}
									if(!isEmpty(data.msg)){
										msg += ", 錯誤訊息 :" + data.msg;
									}
								}
								msgService.error('失敗', msg);
							}
							
						}, function(data, status, headers, config){
							msgService.error('失敗', "新增備註錯誤");
					});
			}
			
			//開起上傳檔案
			$scope.openUploadFile = function(){
				//重置檔案上傳
				$scope.uploadFileInfo = {
						ticketId : $scope.ticketId
				};

				$scope.cleanSelectFile();
				
				$scope.modalService.open($scope.modalKey.upload)
			}
			
			$scope.cleanSelectFile = function(){
				document.getElementById("uploadFile").value = "";
			}
			
			//檔案上傳
			$scope.uploadFile = function(){

				var fd = new FormData();
				if(!isEmpty($scope.uploadFileInfo)){
					for(var key in $scope.uploadFileInfo){
						fd.append(key, $scope.uploadFileInfo[key]);
					}
				}
				
				//鎖定畫面
				uiService.block();
				
				$http.post('ticketDetail/uploadfile.action', fd, {
				transformRequest: angular.identity,
				headers: {'Content-Type': undefined}
				})
				.success(function(data, status, headers, config){
					var suc = false;
					if(!isEmpty(data)){
						if(data.suc){
							suc = true;
							msgService.success('成功', '上傳成功')
							$scope.curOprMode = $scope.oprMode.READONLY; //初始為檢視模式
							$scope.modalService.close($scope.modalKey.upload)
						}
					}
					
					if(!suc){
						var msg = "上傳失敗";
						if(!isEmpty(data)){
							if(!isEmpty(data.code)){
								msg += ", 錯誤代碼 :" + data.code;
							}
							if(!isEmpty(data.msg)){
								msg += ", 錯誤訊息 :" + data.msg;
							}
						}
						msgService.error('失敗', msg);
					}

					//解鎖畫面
					uiService.unblock();
				})
				.error(function(data, status, headers, config){
					msgService.success('成功', '上傳錯誤')

					//解鎖畫面
					uiService.unblock();
				});
			}
			
			
			$scope.fileDownload = function(fileId){

                var form = document.createElement("form");
                form.setAttribute("method", "post");
                form.setAttribute("action", "ticketDetail/fileDownload.action");
                form.setAttribute("target", '_blank');
                    
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'fileId';
                input.value = fileId;    
                
                form.appendChild(input);
                document.body.appendChild(form);
                form.submit();

                document.body.removeChild(form);
			}
			
			
			//轉換成編輯模式
			$scope.changeToEditMode = function(){
				$scope.curOprMode = $scope.oprMode.EDIT;
//				//畫面到最上面
//				$anchorScroll();
			}
			
			//修改
			$scope.modify = function(){
				$scope.curOprMode = $scope.oprMode.READONLY; //初始為檢視模式
				
				ajaxService.post('ticketDetail/modifyTicket.action', 
					{	
						ticketId : $scope.ticketId,
						desc : $scope.desc,
						location : $scope.location,
						notice : $scope.notice,
					},
					function(data, status, headers, config){
						var suc = false;
						if(!isEmpty(data)){
							if(data.suc){
								suc = true;

								msgService.info('成功', '修改成功');
								
								//工單歷程回到第一頁
//								$scope.ticketInfoChangeToFirstPage();
								
								$scope.reLoadTicketData(function(suc, msg){
									if(!suc){
										msgService.error('失敗', msg);
									}
//									//畫面到最上面
//									$anchorScroll();
								})
							}
						}
						
						if(!suc){
							var msg = "修改時發生錯誤";
							if(!isEmpty(data)){
								if(!isEmpty(data.code)){
									msg += ", 錯誤代碼 :" + data.code;
								}
								if(!isEmpty(data.msg)){
									msg += ", 錯誤訊息 :" + data.msg;
								}
							}
							msgService.error('失敗', msg);
						}
						
					}, function(data, status, headers, config){
						msgService.error('失敗', "修改錯誤");
				});
			}
			
			//開單
			$scope.create = function(){
				
				ajaxService.post('ticketDetail/createTicket.action', 
					{
						desc : $scope.desc,
						location : $scope.location,
						notice : $scope.notice,
					}, 
					function(data, status, headers, config){
						var suc = false;
						if(!isEmpty(data)){
							if(data.suc){
								suc = true;
								//進入檢視/編輯模式
								window.open("index.jsp#/ticketDeatil/"+ data.ticketId, "_self");
								
								msgService.success('開單成功', "工單編號:" + data.ticketId);
								msgService.info('注意', '急件申請時請立即修正狀態');
							}
						}
						
						if(!suc){
							var msg = "開單時發生錯誤";
							if(!isEmpty(data)){
								if(!isEmpty(data.code)){
									msg += ", 錯誤代碼 :" + data.code;
								}
								if(!isEmpty(data.msg)){
									msg += ", 錯誤訊息 :" + data.msg;
								}
							}
							msgService.error('失敗', msg);
						}
						
					}, function(data, status, headers, config){
						msgService.error('失敗', "開單錯誤");
				});
			}
			
			//重新讀取工單資訊
			$scope.reLoadTicketData = function(callBack){
				
				//描述區塊資料
				$scope.desc = null;
				//位置區塊資料
				$scope.location = {
					allAddress : [{}],
				};
				//通知區塊資料
				$scope.notice = null;
				
				ajaxService.post('ticketDetail/getTicketData.action', 
					$scope.ticketId, 
					function(data, status, headers, config){
						
						var suc = false;
						if(!isEmpty(data)){
							if(data.suc){
								suc = true;

								//描述區塊資料
								$scope.desc = data.desc;
								//位置區塊資料
								$scope.location = data.location;
								if(isEmpty($scope.location)){
									$scope.location = {
										allAddress : [{}],
									};
								} else if (isEmpty($scope.location.allAddress)){
									$scope.location.allAddress = [{}];
								}
								//通知區塊資料
								$scope.notice = data.notice;
							}
						}
						
						if(!suc){
							var msg = "讀取工單資訊發生錯誤";
							if(!isEmpty(data)){
								if(!isEmpty(data.code)){
									msg += ", 錯誤代碼 :" + data.code;
								}
								if(!isEmpty(data.msg)){
									msg += ", 錯誤訊息 :" + data.msg;
								}
							}
						}
						
						if(callBack){
							callBack(suc, msg);
						}
						
					}, function(data, status, headers, config){
						
						if(callBack){
							callBack(false, "讀取工單資訊錯誤");
						}
					});
			}
			
			$scope.setInit = function(data){
				if(!isEmpty(data)){
					
					//靜態資料
					$scope.staticData = data.staticData;
					
					//現在時間
					if(!isEmpty(data.staticData) && !isEmpty(data.staticData.today)) {
						$scope.today = new Date(data.staticData.today);
					}
				}

				//畫面到最上面
				$anchorScroll();
			}
			
			
			$scope.init = function(){
				ajaxService.post('ticketDetail/getTicketDetailInitData.action', 
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
									msg += ", 錯誤代碼 :" + data.code;
								}
								if(!isEmpty(data.msg)){
									msg += ", 錯誤訊息 :" + data.msg;
								}
							}
						}
					}, function(data, status, headers, config){
						msgService.error('錯誤', "取得初始資料錯誤");
					})
			}
			
			//有ticketId代表不是建立工單
			if(!isEmpty($scope.ticketId)){
				$scope.reLoadTicketData(function(suc, msg){
					if(suc){
						//成功查到工單資料
						$scope.curOprMode = $scope.oprMode.READONLY; //初始為檢視模式
						$scope.queryTicket(); //查詢工單歷程
						$scope.init();
					} else {
//						msgService.error('檢視工單失敗', msg);
						//失敗則跳回開單頁面
						window.open("index.jsp#/queryTicket", "_self");
					}
				});
			} else {
				$scope.init();
			}
			
			
			
			/*
			 * ends	
			 */	
		};
		
		controller.$inject = injectParams;
		appMain.register.controller('ticketDetailController', controller);
	});

}).call(this);