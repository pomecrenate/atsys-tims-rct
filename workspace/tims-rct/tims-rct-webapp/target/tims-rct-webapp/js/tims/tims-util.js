
if (!window["TimsUtil"])
{
	window["_timsDialog"]=null;
	TimsDialog = function(options)
	{
		if (options)
			return new BootstrapDialog(options);
		else
			return new BootstrapDialog();
	};
	window["TimsError"] = function(element, msg)
	{
		this.element = element;
		this.msg = msg;
	};

	window["TimsKeyRanges"] = {
		number : {
			start : 48,
			end : 57
		},
		smallChar : {
			start : 97,
			end : 122
		},
		largeChar : {
			start : 65,
			end : 90
		},
		numPadNum : {
			start : 96,
			end : 105
		},
		numPadChar : {
			start : 96,
			end : 111
		}
	};

	if (!console)
		console = {
			log : function(msg)
			{}
		};
	window["Utilities"]=window["TimsUtil"] = {
		_NULL_RETURN : true,
		browserType : null,
		browserMode : null,
		uniCodeByte : 3,
		dialogs : [],
		getReadableFileSizeString:function (fileSizeInBytes) {

			if(!fileSizeInBytes)
				return "0 KB";
		    var i = -1;
		    var byteUnits = [' KB', ' MB', ' GB', ' TB', 'PB', 'EB', 'ZB', 'YB'];
		    do {
		        fileSizeInBytes = fileSizeInBytes / 1024;
		        i++;
		    } while (fileSizeInBytes > 1024);

		    return Math.max(fileSizeInBytes, 0.1).toFixed(1) + byteUnits[i];
		},
		guid :function() {
  				function s4() {
    				return Math.floor((1 + Math.random()) * 0x10000)
      			.toString(16)
      			.substring(1);
  				}
  				return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
    			s4() + '-' + s4() + s4() + s4();
		},
		getLmsId :function() {
				return TimsUtil.guid().replaceAll("-","").toUpperCase().substring(1,21);
		},
		alert : function(msg)
		{
			return alert(msg);
			// this.toast(msg);
			// return alert(msg);
		},
		confirm : function(msg)
		{
			return confirm(msg);
		},
		alertDialog:function(message,title,width, height,callback)
		{
			if(!title)
				title="정보";
			var buttons = [{
                label: '확인',
                cssClass: 'btn-primary',
                action: function(dialogItself){
                    if(callback)
                    {
                    	try{
                    		callback();
                    	}catch(e){

                    	}
                    }
					dialogItself.close();
                }
            }];
			TimsUtil.dialog(title,message,width, height,buttons);
		},
		confirmDialog:function(message,title,callback)
		{
			if(!title)
				title="정보";
			var buttons = [
			               {
                label: '확인',
                cssClass: 'btn-primary',
                action: function(dialogItself){
                	var cancel = callback();
                	if(cancel != false)
                    	dialogItself.close();
                }
            }
			,{
                label: '취소',
                cssClass: 'btn-info',
                action: function(dialogItself){
                    dialogItself.close();
                }}
			];
			return TimsUtil.dialog(title,message,null, null,buttons);
		},
		closeDialog : function(){
//			if(_timsDialog){
//				try{
//					_timsDialog.close();
//					_timsDialog = null;
//				}catch(e){}
//			}
			
			$("#urlDialog").attr("src","");
			$("#timsdlg").modal('hide');
			$("#urlNoneCloseDialog").attr("src","");
			$("#timsNoneClosedlg").modal('hide');
		},
		dialog : function(title, message, width, height, buttons, options,bOpen)
		{
			if(!$("#timsdlg").length){
				var html = '';
				html+='<div class="modal fade" id="timsdlg" tabindex="-1">	       ';
				html+='<div class="modal-dialog" id="modaldlg">					       ';
				html+='<div class="modal-content">					       ';
				html+='<div class="modal-header">					       ';
				html+='<button type="button" class="close" data-dismiss="modal"><i class="fas fa-times"></i></button>     ';
				html+='<h4 class="modal-title" id="dialogTitle"></h4>	       ';
				html+='</div>								       ';
				html+='<div class="modal-body" id="dialogBody">						       ';
				html+='</div>								       ';
				html+='</div>								       ';
				html+='</div>								       ';
				html+='</div>								       ';
				$("body").append(html);
			}
//			alert(width);
//			$("#timsdlg").width(width)
			$("#timsdlg").find("#dialogTitle").html(title);
			$("#timsdlg").find("#dialogBody").html(message);
			$("#timsdlg").find("#modaldlg").width(width);
			$("#timsdlg").find("#dialogBody").height(height);
			var ret =  $("#timsdlg").modal({
				backdrop : "static",
				keyboard : false
			});
			ret.close=function(){
				$(this).modal('hide');
			};
			$('#timsdlg').on('hidden.bs.modal', function (e) {
				 $('#timsdlg').find("#urlDialog").attr("src","/blank.jsp");
			});
			
			return ret;
//			TimsUtil.closeDialog();--
//
//			if(typeof bOpen === "undefined")
//				bOpen = true;
//			if(typeof message === "string")
//				message ="<div name='dlgWrapper' style='widht100%;height:100%'>"+message+"</div>";
//			else
//				message = jQuery(message).wrapAll("<div name='dlgWrapper' style='widht100%;height:100%'/>").parent();
//			var opts = {
//				title : title,
//				message : message,
//				closable : true,
//				closeByBackdrop: false,
//	            closeByKeyboard: false,
//				draggable : true,
//				onshown : function(dialog){
//					jQuery(document).off('focusin.modal');
//				},
//				onhidden : function(dialog){
//					window.focus();
//				}
//			};
//			if (buttons)
//			{
//				if(!options)
//					options={};
//				options["buttons"] = buttons;
//			}
//
//			jQuery.extend(opts,options);
//			var dialog = BootstrapDialog.show(opts);
//			dialog.getModalDialog().css("top",50);
//			dialog.getModalDialog().width(width);
//			dialog.getModalBody().find("[name=dlgWrapper]").parent().height(height);
//			_timsDialog = dialog;
//			return dialog;
				
		},
		noneCloseDialog : function(title, message, width, height, buttons, options,bOpen)
		{
			if(!$("#timsNoneClosedlg").length){
				var html = '';
				html +=	'<div class="modal fade" id="timsNoneClosedlg" tabindex="-1">';
				html +='	<div class="modal-dialog" id="modaldlg">';
				html +='		<div class="modal-content">';
				html +='			<div class="modal-header">';
				html +='				<h4 class="modal-title" id="dialogTitle"></h4>';
				html +='			</div>';
				html +='			<div class="modal-body" id="dialogBody">';
				html +='			</div>';
				html +='		</div>';
				html +='	</div>';
				html +='</div>';
				$("body").append(html);
			}
			$("modal-header").find("button").hide();
			$("#timsNoneClosedlg").find("#dialogTitle").html(title);
			$("#timsNoneClosedlg").find("#dialogBody").html(message);
			$("#timsNoneClosedlg").find("#modaldlg").width(width);
			$("#timsNoneClosedlg").find("#dialogBody").height(height);
			var ret =  $("#timsNoneClosedlg").modal({
				backdrop : "static",
				keyboard : false
			});
			ret.close=function(){
				$(this).modal('hide');
			};
			$('#timsNoneClosedlg').on('hidden.bs.modal', function (e) {
				 $('#timsNoneClosedlg').find("#urlNoneCloseDialog").attr("src","/blank.jsp");
			});
			
			return ret;
		},
		urlDialog : function(title, url, width, height, buttons,options,hasClose)
		{
			var html = '<iframe id="urlDialog" name="urlDialog" frameborder="0" src="' + encodeURI(url) + '" width="100%" height="100%"></iframe>';
			if(!options)
				options = {};
			if(!buttons && hasClose)
			{
				buttons = [{
               				 label: '닫기',
			                cssClass: 'btn-primary',
			                action: function(dialogItself){
			                   dialogItself.close();
                				}
            				}];
			}

			// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
		/*	callTimeOffsetBetweenServerAndClient(url);*/
			
			return TimsUtil.dialog(title,html,width,height,buttons,options);
		},
		urlNoneCloseDialog : function(title, url, width, height, buttons,options,hasClose)
		{
			var html = '<iframe id="urlNoneCloseDialog" name="urlNoneCloseDialog" frameborder="0" src="' + encodeURI(url) + '" width="100%" height="100%"></iframe>';
			if(!options)
				options = {};
//			if(!buttons && hasClose)
//			{
//				buttons = [{
//               				 label: '닫기',
//			                cssClass: 'btn-primary',
//			                action: function(dialogItself){
//			                   dialogItself.close();
//                				}
//            				}];
//			}
			return TimsUtil.noneCloseDialog(title,html,width,height,buttons,options);
		},
		openFrontLayerPopup: function(url, param, width, height){
			if(!url) return ;
			if(!param) return ;
			if(!width) return ;
			if(!height) return ;
			
			APP.MODAL.open({
				iframe : {
						url : url,
						param : param
				},
				width: $(window).width() > width ? width : $(window).width() * 0.9,
				height: $(window).height() > height ? height : $(window).height() * 0.9,
				header : false
			});
			
			// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
			callTimeOffsetBetweenServerAndClient(url);
		},
		closeFrontLayerPopup : function(){
			parent.APP.MODAL.callback();
		},
		openLayerPopup:function(title,html){
			if(!$("div#layerPopup").length){
				//var div ='<div class="layerArea" id="layerPopup"><p class="tit"><span id="layerPopupTitle">'+title+'</span><a href="javascript:void(0);" class="closeLy"><img src="/tiles/front/images/common/btn_popClose.png" alt="" /></a></p><div id="layerPopupContent" class="contLy"></div></div>';
				var div ='<div class="layerArea" id="layerPopup"><p class="tit"><span id="layerPopupTitle">'+title+'</span><a href="javascript:void(0);" class="closeLy"></a></p><div id="layerPopupContent" class="contLy"></div></div>';

				$("body").append(div);
			}
			$("#layerPopupContent").html(html);
			$("#layerPopupTitle").html(title);
			$("div#layerPopup").layerScript({divs : "layerPopup"});

		},
		openLayerUrlPopup:function(title,url,width,height){
			if(width > $("body").width()-60)
				width = $("body").width()-60;
			var html = '<iframe id="layerPopupIF" name="layerPopupIF" width="'+width+'" height="'+height+'" src="'+url+'" frameborder="0"/>';
			TimsUtil.openLayerPopup(title,html);
		},
		closeLayerPopup:function(){

			layerClose("layerPopup");
		},
		showToast : function(option)
		{
			return true;
			if (!option.text)
			{
				TimsUtil.hideToast();
				return;
			}
			var defOption = {
				showHideTransition : 'plain',
				allowToastClose : true,
				hideAfter : 3000,
				stack : 1,
				position : 'mid-center',
				textAlign : 'left'
			};
			$.extend(defOption,option);
			$.toast(defOption);
		},
		hideToast : function(option)
		{
			$.toast().reset('all');
		},
		toast : function(msg, title, type, position)
		{
			if (!msg)
				msg = "";
			var option = {
				text : msg
			};
			if (title)
				option.heading = title; // Optional heading to be shown on the
			// toast
			if (!type) /* info,warning ,error,success */
				type = "info";
			option.icon = type;// Type of toast icon
			if (position)
				option.position = position;
			this.showToast(option);

		},
		toolTip : function(element, text)
		{

		},
		errorMsg : function(text, element)
		{
			TimsUtil.alert(text);
		},
		reportError : function(errors)
		{
			if(errors && errors.length)
			{
				var err = errors[0];
				var msg = TimsUtil.getValidateMsg(err.errors[0].msg,err.element);
				TimsUtil.errorMsg(msg);
				err.element.focus();
				return false;
			}
			return true;
		},
		// highlight : function(element, bHigh)
		// {
		// $(element).highlight(bHigh);
		//
		// },
		debug : function(text, type)
		{
			console.log(text);
		},
		getCharFromEvent : function(e)
		{
			var orgEvent = e.originalEvent;
			if (!orgEvent)
				return null;

			if (this.isControlKey(e))
				return null;
			if (!(typeof e.char === "undefined"))
				return e.char;
			var orgEvent = e.originalEvent;
			if (orgEvent && !(typeof orgEvent.key === "undefined"))
				return orgEvent.key;

			if (orgEvent && orgEvent.key && orgEvent.key.length == 1)
				return orgEvent.key;
			/* maybe chrome */
			if (orgEvent && orgEvent.keyIdentifier)
			{
				if (orgEvent.keyIdentifier.substring(0,2) != "U+")
					return null;
				return String.fromCharCode(this.parseInt(orgEvent.keyIdentifier.substring(2),16));
			}

			if (this.checkCharCodeRange(e.which,TimsKeyRanges.numPadNum))
				return String.fromCharCode(e.which - 48);
			switch (e.which)
			{
			case 106:
				return "*";
			case 107:
				return "+";
			case 109:
				return "-";
			case 110:
				return ".";
			case 111:
				return "/";
			default:
				if (orgEvent && orgEvent.location == 3)
					return null;
				else
					return String.fromCharCode(e.which);
			}

		},
		isControlKeyCode : function(keyCode)
		{
			if (keyCode <= 46)
				return true;
			if (keyCode >= 112 && keyCode <= 123)
				return true;
			return false;
		},
		isControlKey : function(e)
		{
			if (e.type == "keydown")
			{
				if (TimsUtil.isControlKeyCode(e.which))
					return true;
				else
					return false;
			}
			/* 32이하는 controll 문자 */
			if (e.altKey || e.ctrlKey)
				return true;
			if (e.which <= 32)
				return true;
			/* maybe ie 문자를 가지지 않는 키코드는 특수키 */
			if (!(typeof e.char === "undefined"))
				return !e.char;

			var orgEvent = e.originalEvent;
			/* numpad numberkey일 경우 아래코드를 제외하고는 전부 문자 없음. */
			if (orgEvent && orgEvent.location == 3 && !this.checkNumpadKey(e,false))
				return true;
			/**/
			if (orgEvent && orgEvent.key && orgEvent.key.length > 1)
				return true;
			/* maybe chrome */
			if (orgEvent && orgEvent.keyIdentifier)
			{
				if (e.keyCode == 229)
					return true;
				if (orgEvent.keyIdentifier.substring(0,2) != "U+")
					return true;
				return false;
			}
			/* maybe ff */
			if (typeof e.char === "undefined")
			{
				if (e.key && e.key.length > 1)
					return true;
				if (e.keyCode == 0)
					return true;
				return false;
			}

		},
		isNumberKeyCode : function(keyCode)
		{
			return this.isNumberCode(keyCode) || this.checkCharCodeRange(keyCode,TimsKeyRanges.numPadNum);
		},
		isNumberCode : function(charCode)
		{
			return this.checkCharCodeRange(charCode,TimsKeyRanges.number);
		},
		isAlphaKeyCode : function(keyCode)
		{
			this.checkCharCodeRange(keyCode,TimsKeyRanges.largeChar);
		},
		isAlphaCode : function(charCode)
		{
			return this.checkCharCodeRange(charCode,TimsKeyRanges.smallChar) || this.checkCharCodeRange(charCode,TimsKeyRanges.largeChar);;
			// charCode = this.checkCharCode(charCode);
			// return (charCode >= 97 && charCode <= 122)
			// || (charCode >= 65 && charCode <= 90);
		},
		isAlphaNumberCode : function(charCode)
		{
			return this.isNumberCode(charCode) || this.isAlphaCode(charCode);
		},
		isAlphaNumberKeyCode : function(charCode)
		{
			return this.isNumberCode(charCode) || this.isAlphaCode(charCode) || this.isNumberKeyCode(charCode) || this.isAlphaKeyCode(charCode);
		},
		isAnsiKey : function(e)
		{

			return this.isAnsiCode(this.getCharFromEvent(e));
		},
		isAnsiCode : function(charCode)
		{
			return this.checkCharCodeRange(charCode,{
				start : 33,
				end : 127
			})
		},
		isUniCode : function(char, keyCode)
		{
			if (!this.isAlphaCode(char))
				return false;
			return (this.checkCharCode(char.toLowerCase()) != this.checkCharCode(keyCode) && this.checkCharCode(char.toUpperCase()) != this.checkCharCode(keyCode));

		},
		checkCharCode : function(charCode)
		{
			if (typeof charCode === "string" && charCode.length > 0)
				charCode = charCode.charCodeAt(0);
			return charCode;
		},
		checkCharCodeRange : function(charcode, range)
		{
			if (!range || !range.start || !range.end)
				return charcode;
			charcode = this.checkCharCode(charcode);
			return range.start <= charcode && range.end >= charcode;
		},
		checkNumber : function(value)
		{
			if (!value)
				return false;
			return this.checkCharRange(value,48,57);
		},
		checkAlphabet : function(value)
		{
			if (!value)
				return false;
			value = value.toString().toLowerCase();
			return this.checkCharRange(value,97,122);
		},
		checkAnsiCode : function(value)
		{
			return this.checkCharRange(value,33,127);
		},
		checkAlphabetNumber : function(value)
		{
			return this.checkString(value,"an");
		},
		checkString : function(value, flags)
		{
			if (!value || !flags)
				return false;
			var ranges = this.getRangeFlag(flags);

			if (!ranges || !ranges.length)
				return false;
			return this.checkCharRanges(value,ranges);
		},
		checkCharRange : function(value, start, end)
		{
			if (value && !(typeof value === "string"))
				value = value.toString();
			start = this.checkCharCode(start);
			end = this.checkCharCode(end);

			if (!value)
				return true;
			for (var i = 0; i < value.length; i++)
			{
				var n = value.charCodeAt(i);
				if (n < start || n > end)
					return false;
			}
			return true;
		},
		checkCharInString : function(value, string)
		{
			if (!string || !value)
				return false;
			for (var i = 0; i < value.length; i++)
			{
				var s = value.substring(i,i + 1);
				if (string.indexOf(s) < 0)
					return false;
			}
			return true;
		},
		checkCharRanges : function(value, ranges)
		{
			if (!ranges || !ranges.length)
				return false;
			for (var i = 0; i < value.length; i++)
			{
				var n = value.charCodeAt(i);
				for (var j = 0; j < ranges.length; j++)
				{
					if (n >= ranges[j].start && n <= ranges[j].end)
						return true;
				}
			}
			return false;
		},
		checkNumpadKey : function(e, numberOnly)
		{
			var orgEvent = e.originalEvent;

			if (orgEvent && orgEvent.location && orgEvent.location == 3)
			{
				if (numberOnly)
					return this.checkCharCodeRange(e.which,TimsKeyRanges.numPadNum);
				else
					return this.checkCharCodeRange(e.which,TimsKeyRanges.numPadChar);

			}
			return false;
		},
		getClipboardData : function(e)
		{
			var pastedText = undefined;
			if (window.clipboardData && window.clipboardData.getData)
			{ // IE
				pastedText = window.clipboardData.getData('Text');
			}
			else
			{
				if(!e)
					e=event;
				if(!e)
					return null;
				if (e.clipboardData && e.clipboardData.getData)
				{
					pastedText = e.clipboardData.getData('text/plain');
				}
				else
					(e.originalEvent && e.originalEvent.clipboardData)
				pastedText = e.originalEvent.clipboardData.getData('text/plain');
			}
			return pastedText;

		},
		getRangeFlag : function(flags)
		{
			var ranges = [];
			flags = flags.toLowerCase();
			for (var i = 0; flags && i < flags.length; i++)
			{
				switch (flags.substring(i,i + 1))
				{
				case "n":
					ranges.push(TimsKeyRanges.number);
					break;
				case "a":
					ranges.push(TimsKeyRanges.smallChar);
					ranges.push(TimsKeyRanges.largeChar);
					break;
				}
			}
			return ranges;
		},
		getByteLength : function(s)
		{
			var b,i,c;
			for (b = i = 0; c = s.charCodeAt(i++); b += c >> 11 ? this.uniCodeByte : c >> 7 ? 2 : 1);
			return b;
		},
		parseInt : function(str, x)
		{
			if (!x)
				x = 10;
			return parseInt(str,x);
		},

		getEventValue : function(input, repVal)
		{
			repVal = repVal ? repVal : "";
			var checkVal = $(input).val();
			var nStart = this.getSelectionStart(input);
			var nEnd = this.getSelectionEnd(input);
			if (nStart != nEnd || repVal.length)
				checkVal = checkVal.substring(0,nStart) + repVal + checkVal.substring(nEnd);
			return checkVal;
		},
		getBrowserType : function()
		{

			if (this.browserType)
				return this.browserType;
			var _ua = navigator.userAgent;
			var rv = -1;

			// IE 11,10,9,8
			var trident = _ua.match(/Trident\/(\d.\d)/i);
			if (trident != null)
			{
				if (trident[1] == "7.0")
					return this.browserType = rv = "IE" + 11;
				if (trident[1] == "6.0")
					return this.browserType = rv = "IE" + 10;
				if (trident[1] == "5.0")
					return this.browserType = rv = "IE" + 9;
				if (trident[1] == "4.0")
					return this.browserType = rv = "IE" + 8;
			}

			if (navigator.appName == 'Microsoft Internet Explorer')
				return this.browserMode = rv = "IE" + 7;

			var agt = _ua.toLowerCase();
			if (agt.indexOf("chrome") != -1)
				return this.browserType = 'Chrome';
			if (agt.indexOf("opera") != -1)
				return this.browserType = 'Opera';
			if (agt.indexOf("staroffice") != -1)
				return this.browserType = 'Star Office';
			if (agt.indexOf("webtv") != -1)
				return this.browserType = 'WebTV';
			if (agt.indexOf("beonex") != -1)
				return this.browserType = 'Beonex';
			if (agt.indexOf("chimera") != -1)
				return this.browserType = 'Chimera';
			if (agt.indexOf("netpositive") != -1)
				return this.browserType = 'NetPositive';
			if (agt.indexOf("phoenix") != -1)
				return this.browserType = 'Phoenix';
			if (agt.indexOf("firefox") != -1)
				return this.browserType = 'Firefox';
			if (agt.indexOf("safari") != -1)
				return this.browserType = 'Safari';
			if (agt.indexOf("skipstone") != -1)
				return this.browserType = 'SkipStone';
			if (agt.indexOf("netscape") != -1)
				return this.browserType = 'Netscape';
			if (agt.indexOf("mozilla/5.0") != -1)
				return this.browserType = 'Mozilla';
		},
		getBrowserMode : function()
		{

			if (this.browserMode)
				return this.browserMode;
			if (this.getBrowserType().substring(0,2) != "IE")
				return this.browserMode = this.getBrowserType();

			var _ua = navigator.userAgent;
			if (0 > _ua.indexOf("compatible"))
				return this.browserMode = this.getBrowserType();
			// IE 11,10,9,8
			var idx = _ua.indexOf("MSIE ");
			if (idx < 0)
				return this.browserMode = this.getBrowserType();
			idx += 5;
			var ver = _ua.substring(idx,_ua.indexOf(".",idx));
			if (ver)
				return this.browserMode = "IE" + ver;
			if (navigator.appName == 'Microsoft Internet Explorer')
				return this.browserMode = "IE" + 7;
			return this.browserMode = "IE";
		},
		getSelectionStart : function(input)
		{
			var pos = input.value.length;
			if (typeof (input.selectionStart) != "undefined")
				pos = input.selectionStart;
			else if (input.createTextRange)
			{
				var r = document.selection.createRange().duplicate();
				r.moveEnd('character',input.value.length);
				if (r.text == '')
					pos = input.value.length;
				pos = input.value.lastIndexOf(r.text);
			}
			return pos;
		},
		getSelectionEnd : function(input)
		{
			var pos = input.value.length;
			if (typeof (input.selectionEnd) != "undefined")
				pos = input.selectionEnd;
			else if (input.createTextRange)
			{
				var r = document.selection.createRange().duplicate();
				if (r.text == '')
					return this.getSelectionStart(input);
				r.moveStart('character',-input.value.length);
				if (r.text == '')
					pos = input.value.length;
				pos = input.value.lastIndexOf(r.text);
			}
			return pos;
		},
		setSelectionRange : function(input, selectionStart, selectionEnd)
		{
			if (input.setSelectionRange)
			{
				input.focus();
				input.setSelectionRange(selectionStart,selectionEnd);
			}
			else if (input.createTextRange)
			{
				var range = input.createTextRange();
				range.collapse(true);
				range.moveEnd('character',selectionEnd);
				range.moveStart('character',selectionStart);
				range.select();
			}

			return input;
		},
		getDateMask : function(fmt)
		{

			if (fmt)
				fmt = "Ymd";
			var mask = "";
			for (var i = 0; i < fmt.length; i++)
			{
				if (mask.length > 0)
					mask += "-"
				var charFmt = fmt.substring(i,i + 1);
				switch (charFmt)
				{
				case "y":
				case "Y":
					mask += "YYYY";
					break;
				case "m":
				case "M":
					mask += "MM";
					break;
				case "d":
				case "D":
					mask += "DD";
				}
			}
			return mask;
		},
		checkBrowserUnicode : function(e)
		{
			if (!e.char && e.which == 229)
				return false;

			var orgEvent = e.originalEvent;
			/* numpad numberkey일 경우 아래코드를 제외하고는 전부 문자 없음. */
			if (orgEvent && orgEvent.location == 3 && !this.checkNumpadKey(e,false))
				return false;
			if (e.which == 0)
			{
				if (orgEvent.code && orgEvent.code.length && orgEvent.code.length == 4 && orgEvent.code.substring(0,3).toLowerCase() == "key" && TimsUtil.isAlphaCode(orgEvent.code.substring(3)))
					return false;
			}
			return true;
		},
		getDate : function(strDate)
		{
			try
			{
				if (!strDate)
					return null;
				strDate = this.getNumberOnly(strDate);
				if(strDate.length==4)
					strDate += "0101";
				else if(strDate.length==6)
					strDate +="01";
				if (!strDate || strDate.length != 8)
					return false;
				var year = this.parseInt(strDate.substring(0,4)), month = this.parseInt(month = strDate.substring(4,6)), day = this.parseInt(day = strDate.substring(6,8));
				return new Date(year,month - 1,day);
			}
			catch (e)
			{
				return null;
			}
		},
		getDateTime : function(strDate)
		{
			try
			{
				if (!strDate)
					return null;
				strDate = this.getNumberOnly(strDate);
				if(strDate.length==4)
					strDate += "0101";
				else if(strDate.length==6)
					strDate +="01000000";
				var year = this.parseInt(strDate.substring(0,4)), month = this.parseInt(strDate.substring(4,6)), day = this.parseInt(strDate.substring(6,8))
				, hour = this.parseInt(strDate.substring(8,10)), min = this.parseInt(strDate.substring(10,12)), sec = this.parseInt(strDate.substring(12,14))
				;
				return new Date(year,month - 1,day,hour,min,sec,0);
			}
			catch (e)
			{
				return null;
			}
		},
		getCurrentDate : function()
		{
			try
			{
				var cur = new Date();
				return new Date(cur.getFullYear(),cur.getMonth(),cur.getDate());
			}
			catch (e)
			{
				return null;
			}
		},
		getDateString : function(date, format)
		{
			if (!format)
				format = "yy년 mm월 dd일";
			else
			{
				var regEx = /yy/gi;
				format = format.toLowerCase().replace(regEx,'y');
			}
			if (!date)
				date = new Date();
			return $.datepicker.formatDate(format,date);
		},
		validateDate : function(strDate)
		{
			var date = this.getDate(strDate);
			if (!date)
				return false;
			var tmpDate = this.getNumberOnly(strDate);
			if(tmpDate.length==4)
				tmpDate += "0101";
			else if(tmpDate.length==6)
				tmpDate +="01";
			var year = this.parseInt(tmpDate.substring(0,4)), month = this.parseInt(month = tmpDate.substring(4,6)), day = this.parseInt(day = tmpDate.substring(6,8));

			if (date.getFullYear() == year && (date.getMonth() + 1) == month && date.getDate() == day)
				return date;
			else
				return null;

		},
		ssn1ToDate : function(ssn1, flag)
		{
			if (!ssn1 || ssn1.length != 6)
				return null;
			var prefix;
			if (flag)
			{
				if (flag > 2)
					prefix = "20";
				else
					prefix = "19";
				return this.validateDate(prefix + ssn1);
			}
			else
				return this.validateDate("19" + ssn1) || this.validateDate("20" + ssn1);

		},
		getNumberOnly : function(str)
		{
			var ret = "";
			for (var i = 0; str && i < str.length; i++)
			{
				var ch = str.substring(i,i + 1);
				if (this.isNumberCode(ch))
					ret += ch;
			}
			return ret;
		},
		getAlphaOnly : function(str)
		{
			var ret = "";
			for (var i = 0; str && i < str.length; i++)
			{
				var ch = str.substring(i,i + 1);
				if (this.isAlphaCode(ch))
					ret += ch;
			}
			return ret;
		},
		getAlphaNumberOnly : function(str)
		{
			var ret = "";
			for (var i = 0; str && i < str.length; i++)
			{
				var ch = str.substring(i,i + 1);
				if (this.isAlphaNumberCode(ch))
					ret += ch;
			}
			return ret;
		},
		getLabel : function(fieldName, frmId)
		{
			if (frmId)
			{
				var frm = document.getElementById(frmId);
				if (!frm)
					return null;
				return TimsUtil.getFormLabel(frm[fieldName]);
			}
			return TimsUtil.getFormLabel(document.getElementById(fieldName));
		},
		getFormLabel : function(input)
		{
			if (!input)
				return null;
			attr = $(input).attr("title");
			if (attr)
				return attr;
			var id = input.id;

			if (!id)
				id = $(input).attr("name")
			if (!id)
				return "";
			var label = null;
			if (input.form)
			{
				label = $(input.form).find("label[for=" + id + "]");
				if(!label.length)
					label=$(input.form).find("label[for=" + id.toLowerCase() + "]");
			}
			if (!label || !label.length)
			{
				lable = $("label[for=" + id + "]");
				if(!label.length)
					lable = $("label[for=" + id.toLowerCase() + "]");
			}
			if (label && label.length)
				return $(label[0]).text();
			else
				return "";
		},
		trim : function(value)
		{
			if (!value)
				return value;
			return $.trim(value);
		},
		getFormObject : function(frm)
		{
			if (!frm)
				return null;
			var selector = null;
			if (typeof frm === "string")
			{
				if(frm.substring(0,1)!="#")
					selector = "#" + frm;
			}
			else
				selector = frm;

			var jFrm = $(selector);
			if (!jFrm || !jFrm.length)
				return null;
			return jFrm;
		},
		paramToMap : function(param)
		{
			if (!param)
				return null;
			var map = {};
			if (param.substring(0,1) == "?" || param.substring(0,1) == "#")
				param = param.substring(1);
			var arr = param.split("&");
			for (var i = 0; i < arr.length; i++)
			{
				var str = arr[i];
				var idx = arr[i].indexOf("=");
				var name = "";
				var value = "";
				if (idx < 0)
					map[str] = null;
				else if (idx == 0)
					continue;
				else
					map[str.substring(0,idx)] = decodeURIComponent(str.substring(idx + 1));
			}
			return map;
		},
		mapToForm : function(map, frm)
		{
			if(!map)
				return;
			var jFrm = TimsUtil.getFormObject(frm);
			if (!jFrm || !jFrm.length)
				return null;
			var frmObj = jFrm[0];
			frmObj.reset();
			for(var name in map){

				jFrm.find("[name="+name+"]").val(map[name]);
			}
			return;
			var arr = jFrm.serializeArray();
			$.each(arr,function()
			{
//								if (typeof map[this.name] === "undefined" || !frmObj[this.name])

				if (typeof map[this.name] === "undefined" || jFrm.find("[name="+this.name+"]").length==0)
					return;
				jFrm.find("[name="+this.name+"]").val(typeof jFrm.find("[name="+this.name+"]").length==1 ? map[this.name] : [ map[this.name] ]);
			});
		},
		mapToParam : function(map)
		{
			if(!map)
				return "";
			return $.param(map);
			// var str = "";
			// for ( var idx in map)
			// {
			// if (str.length)
			// str += "&";
			// var typeOf = typeof map[idx];
			// if (typeOf.toLowerCase() == "object")
			// continue;
			// str += idx + "=" + map[idx];
			// }
			// return str;
		},
		formToParam : function(frm)
		{
			var jFrm = TimsUtil.getFormObject(frm);
			if (!jFrm || !jFrm.length)
				return null;
			return jFrm.serialize();
		},
		formToMap : function(frm)
		{
			var jFrm = TimsUtil.getFormObject(frm);
			if (!jFrm || !jFrm.length)
				return null;
			var map = {};
			var arr = jFrm.serializeArray();
			$.each(arr,function()
			{
				if (typeof map[this.name] === "undefined")
				{
					map[this.name] = this.value || '';
				}
				else
				{
					if (!map[this.name].push)
					{
						map[this.name] = [ map[this.name] ];
					}
					map[this.name].push(this.value || '');

				}
			});
			return map;

		},
		replaceString : function(str, rep, value)
		{

			if (!rep||!str)
				return str;

			var val = null;
			try
			{
				var arr = str.split(rep);
				val = arr.join(value);
			}
			catch (e)
			{
				val = str.replace(rep,value);
				while (val != str)
				{
					str = val;
					val = str.replace(rep,value);
				}
				return val;
			}
			return val;

		},
		highlight : function(input, bHighlight)
		{
			if(!input)
				return;
			if(input.length&&!input.tagName)
			{
				$(input).each(function(){
					if(this.tagName)
						TimsUtil.highlight(this,bHighlight);
				});
				return;
			}
			if ( !input.tagName)
				return;
			var tagName = input.tagName.toLowerCase();
			var color = "rgb(252, 248, 227)";
			var orgColor = $(input).attr("orgBgColor");
			if (typeof orgColor === "undefined")
				orgColor = "";
			if (typeof bHighlight === "undefined")
				bHighlight = !orgColor;
			var attr = "backgroundColor";// tagName=="select"?"background" :
			// "backgroundColor";
			if (bHighlight)
			{
				$(input).addClass("has-error");
				// if (!orgColor && orgColor != "")
				// {
				// orgColor = input.style[attr];
				// $(input).attr("orgBgColor",orgColor)
				// }
				//
				// input.style[attr] = color;
			}
			else
			{
				$(input).removeClass("has-error");
				// $(input).removeAttr("orgBgColor");
				// input.style[attr] = orgColor;
			}

		},
		isNumberInput : function(input)
		{
			return $(input).attr("type") == "number" || $(input).attr("data-type") == "number" || $(input).attr("data-type") == "ssn1" || $(input).attr("data-type") == "ssn2";
		},
		isCalendarInput : function(input)
		{
			return $(input).attr("type") == "date" || $(input).attr("data-type") == "date";
		},

		isCheckPass : function(value)
		{
			if (value == null)
				return true;
			if (value == "")
				return true;
			return false;
		},

		assertFalse : function(value)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return value.toString() == false.toString();
		},
		assertTrue : function(value)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return value.toString() == true.toString();
		},
		checkDecimalMax : function(value, max)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return Number(value.toString()) <= Number(max.toString());
		},
		checkDecimalMin : function(value, min)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return Number(value.toString()) >= Number(min.toString());
		},
		checkMax : function(value, max)
		{
			return this.checkDecimalMax(value,max);
		},
		checkMin : function(value, min)
		{
			return this.checkDecimalMin(value,min);
		},
		checkDigits : function(value, integer, fraction)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var values = value.toString().split(".");
			if (values.length == 0 || values.length > 2)
				return false;

			intValue = values[0];
			fraValue = values.length == 2 ? values[1] : "";

			if (integer > -1)
			{
				intValue = TimsUtil.parseInt(intValue);
				if (integer == 0 && (intValue != 0))
					return false;
				if (intValue.toString().length > integer)
					return false;
			}
			if (fraction > -1 && fraValue.length > 0)
			{
				if (values.length != 2)
					return false;
				var fr = TimsUtil.parseInt(fraValue);
				if (!typeof fr === "NaN")
					return false;
				if (fr.toString().length > fraction)
					return false;

			}

			return true;
		},
		checkFuture : function(value)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return Number(value) > Number(new Date());
		},
		checkPast : function(value)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return Number(value) < Number(new Date());
		},
		checkNotNull : function(value)
		{
			return value != null;
		},
		checkNull : function(value)
		{
			return value == null;
		},
		checkPattern : function(value, pattern, flags)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var flag = "";
			for (var i = 0; flags != null && i < flags.length; i++)
			{
				if (0x02 == flags[i])
					flag += "i";
				else if (0x08 == flags[i])
					flag += "m";
			}
			var reg = null;
			if (flag == "")
				reg = new RegExp(pattern);
			else
				reg = new RegExp(pattern,flag);
			return reg.test(value);
		},
		checkSize : function(value, min, max)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return this.checkMax(value.length,max) && this.checkMin(value.length,min);
		},
		checkSizeB : function(value, min, max)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var btLen = TimsUtil.getByteLength(value);
			return this.checkMax(btLen,max) && this.checkMin(btLen,min);
		},
		checkIsInteger : function(value)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var num = Number(value.toString());
			if (num == NaN)
				return false;
			return num == TimsUtil.parseInt(value.toString());
		},
		checkEmail : function(value)
		{

			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			// var pattern =
			// "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-.]+\.[a-zA-Z0-9-.]+$";
			// var pattern
			// ="^[0-9a-zA-Z]([\-.\w]*[0-9a-zA-Z\-_+])*@([0-9a-zA-Z][\-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9}$";
			var pattern = "^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$";
			var flags = [ 0x02 ];
			return this.checkPattern(value,pattern,flags);
		},
		checkCreditCardNumber : function(value)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return true;
		},
		checkLength : function(value, min, max)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			if (!typeof value === 'string')
				return false;
			return this.checkSize(value,min,max);
		},
		checkNotBlank : function(value)
		{
			return this.checkNotEmpty(TimsUtil.trim(value));
		},
		checkNotEmpty : function(value)
		{
			if (value == null)
				return false;
			if (value.length == 0)
				return false;
			return true;
		},

		checkRange : function(value, min, max)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return value <= max && value >= min;
		},
		checkSafeHtml : function(value, type, add)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return true;
		},
		checkScriptAssert : function(value, lang, script, alias)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			return true;
		},
		checkURL : function(value)
		{
			if (this.isCheckPass(value))
				return this._NULL_RETURN;
			var reg = /(http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w-.\/?%&;=]*)?)/gi;
			var url = value.match(reg);
			if (url == null || url.length == 0)
				return false;
			if (url[0] == value)
				return true;
			return false;
		},
		checkNumberOnly : function(value)
		{
			return this.checkPattern(value,"^[0-9]*$","");
		},
		goUrl : function(url, context)
		{
			if (!context)
				context = window;
			context.open(url,"_self");
		},
		checkAllBox : function(name, checked)
		{
			$("input[name=" + name + "]").prop("checked",checked);
		},
		toggleCheckBox : function(name)
		{
			var checked = false;
			if (TimsUtil.hasUncheckedBox(name))
				checked = true;
			TimsUtil.checkAllBox(name,checked);

		},
		hasUncheckedBox : function(name)
		{
			var chks = $("input[name=" + name + "]");
			for (var i = 0; i < chks.length; i++)
			{
				if ($(chks[i]).attr("checkAll"))
					continue;
				if (!chks[i].checked)
					return true;
			}
			return false;
		},
		
		objectToPostParam : function(obj)
		{
			var strParam = "";
			for ( var i in obj)
			{
				if (strParam.length)
					strParam += "&";
				strParam += i.toString() + "=" + obj[i];
			}
			return strParam;
		},
		overrideEvent : function(fnName, fn, bReplace)
		{
			if (!window[fnName] || bReplace)
				window[fnName] = fn;
			else
			{
				var orgFn = window[fnName];
				window[fnName] = function()
				{

					try
					{
						if (!orgFn.apply(this,arguments))
							return fn.apply(this,arguments);
					}
					catch (e)
					{
						console.error(e);
						return fn.apply(this,arguments);
					}
				};
			}

		},
		ajax : function(param)
		{
			$.ajax(param);
		},
		toJSON : function(param)
		{
			return JSON.stringify(param);
		},
		parseJSON :function(param)
		{
			return $.parseJSON(param);
		},
		blockUI : function(block)
		{
			if (typeof block === "undefined")
				block = true;
			if (!block)
				$.unblockUI();
			else
				$.blockUI();
		},
		getObject : function(url, param, jsonBody, option)
		{	
			// CSRF Token 
			var idChk = false;
			if(param) {
				var idChk = false;		
				$.each(param, function(id, value) {
					if(value && !Array.isArray(value)) {
						if(id == csrfTokenId) {
							idChk = true;		
						}
					}
				});
			} else {
				var param = {};
				param[csrfTokenId] = csrfToken;
			}
			if(!idChk) {
				param[csrfTokenId] = csrfToken;
			}
			
			var nocaching;
			if(url.indexOf("?")>0)
				nocaching="&";
			else
				nocaching="?";
			var url =url+ nocaching+"nocachingParam="+TimsUtil.guid();
			var retVal = null;
			var opt = {
					url : url,
					dataType : 'json',
					data : jsonBody&&param ? TimsUtil.toJSON(param) : param,
					type : 'POST',
					cache : false,
					contentType : jsonBody ? 'application/json; charset=utf-8' : 'application/x-www-form-urlencoded; charset=utf-8',
					async : false, // false : 동기식, true : 비동기식
					success : function(data, textStatus, jqXHR)
					{
						if(data.error){
							if(data.message)
								alert(data.message);
						}
						else
							retVal = data;
					},
					error : function(jqXHR, textStatus, errorThrown)
					{
						retVal = null;
					},
					handleError: true

				};
			
			if(option)
				opt = $.extend(opt,option);
				
			TimsUtil.ajax(opt);
			
			// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
			callTimeOffsetBetweenServerAndClient(url);
			
			return retVal;
		},
		sendData : function(url,param,jsonBody,callback)
		{
			// CSRF Token 
			var idChk = false;
			if(param) {
				var idChk = false;		
				$.each(param, function(id, value) {
					if(value && !Array.isArray(value)) {
						if(id == csrfTokenId) {
							idChk = true;		
						}
					}
				});
			} else {
				var param = {};
				param[csrfTokenId] = csrfToken;
			}
			if(!idChk) {
				param[csrfTokenId] = csrfToken;
			}

			var nocaching;
			if(url.indexOf("?")>0)
				nocaching="&";
			else
				nocaching="?";
			var url =url+ nocaching+"nocachingParam="+TimsUtil.guid();
			var returnValue=null;
			var result=false;
			TimsUtil.ajax({
				url : url,
				dataType : 'json',
				data : jsonBody&&param ? TimsUtil.toJSON(param) : param,
				type : 'POST',
				cache : false,
				contentType : jsonBody ? 'application/json; charset=utf-8' : 'application/x-www-form-urlencoded; charset=utf-8',
				async : true,
				success : function(data, textStatus, jqXHR)
				{
					result = true;
					returnValue =data;
				},
				error : function(jqXHR, textStatus, errorThrown)
				{
					returnValue = null;
				},
				complete : function(  jqXHR,  textStatus ){
					if(callback){
						try{
							callback(returnValue, result,textStatus, jqXHR);
						}catch(e){

						}
					}
				},
				handleError: true
			});
			
			// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
			callTimeOffsetBetweenServerAndClient(url);
		},
		alertFoMessage : function(comment,width,height)
		{
			var url = '/front/commonMsg/alert';
			// iframe 모달창 open
			APP.MODAL.open({
				iframe : {
					url : url,
					param : {
						msg : '<center>'+comment+'</center>'
					}
				},
				width: $(window).width() > width ? width : $(window).width() * 0.9,
				height: $(window).height() > height ? height : $(window).height() * 0.9,
				header : false,
			})			
		},			
		confirmFoMessage : function(comment,width,height,methods)
		{
			var url = '/front/commonMsg/confirm';
			// iframe 모달창 open
			APP.MODAL.open({
				iframe : {
					url : url,
					param : {
						msg : '<center>'+comment+'</center>',
						method : methods
					}
				},
				width: $(window).width() > width ? width : $(window).width() * 0.9,
				height: $(window).height() > height ? height : $(window).height() * 0.9,
				header : false,
			})			
		},	
		uploadFile : function(url, param, callback)
		{
			if (!param)
				param = {};
			if (!callback)
				callback = window['onUploadComplete'];
			if (!url)
				url = "/nxlms/service/nxlms/file/upload";
			var sendButton = null;
			var html = '<form id="nxFileUploader" action="' + url + '" method="post" enctype="multipart/form-data">';
			var strParam = "";
			for ( var i in param)
			{
				strParam += i + '="' + param[i] + '" ';
			}
			html += '<input  style="display:none" type="file" ' + strParam + ' name="ufile" id="ufile" data-url="' + url + '"  /> <input style="display:none" type="submit" id="sbutton" value="Upload File to Server"></form>';
			html += '<div id="nxUploderProgress" class="progress" > <div class="bar"></div ><div class="percent">0%</div ></div>';
			buttons = [ {
				label : '전송',
				action : function(dialog)
				{
					sendButton = this;
					var frm = $(document.getElementById("nxFileUploader"));
					var objFiles = frm.find("input[type=file]");
					objFiles.click();

				}
			}, {
				label : '닫기',
				action : function(dialogItself)
				{
					dialogItself.close();
				}
			} ];
			var dlg = null;
			TimsUtil.dialog("upload file",html,400,20,buttons,{
				onshown : function(dialogRef)
				{
					dlg = dialogRef;
					var frm = $(document.getElementById("nxFileUploader"));
					var orgFile = "";
					var progressBar = $("#nxUploderProgress");
					var bar = progressBar.find('.bar');
					bar.css({
						'background-color' : '#B4F5B4',
						'width' : '0%',
						'height' : '20px',
						'border-radius' : '3px'
					});
					var percent = progressBar.find('.percent');
					percent.css({
						'position' : 'absolute',
						'display' : 'inline-block',
						'top' : '3px',
						'left' : '48%'
					});
					var status = progressBar.find('#status');
					progressBar.css({
						'position' : 'relative',
						'width' : '100%',
						'border' : '1px solid #ddd',
						'padding' : '1px',
						'border-radius' : '3px'
					});
					var bSuccess = false;
					frm.find("input[type=file]").change(function()
					{
						orgFile = $(this).val();
						if ($(this).val())
							frm.find("input[type=submit]").click();
					});
					frm.ajaxForm({
						beforeSend : function()
						{
							status.empty();
							var percentVal = '0%';
							bar.width(percentVal);
							percent.html(percentVal);
							progressBar.show();
							sendButton.disable();
							sendButton.spin();
							dlg.setClosable(false);
						},
						uploadProgress : function(event, position, total, percentComplete)
						{
							var percentVal = percentComplete + '%';
							bar.width(percentVal);
							percent.html(percentVal);
							// console.log(percentVal, position, total);
						},
						success : function()
						{
							var percentVal = '100%';
							bar.width(percentVal);
							percent.html(percentVal);
							bSuccess = true;
						},
						complete : function(xhr)
						{
							var fileName = "";
							if (bSuccess)
							{
								fileName = xhr.responseText.substring(1,xhr.responseText.length - 1);

							}
							else
							{

							}
							if (callback)
							{
								callback(orgFile,fileName,bSuccess);
							}
							dialogRef.close();
						}

					});
					dlg = dialogRef;

				}
			});

		},
		min:function(x1,x2)
		{
			return x1>x2?x2:x1;
		},
		max:function(x1,x2)
		{
			return x1<x2?x2:x1;
		},
		getRect:function(left,right,top,bottom){
			return {
				left:left
				,right:right
				,top:top
				,bottom:bottom
				,height:function(){
					return this.bottom-this.top;
				}
				,width:function(){
					return this.right-this.left;

				}
				,ptInRect:function(x,y){
					return x>=this.left && x<=this.right && y>=this.top && y<=this.bottom;
				}
				,interSection:function(rect){
					var section = TimsUtil.getRect();
					section.left=TimsUtil.max(this.left,rect.left);
					section.right=TimsUtil.min(this.right,rect.right);
					section.top=TimsUtil.max(this.top,rect.top);
					section.bottom=TimsUtil.min(this.bottom,rect.bottom);
					return section;
				}
				,union:function(rect){
					var section = TimsUtil.getRect();
					section.left=TimsUtil.min(this.left,rect.left);
					section.right=TimsUtil.max(this.right,rect.right);
					section.top=TimsUtil.min(this.top,rect.top);
					section.bottom=TimsUtil.max(this.bottom,rect.bottom);
					return section;
				}
				,normalize:function()
				{
					var left=this.left,right=this.right,top=this.top,bottom=this.bottom;
					if(!left)
						left=0;
					if(!right)
						right=0;
					if(!top)
						top=0;
					if(!bottom)
						bottom=0;
					this.top=TimsUtil.min(top,bottom);
					this.left=TimsUtil.min(left,right);
					this.bottom=TimsUtil.max(top,bottom);
					this.right=TimsUtil.max(left,right);
					return this;
				},
				moveRect:function(x,y){
					this.left-=x;
					this.right-=x;
					this.top-=y;
					this.bottom-=y;
					return this;
				}
			}.normalize();
		},
		getBoundRect:function(element,onlyView){
			var bounds = TimsUtil.getRect();
			var rect = element.offset();
			bounds.left=rect.left;
			bounds.top =rect.top;

    		bounds.right = rect.left + element.outerWidth();
    		bounds.bottom = rect.top + element.outerHeight();
    		var rect ;
    		if(onlyView)
    		{
    			var view = TimsUtil.getViewport();
    			return view.interSection(bounds);
    		}
    		return bounds;
		},
		getViewport:function()
		{
			var viewport = TimsUtil.getRect();
			var win = $(window);
    	    viewport.top = win.scrollTop();
	        viewport.left = win.scrollLeft();
    		viewport.right = viewport.left + win.width();
    		viewport.bottom = viewport.top + win.height();
    		return viewport;
		},
		isOnScreen:function(element){
			var viewport =TimsUtil.getViewport();
    		var bounds = TimsUtil.getBoundRect(element);
    		var ret = viewport.right < bounds.left || viewport.left > bounds.right|| viewport.bottom < bounds.top|| viewport.top > bounds.bottom;
			return  !ret;
		},
		checkForm : function(frm){
			var jFrm = TimsUtil.getFormObject(frm);
			if(!jFrm || !jFrm.length) {
				return true;
			}
			var ret = validator.checkForm(jFrm);
			if(ret && ret.field){
				var id = ret.field.attr("id") || ret.field.attr("name");
				var name = jFrm.find("label[for= " + id + "]").text() || id;
				alert("[" + name + "] " + ret.msg);
				ret.field.focus();
				return false;
			}
			var children = jFrm.find("input,select,textarea");
			for(var i=0;i<children.length;i++){
				var child = $(children[i]);
				if(child.val() && !child.inputmask("isComplete"))
				{
					var id = child.attr("id") || child.attr("name");
					var name = jFrm.find("label[for="+id+"]").text() ||id;
					alert("["+name+"] 형식이 맞지 않습니다." );
					child.focus();
					return false;
				}
			}
			
			return true;
		},
		checkFormEn : function(frm){
			var jFrm = TimsUtil.getFormObject(frm);
			if (!jFrm || !jFrm.length)
				return true;
			var ret = validatorEn.checkForm(jFrm);
			if(ret&&ret.field){
				var id = ret.field.attr("id") || ret.field.attr("name");
				var name = jFrm.find("label[for="+id+"]").text() ||id;
				alert("["+name+"] " + ret.msg);
				ret.field.focus();
				return false;
			}
			var children = jFrm.find("input,select,textarea");
			for(var i=0;i<children.length;i++){
				var child = $(children[i]);
				if(child.val() && !child.inputmask("isComplete"))
				{
					var id = child.attr("id") || child.attr("name");
					var name = jFrm.find("label[for="+id+"]").text() ||id;
					alert("["+name+"] 형식이 맞지 않습니다." );
					child.focus();
					return false;
				}
			}
			return true;


		},
		removeCookie:function(name){
			return $.removeCookie(name);
		},
		setCookie:function(name,value,day){
			if(day == undefined)
				day = 1;
			TimsUtil.removeCookie(name);
			$.cookie(name,value,{expires:day,path: '/' });
		},
		getCookie:function(name){
			return $.cookie(name);
		},
		showNoticeLayerPopup : function(param, popCookieYn){
			var dataObj = {};
			$.extend(dataObj, param);
			var popupHtml = "";
			popupHtml += "<div name='bnZone' data-target='"+dataObj.target+"' class='modal_popup' style='padding:0 !important;width:"+dataObj.width+"px; height:"+dataObj.height+"px; left:"+dataObj.left+"px; top:"+dataObj.top+"px; position:absolute; z-index:9999;'>";
			popupHtml += 	"<div id='tempDiv' class='popup_body "+dataObj.templateType+"'>";
			popupHtml += 		"<div class='popup_header group'><span class=\"log_img\"></span><a href='#none' class='popup_close_btn' onclick=\"javascript:TimsUtil.closeNoticeLayerPopup('"+dataObj.target+"', false)\"></a></div>";
			popupHtml += 		"<div class='popup_content group' style='line-height:1.5;'> <p class='txt' style='padding-top:10px !important' id='bnZoneContents'></p></div>";
			popupHtml += 		"<div class='popup_footer group'>";
			popupHtml += 			"<p class='f_L'  name='btnPTag' data-target='"+dataObj.target+"' ><input type='checkbox' name='checkbox_noview' data-target='"+dataObj.target+"' class='checkNoView' value='Y'/>&nbsp;&nbsp;<label for='checkbox_noview' style='color:#ffffff;cursor:pointer;' >오늘하루 열지않음</label><p>";
			popupHtml += 		"</div>";
			popupHtml += 	"</div>";
			popupHtml += "</div>";
			
			$('body').append(popupHtml);
			$('div[name=bnZone][data-target='+dataObj.target+']').find("p#bnZoneContents").html(dataObj.contents);
			
			if(dataObj.templateType == 'style3'){
				$('div[name=bnZone][data-target='+dataObj.target+'] .popup_body').css('background-image', 'url('+''+')');
			}
			
			$('div[name=bnZone][data-target='+dataObj.target+']').draggable();
			
			$('p[name=btnPTag][data-target='+dataObj.target+']').click(function(e){
				if(popCookieYn){
					TimsUtil.closeNoticeLayerPopup($(this).attr('data-target'), true); 
				}else{
					$('div[name=bnZone][data-target='+dataObj.target+']').remove();
				}
			});
			
		},
		showNoticeLayerPopupFo : function(param){
			var dataObj = {};
			$.extend(dataObj, param);
			var popupHtml = "";
				if(dataObj.isMobile == 'M'){
					popupHtml += "<div name='mainNotice' class='main-notice' data-target='" + dataObj.target + "' style='position: absolute; z-index: 9999;'>";
				}else{
					popupHtml += "<div name='mainNotice' class='main-notice' data-target='" + dataObj.target + "' style='width: " + dataObj.width + "px; height: " + dataObj.height + "px; left: " + dataObj.left + "px; top: " + dataObj.top + "px; position: absolute; z-index: 9999;'>";
				}
				popupHtml += "<h2 class='data-title'>" + dataObj.title + "</h2>";
				popupHtml += "<div class='cont'>" + dataObj.contents + "</div>";
				popupHtml += "<div class='close'>";
				popupHtml += "<p><input type='checkbox' value='N' id='" + dataObj.target + "' onchange=\"checkboxChanged('" + dataObj.target + "')\"> <label for='todayClose'>오늘 더 이상 보지 않음</label></p>";
				popupHtml += "<button class='btn-close' name='closePopup' data-target='" + dataObj.target + "'><span class='hidden'>공지팝업 닫기</span></button>";
				popupHtml += "</div>";
				popupHtml += "</div>";
			$('body').append(popupHtml);
			
			//내용 부분 height 조절
			var maxHeight = Number(dataObj.height) - 170;
			if(dataObj.isMobile != 'M' && Number(dataObj.height) <= 200){
				$('.cont').css('height', '30px');
			}else if(dataObj.isMobile != 'M'){
				$('.cont').css('height', maxHeight+'px');
			}
			
			$('button[name=closePopup][data-target='+dataObj.target+']').click(function(e){
				if($('#'+dataObj.target).val() == 'Y'){
					TimsUtil.closeNoticeLayerPopupFo(dataObj.target, true); 
				}else{
					TimsUtil.closeNoticeLayerPopupFo(dataObj.target, false); 
				}
			});
			
		},
		showOpenStylePopup : function(param){ //수강생관리 - 신청사유 확인 팝업
			var dataObj = {};
			$.extend(dataObj, param);
			var popupHtml = "";
			popupHtml += "<div name='bnZone' data-target='"+dataObj.target+"' data-saveName='"+dataObj.saveName+"' class='modal_popup' style='padding:0 !important;width:"+dataObj.width+"px; height:"+dataObj.height+"px; left:"+dataObj.left+"px; top:"+dataObj.top+"px; position:absolute; z-index:9999;'>";
			popupHtml += 	"<div id='tempDiv' class='popup_body "+dataObj.templateType+"'>";
			popupHtml += 		"<div class='popup_header group'><span style='font-size : 12pt;'>"+dataObj.title+"</p><a href='#none' class='popup_close_btn' onclick=\"javascript:closeOpenPopup('"+dataObj.row+"', '"+dataObj.saveName+"')\"></a></div>";
			popupHtml += 		"<div class='popup_content group' style='line-height:1.5;'> <p class='txt' style='padding-top:10px !important' id='bnZoneContents'></p></div>";
			popupHtml += 		"</div>";
			popupHtml += 	"</div>";
			popupHtml += "</div>";

			$('body').append(popupHtml);
			$('div[name=bnZone][data-target='+dataObj.target+']').find("p#bnZoneContents").html(dataObj.contents);
			
			$('div[name=bnZone][data-target='+dataObj.target+']').draggable();
		},
		showNoticeLayerPopupEn : function(param){
			var dataObj = {};
			$.extend(dataObj, param);
			var popupHtml = "";
			popupHtml += "<div name='bnZone' data-target='"+dataObj.target+"' class='modal_popup' style='padding:0 !important;width:"+dataObj.width+"px; height:"+dataObj.height+"px; left:"+dataObj.left+"px; top:"+dataObj.top+"px; position:absolute; z-index:9999;'>";
			popupHtml += 	"<div id='tempDiv' class='popup_body "+dataObj.templateType+"'>";
			popupHtml += 		"<div class='popup_header group'><span class=\"log_img\"></span><a href='#none' class='popup_close_btn' onclick=\"javascript:TimsUtil.closeNoticeLayerPopup('"+dataObj.target+"', false)\"></a></div>";
//			popupHtml += 		"<div class='popup_content group'> <p class='title'>"+dataObj.title+"</p><p class='txt' style='padding-top:10px !important' id='bnZoneContents'></p></div>";
			popupHtml += 		"<div class='popup_content group' style='line-height:1.5;'> <p class='txt' style='padding-top:10px !important' id='bnZoneContents'></p></div>";
			popupHtml += 		"<div class='popup_footer group'>";
			popupHtml += 			"<p class='f_L'  name='btnPTag' data-target='"+dataObj.target+"' ><input type='checkbox' name='checkbox_noview' class='checkNoView' value='Y'/>&nbsp;&nbsp;<label for='checkbox_noview' style='color:#ffffff;cursor:pointer;' >Not open today</label><p>";
			popupHtml += 		"</div>";
			popupHtml += 	"</div>";
			popupHtml += "</div>";
			
			$('body').append(popupHtml);
			$('div[name=bnZone][data-target='+dataObj.target+']').find("p#bnZoneContents").html(dataObj.contents);
			
			if(dataObj.templateType == 'style3' && dataObj.fileCode != ''){
//				$('div[name=bnZone][data-target='+dataObj.target+'] .popup_body').css('background-image', 'url('+svnmFileUpload+'/thumbnailConv?fileCode='+dataObj.fileCode+')');
				$('div[name=bnZone][data-target='+dataObj.target+'] .popup_body').css('background-image', 'url('+contextPath+'/edata'+dataObj.filePath+')');
			}
			
			$('div[name=bnZone][data-target='+dataObj.target+']').draggable();
			
			$('p[name=btnPTag][data-target='+dataObj.target+']').click(function(e){
				TimsUtil.closeNoticeLayerPopup($(this).attr('data-target'), true); 
			});
		},
		closeNoticeLayerPopup : function(target, cookieFlag){
			if(cookieFlag) TimsUtil.setCookie(target, 'Y', 1);
			$('div[name=bnZone][data-target='+target+']').remove();
		},
		closeNoticeLayerPopupFo : function(target, cookieFlag){
			if(cookieFlag) TimsUtil.setCookie(target, 'Y', 1);
			$('div[name=mainNotice][data-target='+target+']').remove();
		},
		showPopupVideo : function(url,title){
			title = title || url;
			if(!$("#divPopupVideo").length){
				var html = ''
				+'<div id="divPopupVideo" class="modal fade " tabindex="-1" role="dialog" aria-hidden="true">'
				+'  <div class="modal-dialog modal-lg">'
				+'    <div class="modal-content">'
				+'      <div class="modal-header">'
				+'      	<button type="button" class="close" data-dismiss="modal">&times;</button>'
				+'			<h4 id="vodModalTile" class="modal-title">'+title+'</h4>'
				+'      </div>'
				+'      <div class="modal-body" id="popupVideoWrapper">'
				+'                    <div class="embed-responsive embed-responsive-16by9">'

				+'			<video  id="popupVideoPlayer" controlsList="nodownload" class="embed-responsive-item"  controls preload="none" '
     			+'				data-setup=\'{}\''
//      			+' 			poster="http://d2zihajmogu5jn.cloudfront.net/elephantsdream/poster.png"'
    	  		+'>'
  				+'			</video>'
  				+'</div>'
				+'      </div>'
				+'    </div>'
				+'  </div>'
				+'</div>';
				$(document.body).append(html);

				$('#divPopupVideo').on('hidden.bs.modal', function (e) {
					$("#popupVideoPlayer")[0].pause();
					$("#popupVideoPlayer").attr("src","/blank.jsp");
				});
			}
			$("#vodModalTile").html(title);
			$("#popupVideoPlayer").attr("src",url);
			$("#popupVideoPlayer")[0].play();
			$("#divPopupVideo").modal("show");
			$("#popupVideoPlayer").contextmenu(function() {
				return false;
			});
		},
		showPopupVimeo : function(vimeoCd, appId){
			var defaultUrl = 'https://player.vimeo.com/video/';
			var urlParam = '?h=cb2329b4b3&amp;title=0&amp;byline=0&amp;portrait=0&amp;speed=0&amp;badge=0&amp;autopause=0&amp;player_id=0&amp;app_id=' + appId;
			
			var url = defaultUrl + vimeoCd + urlParam;
			window.open(url, "_blank", "width=800, height=500");
		},
		showPopupVodPath : function(vodPath, appId){
			var urlParam = '?h=cb2329b4b3&amp;title=0&amp;byline=0&amp;portrait=0&amp;speed=0&amp;badge=0&amp;autopause=0&amp;player_id=0&amp;app_id=' + appId;
			
			var url = vodPath + urlParam;
			window.open(url, "_blank", "width=800, height=500");
		},
		showPopupThum : function(path, width, height){
			var thumPath = path;
			if(thumPath.length == 0){
				return false;
			}
			var win = window.open('', "about:blank", "width="+width+", height="+height);
			win.document.write('<html><body><img src="'+thumPath+'"></body></html>');
			return true;
		},
		showPopupWindow : function(target, programId, width, height){
			//만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
			var left = (window.screen.width / 2) - (width / 2);
			//만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
			var top= (window.screen.height / 3) - (height / 2);
			var pop = window.open(serviceBathPath+"/"+programId+"/popup/"+target,"addrPop", "width="+width+", height="+height+", left="+left+", top="+top+", scrollbars=yes, resizable=yes");
		},
		setPopupData : function(data, target){
			$('input[data-popup="'+target+'"]').each(function(idx, obj) {
				var formSlt = $(this).parents('form');
				var formId = formSlt.attr('id');
				var bindSheetName = formSlt.attr('data-sheet');
				var eleId = $(this).attr('id');
				var addrDiv = $(this).attr('data-sch-rel');
				if(addrDiv != ''){
					$(this).val(data[addrDiv]);
					if(formId != '' && bindSheetName != ''){
						var sheetObj = window[bindSheetName];
						if(sheetObj){
							var selRow = sheetObj.GetSelectRow();
							sheetObj.SetCellValue(selRow, eleId, data[addrDiv]);
						}
					}
				}
			});
		},
		disableForm : function(frm,disabled){
			var jFrm = TimsUtil.getFormObject(frm);
			if (!jFrm || !jFrm.length)
				return null;
			if(disabled == undefined)
				disabled = true;
			jFrm.find('*').prop("disabled", disabled);
		},
		disabledForm : function(frm){
			this.disableForm(frm,true);
		},
		enabledForm : function(frm){
			this.disableForm(frm,false);
		},
		formReset : function(formId){
			$('form[id="'+formId+'"]')[0].reset();
		},
		pasteExcel :function(e){
			var clipText = TimsUtil.getClipboardData(e);
			var list = new Array();
			if(clipText != "" && clipText != null){
				clipRows = clipText.split(String.fromCharCode(13));
				for (i=0; i<clipRows.length; i++) {
					var str = clipRows[i];
					if(!str || !str.length)
						return;
					list.push(str.split(String.fromCharCode(9)));
				}
			}
		    return list;
		},
		openCaptcha:function(gwajungCode,chasooCode,sGubun){
			/*
			 if("01".equals(sGubun)) name = "진도";
				else if("02".equals(sGubun))name = "과제";
				else if("03".equals(sGubun))name = "진행단계평가";
				else if("04".equals(sGubun))name = "최종평가";
				else name = "기타";
				
				성공시 onSuccessCaptcha()호출
			 * */
			if(!sGubun)
				sGubun = "02";
			var url = "/front/mauth/captcha?sGwajungCode="+gwajungCode+"&sChasooCode="+chasooCode+"&sGubun="+sGubun;
			TimsUtil.openLayerUrlPopup("본인인증",url,800,600);
		},
		sheetDownExcel:function(option){
			var param = {
				SheetName:"mySheet",
				URL:"${svnm}/downToExcel",
				ExtendParam:$("form#search").serialize(),
				FileName:"Excel.xls",
				Multipart:0,
				AllTypeToText:1,
				AutoSizeColumn:1,
				Merge:1,
				SheetDesign:1
			};
			
			$.extend(param, option);
			
			//시트에 조회된 데이터는 전달되지 않고, 헤더타이틀이나 컬럼의 포멧 만 서버로 전달됨.
			window[param.SheetName].DirectDown2Excel(param);
		},
		byteCheck:function(elSlt){
			var codeByte = 0;
			for (var idx = 0; idx < elSlt.val().length; idx++) {
				var oneChar = escape(elSlt.val().charAt(idx));
				if ( oneChar.length == 1 ) {
					codeByte ++;
				} else if (oneChar.indexOf("%u") != -1) {
					codeByte += 3;
				} else if (oneChar.indexOf("%") != -1) {
					codeByte ++;
				}
			}
			return codeByte;
		},
		maxLengthCheck:function(elSlt, title, maxLength){
			var obj = elSlt;
			if(maxLength == null) {
			    maxLength = obj.attr("data-max-byte") != null ? obj.attr("data-max-byte") : 1000;
			}
			if(Number(TimsUtil.byteCheck(obj)) > Number(maxLength)) {
				alert(title + "이(가) 입력가능문자수를 초과하였습니다.\n(영문, 숫자, 일반 특수문자 : " + maxLength + " / 한글, 한자, 기타 특수문자 : " + parseInt(maxLength/3, 10) + ").");
				elSlt.val(TimsUtil.cutByteString(elSlt, maxLength));
				obj.focus();
				return false;
			}
			else {
				return true;
			}
		},
		cutByteString:function(elSlt, len){
			var str = elSlt.val();
			var count = 0;
			 
			for(var i = 0; i < str.length; i++) {
				if(escape(str.charAt(i)).length >= 4)
					count += 3;
				else
					if(escape(str.charAt(i)) != "%0D")
						count++;

				if(count >  len) {
					if(escape(str.charAt(i)) == "%0A")
						i--;
					break;
				}
			}
			return str.substring(0, i);
		},
		decodeHtmlText : function(value){
			if(!value)
				return value;
			//return value.replaceAll(value,"&lt;","<").replaceAll(value,"&gt;",">").replaceAll("&amp;", "&").replaceAll("&nbsp;", " ");
			return value.replaceAll("&lt;","<").replaceAll("&gt;",">").replaceAll("&amp;", "&").replaceAll("&nbsp;", " ");
		},
		getXMLText : function(value){
			if(value) {
				return value.replaceAll("&","&amp;").replaceAll("<", "&lt;").replaceAll(">","&gt;").replaceAll(" ", "&nbsp;");
			} else {
				return value;
			}
		},
		popup : function(url,name,width,height,scrollBars){
			if(url.indexOf("?")>0)
				nocaching="&";
			else
				nocaching="?";
			var url =url+ nocaching+"nocachingParam="+TimsUtil.guid();
			
			if(!name)
				name = "_blank";
			
			var left = window.screenLeft != undefined ? window.screenLeft : screen.left;
			 var top = 1;//window.screenTop != undefined ? window.screenTop : screen.top;
			 
			 
			var option = "left="+left+",top="+top+",fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars="+(scrollBars?scrollBars:"auto")+",resizable=no";
			if(width)
				option+=",width="+width;
			if(height)
				option+=",height="+height;
			var win= window.open(url,name,option);
			if(!win){
				alert("팝업설정을 확인해 주세요");
			}
			else
				win.focus();
			return win;
		},
		openTabWindow : function(url,name){
			
			var win = null;
			if(name){
				win = window.open(url,name);
			}
			else{
				win = window.open(url);
			}
			
			if(win)
				win.focus();
			return win;
		},
		openFullScreen : function(url,name){
			return this.openTabWindow(url,name);
//			
//			 var left = window.screenLeft != undefined ? window.screenLeft : screen.left;
//			 var top = window.screenTop != undefined ? window.screenTop : screen.top;
//			 var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
//			 var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;
//
//			var win = window.open(url, name, 'fullscreen=no,scrollbars=yes, width=' + width + ', height=' + width + ', top=' + top + ', left=' + left);
//			if(win)
//				win.focus();
//			return win;
		},
		encodeURI : function(url){
			if(!url) return "";
			
			var idxUrl = url.indexOf('?');
			var rootUrl = url.substring(0, idxUrl);
			var newUrl = "";
			if(idxUrl > 0){
				var paramStr = url.substring(idxUrl+1,url.length);
				var paramArr = paramStr.split('&');
				var newParam = [];
				$.each(paramArr, function(idx,obj){
					var objArr = obj.split('=');
					var key = objArr[0];
					var val = objArr[1];
					if(val != '' || val != 'undefined' || val != null){
						val = encodeURI(val);
					}
					newParam.push(key+'='+val);
				});
				if(newParam.length > 0){
					var newParamStr = newParam.join('&');
					newUrl = rootUrl+'?'+newParamStr;
				}
				else {
					newUrl = url;
				}
			}
			return newUrl;
		},
		popDownResson : function(mstHistoryCd) { // 엑셀다운로드 시 개인사유이력 관리가 필요한 경우 공통으로 호출
			var url = serviceBathPath + "/mem1100e/popup/downReason/";
			if(typeof mstHistoryCd === "undefined" ){
				url += 'NEW';
			} else {
				url += mstHistoryCd;
			}
			return TimsUtil.urlDialog("다운로드 사유", url, 800, 350);
		},
		isAndroid : function() {
			return /Android/i.test(navigator.userAgent);
		}, 
		canPlayHls : function() {
			var iDevices = [
				'iPad Simulator',
				'iPhone Simulator',
				'iPod Simulator',
				'iPad',
				'iPhone',
				'iPod'
			];

			if(!!navigator.platform) {
				while(iDevices.length) {
					if(navigator.platform === iDevices.pop()){ 
						return true; 
					}
				}
			}

			return false;
		},
		cmmExcelDwldRsn : function(obj, sysMenuCd) {
			var sheet = obj.attr("data-sheet");
			var fnc = obj.attr("data-fnc");
			if(window[sheet].RowCount() == 0) {
				alert('조회된 데이터가 없습니다.');
				return;
			}
			
			// 개인정보조회설정에 엑셀다운로드 체크되어 있는지 확인
			var param = {};
			param.sysMenuCd = sysMenuCd;
			var ret = TimsUtil.getObject(serviceBathPath + '/excelDwldRsn/prsnSetn', param, false);
			if(ret != null) {
				if(ret.prsnInfoSetn != null) {
					if(ret && ret.prsnInfoSetn.indexOf("E") > -1) {
						if(ret.prsnInfoSetn.indexOf("E") > -1) {
							var url = serviceBathPath + '/excelDwldRsn?fnNm=' + fnc;
							TimsUtil.urlDialog('엑셀파일 다운로드 사유입력', url, 680, 240);
						}
					} else {
						(new Function(fnc + '();'))();
					}
				} else {
					(new Function(fnc + '();'))();
				}
			} else {
				(new Function(fnc + '();'))();
			}
		},
		openPdfViewer : function(pdfViewPath){
			var options = "toolbar=no,scrollbars=no,resizable=yes,status=no,menubar=no,width=1200, height=800, top=0,left=0";
			var win = window.open('', '_blank', options);
			win.document.write("<html><body style='margin:0;padding:0;'><iframe width='100%' height='100%' style='border:0; padding:0;' src='" + pdfViewPath + "'></iframe></body></html>");
			return win;
		}
	};
	String.prototype.padLeft=function(len,padVal){
		if(padVal == undefined)
			padVal = ' ';
		if(this.length>=len)
			return this;
		var val = this;
		while(val.length<len)
		{
			val = padVal + val;
		}
		return val;
	};
	String.prototype.trim=function(){
		return TimsUtil.trim(this);
	};
	String.prototype.getByteLength=function()
	{
		return TimsUtil.getByteLength(this);
	};
	String.prototype.hasUnicode=function()
	{
		return this.length != this.getByteLength();
	};
	String.prototype.isNumber=function(){
		return TimsUtil.checkNumber(this);
	};
	String.prototype.replaceAll = function(target,value){
		return TimsUtil.replaceString(this,target,value);
	};

	$.fn.getBounds=function(onlyView){
		return TimsUtil.getBoundRect(this,onlyView);
	};
	$.fn.isOnScreen=function(){
		return TimsUtil.isOnScreen(this);
	};

	$.fn.getJson=function(selectedRow){
		if(this.length==0)
			return;
		if(this[0].tagName != "TABLE")
		{
			var parents = $(this).parents("table");
			if(!parents.length)
				return null;
			var tbl = parents[0];
			return $(tbl).getJson(this);

		}
		var list = [];
		var trList = $(this).find("tr");
		var header = null;
		var bFind = false;
		var arrCols = [];
		for(var i=0;i<trList.length;i++){

			var tr = $(trList[i]);
			if(!bFind && tr.data("type")!="data-header")
				continue;
			var tdList = tr.children();
//			if(tdList.length==0)
//				tdList = tr.find("th");
			if(tr.data("type") == "data-header")
			{
				bFind = true;
				tdList.each(function(){
					arrCols.push($(this).data("colId"));
				});
				continue;
			}
			if(!bFind)
				continue;
			var obj={};
			for(var j=0;j<arrCols.length;j++){
				if(!arrCols[j])
					continue;


				obj[arrCols[j]]=$(tdList[j]).text().trim();

			}
			list.push(obj);
			if(selectedRow&&selectedRow.length && selectedRow[0] == trList[i])
				return obj;
		}
		if(selectedRow)
			return null;
		return list;
	}
}


