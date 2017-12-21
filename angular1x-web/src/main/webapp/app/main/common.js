
// 是否為空值
function isEmpty(item){
	var result = false;
	
	if(angular.isArray(item)){
		if(item.length == 0){
			result = true;
		}
	} else if(item == null || item == undefined || String(item).trim() == ""){
		result = true;
	}
	
	return result;
};