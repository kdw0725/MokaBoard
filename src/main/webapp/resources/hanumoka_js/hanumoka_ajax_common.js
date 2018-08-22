var HANUAjaxCommon = (function(){
	
	var ajax = function(ajaxParamObj){
		
		var tmpType = ajaxParamObj.type.toLowerCase(); 
		var type = null;
		
		if(tmpType == "post" || tmpType == "delete" || tmpType == "patch"){
			type = "post";
		}else if(tmpType == "get"){
			type = "get";
		}
		
		$.ajax({
			type: type,
			url: ajaxParamObj.url,
			headers: {
				"Content-type" : "application/json",
				"X-HTTP-Method-Override" : ajaxParamObj.type.toUpperCase()
			},
			dataType: 'text',
			data : JSON.stringify(ajaxParamObj.data),
			success: function(result){
				console.log("result:" + result);
				if(result == 'SUCCESS'){
					alert("ajax 요청 성공");
					ajaxParamObj.successfn(result);
				}
			},
		});
	};
	
	return {
		callAjax : function(ajaxParamObj){
			ajax(ajaxParamObj);
		},
	};
	
})();