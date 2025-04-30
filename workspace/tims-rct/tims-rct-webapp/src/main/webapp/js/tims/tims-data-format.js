$(document).ready(function () {
	applyElementFormat();
	sltMakeAction();
	sltDataIme();
	sltLowerCase();
	sltUpperCase();
	sltByteCheck();
	sltEditorBind();
	sltDataPlayer();
});

if(!window["contextRoot"])
{
	window["contextRoot"]="..";
}

var _date_range_option={
	startDate: moment(),
	endDate: moment(),
	ranges: {
		'오늘': [moment(), moment()],
		'어제': [moment().subtract(1, 'days'), moment()],
		'최근 7일': [moment().subtract(6, 'days'), moment()],
		'최근 30일': [moment().subtract(29, 'days'), moment()],
		'최근 6개월': [moment().subtract(5, 'months'), moment()],
		'최근 1년': [moment().subtract(1, 'years'), moment()]	
	},
	dateLimit: {
		days: 10000
	},
	opens : 'right',
	showDropdowns : true,
	showCustomRangeLabel : true,
	alwaysShowCalendars : true,
	linkedCalendars : false,
	locale: {
		applyLabel: '확인',
		cancelLabel: '취소',
		fromLabel: 'From',
		toLabel: 'To',
		daysOfWeek: ['일', '월', '화', '수', '목', '금', '토'],
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		firstDay: 1,
		format: 'YYYYMMDD',
		customRangeLabel: '사용자지정'
	}
};

/**
 * 2016-08-19 유홍열
 * element 에 format 적용
 * @return
 */

