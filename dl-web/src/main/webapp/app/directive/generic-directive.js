(function() {

	define(['appMain'], function(appMain) {

		var fileModel = function($parse){
            return {
                restrict: 'A',
                link: function(scope, element, attrs) {
                	
                	var model = $parse(attrs.fileModel);
                	var modelSetter = model.assign;
					element.bind('change', function(){
						scope.$apply(function(){
							modelSetter(scope, element[0].files[0]);
						});
					});
            	}				
             };
		}

		fileModel.$inject = ['$parse'];
		appMain.directive('fileModel', fileModel);
		
		
		var pagination = function($timeout){
			return{
				restrict : 'A',
				templateUrl : 'app/directive/templates/pagination.html',
				replace : true,
				scope:{
					/*
					 * 所有的資料數量
					 */
					totalCount : '=?',
					/*
					 * 所有的資料
					 */
					pageSize : '=?',
					/*
					 * 換頁的call back
					 * callBack(pageNum)
					 */
					changeFunc : '=?',
					/*
					 * 初始化的api
					 */
					initApi : '=?',
					
				},
				link : function (scope, element, attrs) {	

					scope.isEmpty = isEmpty;
					var defaulPageSize = 20;
					var totalCount = 0;
					var pageSize = defaulPageSize;
					
					scope.init = function(){

						$timeout(function(){
							scope.curPage = 0;
							scope.pageCountInfos = [];
							totalCount = 0;
							pageSize = defaulPageSize;
							
							if(!isEmpty(scope.pageSize)) {
								pageSize = scope.pageSize;
							}
							
							if(!isEmpty(scope.totalCount)){
								totalCount = scope.totalCount;
							}
							
							
							var pageCount = Math.floor(totalCount/pageSize) + (totalCount%pageSize > 0 ? 1 : 0);
							
							
							for(var i=0 ; i<pageCount ; i++){
								scope.pageCountInfos.push(i);
							}
							
						});
					};
					
					scope.jumpPage = function(index){
						if(scope.curPage != index){
							scope.curPage = index;
							scope.pageChange();
						}
					};
					
					scope.previousPage = function(){
						if(!isEmpty(scope.curPage) && scope.curPage > 0){
							scope.curPage--;
							scope.pageChange();
						}
					};
					
					scope.nextPage = function(){
						if(!isEmpty(scope.curPage) && scope.curPage < scope.pageCountInfos.length-1){
							scope.curPage++;
							scope.pageChange();
						}
					};
					
					scope.pageChange = function(){
//						if(!isEmpty(scope.totalCount)){
//							totalCount = scope.totalCount;
//						}
//						var start = scope.curPage * pageSize + 1;
//						var end = start + pageSize - 1;
//						if(end > totalCount){
//							end = totalCount;
//						}
						
						var pagination = {
								pageSize : pageSize,
								pageNumber : scope.curPage+1,
								pageIdx : scope.curPage*pageSize,
						};
						
						if(scope.changeFunc){
							scope.changeFunc(pagination);
						}
					};
					
		            /**
		             * api
		             */
		            if(!scope.initApi){
		                scope.initApi = function() {
							scope.init();
		                };
		            }
					
				}								
			};
		};
		pagination.$inject = ['$timeout'];
		appMain.directive('pagination', pagination);

		var datePicker = function($timeout){
			return{
				restrict : 'A',
				templateUrl : 'app/directive/templates/datePicker.html',
				replace : true,
				scope:{
					/*
					 * 所有的資料數量
					 */
					model : '=',
					/*
					 * 初始化的api
					 */
					initApi : '=?',
					
				},
				link : function (scope, element, attrs) {	
					//http://www.bootcss.com/p/bootstrap-datetimepicker/index.htm

					scope.isEmpty = isEmpty;
					
					scope.datepickerId = "datepicker" + new Date().getTime();

					scope.init = function(){

						$timeout(function(){
					        $("#" + scope.datepickerId).datetimepicker({
					            autoclose:true,    
					            todayBtn:true,
					            clearBtn : false,
					            todayHighlight:true,
					            language:"zh-TW",
					            format:"yyyy/mm/dd",
					            minView:"2",
					            pickerPosition: "bottom-right",
					            
					        });
						});
					};
					
					scope.init();
					
		            /**
		             * api
		             */
		            if(!scope.initApi){
		                scope.initApi = function() {
							scope.init();
		                };
		            }
					
				}								
			};
		};
		datePicker.$inject = ['$timeout'];
		appMain.directive('datePicker', datePicker);
		

	});
}).call(this);