/*from base 1.0*/
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
var serializeArray = $.fn.serializeArray;
$.fn.serializeArray = function(){
    var inputs = this.find(':disabled');
    inputs.prop('disabled', false);
    var serialized = serializeArray.apply( this, arguments );
    inputs.prop('disabled', true);
    return serialized;
};



$(document).ajaxError( function( e, xhr, settings, error){
// 안내페이지를 사용하기 위해서... handleAjaxError 호출을 막음 	
//	if(settings &&!settings.skipError && (xhr.status || error)!=532) {
//		handleAjaxError(xhr, settings, error);		
//	}
});

function handleErrorCode(errorCode, xhr, settings, error){
	switch (errorCode) {
		case 530 :
//			alert("권한이 충분하지 않습니다.");
			if(opener) {
				self.close();
			}
			if(parent.$('#timsdlg').length > 0) {
				parent.TimsUtil.closeDialog();
				parent.location.href = serviceBathPath + "/error/" + errorCode;
			}
			location.href = serviceBathPath + "/error/" + errorCode;
			return;
		case 531 :
			alert("권한이 없거나 장시간 사용하지 않아 세션이 만료 되었습니다.\n다시 로그인 하시기 바랍니다.");
			location.href = serviceBathPath + "/logout";
//			if(opener) {
//				self.close();
//			}
//			if(parent.$('#timsdlg').length > 0) {
//				parent.TimsUtil.closeDialog();
//				parent.location.href = serviceBathPath + "/error/" + errorCode;
//			}
//			location.href = serviceBathPath + "/error/" + errorCode;
			return;
		case 532 :
//			alert("권한이 충분하지 않습니다.");
			if(opener) {
				self.close();
			}
			if(parent.$('#timsdlg').length > 0) {
				parent.TimsUtil.closeDialog();
				parent.location.href = serviceBathPath + "/error/" + errorCode;
			}
			location.href = serviceBathPath + "/error/" + errorCode;
			return;
		case 533 :
//			alert("등록되지 않은 아이피입니다.");
			if(opener) {
				self.close();
			}
			if(parent.$('#timsdlg').length > 0) {
				parent.TimsUtil.closeDialog();
				parent.location.href = serviceBathPath + "/error/" + errorCode;
			}
			location.href = serviceBathPath + "/error/" + errorCode;
			return;	
		case 534 :
//			alert("TIMS-LMS에서 알려드립니다.\n다른 브라우저에서 로그인 했습니다.\n다른 사용자가 사용하던 페이지를 재로그인시 발생할 수 있습니다.");
			if(opener) {
				self.close();
			}
			if(parent.$('#timsdlg').length > 0) {
				parent.TimsUtil.closeDialog();
				parent.location.href = serviceBathPath + "/error/" + errorCode;
			}
			location.href = serviceBathPath + "/error/" + errorCode;
			return;	
		case 535 :
//			alert("현재 화면에서 URL/파라미터 위변조현상이 감지되어 서비스 이용이 제한되었습니다.");
			if(opener) {
				self.close();
			}
			if(parent.$('#timsdlg').length > 0) {
				parent.TimsUtil.closeDialog();
				parent.location.href = serviceBathPath + "/error/" + errorCode;
			}
			location.href = serviceBathPath + "/error/" + errorCode;
			return;	
		case 536 :
//			alert("등록되지 않은 사용자입니다.");
			if(opener) {
				self.close();
			}
			if(parent.$('#timsdlg').length > 0) {
				parent.TimsUtil.closeDialog();
				parent.location.href = serviceBathPath + "/error/" + errorCode;
			}
			location.href = serviceBathPath + "/error/" + errorCode;
			return;	
		default :
			if(!settings || !settings.handleError) {
				return;
			}
			if(xhr && xhr.responseJSON && xhr.responseJSON.error && xhr.responseJSON.message) {
				var data = xhr.responseJSON;
				alert(data.message);
			} else {
//				alert("작업에 실패했습니다.");
			}
	}
}

