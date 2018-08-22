/* handlebar 용 라이브러리 */
var HANUHandlebarCommon = (function(){
	
/*	//private 변수들과 함수들 선언
	
	 handlebar 탬플릿 그리기 함수 
	var printDate = function(replyArr, target, templateObject){
		var template = Handlebars.compile(templateObject.html());
		
		var html = template(replyArr);
		$(".replyLi").remove();
		target.after(html);
	};
	
	return {
		//public 변수들과 함수들 선언
		callPrintDate : function(replyArr, target, templateObject){
			printDate(replyArr, target, templateObject);
		},
	};*/
	
})();

/* handlebar 기능 확장  */
Handlebars.registerHelper("prettifyDate", function(timeValue){
	var dateObj = new Date(timeValue);
	var year = dateObj.getFullYear();
	var month = dateObj.getMonth() + 1;
	var date = dateObj.getDate();
	return year + "/" + month + "/" + date;
});