(function() {
	define([ 'appMain' ], function(appMain) {
		var injectParams = ['$scope','$routeParams','$http', 'ajaxService'];
		var controller = function($scope,$routeParams,$http, ajaxService) {
			/*
			 * start
			 */
			
			$scope.fileDownload = function(){

//                var form = document.createElement("form");
//                form.setAttribute("method", "post");
//                form.setAttribute("action", "fileDownload.action");
//                form.setAttribute("target", '_blank');
//                    
//                var input = document.createElement('input');
//                input.type = 'hidden';
//                input.name = 'fileId';
//                input.value = 1;                
//                form.appendChild(input);
//                document.body.appendChild(form);
//                form.submit();
//
//                document.body.removeChild(form);
                

				var fd = new FormData();

				fd.append("fileId", 123);
				
				$http.post('fileDownload.action', fd, {
				transformRequest: angular.identity,
				headers: {'Content-Type': undefined}
				})
				.success(function(data, status, headers, config){

					console.log("!!!")
				})
				.error(function(data, status, headers, config){
					console.log("~~")
				});
				
			}
			
			//有套件的樣子
			//https://github.com/nervgh/angular-file-upload
			
			$scope.uploadFile = function() {
				
               var file = $scope.myFile;
               
               
//               var test = {
//        		   description:"Test",
//        		   status: "REJECTED"
//        		 };
//
//    		 var fd = new FormData();
//    		 fd.append('data', angular.toJson(test));
//    		 fd.append('file', file);
//
//    		 //remove comment to append a file to the request
//    		 //var oBlob = new Blob(['test'], { type: "text/plain"});
//    		 //fd.append("file", oBlob,'test.txt');
//
//    		 $http.post('uploadFile2.action', fd, {
//    		   transformRequest: angular.identity,
//    		   headers: {
//    		     'Content-Type': undefined
//    		   }
//    		 })
//    		 .success(function(data, status, headers, config){
//		  	   console.log(data);
//		     })
//		     .error(function(data, status, headers, config){
//		  	   console.log("error")
//		     });;
               var test = {
                	   ticketId : 222,
                	   fileType : 556633,
                	   remark : "fff"
            		 };
               var fd = new FormData();
               fd.append('file', file);
               fd.append('ticketId', 123);
               fd.append('fileType', 555555);
               fd.append('remark', "ijr哈哈哈goe");

               console.log(file);
               console.log(fd);
               
               $http.post('uploadFile.action', fd, {
                   transformRequest: angular.identity,
                   headers: {'Content-Type': undefined}
               })
               .success(function(data, status, headers, config){
            	   console.log(data);
               })
               .error(function(data, status, headers, config){
            	   console.log("error")
               });
               
               
//				$http.post("testUpload.action", {file : file})
//				.success(function(data, status, headers, config) {
//					console.log("suc");
//				})
//				.error(function(data, status, headers, config) {
//					console.log("error");
//				});
				
				
				
//               var req = {
//            		   method: 'POST',
//            		   url: 'testUpload.action',
//            		   data: { file: file, name : "sdsd" }
//            		  }
//
//        		  $http(req).then(function(){}, function(){});
               
//               var fd = new FormData();
//               fd.append('file', file);
//               console.log(fd)
//               $http.post("singleSave.action", fd, {
//                   transformRequest: angular.identity,
//                   headers: {'Content-Type': undefined}
//               })
//               .success(function(){
//               })
//               .error(function(){
//               });
			}
			
			$scope.testButton = function() {
				$http.post('testAjax.action', "json")
				.success(function(data, status, headers, config) {
					$scope.message = data;
				});
			}
			
			$scope.song = "3345678";
			
			/*
			 * ends	
			 */	
		};
		
		controller.$inject = injectParams;
		appMain.register.controller('testController', controller);
	});

}).call(this);