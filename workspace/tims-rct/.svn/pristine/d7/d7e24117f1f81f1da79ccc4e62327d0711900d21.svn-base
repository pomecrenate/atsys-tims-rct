/******************************************************
@ Init
******************************************************/
$(function () {
	mResizeCheck = $(window).width();
	npos = $(window).scrollTop();
	SW = $(window).width();
	SH = $(window).height();
	
	commonJs.init();
	detailPop.init();
	agent = commonJs.checkMedia();
	
	if(SW > 1024){
		$('.btn-drop').on('mouseenter mouseleave focusin focusout',function(e){
			if(e.type == 'mouseenter' || e.type == 'focusin'){
				$(this).find('> ul').stop(true).slideDown(300);
			}else{
				$(this).find('> ul').stop(true).slideUp(300);
			}
		});
	}else{
		$('.btn-drop > a').on('click', function(){
			if($(this).next('ul').css('display') == 'block'){
				$(this).next('ul').slideUp();
			}else{
				$(this).next('ul').slideDown();
			}
		});
	}
	
	$('.input-form select').on('change', function(){
		if($(this).find('option:selected').val()==1){
			$(this).parents('.input-mail').addClass('open').find('.input-direct input').focus();
		}else{
			$(this).parents('.input-mail').removeClass('open');
		}
	});
	
	$('.tooltip').each(function(){
		const _text = $(this).find('.text-over').text();
		if(_text.length > 20){
			$(this).attr('tooltip', _text);
		}
	})
	
	/******************************************************
	@ Window Scroll
	******************************************************/
	$(window).on("scroll",function () {
		npos = $(window).scrollTop();
		SW = $(window).width();
		SH = $(window).height();
		
		commonJs.scroll();
	});
	
	/******************************************************
	@ Window Resize
	******************************************************/
	$(window).on("resize",function () {
		npos = $(window).scrollTop();
		SW = $(window).width();
		SH = $(window).height();
		
		commonJs.resize();
	});
});