function handleAjaxError(xhr, settings, error, errorCode) {
	var _top = window.front || top;
	if((!error) && xhr.statusText != "error") {
		return;
	}
	handleErrorCode(xhr.status || errorCode, xhr, settings, error);
}

//ibsheet 관련함수
function hfn_sheet2form(sheetId, form) {
	var param = {};
	param.sheet = sheetId;
	param.form = form;
	param.formPreFix = "";
	
	IBS_CopySheet2Form(param);
}

function layerClose(name){
	if(window.onLayerClose){
		if(!window.onLayerClose())
			return false;
	}
	
	$(".layerArea").each(function(index){
		var e = $(this);
		
		e.hide().attr("style","");
		$("#layerBg").attr("style","");
		
		lyNums --;
		if(lyNums == 0 || $("body").hasClass("lyOn")){
			$("html").css("overflow-y","auto");
			$("body").removeClass("lyOn");
			$(this).removeClass(name);
			$("#layerBg").remove();
		}
	});
	$("#layerPopupIF").attr("src","/blank.jsp")
	$("#layerPopupContent").html("");
}

//영문,숫자,공백,특수문자,한글 혼합체크
//공백과 한글은 입력되면 안됨
//ID 체크
function checkID(str){
	if ( str == null ) return false ;
	
	var chk_num = str.search(/[0-9]/g);
	var chk_eng = str.search(/[a-z]/ig);
	var specStr = str.search(/[~!@\#$%^&*\()\=+_']/gi);
	var trimStr = str.search(/[\s]/g); // 공백체크
	var hangulStr = str.search( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g ); // 한글체크
	
	if(chk_eng < 0 || specStr >= 0 || trimStr >= 0 || hangulStr >= 0)
	{
		return false;
	}
	
	return true;
}

//영문,숫자,공백,특수문자,한글 혼합체크
//공백과 한글,특수문자은 입력되면 안됨
function checkEngNum(str){
	if ( str == null ) return false ;
	
	var chk_num = str.search(/[0-9]/g);
	var chk_eng = str.search(/[a-z]/ig);
	var specStr = str.search(/[~!@\#$%^&*\()\=+_']/gi);
//	var trimStr = str.search(/[\s]/g); // 공백체크
	var hangulStr = str.search( /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g ); // 한글체크
	
//	if(chk_num < 0 || chk_eng < 0 || specStr >= 0 || trimStr >= 0 || hangulStr >= 0)
//	if((specStr >= 0 || trimStr >= 0 || hangulStr >= 0) || (chk_num >= 0 && chk_eng < 0)) {
	if((specStr >= 0 || hangulStr >= 0) || (chk_num >= 0 && chk_eng < 0)) {
		return false;
	}
	
	return true;
}

//숫자만
function checkNumOnly(str){
	var flag=true;
	if (str.length > 0){
		for (var i = 0; i < str.length; i++){
			if (str.charAt(i) < '0' || str.charAt(i) > '9')
				flag=false;
		}
	}
	return flag;
}

function isPasswordCk(pwd){
	var regexp0 = /[0-9a-zA-Z\\/?,.<>;:'"\[\]{}|+=_`~!@#$%^&*()-]{9,20}$/;
	var regexp1 = /[\\/?,.<>;:'"\[\]{}|+=_`~!@#$%^&*()-]/;
	var regexp2 = /[0-9]/;
	var regexp3 = /[a-zA-Z]/;
	var regexp5 = /(\w)\1\1\1/; //같은문자 3번까지
	
	if(!regexp0.test(pwd)){
		alert('비밀번호는 영문, 숫자, 특수문자를 조합하여 9~20자리를 사용해야합니다.'); 
		return false;
	}
	
	var msg = "";
	if(!regexp1.test(pwd)){
		msg += "[특수문자]" ;
	}
	if(!regexp2.test(pwd)){
		msg += "[숫자]" ;
	}
	if(!regexp3.test(pwd)){
		msg += "[영문]" ;
	}
	if(msg.length > 0){
		alert("비밀번호에  "+ msg + "를 포함하여야 합니다.");
		return false;
	}
	if(regexp5.test(pwd)){
		alert('비밀번호에 같은 문자를 4번 이상 사용하실 수 없습니다.'); 
		return false;
	}
	
	return true;
}

function sheetMaskUsrNm(sheet, Row, Col, release) {
	var colLen = window[sheet].GetCellValue(Row, Col).length;
	var _Format = "";
	if(colLen < 3) {
		if(release) {
			_Format += "##";
		} else {
			_Format += "#*";
		}
	} else {
		for(var i = 0; i < colLen; i++) {
			if(release) {
				_Format += "#";	
			} else {
				if(i == 0 || i + 1 == colLen) {
					_Format += "#";
				} else {
					_Format += "*";
				}
			}
		}
	}
	var iType = window[sheet].GetCellProperty(Row, Col, "Type");
	var info = {Type:iType,Format:_Format};
	window[sheet].InitCellProperty(Row, Col, info);
}

function sheetMaskHp(sheet, Row, Col, release) {
	var _Format = "###-****-####";
	if(release) {
		_Format = "###-####-####";
	}
	var iType = window[sheet].GetCellProperty(Row, Col, "Type");
	var info = {Type:iType,Format:_Format};
	window[sheet].InitCellProperty(Row, Col, info);
}

function sheetMaskBrthDt(sheet, Row, Col, release) {
	var _Format = "######**";
	if(release) {
		_Format = "###########";
	}
	var iType = window[sheet].GetCellProperty(Row, Col, "Type");
	var info = {Type:iType,Format:_Format};
	window[sheet].InitCellProperty(Row, Col, info);
}

function sheetMaskIp(sheet, Row, Col, release) {
	var colValue = window[sheet].GetCellText(Row, Col).split('.');
	var _Format1 = "";
	for(var i = 0; i < colValue[0].length; i++) {
		_Format1 += "#";
	}
	var _Format2 = "";
	colValue[1] = '.' + colValue[1];
	for(var i = 0; i < colValue[1].length; i++) {
		_Format2 += "#";
	}
	var _Format3 = "";
	colValue[2] = '.' + colValue[2];
	for(var i = 0; i < colValue[2].length; i++) {
		if(release) {
			_Format3 += "#";
		} else {
			_Format3 += i == 0 ? "#" : "*";
		}
	}
	var _Format4 = "";
	colValue[3] = '.' + colValue[3];
	for(var i = 0; i < colValue[3].length; i++) {
		_Format4 += "#";
	}
	var _Format = _Format1 + _Format2 + _Format3 + _Format4;
	var iType = window[sheet].GetCellProperty(Row, Col, "Type");
	var info = {Type:iType,Format:_Format};
	window[sheet].InitCellProperty(Row, Col, info);
}

function sheetMaskUpdInfo(sheet, Row, Col, release) {
	var colValue = window[sheet].GetCellText(Row, Col).split('(2');
	if(colValue[0] == '') {
		return;
	}
	var colLen = colValue[0].length;
	var _Format1 = "";
	for(var i = 0; i < colLen; i++) {
		if(release) {
			_Format1 += "#";	
		} else {
			if(i == 0 || i + 1 == colLen) {
				_Format1 +=  "#";
			} else {
				_Format1 +=  "*";
			}
		}
	}
	_Format1 += "#";
	var _Format2 = "";
	colValue[1] = '(2' + colValue[1];
	for(var i = 0; i < colValue[1].length; i++) {
		_Format2 += "#";
	}
	var _Format = _Format1 + _Format2;
	var iType = window[sheet].GetCellProperty(Row, Col, "Type");
	var info = {Type:iType,Format:_Format};
	window[sheet].InitCellProperty(Row, Col, info);
}

function sheetMaskTtrUsrNm(sheet, Row, Col, release) {
	var colValue = window[sheet].GetCellText(Row, Col);
	var arrCol = window[sheet].GetCellText(Row, Col).split('(');
	if(arrCol[0] == '') {
		return;
	}
	var colLen = arrCol[0].length;
	var _Format1 = "";
	for(var i = 0; i < colLen; i++) {
		if(release) {
			_Format1 += "#";	
		} else {
			if(i ==0 || i + 1 == colLen) {
				_Format1 += "#";
			} else {
				_Format1 += "*";
			}
		}
	}
	var colValue2 = colValue.substring(colLen + 1, colValue.length);
	colValue2 = '(' + colValue2;
	var _Format2 = "";
	for(var i = 0; i < colValue2.length; i++) {
		_Format2 += "#";
	}
	var _Format = _Format1 + _Format2;
	var iType = window[sheet].GetCellProperty(Row, Col, "Type");
	var info = {Type:iType,Format:_Format};
	window[sheet].InitCellProperty(Row, Col, info);
}

function formMaskUsrNm(data) {
	var colLen = data.length;
	if(colLen == 2) {
		return data.substring(0, 1) + '*';
	} else {
		var midNm = '';
		for(var i = 1; i < colLen - 1; i++) {
			midNm += '*';
		}
		return data.substring(0, 1) + midNm + data.substring(colLen - 1);
	}
}

function formMaskHp(data) {
	return data.substring(0, 3) + '-****-' + data.substring(9);
}

function formMaskRdnmAddr(data) {
	var colLen = data.length;
	var rdnmAddr = '';
	for(var i = 0; i < colLen; i++) {
		rdnmAddr += '*';
	}
	return rdnmAddr;
}

function formMaskUpdInfo(data) {
	var colValue = data.split('(2');
	var colLen = colValue[0].length;
	return formMaskUsrNm(colValue[0]) + '(2' + colValue[1];
}

//페이지 건수 select box에서 값을 선택 시 조회
//pageSizeChg(시트 id, 선택한 값);
function pageSizeChg(sheetid, pageValue, callSearchFnNm, objId, sheetHeightChg) {
	sheetid.SetConfig({Page: pageValue}, 1);
	window[callSearchFnNm]();
}

// setTimeOffsetBetweenServerAndClient 함수의 존재여부를 확인해서 호출하도록 하기위해서...
function callTimeOffsetBetweenServerAndClient(url) {
	//console.log('tims-util에서 callTimeOffsetBetweenServerAndClient url',url);
	if(loginYn == 'false') {
		return;
	}
	// 공통코드 || tims-sessionTimer.js || tree 에서 세션연장을 위해 호출되는 경우
	if(url.indexOf('/codeColls') > -1 || url.indexOf('/blank') > -1 || url.indexOf('/tree') > -1) {
		return;
	}
	try {
		if(typeof(setTimeOffsetBetweenServerAndClient) === "function") {
//			console.log("현재 화면 TimeOffset 함수 있음");
			if(url.indexOf('/service') > -1) {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				setTimeOffsetBetweenServerAndClient('admin');
			} else {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				setTimeOffsetBetweenServerAndClient('front');
			}
		} else if(typeof(opener.setTimeOffsetBetweenServerAndClient) === "function") {
//			console.log("opener 화면 TimeOffset 함수 있음");
			if(url.indexOf('/service') > -1) {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				opener.setTimeOffsetBetweenServerAndClient('admin');
			} else {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				opener.setTimeOffsetBetweenServerAndClient('front');
			}
		}
	} catch(e) {
		if(typeof(parent.setTimeOffsetBetweenServerAndClient) === 'function') {
//			console.log("parent 화면 TimeOffset 함수 있음");
			if(url.indexOf('/service') > -1) {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				parent.setTimeOffsetBetweenServerAndClient('admin');
			} else {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				parent.setTimeOffsetBetweenServerAndClient('front');
			}
		} else if(typeof(parent.parent.initTimeOffset) === 'function') {
//			console.log("parent parent 화면 TimeOffset 함수 있음");
			if(url.indexOf('/service') > -1) {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				parent.parent.setTimeOffsetBetweenServerAndClient('admin');
			} else {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				parent.parent.setTimeOffsetBetweenServerAndClient('front');
			}
		} else if(typeof(parent.opener.setTimeOffsetBetweenServerAndClient) === 'function') {
//			console.log("parent opener 화면 TimeOffset 함수 있음");
			if(url.indexOf('/service') > -1) {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				parent.opener.setTimeOffsetBetweenServerAndClient('admin');
			} else {
				// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
				parent.opener.setTimeOffsetBetweenServerAndClient('front');
			}
		}
	}
}