function applyElementFormat() {
	$("input[type=text], input[type=password], textarea").each(function(index) {
		if($(this).attr("hasFormat"))
			return;
		
		$(this).attr("hasFormat","true");
		var element = $(this);
		
		var eId = element.attr("id"); // element id
		var eDataFormat = element.attr("data-format"); // 데이터 format
		var eDataPass = element.attr("data-pass"); // 특정문자 처리 여부
		var eDecPoint = element.attr("data-decp") ? element.attr("data-decp") : "2"; // 소수점 자리수
		var eMin = element.attr("data-min") ? element.attr("data-min") : "1"; // 최소값
		var eMax = element.attr("data-max") ? element.attr("data-max") : "10"; // 최대값
		var eAlign = element.attr("data-align") ? element.attr("data-align") : "left"; // 정렬
		var eMlen = element.attr("data-maxLen") ? element.attr("data-maxLen") : "100"; // max length
		var ePrefix = element.attr("data-pre") ? element.attr("data-pre") : ""; //prefix
		if(!eDataFormat)
			return;
		
		element.css("text-align",eAlign);
		switch ( eDataFormat )
		{
			case "dfDateYy":
			{
				element.inputmask("9999");
//				element.IBMaskEdit("YYYY", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfDateMm":
			{
				element.inputmask("m",{ placeholder: "________" });
				
//				element.IBMaskEdit("MM", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfDateYmd":
			{
				element.inputmask("9999md",{ placeholder: "________" });
				
//				element.IBMaskEdit("yyyy-MM-dd", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfDateYm":
			{
//				element.inputmask("9999-m");
				element.inputmask("9999m",{ placeholder: "______" });
//				element.IBMaskEdit("yyyy-MM", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfDateMd":
			{
				element.inputmask("md",{ placeholder: "____" });
//				element.IBMaskEdit("MM-dd", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfTimeHms":
			{
				element.inputmask("hss",{ placeholder: "______" });
//				element.IBMaskEdit("hh:mm:ss", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfTimeHm":
			{
				element.inputmask("hs",{ placeholder: "____" });
				
//				element.IBMaskEdit("hh:mm", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfTimeYmdhms":
			{
				element.inputmask("9999mdhss",{ placeholder: "______________" });
//				element.IBMaskEdit("yyyyMMddhhmmss", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfTimeYmdhm":
			{
				element.inputmask("9999mdhs",{ placeholder: "____________" });
//				element.IBMaskEdit("yyyyMMddhhmmss", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfIdNo":
			{
				
				element.inputmask("999999-9999999",{ placeholder: "" });
//				if(eDataPass == "true"){
//					element.IBMaskEdit("999999-9######", {
//						align: "center",
//						passwordChar: "*",
//						rules: {
//							"#": {
//								exp: "[0-9]",
//								password: true
//							}
//						},
//						unmaskOnSubmit: true
//					});
//				}else{
//					element.IBMaskEdit("999999-9999999", {
//						align: "center",
//						unmaskOnSubmit: true
//					});
//				}
				
			}
			break;
			case "dfSaupNo":
			{
				element.inputmask("999-99-99999",{ placeholder: "" });
				
//				element.IBMaskEdit("999-99-99999", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfCardNo":
			{
				element.inputmask("9999-9999-9999-9999",{ placeholder: "" });
//				if(eDataPass == "true"){
//					element.IBMaskEdit("9999-9999-####-9999", {
//						align: "center",
//						passwordChar: "*",
//						rules: {
//							"#": {
//								exp: "[0-9]",
//								password: true
//							}
//						},
//						unmaskOnSubmit: true
//					});
//				}else{
//					element.IBMaskEdit("9999-9999-9999-9999", {
//						align: "center",
//						unmaskOnSubmit: true
//					});
//				}
			}
			break;
			case "dfOldPostNo":
			{
				element.inputmask("999-999",{ placeholder: "" });
//				element.IBMaskEdit("999-999", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfNewPostNo":
			{
				element.inputmask("99999",{ placeholder: "" });
//				element.IBMaskEdit("99999", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfCorpNo":
			{
				element.inputmask("999-99-99999",{ placeholder: "" });
//				element.IBMaskEdit("999999-9999999", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfIssueNo":
			{
				element.inputmask("9999-999999",{ placeholder: "" });
//				element.IBMaskEdit("9999-999999", {
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfNo":
			{
				if(eMax){
					element.attr("maxlength",(eMax).toString().length);
				}
				else if(eMlen){
					element.attr("maxlength",data-maxLen);
				}
				element.inputmask("integer",{ placeholder: "",rightAlign : false ,min:eMin ,max : eMax});
//				element.IBMaskEdit("number", {
//					min: eMin,
//					max: eMax,
//					align: "center",
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfInteger":
			{
				if(eMax){
					element.attr("maxlength",(eMax).toString().length);
				}
				else if(eMlen){
					element.attr("maxlength",data-maxLen);
				}
				var option ={ placeholder: "" };
				if(eMax){
					option["max"] = eMax;
				}					
				if(eMin){
					option["min"] = eMin;
				}	
				element.inputmask("integer",option);
//				element.IBMaskEdit("number", {
//					autoGroup: true,
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfAmt":
			{
				if(eMax){
					element.attr("maxlength",(eMax).toString().length);
				}
				else if(eMlen){
					element.attr("maxlength",data-maxLen);
				}
				element.inputmask("integer",{ placeholder: "" });
//				element.IBMaskEdit("number", {
//					min:0,
//					align: "right",
//					repeat:13,
//					autoGroup: true,
//					unmaskOnSubmit: true,
//				});
			}
			break;
			case "dfFloat":
			{
				if(eMax){
					element.attr("maxlength",(eMax).toString().length);
				}
				else if(eMlen){
					element.attr("maxlength",data-maxLen);
				}
				element.inputmask("numeric",{ placeholder: "" ,"min":eMin ,"max" : eMax});
//				element.IBMaskEdit("number", {
//					autoGroup: true,
//					min: eMin,
//					max: eMax,
//					decimalPoint: eDecPoint,
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfId":
			{
//				element.data("inputmask","'mask': '*********'");
//				element.inputmask("a*{1,10}",{ placeholder: "" });
				
//				element.inputmask({ alias: "m"});
//				element.IBMaskEdit("a{0,10}", {
//					align: "center",
//					hideGuideCharOnBlur: true,
//					unmaskOnSubmit: true
//				});
			}
			break;
			 case "dfNumber":
			{
//				element.data("inputmask","'mask': '*********'");
				element.inputmask("9{1,"+eMlen+"}",{ placeholder: "" });
				
//				element.inputmask({ alias: "m"});
//				element.IBMaskEdit("a{0,10}", {
//					align: "center",
//					hideGuideCharOnBlur: true,
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfStr":
			{
				element.inputmask("",{ placeholder: "" ,prefix:ePrefix});
				element.attr("max-length",eMlen);
//				element.IBMaskEdit("a{0,"+eMlen+"}", {
//					align: eAlign,
//					prefix: ePrefix,
//					hideGuideCharOnBlur: true,f
//					unmaskOnSubmit: true
//				});
			}
			break;
			case "dfEmail":
			{
//				element.inputmask({alias: "email"});
//				element.attr("type","email");
//				element.inputmask('Regex', {regex: "[\w\d@.]*"});
				element.inputmask('Regex', {regex: "[A-z0-9@.-]*"});
			}
			break;
			case "dfIp":
			{
				element.inputmask('Regex', {regex: "[0-9.]*"});
			}
			break;
			 case "dfPhone":
			{
				element.inputmask("09{1,2}-999{1,2}-9999",{ placeholder: "" });
				element.change(function(){
					var value = $(this).val().replaceAll("-","");
				if(value.length<9)
					return;
				var a,b,c;
				c=value.substring(value.length-4,value.length);
				a= value.substring(0,2)=="02" ? "02" : value.substring(0,3);
				b = value.substring(a.length,value.length-4);
				value = a+"-"+ b +"-"+ c;
				$(this).val(value);
			});
			
			}
			break;
			case "dfMobile":
			{
				element.inputmask("019-999{1,2}-9999",{ placeholder: "" });
			}
			break;
			case "dfAlphaNumber":
			{
				element.inputmask('Regex', {regex: "[A-z0-9]*"});
			}
			break;
			case "dfAlpha":
			{
				element.inputmask('Regex', {regex: "[A-z]*"});
			}
			break;
			case "dfAlphaUpper":
			{
				element.inputmask('Regex', {regex: "[A-Z]*"});
			}
			break;
			case "dfAlphaLower":
			{
				element.inputmask('Regex', {regex: "[a-z]*"});
			}
			break;
			case "dfAlphaUpperNumber":
			{
				element.inputmask('Regex', {regex: "[A-Z0-9]*"});
			}
			break;
			case "dfAlphaLowerNumber":
			{
				element.inputmask('Regex', {regex: "[a-z0-9]*"});
			}
			break;
			default:
			{
				element.val();
			}
			break;
		}
		if ( (eDataFormat == "dfTimeYmdhm"||eDataFormat == "dfTimeYmdhms" ) && ! (element.attr("readOnly") || element.attr("disabled"))  )
		{
			var eformat = eDataFormat=="dfTimeYmdhm" ? 'YYYYMMDDHHmm' : 'YYYYMMDDHHmmss';
			element.wrap(" <div class='input-group date' id='myDatepicker'></div>");
			$('<span class="input-group-addon"><i class="fas fa-calendar-alt"></i></span>').insertAfter(element);
			element.parent().datetimepicker({
				locale : 'ko', 
				format: eformat,
				allowInputToggle: true,
				sideBySide: true
  			  });
			element.parent().on("dp.change",function(e){
				element.change();
			});
		}
		if ( eDataFormat == "dfDateYmd" && ! (element.attr("readOnly") || element.attr("disabled"))  )
		{
			/*
			 <div class='input-group date' id='myDatepicker'>
						<input type='text' class="form-control" />
							<span class="input-group-addon"><i class="fas fa-calendar-alt"></i></span>
						</div>
			 * */
			element.wrap(" <div class='input-group date' id='myDatepicker'></div>");
			$('<span class="input-group-addon"><i class="fas fa-calendar-alt"></i></span>').insertAfter(element);
			element.parent().datetimepicker({
				locale : 'ko', 
				format: 'YYYYMMDD',
				allowInputToggle: true
			
			});
			element.parent().on("dp.change",function(e){
				element.change();
			});
//			element.datepicker({
//				showOn: "button",
//				buttonImage: contextRoot + "/images/admin/btn_calendar.gif",
//				buttonImageOnly: true,
//				buttonText : "",
//				dateFormat : "yy-mm-dd",
//				changeYear:true,
//				changeMonth:true,
//				closeText:"Close",
//				yearRange:"1900:2100",
//				showOtherMonths:true,
//				selectOtherMonths:true,
//				showButtonPanel: true,
//				//여러가지 달력의 액션이 다를경우 본인의 아이디 값으로 호출하기 위해....lsg1lsg2
//				onSelect : function(dateTEXT, inst){try{ change_date(eId);$(element).trigger("change");}catch(e){}}
//			});
			/*
			var params = {id : eId, 
						  data_format : eDataFormat,
						  el : element 
					};
			var val = $("#" + eId).val();
			var obj = {Format:"yyyy-MM-dd", Target:eId, CallBack: "setDate", CallBackParam: params};
			
			// 달력 출력
			IBCalendar.Show(val, obj);
			*/
		}
		else if ( eDataFormat == "dfDateYmd2" && ! (element.attr("readOnly") || element.attr("disabled"))  )
		{
			element.wrap(" <div class='input-group date' id='myDatepicker'></div>");
			$('<span class="input-group-addon"><i class="fas fa-calendar-alt"></i></span>').insertAfter(element);
			element.parent().datetimepicker({
			    locale: 'ko',
			    format: 'YYYYMMDD',
			    allowInputToggle: true,
			
			    // 기본 날짜 설정
			    defaultDate: moment('1990-01-01', 'YYYY-MM-DD'),
			
			    // 달력을 열 때 1990년 1월을 보여주기 위한 설정
			    useCurrent: false
			});
			element.parent().on("dp.change",function(e){
				element.change();
			});
		}
		else if( eDataFormat == "dfDateYm" && ! (element.attr("readOnly") || element.attr("disabled")) ) {
			element.wrap(" <div class='input-group date' id='myDatepicker'></div>");
			$('<span class="input-group-addon"><i class="fas fa-calendar-alt"></i></span>').insertAfter(element);
			element.parent().datetimepicker({
				locale : 'ko', 
				format: 'YYYYMM',
				allowInputToggle: true,
			
			});
			
//			element.datepicker({
//				showOn: "button",
//			buttonImage: contextRoot + "/images/admin/btn_calendar.gif",
//			buttonImageOnly: true,
//			buttonText : "",
//			dateFormat : "yy-mm",
//			monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
//			monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
//			changeYear:true,
//			changeMonth:true,
//			changeDay:false,
//			showMonthAfterYear: true ,
//			yearSuffix: '',
//			onSelect : function(dateTEXT, inst){try{ change_month(eId);}catch(e){}}
//			});
		}else if( eDataFormat == "dfDateYy" && ! (element.attr("readOnly") || element.attr("disabled"))  )  {
			element.wrap(" <div class='input-group date' id='myDatepicker'></div>");
			$('<span class="input-group-addon"><i class="fas fa-calendar-alt"></i></span>').insertAfter(element);
			element.parent().datetimepicker({
				locale : 'ko', 
				format: 'YYYY',
				allowInputToggle: true,
			
			});
		}
	});
}

function formatValue(eId, str, data_format)
{
	$("#"+eId).val(str);
//  $("#"+eId).IBMaskEdit("value", str);
}

function sltEditorBind(element){
	$("textarea[data-editor=true]").each(function(){
		var id = $(this).attr('id');
		$('#'+id).summernote({
			lang: 'ko-KR',
			height: 250,
			disableDragAndDrop: true,
			toolbar: [
				['style', ['bold', 'italic', 'underline', 'clear']],
				['fontsize', ['fontsize']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['height', ['height']],
				['table', ['table']],
				['hr', ['hr']],
				['insert', ['link']],
				['codeview',['codeview']]
			]
		});
	});
}

function sltByteCheck(element){
	//Input[text,password], Textarea byte 체크(UTF-8)
	$("input[type='text'][data-max-byte], input[type='password'][data-max-byte], textarea[data-max-byte]").change(function(){
		var id = $(this).attr("id");
		var title = $("label[for="+id+"]").text();
		var maxLength = $(this).attr("data-max-byte");
		TimsUtil.maxLengthCheck($(this), title, maxLength);
	});
}

function sltUpperCase(element){
	var selector = null;
	if(element)
		selector = $(element).find("input[data-upper-case], textarea[data-upper-case]");
	else
		selector = $("input[data-upper-case], textarea[data-upper-case]");
	
	selector.change(function(){
		var value = $(this).val().toUpperCase();
		$(this).val(value);
	});
}

function sltLowerCase(element){
	var selector = null;
	if(element)
		selector = $(element).find("input[data-lower-case], textarea[data-lower-case]");
	else
		selector = $("input[data-lower-case], textarea[data-lower-case]");
	
	selector.change(function(){
		var value = $(this).val().toLowerCase();
		$(this).val(value);
	});
}

function sltDataPlayer(element){
	var selector = null;
	if(element)
		selector = $(element).find("div[data-player]");
	else
		selector = $("div[data-player]");
	
	selector.each(function(){
		var playerId = $(this).attr('data-player');
		var posterUrl = $(this).attr('data-poster');
		var videoUrl = $(this).attr('data-video');
		var videoType = $(this).attr('data-video-type');
		
		var html= "<video id='"+playerId+"' class='video-js'></video>";
		$(this).append(html);
		
		var player = videojs(playerId, {
			width: 960, //플레이어 넓이
			height: 640, //플레이어 높이
//	 		aspectRatio: '4:3', //플레이어 가로세로 비율
			controls: true, //컨트롤바여부
			autoplay: false, //시작버튼을 누르지 않아도 시작 여부
			loop: false, //반복재생여부
			muted: false, //음소거여부
			preload: 'auto', //?
			poster: posterUrl, //시작화면에서 포스터URL
			sources: [
				{src: videoUrl, type: videoType}
			]
		});
	});
}

function sltDataIme(element){
	var selector = null;
	if(element)
		selector = $(element).find("input[data-ime], textarea[data-ime]");
	else
		selector = $("input[data-ime], textarea[data-ime]");
	
	selector.each(function(){
		var imeMode = $(this).attr("data-ime");
		var mode="";
		switch(imeMode.toUpperCase()){
			case "EN":{
				//첫 입력시 영어모드
				mode = "ime-en";
			}
			break;
			case "KR":{
				//첫 입력시 한글모드
				mode = "ime-kr";
			}
			break;
			case "NKR":{
				//영문만 입력가능
				mode = "ime-nkr";
			}
			break;
			default :{
				//한영모드
				mode = "ime-auto";
			}
		}
		$(this).addClass(mode);
	});
}

function sltMakeAction(element){
	var selector = null;
	
	if(element)
		selector = $(element).find('input[data-check-all]');
	else
		selector = $('input[data-check-all]');
	
	selector.click(function(e){
		var name = $(this).attr("data-check-all");
		var checked = this.checked;
		$("input[type=checkbox][name="+name+"]").each(function(){
			this.checked=checked;
		});
	});
	//--------------------------------------------------------------
	if(element)
		selector = $(element).find('[data-action],[data-click]');
	else
		selector = $('[data-action],[data-click]');
	
	selector.each(function(e){
		var action = $(this).attr("data-action");
		if(action){
			var ev = "";
			if(this.tagName == "SELECT"){
				ev="change";
			}
			else{
				ev="click";
				$(this).css("cursor","pointer");
			}
			$(this).on(ev,function(e){
				if($(this).hasClass("disabled"))
					return ;
				if($(this).parents(".disabled").length)
					return;
				if(window[action]&&typeof window[action]==="function"){
					//|| $(this).attr("type").toLowerCase() != "radio"
					if(this.tagName.toLowerCase() != "input" )
						e.preventDefault();
					window[action]($(this));
				}
				else if(window["doAction"]&&typeof window["doAction"]==="function")
				{
					if(this.tagName.toLowerCase() != "input" )
						e.preventDefault();
					window["doAction"](action,$(this));
				}
			});
		}
	});
	//--------------------------------------------------------------
	if(element)
		selector = $(element).find('[data-enter]');
	else
		selector = $('[data-enter]');
	
	selector.each(function(){
		var action = $(this).attr("data-enter");
		if(action){
			$(this).keydown(function(e){
				try{
					if(e.which==13)
					{
						e.preventDefault();
						if(window[action]&&typeof window[action]==="function")
							window[action]($(this));
						else if(window["doAction"]&&typeof window["doAction"]==="function")
							window["doAction"](action,$(this));
						
					}
				}catch(e){
					console.log(e.message);
				}
			});
		}
	});
}

/*
function setDate (date, params){
	var id = params.id;
	var data_format = params.data_format;
	var element = params.el;
	// 달력 팝업 Dialog서 날짜 선택시 리턴값
	if(data_format == "dfDateYmd") {
		eval(id).SetValue(date);
	}
	
	if(typeof(change_date) == "function") {
		try{ change_date(id);$(element).trigger("change");}catch(e){}
	}
}
*/