/**********************************
@ common
**********************************/
let commonJs = {
	
	btnTopFlag: false,
	petTimer : null,
	
	init: function () {
		$('#Wrap').addClass('start');
		
		if(SW > 1024){
			$('.gnb-list > li').on('mouseenter focusin', function(){
				$('#Header').addClass('open');
			});
			$('#Header').on('mouseleave', function(){
				$('#Header').removeClass('open');
			});
		}else{
			$('.gnb-list > li').on('click', function(){
				if($(this).hasClass('actived')){
					$(this).removeClass('actived').find('.sub').hide();
				}else{
					$('.gnb-list > li').removeClass('actived').find('.sub').hide();
					$(this).addClass('actived').find('.sub').slideDown(500);
				}
			})
		}
		
		//딤드 배경 생성
		$('#wrap').append('<div id="cover"></div>');
		
		setTimeout(function(){
			commonJs.scroll();
			commonJs.resize();
		},100)
	},
	
	scroll : function(){
		if(npos <= 0){
			$('.btn-page-top').hide();
		}else{
			$('.btn-page-top').show();
		}
		if(SW > 1024){
			if(npos > 0){
				$('#Header').addClass('fix');
			}else{
				$('#Header').removeClass('fix');
			}
		}else{
			if(npos > 0){
				$('#Header').addClass('fix');
			}else{
				$('#Header').removeClass('fix');
			}
		}
	},
	
	resize : function(){
		urlRemove();
	},
	subControl: function (){
		if(!$('.gnb-wrap').hasClass('open')){
			$('.gnb-wrap').addClass('open');
		}else{
			$('.gnb-wrap').removeClass('open')
		}
	},
	gnbControl : function(){
		if(!$('#Header').hasClass('open')){
			$('html').addClass('fix')
			$('#Header').addClass('open');
		}else{
			$('html').removeClass('fix')
			$('#Header').removeClass('open');
			$('.gnb-list > li').removeClass('actived').find('.sub-menu').hide();
		}
	},	
	quickControl : function(){
		if(!$('.quick-menu').hasClass('open')){
			$('.quick-menu').addClass('open');
		}else{
			$('.quick-menu').removeClass('open');
		}
	},
	scollTopStart : function () {
		$('html,body').stop().animate({ scrollTop: 0 }, 600)
	},
	getParameter:function(key){
		var url = location.href;
		var spoint = url.indexOf("?");
		var query = url.substring(spoint, url.length);
		var keys = new Array;
		var values = new Array;
		var nextStartPoint = 0;
		while (query.indexOf("&", (nextStartPoint + 1)) > -1) {
			var item = query.substring(nextStartPoint, query.indexOf("&", (nextStartPoint + 1)));
			var p = item.indexOf("=");
			keys[keys.length] = item.substring(1, p);
			values[values.length] = item.substring(p + 1, item.length);
			nextStartPoint = query.indexOf("&", (nextStartPoint + 1));
		}
		item = query.substring(nextStartPoint, query.length);
		p = item.indexOf("=");
		keys[keys.length] = item.substring(1, p);
		values[values.length] = item.substring(p + 1, item.length);
		var value = "";
		for (var i = 0; i < keys.length; i++) {
			if (keys[i] == key) {
				value = values[i];
			}
		}
		return value;
	},
	checkMedia:function(){
		var UserAgent = navigator.userAgent;
		var UserFlag = true;
		if (UserAgent.match(/iPhone|iPad|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) UserFlag = false
		return UserFlag
	}
};

var detailPop = {
	btnTopFlag: false,
	target: null,
	init: function () {
		detailPop.target = $('#layerPop');
		
		$('#layerPop').on("click",function(e) { 
			if($(e.target).closest('.pop-contents').length == 0) {
				detailPop.close('');
			}
		});
	},
	open: function (type, _url, param) {
		$('html').addClass('fix');
		$('#layerPop').show();
		jQuery.ajax({
			url: _url,
			type: type,
			data: param,
			success: function (data) {
				var newItems = $(data);
				detailPop.target.html(newItems);
				detailPop.target.scrollTop(0)
				detailPop.target.stop(true).fadeIn(400);
				popFlag = true;
			},
			error: function (result) {
			}
		});
	},
	change: function (type, _url, param) {
		detailPop.open(type, _url, param);
	},
	close: function (type) {
		$('html').removeClass('fix');
		detailPop.target.scrollTop(0)
		detailPop.target.stop(true).fadeOut(400, function () {
			detailPop.target.html('');
		});
		if(type == 'pop-alert'){
			$('.pop-data').hide();
		}
		popFlag = false;
	},
	resize : function(){
		if(SW > 1024){
		}else{
			$('#wrap').attr('style','');
			$('#header').attr('style','');
		}
	}
}

function urlRemove(){
	if(SW > 1024){
		$('.gnb-list > li').each(function(){
			if($(this).hasClass('no-sub')){
				$(this).removeClass('no-sub');
			}
		})
	}else{
		$('.gnb-list > li').each(function(){
			if($(this).find('.sub').length >= 1){
				$(this).find('> a').attr('href','#gnb');
			}else{
				$(this).addClass('no-sub');
			}
		})
	}
}

function Attachments(_t){
	const _file = $(_t).parents('.input-file').find('input[type="file"]');
	const _name = $(_t).parents('.input-file').find('input[type="text"]');
	if(!$(_t).parents('.input-file').hasClass('is-attached')){
		_file.trigger('click');
		
		_file.on('change', function(){
			_name.val(_file.val());
			//$(_t).parents('.input-file').addClass('is-attached');
		});
	}else{
		_file.val('');
		_name.val('');
		$(_t).parents('.input-file').removeClass('is-attached');
	}
}

function Search(_t){
	let searchBox = $(_t).parent('.list-search');
	if(!searchBox.hasClass('open')){
		searchBox.addClass('open');
		$(_t).addClass('btn-close').find('.hidden').text('검색영역 닫기');
		setTimeout(() => {
			searchBox.find('input').focus();
		},250)
	}else{
		searchBox.removeClass('open').find('input').val('');
		$(_t).removeClass('btn-close').find('.hidden').text('검색영역 열기');
	}
}

function inputDirect(_t){
	const _text = $(_t).find('option:selected').text();
	if(_text == '직접입력'){
		$(_t).parents('.select-direct').addClass('input-mode');
	}else{
		$(_t).parents('.select-direct').removeClass('input-mode');
	}
}

function charCnt(_t){
	const _number = $(_t).val().length;	
	$(_t).parent('.input-document').find('#inputCnt').text(_number);
}

function tabCont(_t){
	const _i = $(_t).parent().index();
	$(_t).parent().addClass('actived').siblings().removeClass('actived');
	$('.tab-cont-wrap > div').hide().eq(_i).show();
}

function activeLoadingScreen(){
	let loadingScreen = document.getElementById("Loading");
	let notIos = (navigator.userAgent.match(/iPhone|iPad|iPod/) == null);
	
	//submit중임을 명시하는 screen 생성 : 기존에 화면에 별도로 설정된게 있다면 생성하지 않는다. + ios에서는 띄우지 않는다.
	if(loadingScreen == null && notIos){
		let loadingDivParent = document.createElement("div");
		loadingDivParent.id = "myLoading";
		loadingDivParent.style.display = "block";
		
		let loadingDivChild = document.createElement("div");
		loadingDivChild.id = "loading";
		loadingDivChild.className = "actived";
		
		let loadingDivChildWrap = document.createElement("div");
		loadingDivChildWrap.className = "loading-wrap";
		
		let loadingDivChildWrapTxt = document.createElement("div");
		loadingDivChildWrapTxt.className = "txt";
		loadingDivChildWrapTxt.innerText = "Loading";
		
		let loadingDivChildWrapBar = document.createElement("div");
		loadingDivChildWrapBar.className = "bar";
		
		loadingDivChildWrap.append(loadingDivChildWrapTxt);
		loadingDivChildWrap.append(loadingDivChildWrapBar);
		
		loadingDivChild.append(loadingDivChildWrap);
		loadingDivParent.append(loadingDivChild);
		
		document.body.append(loadingDivParent);
		
	} else if(loadingScreen != null && notIos) {
		loadingScreen.style.display = "block";
	}
	
	//submit중임을 명시하는 screen 생성 : 기존에 화면에 별도로 설정된게 있다면 생성하지 않는다. (Jquery Version)
	/*
	if($("div#loading").length <= 0){
		$("body").append($("<div>").prop({id : "myLoading"}).css({
			"display" : "block"
		}).append($("<div>").prop({id : "loading"}).addClass("actived")
				.append($("<div>").addClass("loading-wrap")
						.append($("<div>").addClass("txt").html("Loading"))
						.append($("<div>").addClass("bar"))
						)
				)
		);
	} else {
		$("div#myLoading").css({"display" : "block"});
	}
	*/
}

//로딩스크린 제거
function deactiveLoadingScreen(){
	let loadingScreen = document.getElementById("Loading");
	if(loadingScreen != null){
		loadingScreen.style.display = "none";
	}
	
}

//기본 submit에 activeloadingscreen 추가
HTMLFormElement.prototype._submit = HTMLFormElement.prototype.submit;
HTMLFormElement.prototype.submit = function(){
	if(this.target == "_self" || this.target == ""){
		activeLoadingScreen();
	}
	
	this._submit();
}

//download 시작시 로딩스크린 생성 + 다운로드 시작되면 로딩스크린 제거
HTMLFormElement.prototype.submitDownload = async function(){
	const urlaction = this.action;
	const options = {
		method : this.method,
		body : new URLSearchParams(new FormData(this))
	}
	
	activeLoadingScreen();
	
	try{
		const init = await fetch(urlaction, options);
		const blob = await init.blob();
		
		const disposition = init.headers.get("content-disposition");
		
		let fileName = "file";
		if(disposition && disposition.indexOf('attachment') !== -1) {
			const filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
			const matches = filenameRegex.exec(disposition);
			if (matches != null && matches[1]) {
				fileName = decodeURIComponent(matches[1].replace("UTF-8''", "").replace(/['"]/g, ''));
			} 
		}
		
		// 가상 링크 DOM 만들어서 다운로드 실행
		const url = URL.createObjectURL(blob);
		const a = document.createElement("a");
		a.href = url;
		a.download = fileName;
		document.body.appendChild(a);
		a.click();
		window.URL.revokeObjectURL(url);
	} catch(error){
		alert("다운로드에 실패하였습니다!");
	} finally {
		deactiveLoadingScreen();
	}
}

$(function() {$(document).tooltip();});
