	var __codeCollections = {};
	function getCombo( codes){
		url = svnmCodeColls;
		var dataId = codes.id;
		var data = __codeCollections[dataId];
		if(data)
			return data;
		var codes = {
				codeColls : [{cls: codes.cls, id: codes.id, cdg : codes.cdg, use: codes.use, cond: codes.cond, smd:codes.smd, inc:codes.inc, exc:codes.exc, params:codes.params}]
			};
			
		data = TimsUtil.getObject(url,codes,true);
		__codeCollections[dataId] = data;
		return data;
	}

	function setCombo(url, codes, formFlag, formName, saveName, option, cdLbl, sheetRow, targetId) {
		var asyncMode = codes && codes.async;
		var defaultValue = codes.value; 
		var orgCodes = codes;
		var comboCode = [];
		var comboText = [];
		
		if(formFlag == "G") {
			if(option == "S") {
				comboCode.push("");
				comboText.push("선택");
			} 
			if(option == "A"){
				comboCode.push("");
				comboText.push("전체");
			}
			if(option == "N"){
				comboCode.push("");
				comboText.push("");
			}
			if(option == "A1") {
				comboCode.push("");
				comboText.push("-전체-");
			}
			if(option == "S1") {
				comboCode.push("");
				comboText.push("-선택-");
			}
			if(option == "A2") {
				comboCode.push("");
				comboText.push("무관");
			}
		} else if(formFlag == "F") {
			if(option == "A") {
				comboCode.push("");
				comboText.push("전체");
			} else if(option == "S") {
				comboCode.push("");
				comboText.push("선택");
			} else if(option == "A2") {
				comboCode.push("");
				comboText.push("무관");
			} 
			if(option == "TC") { // FO 대상 분야용
				comboCode.push("");
				comboText.push("대상 전체");
			} 
			if(option == "FC") {
				comboCode.push("");
				comboText.push("분야 전체");
			} 
		} 
		var dataId = codes.id;
		var data = __codeCollections[dataId];
		if(codes.cdg && !data){
			data = __codeCollections[codes.cdg];
		}
		var codes = {
			codeColls : [{cls:codes.cls, id:codes.id, cdg:codes.cdg, use:codes.use, cond:codes.cond, smd:codes.smd, inc:codes.inc, exc:codes.exc, params:codes.params}]
		};

		if(url) {
			url = url;
		} else {
			console.log(svnmCodeColls);
			url = svnmCodeColls;
		}
		if(!data) {
			if(!asyncMode){			
				data = TimsUtil.getObject(url, codes, true);
				__codeCollections[dataId] = data;
			} else {
				var nocaching;
				if(url.indexOf("?") > 0) {
					nocaching = "&";
				} else {
					nocaching = "?";
				}
					
				var url = url + nocaching + "nocachingParam=" + TimsUtil.guid();
				var returnValue = null;
				var result = false;
				TimsUtil.ajax({
					url : url,
					dataType : 'json',
					data :TimsUtil.toJSON(codes),
					type : 'POST',
					cache : false,
					contentType : 'application/json; charset=utf-8',
					async : true,
					success : function(data, textStatus, jqXHR) {
						__codeCollections[dataId] = data;
						setCombo(url, orgCodes, formFlag, formName, saveName, option, cdLbl, sheetRow, targetId);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						returnValue = null;
					},
					complete : function(jqXHR, textStatus) {
						
					},
					handleError: true
				});
				
				return;
			}
		}

		if(formFlag == "G") {
			if(!$.isEmptyObject(data)) {
				$(Object.keys(data)).each(function(i, item) {
					if(data[item].length > 0){
						for(var i = 0; i < data[item].length; i++){
							comboCode.push(data[item][i].smallDiv);
							if(cdLbl == "A") {
								comboText.push(data[item][i].cdLbl);
							} else if(cdLbl == "C") {
								comboText.push(data[item][i].smallDiv);
							} else {
								comboText.push(data[item][i].cdNm);
							}
						}
					}
				});
			}
			if(comboCode.length > 0){
				comboCodeStr = comboCode.join('|');
				comboTextStr = comboText.join('|');
			}
			if(sheetRow) {
				var info = {ComboCode: comboCodeStr, ComboText: comboTextStr};
				formName.CellComboItem(sheetRow, saveName, info);
			} else {
				formName.InitDataCombo(0, saveName, comboTextStr, comboCodeStr);
			}
		} else if(formFlag == "F") {
			var $selector =  null;
			if(formName) {
				$selector= $("#" + formName + " #" + saveName);
			} else {
				$selector =$("#" + saveName);
			}

			$selector.html("");

			if(!$.isEmptyObject(data)) {
				$(Object.keys(data)).each(function(i, item) {
					if(data[item].length > 0){
						if(option != 'Z' && comboCode.length) {
							$selector.append("<option value='" + comboCode[0] + "'>" + comboText[0] + "</option>");
						}
						for(var i = 0; i < data[item].length; i++){
							if(cdLbl == "A") {
								$selector.append("<option value='" + data[item][i].smallDiv + "'>" + data[item][i].cdLbl + "</option>");
							} else if(cdLbl == "C") {
								$selector.append("<option value='" + data[item][i].smallDiv + "'>" + data[item][i].smallDiv + "</option>");
							} else {
								$selector.append("<option value='" + data[item][i].smallDiv + "'>" + data[item][i].cdNm + "</option>");
							}
						}
					}
				});
			} else {
				if(codes.codeColls[0].cls == 'SY3_2') {
					$('#' + targetId).empty();
				} else {
					$selector.append("<option value='" + comboCode + "'>" + comboText + "</option>");
				}
			}
			if(defaultValue) {
				$selector.val(defaultValue);
			}
		}
	}

	var progressCount = 0;
	
	function getCodeParam(codes){
		progressCount++;
		var codes = {
			codeColls : [{cls: codes.cls, id: codes.id, cdg : codes.cdg, use: codes.use, cond: codes.cond, smd:codes.smd, inc:codes.inc, exc:codes.exc, params:codes.params}]
		};
		return codes;
	}
	
	function setSelectTag(data, formFlag, option, cdLbl, formName, saveName, sheetRow){
		var comboCode = [];
		var comboText = [];
		
		if(formFlag == "G") {
			if(option == "S") {
				comboCode.push("");
				comboText.push("선택");
			} 
			if(option == "A"){
				comboCode.push("");
				comboText.push("전체");
			}
			if(option == "N"){
				comboCode.push("");
				comboText.push("");
			}
			if(option == "A1") {
				comboCode.push("");
				comboText.push("-전체-");
			}
			if(option == "S1") {
				comboCode.push("");
				comboText.push("-선택-");
			}
			if(option == "A2") {
				comboCode.push("");
				comboText.push("무관");
			}
			
			if(!$.isEmptyObject(data)) {
				$(Object.keys(data)).each(function(i, item) {
					
					if(data[item].length > 0){
						for(var i = 0; i < data[item].length; i++){
							comboCode.push(data[item][i].smallDiv);
							if(cdLbl == "A") {
								comboText.push(data[item][i].cdLbl);
							} else if(cdLbl == "C") {
								comboText.push(data[item][i].smallDiv);
							} else {
								comboText.push(data[item][i].cdNm);
							}
						}
					}
				});
			}
			if(comboCode.length > 0){
				comboCodeStr = comboCode.join('|');
				comboTextStr = comboText.join('|');
			}
			
			if(sheetRow) {
				var info = {ComboCode: comboCodeStr, ComboText: comboTextStr};
				formName.CellComboItem(sheetRow, saveName, info);
			} else {
				formName.InitDataCombo(0, saveName, comboTextStr, comboCodeStr);
			}
			
		} else if(formFlag == "F") {
			if(option == "A") {
				comboCode.push("");
				comboText.push("전체");
			} else if(option == "S") {
				comboCode.push("");
				comboText.push("선택");
			}
			else if(option == "A2") {
				comboCode.push("");
				comboText.push("무관");
			}
			
			var $selector =  null;
			if(formName)
				$selector= $("#" + formName + " #" + saveName);
			else
				$selector =$("#" + saveName);

			$selector.html("");

			if(!$.isEmptyObject(data)) {
				$(Object.keys(data)).each(function(i, item) {					
					if(data[item].length > 0){
						if(option!='Z' && comboCode.length)
							$selector.append("<option value='"+comboCode[0]+"'>"+comboText[0]+"</option>");
						
						for(var i = 0; i < data[item].length; i++){
							if(cdLbl == "A") {
								$selector.append("<option value='"+data[item][i].smallDiv+"'>"+data[item][i].cdLbl+"</option>");
							} else if(cdLbl == "C") {
								$selector.append("<option value='"+data[item][i].smallDiv+"'>"+data[item][i].smallDiv+"</option>");
							} else {
								$selector.append("<option value='"+data[item][i].smallDiv+"'>"+data[item][i].cdNm+"</option>");
							}
						}
					}
				});
			} else {
				if(codes.codeColls[0].cls == 'SY3_2') {
					$('#' + targetId).empty();
				} else {
					$selector.append("<option value='"+comboCode+"'>"+comboText+"</option>");
				}
			}			
			/*if(defaultValue)
				$selector.val(defaultValue);*/
		}
		progressCount--;
	}
	
	function progressEndFn(fn){
		var _interval = setInterval(function(){
			//console.log(progressCount);
			if(progressCount == 0){
				fn();
				clearInterval(_interval);
			}
		},500);
	}
	
	function bindClsCode(){
		 $("input[type=text][data-type=cls-code]").each(
		function(index){
			var guid = TimsUtil.guid();
			var element = $(this);
			if(element.attr("data-guid"))
				return;
			element.attr("data-cls-guid",guid);
			
			element.wrap(" <div class='input-group' id='divClsCode'></div>");
			
	    	$('<span class="input-group-btn"><button data-action="selectClsCode" data-target-guid="'+guid+'" class="btn btn-default" type="button"><span class="glyphicon glyphicon-search" ></span></button></span>').insertAfter(element);
		});
	}
	$(document).ready(function(){
		bindClsCode();
	});
	var _clsCodeDlg= null;
	var _clsGuid = null;
	function selectClsCode(element){
		_clsGuid = element.attr("data-target-guid");
		var url = serviceBathPath + "/codeColls/popup/clsCode/tree";
		_clsCodeDlg= TimsUtil.urlDialog("분류체계설정",url,600,600);
	}
	function onSelectClsCode(clsCode,clsName,clsPathCd,clsPathNm){
		if(_clsGuid){
			var target = $("[data-cls-guid="+_clsGuid+"]");
			if(target && target.length){
				var inputId = target.attr("data-input-id");
				var inputName = target.attr("data-input-name");
				var inputPathCd = target.attr("data-input-path-id");
				var inputPathName = target.attr("data-input-path-name");
				if(inputId){
					$("#"+inputId).val(clsCode);
					$("#"+inputId).change();
				}
				if(inputName){
					$("#"+inputName).val(clsName);
					$("#"+inputName).change();
				}
				if(inputPathCd){
					$("#"+inputPathCd).val(clsPathCd);
					$("#"+inputPathCd).change();
				}
				if(inputPathName){
					$("#"+inputPathName).val(clsPathNm);
					$("#"+inputPathName).change();
				}
			}
		}
		if(_clsCodeDlg)
			_clsCodeDlg.close();
		_clsCodeDlg = null;
	}
	
	function setMultiSelect(url, codes, formName, saveName, option) {
		/*
			(example)
			setMultiSelect("", {cls: "COM_1", id: "demo" , cdg : "EMA420"}, 'search', "demo" , {type:"", selectAll:"N", multiYn:"Y"});
			
			option = type, selectAll, multiYn, codeView
			option.type => "S" - 검색기능
			option.selectAll =>  multiYn이 Y인 경우(멀티셀렉트인 경우) "Y"면 "전체선택" 추가
			option.multiYn => "Y" - 멀티(checkbox), "N" - 단일(radio 버튼)
		*/ 
		
		if(!option.selectAll){
			option.selectAll = "N";
		}
		if(!option.multiYn){
			option.multiYn = "N";
		}
		if(!option.codeView){
			option.codeView = "N";
		}
		
		var asyncMode = codes && codes.async;
		var defaultValue = codes.value; 
		var orgCodes = codes;
		
		var dataId = codes.id;
		var data = __codeCollections[dataId];
		if(codes.cdg && !data){
			data = __codeCollections[codes.cdg];
		}
		var codes = {
			codeColls : [{cls:codes.cls, id:codes.id, cdg:codes.cdg, use:codes.use, cond:codes.cond, smd:codes.smd, inc:codes.inc, exc:codes.exc, params:codes.params, sctr:codes.sctrCd}]
		};
		
		if(url) {
			url = url;
		} else {
			url = svnmCodeColls;
		}
		
		if(!data) {
			if(!asyncMode){
				data = TimsUtil.getObject(url, codes, true);
				__codeCollections[dataId] = data;
			} else {
				var nocaching;
				if(url.indexOf("?") > 0) {
					nocaching = "&";
				} else {
					nocaching = "?";
				}
				
				var url = url + nocaching + "nocachingParam=" + TimsUtil.guid();
				var returnValue = null;
				var result = false;
				TimsUtil.ajax({
					url : url,
					dataType : 'json',
					data :TimsUtil.toJSON(codes),
					type : 'POST',
					cache : false,
					contentType : 'application/json; charset=utf-8',
					async : true,
					success : function(data, textStatus, jqXHR) {
						__codeCollections[dataId] = data;
						setMultiSelect(url, orgCodes, formName, saveName, option); 
					},
					error : function(jqXHR, textStatus, errorThrown) {
						returnValue = null;
					},
					complete : function(jqXHR, textStatus) {
						
					},
					handleError: true
				});
				
				return;
			}
		}
		
		var $selector = null;
		if(formName) {
			$selector= $("#" + formName + " #" + saveName);
		} else {
			$selector =$("#" + saveName);
		}
		
		$selector.html("");
		
		if(!$.isEmptyObject(data)) {
			$(Object.keys(data)).each(function(i, item) {
				if(data[item].length > 0){
					if(option.multiYn=="N" && option.selectAll == "N"){
						$selector.append("<option value=''>"+ ("선택") +"</option>");
					}
					else if(option.multiYn=="N" && option.selectAll == "Y"){
						$selector.append("<option value=''>"+ ("전체") +"</option>");
					}
					for(var i = 0; i < data[item].length; i++){
						if(option.codeView == "Y"){
							$selector.append("<option value='" + data[item][i].smallCd + "'>" + '['+ data[item][i].smallCd + '] ' +data[item][i].cdNm + "</option>");
						}else{
							$selector.append("<option value='" + data[item][i].smallCd + "'>" + data[item][i].cdNm + "</option>");
						}
					}
				}
			});
		}
		
		var multiSelectOption = {
			nonSelectedText		: "전체"		//미선택시 selectbox에 표시되는 문구
			, buttonWidth		: "100%"													//버튼 길이
			, maxHeight			: 300														//selectbox Height
			, nSelectedText		: "개 선택"	//n개 선택 시 표시할 문구
		};
		
		if(option.multiYn == "Y"){
			if(option.selectAll == "Y"){
				multiSelectOption.includeSelectAllOption = true;	//select All option 포함 여부
				multiSelectOption.selectAllText   = "모두 선택";	//전체 선택 시 문구(셀렉트 박스 내의 문구)
				multiSelectOption.allSelectedText = "모두 선택";	//전체 선택 시 문구(전체 선택 시 표시되는 문구)
			}
			$selector.prop('multiple', 'multiple');
		}
		
		if(option.type == "S"){
			multiSelectOption.enableFiltering = true;
			multiSelectOption.enableCaseInsensitiveFiltering = true;
		}
		
		$selector.multiselect(multiSelectOption);
		
		if(defaultValue) {
			$selector.val(defaultValue);
		}
		else{
			if(option.multiYn=="Y"){
				$selector.multiselect('deselect', $selector.val()); //초기화
			}
		}
	}
	
	function setBrdBinary(url, codes, formName, saveName, option, brdPropBinary, pstPropBinary) {
		/*
			(example)
			setMultiSelect("", {cls: "COM_1", id: "demo" , cdg : "EMA420"}, 'search', "demo" , {type:"", selectAll:"N", multiYn:"Y"});
			
			option = type, selectAll, multiYn
			option.type => "S" - 검색기능
			option.selectAll =>  multiYn이 Y인 경우(멀티셀렉트인 경우) "Y"면 "전체선택" 추가
			option.multiYn => "Y" - 멀티(checkbox), "N" - 단일(radio 버튼)
		*/
		
		if(!option.selectAll){
			option.selectAll = "N";
		}
		if(!option.multiYn){
			option.multiYn = "N";
		}
		if(!pstPropBinary){
			pstPropBinary = "";
		}
		
		var asyncMode = codes && codes.async;
		var defaultValue = codes.value; 
		var orgCodes = codes;
		
		var dataId = codes.id;
		var data = __codeCollections[dataId];
		if(codes.cdg && !data){
			data = __codeCollections[codes.cdg];
		}
		var codes = {
			codeColls : [{cls:codes.cls, id:codes.id, cdg:codes.cdg, use:codes.use, cond:codes.cond, smd:codes.smd, inc:codes.inc, exc:codes.exc, params:codes.params}]
		};
		
		if(url) {
			url = url;
		} else {
			url = svnmCodeColls;
		}
		
		if(!data) {
			if(!asyncMode){
				data = TimsUtil.getObject(url, codes, true);
				__codeCollections[dataId] = data;
			} else {
				var nocaching;
				if(url.indexOf("?") > 0) {
					nocaching = "&";
				} else {
					nocaching = "?";
				}
					
				var url = url + nocaching + "nocachingParam=" + TimsUtil.guid();
				var returnValue = null;
				var result = false;
				TimsUtil.ajax({
					url : url,
					dataType : 'json',
					data :TimsUtil.toJSON(codes),
					type : 'POST',
					cache : false,
					contentType : 'application/json; charset=utf-8',
					async : true,
					success : function(data, textStatus, jqXHR) {
						__codeCollections[dataId] = data;
						setMultiSelect(url, orgCodes, formName, saveName, option);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						returnValue = null;
					},
					complete : function(jqXHR, textStatus) {
						
					},
					handleError: true
				});
				
				return;
			}
		}
		
		var $selector = null;
		if(formName) {
			$selector= $("#" + formName + " #" + saveName);
		} else {
			$selector =$("#" + saveName);
		}
		
		$selector.html("");
		
		if(!$.isEmptyObject(data)) {
			
			$(Object.keys(data)).each(function(i, item) {
				if(data[item].length > 0){
					if(option.tag == 'radio'){
						var html = "";
						var resultHtml = '<div class="check_list">';
						for(var i in data[item]){
							html += '<span class="form_check_input">'							
							html += '<input required type="radio" id="tmpPstPropBinary_'+i+'" name="tmpPstPropBinary" value="'+data[item][i].smallDiv+'"';
							
							if(pstPropBinary != ''){
								if(pstPropBinary == data[item][i].smallDiv){
									html += ' checked="checked"';
								}
							}
							else{
								if(i == 0){
									html += ' checked="checked"';
								}
							}
							html += '/>'
							html += '<label for="tmpPstPropBinary_'+i+'">'+data[item][i].cdNm+'</label>';
							html += '</span>'
							//html += '/>'+data[item][i].cdNm+'</label>';
							
							if(data[item][i].smallDiv == '2' && brdPropBinary.indexOf("C") == -1){//공지글일 경우 공지글 속성이 없을 때 
								html = "";
							}
							
							if(data[item][i].smallDiv == '4' && brdPropBinary.indexOf("E") == -1){//비밀글일 경우 비밀글 속성이 없을 때
								html = "";
							}
							
							if(data[item][i].smallDiv == '3' && brdPropBinary.indexOf("A") == -1){//댓글 경우 댓글기능 속성이 없을 때
								html = "";
							}
							resultHtml += html;
							html = "";
						}
						resultHtml += "</div>";
						$selector.append(resultHtml);
					}
					else if(option.tag == 'select'){
						if(option.multiYn=="N" && option.selectAll == "N"){
							$selector.append("<option value=''>"+ ("선택") +"</option>");
						}
						else if(option.multiYn=="N" && option.selectAll == "Y"){
							$selector.append("<option value=''>"+ ("모두 선택") +"</option>");
						}
						
						for(var i in data[item]){
							html = "<option value='" + data[item][i].smallDiv + "'>" + data[item][i].cdNm + "</option>";
							
							if(data[item][i].smallDiv == '2' && brdPropBinary.indexOf("C") == -1){//공지
								html = "";
							}
							
							if(data[item][i].smallDiv == '4' && brdPropBinary.indexOf("E") == -1){//비밀글
								html = "";
							}
							
							if(data[item][i].smallDiv == '3' && brdPropBinary.indexOf("A") == -1){//댓글기능
								html = "";
							}
							
							$selector.append(html);
							html = "";
						}
					}
				}
			});
		}
		
		if(option.tag == 'select'){
			var multiSelectOption = {
				nonSelectedText		: "전체"		//미선택시 selectbox에 표시되는 문구
				, buttonWidth		: "100%"													//버튼 길이
				, maxHeight			: 300														//selectbox Height
				, nSelectedText		: "개 선택"	//n개 선택 시 표시할 문구
			};
			
			if(option.multiYn == "Y"){
				if(option.selectAll == "Y"){
					multiSelectOption.includeSelectAllOption = true;	//select All option 포함 여부
					multiSelectOption.selectAllText   = "모두 선택";	//전체 선택 시 문구(셀렉트 박스 내의 문구)
					multiSelectOption.allSelectedText = "모두 선택";	//전체 선택 시 문구(전체 선택 시 표시되는 문구)
				}
				$selector.prop('multiple', 'multiple');
			}
			
			if(option.type == "S"){
				multiSelectOption.enableFiltering = true;
			}
			
			$selector.multiselect(multiSelectOption);
			
			if(defaultValue) {
				$selector.val(defaultValue);
			}
			else{
				if(option.multiYn=="Y"){
					$selector.multiselect('deselect', $selector.val()); //초기화
				}
			}
		}
	}	
	
	function setChkRadioCombo(url, codes, formName, uprSaveName, saveName, tag, required) {
		var asyncMode = codes && codes.async;
		var defaultValue = codes.value; 
		var orgCodes = codes;
		
		var dataId = codes.id;
		var data = __codeCollections[dataId];
		if(codes.cdg && !data){
			data = __codeCollections[codes.cdg];
		}
		var codes = {
			codeColls : [{cls:codes.cls, id:codes.id, cdg:codes.cdg, use:codes.use, cond:codes.cond, smd:codes.smd, inc:codes.inc, exc:codes.exc, params:codes.params}]
		};
		
		if(url) {
			url = url;
		} else {
			url = svnmCodeColls;
		}
		
		if(!data) {
			if(!asyncMode){
				data = TimsUtil.getObject(url, codes, true);
				__codeCollections[dataId] = data;
			} else {
				var nocaching;
				if(url.indexOf("?") > 0) {
					nocaching = "&";
				} else {
					nocaching = "?";
				}
					
				var url = url + nocaching + "nocachingParam=" + TimsUtil.guid();
				
				TimsUtil.ajax({
					url : url,
					dataType : 'json',
					data :TimsUtil.toJSON(codes),
					type : 'POST',
					cache : false,
					contentType : 'application/json; charset=utf-8',
					async : true,
					success : function(data, textStatus, jqXHR) {
						__codeCollections[dataId] = data;
						setMultiSelect(url, orgCodes, formName, saveName, option);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						returnValue = null;
					},
					complete : function(jqXHR, textStatus) {
						
					},
					handleError: true
				});
				
				return;
			}
		}
		
		var $selector = null;
		if(formName) {
			if(uprSaveName){
				$selector= $("#" + formName + " #" + uprSaveName);
			}
			else{
				$selector= $("#" + formName + " #" + saveName);
			}
		} else {
			if(uprSaveName){
				$selector =$("#" + uprSaveName);
			}
			else{
				$selector =$("#" + saveName);
			}
		}
		
		$selector.html("");
		
		if(!$.isEmptyObject(data)) {
			$(Object.keys(data)).each(function(i, item) {
				if(data[item].length > 0){
					var html = "";
					html += '<div class="check_list">';
					var tagNm = '';
					if(tag == 'C'){
						tagNm = 'checkbox';
					}
					else if(tag == 'R'){
						tagNm = 'radio';
					}
					for(var i = 0; i < data[item].length; i++){
//						if(tag == 'C'){
//							html += '<input type="checkBox"  id="'+saveName+'_'+data[item][i].cdNm+'" name="'+saveName+'" value="'+data[item][i].smallDiv+'"/>';
//							html += '<label for="'+saveName+'_'+data[item][i].cdNm+'">'+data[item][i].cdNm+'</label>';
//							$selector.append(html);
//						}
//						else if(tag == "R"){
							html += '<span class="form_check_input">';								
							html += '<input type="'+tagNm+'" class="form-control"';
							if(i==0 && tag == 'R'){
								html += ' checked ';
							}
							if(required){
								html += ' required ';
							}
							html += 'id="'+saveName+'_'+i+'" name="'+saveName+'" value="'+data[item][i].smallDiv+'"/>';							
							html += '<label for="'+saveName+'_'+i+'">'+data[item][i].cdNm+'</label>';							
							html += '</span>'
							//html += data[item][i].cdNm+'</label>';							
//							$selector.append(html);
//						}
					}
					html += '</div>';
					$selector.append(html);
					html = "";
				}
			});
		}
	}
	
	//현재 날짜 구하기
	function nowDay(){
		var date = new Date(); 
		var year = date.getFullYear(); 
		var month = new String(date.getMonth()+1); 
		var day = new String(date.getDate()); 
		// 한자리수일 경우 0을 채워준다. 
		if(month.length == 1){ month = "0" + month; }
		if(day.length == 1){ day = "0" + day; }
	
		return year+month+day; //현재년도월날짜
	}
	
	//현재 날짜 구하기
	function nowDate(){
		var date = new Date(); 
		var year = date.getFullYear(); 
		var month = new String(date.getMonth()+1); 
		var day = new String(date.getDate()); 
		// 한자리수일 경우 0을 채워준다. 
		if(month.length == 1){ month = "0" + month; }
		if(day.length == 1){ day = "0" + day; }
		var toDay = year + '-' + month + '-' + day;
		return toDay; //현재년도월날짜
	}
	
	//이번달 날짜 입력
	function setNowMonth(startDt, endDt){
		var date = new Date(); 
		var year = date.getFullYear(); 
		var month = date.getMonth()+1; 
		
		var nowMonthLastDate = new Date(year, month, 0);
		var lastDay = new String(nowMonthLastDate.getDate());
		var firstDay = '01';
		
		// 한자리수일 경우 0을 채워준다. 
		if(month.length == 1){ month = "0" + month; }
		
		$("#"+startDt).val(""+year+month+firstDay);
		$("#"+endDt).val(""+year+month+lastDay);
	}
	
	//최근 3개월 날짜 입력
	function setPrev3Month(startDt, endDt){
		var date = new Date();
		
		var prev3MonthDate = new Date(date.setMonth(date.getMonth() - 3));
		
		var prev3Year = new String(prev3MonthDate.getFullYear()); 
		var prev3Month = new String(prev3MonthDate.getMonth()+1);
		var prev3Day = new String(prev3MonthDate.getDate());
		
		// 한자리수일 경우 0을 채워준다. 
		if(prev3Month.length == 1){ prev3Month = "0" + prev3Month; }
		if(prev3Day.length == 1){ prev3Day = "0" + prev3Day; }
		
		$("#"+startDt).val(prev3Year+prev3Month+prev3Day);
		$("#"+endDt).val(nowDay());
	}
	
	//지난달 날짜 입력
	function setPrevMonth(startDt, endDt){
		var date = new Date(); 
		var year = date.getFullYear(); 
		var month = date.getMonth()+1;
		
		if(month == 1){
			year = year-1;
			month = 12;
		}
		
		var prev3MonthLastDate = new Date(year, month-4, 0);
		var lastDay = new String(prev3MonthLastDate.getDate());
		var firstDay = '01';
		
		year = new String(year);
		month = new String(month-1);
		
		// 한자리수일 경우 0을 채워준다. 
		if(month.length == 1){ month = "0" + month; }
		
		$("#"+startDt).val(year+month+firstDay);
		$("#"+endDt).val(year+month+lastDay);
	}
	
	//날짜 입력 버튼(시작날짜 ~ 종료날짜 버튼 입력 시 사용)
	function getDateInfo(startId, endId, dateType, startInterval, endInterval){
		/*
			startId			:	시작 날짜 tag id
			endId			:	종료 날짜 tag id
			dateType		:	year, month, day(week의 경우 day로 입력)
			startInterval	:	시작 날짜 interval
			endInterval		:	종료 날짜 interval
			Example			:	최근 3개월인 경우	dateType:month, startInterval:3, endInterval:0
								오늘인 경우			dateType:day,   startInterval:0, endInterval:0
								일주일인 경우		dateType:day,   startInterval:6, endInterval:0		=> 일주일 계산은 오늘 날짜를 기준으로 계산하므로, 7을 넣을 경우 8일이 계산되어 6을 넣어야 함
		*/
		
		if(startInterval == '' && startInterval == null){
			startInterval = 0;
		}
		if(endInterval == '' && endInterval == null){
			endInterval = 0;
		}
		
		var param = {
			dateType		: dateType
			, startInterval	: startInterval
			, endInterval	: endInterval
		};
		
		var dateInfo = TimsUtil.getObject(serviceBathPath+'/datePeriod/getDateInfo', param, false);
		if(dateInfo){
			$('#'+startId).val(dateInfo.startDt);
			$('#'+endId).val(dateInfo.endDt);
		}
	}
	// 전화번호 정규식
	function isValidTelNumber(input) {
	  // 전화번호 정규식
	  var telRegex = /^(010|070|031|032|033|041|042|044|052|051|053|054|055|062|063|064|067)-?(\d{3,4})-?(\d{4})$/;
	  
	  // 서울 번호인 경우 (02)
	  if (input.startsWith("02")) {
	    var seoulRegex = /^02(\d{7}|\d{8})$/; // 7자리 또는 8자리 서울 번호 패턴
	    return seoulRegex.test(input);
	  }

	  // 010인 경우
	  if (input.startsWith("010")) {
	    var mobileRegex = /^010\d{8}$/; // 010 다음에 8자리 패턴
	    return mobileRegex.test(input);
	  }
	
	  return telRegex.test(input);
	}
	