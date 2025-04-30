/* 전역변수 */
var sessionExpired = false;
var sessionLogout = false;
var _sessionTimeId = 0;
var _window;
var sessionSec = 0;
var initTime = 0;
var _timehold = false;

// 처음 호출되는 함수
function setTimeOffsetBetweenServerAndClient(window, timehold) {
	clearTimeout(_sessionTimeId);
	if(loginYn == 'false') {
		return;
	}
	_timehold = timehold;
	_window = window;
	initTime = (new Date()).getTime();
	timerFunc();
}

function timerFunc() {
//	console.log('_sessionTimeId ====> ' + _sessionTimeId);
	if(_sessionTimeId) {
		clearTimeout(_sessionTimeId);
	}
	_sessionTimeId = null;
	var duration = checkDuration();
//	console.log('duration =====> ' + duration);
	if(!_timehold && duration) {
		_sessionTimeId = setTimeout(timerFunc, 1000);
	}
}

function checkDuration() {
	var startTime = (new Date()).getTime();
	// SessionTimeoutCookieFilter.java에서 가져온 서버의 현재타임
	var serverTime = TimsUtil.getCookie('latestTouch');
	var nowTime = (new Date()).getTime();
	var duration = nowTime - initTime;
	duration /= 1000;
	
	var offsetDuration = serverTime - startTime;
	offsetDuration /= 1000;
	if(offsetDuration > 0) {
		duration += offsetDuration;
	}
	sessionSec = maxInactiveInterval - duration;
//	sessionSec = sessionSec % 3600;
	var lmin = parseInt(sessionSec / 60);
	var lsec = parseInt(sessionSec % 60);
	var sessiontimer = (lmin + "").padLeft(2, "0") + ":" + (lsec + "").padLeft(2, "0");
	$(".session_timer").html(sessiontimer);
//	console.log('sessionSec : ' + sessionSec + ', parseInt(sessionSec) :' + parseInt(sessionSec));
	/* 자동로그아웃 사전 알림 로직 수행 */
	if(parseInt(sessionSec) <= sessionExpiryPreTime && !sessionExpired) { // sessionExpired : 한번만 호출되도록
//		console.log('1 자동로그아웃 창 sessionExpired : ' + sessionExpired);
		sessionExpired = true;
		if(_window == 'admin') { // 관리자용
	    	/* 세션 종료 안내 모달 호출 */
	    	var $modal = $("#modal-session-expiry-info");
	    	$modal.modal();
		} else { // 사용자용
	    	/* 세션 종료 안내 모달 호출 */
	    	var $el = $('#pop-session-expiry-info'); //레이어의 id를 $el 변수에 저장
	    	$el.wPopLayer();
	    	$el.find('[data-button=sessionExtend]').off().click(function(e){ // 연장하기
	        	e.preventDefault();
	        	sessionExtend();
	        	$el.fadeOut();
	            $("[data-mask]").fadeOut('300',function(){
	                $("[data-mask]").remove();
	                $("body").css({
	                    'overflow-y':'auto'
	                });
	            });
	        });
	    	$el.find('[data-button=sessionLogout]').off().click(function(e){ // 로그아웃
	        	e.preventDefault();
	        	__sessionLogout();
	        	$el.fadeOut();
	            $("[data-mask]").fadeOut('300',function(){
	                $("[data-mask]").remove();
	                $("body").css({
	                    'overflow-y':'auto'
	                });
	            });
	        });
		}
	}
//	console.log('sessionLogout : ' + sessionLogout);
	/* 자동로그아웃 로직 수행 */
	if(parseInt(sessionSec) == 0 && !sessionLogout) {
	//	console.log('2 자동로그아웃 창 sessionLogout : ' + sessionLogout);
	//	console.log('_window : ' + _window);
		sessionLogout = true;
		__sessionAutoLogout();
		if(_window == 'admin') { // 관리자용
			// 자동 로그아웃 안내창 닫기
			$('#modal-session-expiry-info').modal("toggle");
			var $modal = $("#modal-session-logout-info");
			$modal.modal();
		} else {
			
		    // 자동 로그아웃 안내창 -> 배경 마스크 fadeOut 감춘뒤 삭제
			//$popLayer = $('#pop-session-expiry-info');
			//$popLayer.fadeOut('fast');
			// 세션만료로 로그아웃 시 알림창 오픈
			var $el = $('#pop-session-logout-info'); //레이어의 id를 $el 변수에 저장
			$el.wPopLayer();
			$el.find('[data-button=sessionLogin]').off().click(function(e){ // 로그인화면이동
		    	e.preventDefault();
		    	sessionLogin();
		    	$el.fadeOut();
		        $("[data-mask]").fadeOut('300',function(){
		            $("[data-mask]").remove();
		            $("body").css({
		                'overflow-y':'auto'
		            });
		        });
		    });
		}
	}
	if(sessionSec > 0) {
		if(sessionLogout) {
			return false;
		} else {
			return true;			
		}
	} else {
		return false;
	}
}

function sessionExtend() {
	sessionExpired = false;
	sessionLogout = false;
	if(_window == 'admin') { // 관리자용
		//$('#modal-session-expiry-info').modal("toggle"); //닫기
		initTimeOffset('admin');
	} else {
	    // 배경 마스크 fadeOut 감춘뒤 삭제
		$popLayer = $('#pop-session-expiry-info');
		$popLayer.fadeOut('fast');
	    $("[data-mask]").fadeOut('300',function(){
	        $("[data-mask]").remove();
	        $("body").css({
	            'overflow-y':'auto'
	        });
	    });
	    initTimeOffset('front');
	}
}

function __sessionLogout() {
//	console.log('로그아웃 수행======================');
	__logout();
}

function __sessionAutoLogout() {
//	console.log('로그아웃 수행======================');
	__autoLogout();
}

function initTimeOffset(window) {
	TimsUtil.getObject(serviceBathPath + '/blank', null, true);
	// 서버시간과 브라우저의 시간을 동기화 하는데 필요한 offset를 구하고 세션타이머 재 호출
	setTimeOffsetBetweenServerAndClient(window);
}

function sessionLogin() {
	var ret = TimsUtil.getObject(serviceBathPath + '/loginUrl', {}, false);
	location.replace(ret.loginUrl);
}
