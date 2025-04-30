/**
 * 시험 첫 응시 화면을 오픈합니다.
 * @param examPaperCd 시험지 코드
 * @param gisuAtndCd 수강코드	
 */
function applyExamPaper(examPaperCd, gisuAtndCd, winDiv) {
	if(winDiv == 'popup') {
		var url = serviceBathPath + "/examWin/apply/" + examPaperCd + "/" + gisuAtndCd + "/" + winDiv;
		TimsUtil.popup(url, "exma_apply", 800, 1000, "YES");
	} else {
		var url = serviceBathPath + "/examWin/apply/" + examPaperCd + "/" + gisuAtndCd + "/" + winDiv;
		// iframe 모달창 open
		APP.MODAL.open({
			iframe : {
				url : url
			},
			width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
			height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
			header : false
		});
	}
}

/**
 * 시험 재응시 화면을 오픈합니다.
 * @param examPaperCd 시험지 코드
 * @param gisuAtndCd 수강코드	
 * @param scoreCd 시험점수코드
 */
function applyReExamPaper(examPaperCd, gisuAtndCd, scoreCd, winDiv){
	if(winDiv == 'popup') {
		var url = serviceBathPath + "/examWin/apply/" + examPaperCd + "/" + gisuAtndCd + "/" + scoreCd + "/" + winDiv;
		TimsUtil.popup(url, "exma_apply", 800, 1000, "YES");
	} else {
		var url = serviceBathPath + "/examWin/apply/" + examPaperCd + "/" + gisuAtndCd + "/" + scoreCd + "/" + winDiv;
		// iframe 모달창 open
		APP.MODAL.open({
			iframe : {
				url : url
			},
			width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
			height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
			header : false
		});
	}
}

/**
 * 시험 결과 확인
 * @param examPaperCd 시험지 코드
 * @param gisuAtndCd 수강코드	
 * @param scoreCd 시험점수코드
 */
function resultExamPaper(examPaperCd, gisuAtndCd, scoreCd, winDiv){
	if(winDiv == 'popup') {
		var url = serviceBathPath + "/examWin/result/" + examPaperCd + "/" + gisuAtndCd + "/" + scoreCd + "/" + winDiv;
		TimsUtil.popup(url, "exma_apply", 800, 1000, "YES");
	} else {
		var url = serviceBathPath + "/examWin/result/" + examPaperCd + "/" + gisuAtndCd + "/" + scoreCd + "/" + winDiv;
		// iframe 모달창 open
		APP.MODAL.open({
			iframe : {
				url : url
			},
			width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
			height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
			header : false
		});
	}
}

/**
 * 차수용 시험지 미리보기
 * @param examPaperCd 시험지 코드
 * @param gisuCd 차수코드
 */
function previewGisuExamPaper(examPaperCd, gisuCd) {
	var url = serviceBathPath + "/examWin/gisuPreview/" + examPaperCd + '/' + gisuCd;
	TimsUtil.popup(url, "exma_preview", 800, 1000, "YES");
}

/**
 * 과정용 시험지 미리보기
 * @param examPaperCd 시험지 코드
 * @param gisuCd 차수코드
 */
function previewCrsExamPaper(examPaperCd, crsCd) {
	var url = serviceBathPath + "/examWin/crsPreview/" + examPaperCd + '/' + crsCd;
	TimsUtil.popup(url, "exma_preview", 800, 1000, "YES");
}

/**
 * 퀴즈 참여
 */
function applyQuiz(param) {
	var url = serviceBathPath + "/quizWin/apply";
	// iframe 모달창 open
	APP.MODAL.open({
		iframe : {
			url : url,
			param : param
		},
		width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
		height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
		header : false
	});
}

function applyNoneQuiz(param) {
	var url = serviceBathPath + "/quizWin/noneApply";
	// iframe 모달창 open
	APP.MODAL.open({
		iframe : {
			url : url,
			param : param
		},
		width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
		height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
		header : false
	});
}

/**
 * 퀴즈 미리보기
 */
function previewQuiz(cntsCd, cntsQuizLocCd, winDiv) {
	var url = serviceBathPath + "/quizWin/preview/" + cntsCd + "/" + cntsQuizLocCd + "/" + winDiv;
	if(winDiv == 'popup') {
		TimsUtil.popup(url, "quiz_preview", 800, 1000, "YES");
	} else {
		// iframe 모달창 open
		APP.MODAL.open({
			iframe : {
				url : url
			},
			width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
			height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
			header : false
		});
	}
}

/**
 * 설문 미리보기
 */
function previewSurvey(srvPaperCd, winDiv) {
	var url = serviceBathPath + "/surveyWin/preview/" + srvPaperCd + "/" + winDiv;
	if(winDiv == 'popup') {
		TimsUtil.popup(url, "survey_preview", 800, 1000, "YES");
	} else {
		// iframe 모달창 open
		APP.MODAL.open({
			iframe : {
				url : url
			},
			width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
			height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
			header : false
		});
	}
}

/**
 * 과정형 설문 참여
 */
function applySurvey(srvPaperCd, gisuAtndCd, winDiv) {
	var url = serviceBathPath + "/surveyWin/applyCrs/" + srvPaperCd + "/" + gisuAtndCd + "/" + winDiv;
	if(winDiv == 'popup') {
		TimsUtil.popup(url, "survey_preview", 800, 1000, "YES");
	} else {
		// iframe 모달창 open
		APP.MODAL.open({
			iframe : {
				url : url
			},
			width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
			height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
			header : false
		});
	}
}
/** * 과정형 설문 참여(결과보기) */
function applySurveyRst(srvPaperCd, gisuAtndCd, winDiv) {
	var url = serviceBathPath + "/surveyWin/applyCrsRst/" + srvPaperCd + "/" + gisuAtndCd + "/" + winDiv;
	// iframe 모달창 open
	APP.MODAL.open({
		iframe : {
			url : url
		},
		width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
		height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
		header : false
	});
}

/**
 * 강사평가형 설문 참여
 */
function applyTtrSurvey(srvPaperCd, gisuAtndCd, ttrCd, winDiv) {
	var url = serviceBathPath + "/surveyWin/applyTtr/" + srvPaperCd + "/" + gisuAtndCd + "/" + ttrCd + "/" + winDiv;
	if(winDiv == 'popup') {
		TimsUtil.popup(url, "survey_preview", 800, 1000, "YES");
	} else {
		// iframe 모달창 open
		APP.MODAL.open({
			iframe : {
				url : url
			},
			width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
			height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
			header : false
		});
	}
}

/**
 * 강사평가형 설문 참여(결과보기)
 */
function applyTtrSurveyRst(srvPaperCd, gisuAtndCd, ttrCd, winDiv) {
	var url = serviceBathPath + "/surveyWin/applyTtrRst/" + srvPaperCd + "/" + gisuAtndCd + "/" + ttrCd + "/" + winDiv;
	// iframe 모달창 open
	APP.MODAL.open({
		iframe : {
			url : url
		},
		width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
		height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
		header : false
	});
}

/**
 * 강사평가형 설문 참여(별점형태)
 */
function applyGradeTtrSurvey(param) {
	var url = serviceBathPath + "/surveyWin/applyGradeTtr";
	// iframe 모달창 open
	APP.MODAL.open({
		iframe : {
			url : url,
			param : param
		},
		width: $(window).width() > 800 ? 400 : $(window).width() * 0.9,
		height: $(window).height() > 630 ? 300 : $(window).height() * 0.9,
		header : false
	});
}

/**
 * 수강신청 사전 설문 확인
 */
function applyAtndSurvey(gisuCd) {
	// 사전설문이 있는지 먼저 확인
	var param = {};
	param.gisuCd = gisuCd;
	var url = serviceBathPath + "/surveyWin/atndSrv"
	var ret = TimsUtil.getObject(url, param, false);
	
	if ( ret ) {
		ret.yn = 'Y';
	} else {
		if ( ret == null ) {
			ret = {yn : 'N'}
		} else {
			ret.yn = 'N';
		}
	}
	return ret;
}

/**
 * 일반설문 - Web(쪽지)용
 */
function generalWebSrv(param, winDiv) {
	var url = serviceBathPath + "/surveyWin/generalWebSrv/" + winDiv;
	// iframe 모달창 open
	APP.MODAL.open({
		iframe : {
			url : url,
			param : param
		},
		width: $(window).width() > 800 ? 800 : $(window).width() * 0.9,
		height: $(window).height() > 630 ? 700 : $(window).height() * 0.9,
		header : false
	});
}

﻿/*
 * 학습 진행
 * gisuAtndCd : 수강번호
 * */
function studyLmsCnts(gisuAtndCd) {
	var url = '/study/' + gisuAtndCd;
	launchContent(url);
}

/**
 * 수강코드없이 학습 진행
 * gisuCd : 차수코드
 */
function studyNoneLmsCnts(gisuCd) {
	var url = '/noneStudy/' + gisuCd;
	launchContent(url);
}

/**
 * 미리보기
 * gisuCd : 차수코드
 */
function studyPreview(div, key) {
	var url = '/preview';
	if(div == 'crs') {
		// 과정
		url += '/crs/' + key;
	} else if(div == 'gisu') {
		// 차수
		url += '/gisu/' + key;
	} else {
		// 콘텐츠
		url += '/cnts/' + key;
	}
	launchContent(url);
}

function launchContent(url){
	var _CONTWIN_STUDY_URL = serviceBathPath + "/contWin" + url;
	var protocol = location.protocol + '//';
	var hostname = $(location).attr('hostname');
	var port = ':' + $(location).attr('port');
	var newUrl = protocol + hostname + port + _CONTWIN_STUDY_URL;
	TimsUtil.openFullScreen(newUrl, "lectWin"); 
}

function isIOS() {
	var iDevices = [
		'iPad Simulator',
		'iPhone Simulator',
		'iPod Simulator',
		'iPad',
		'iPhone',
		'iPod'
	];

	if(!!navigator.platform) {
		while (iDevices.length) {
			if(navigator.platform === iDevices.pop()){ return true; }
		}
	}
	return false;
}

//vimeo 업로드
function vimeoUpload(param, file, callback){
	/* 진행률 표시 */
//	updateVimeoUploadProgress(0);
//	document.getElementById('progress-container').style.display = 'block';
	var loadingMsg = "Vimeo 업로드 중...";
	parent.LoadingStart(loadingMsg);

	/* Vimeo 업로더 인스턴스화 */
	(new VimeoUpload({
		name: param.name,
		description: param.description,
		file: file,
		token: param.token,
		onError: function(data) {
			alert("저장에 실패했습니다.["+JSON.parse(data).error+"]");
			parent.LoadingEnd();
		},
		onProgress: function(data) {
			var progress = updateVimeoUploadProgress(data.loaded / data.total);
			parent.LoadingStart(loadingMsg + progress+'%');
		},
		onComplete: function(videoId, index) {
			var url = 'https://vimeo.com/' + videoId;
			var flag = false;
			var ret;
			if (index > -1) {
				loadingMsg = "Vimeo 트랜스코딩 중...";
				parent.LoadingStart(loadingMsg);
				
				var ajaxUrl = 'https://vimeo.com/api/v2/video/'+videoId+'.json?transcode.status';
				var interval = setInterval(function(){//5초 단위로 VImeo api를 호출하여 동영상 트랜스코딩이 완료되었는지 확인
					$.ajax({
						url: ajaxUrl
						, dataType:"json"
						, async:false
					})
					.done(function (data) {
						console.log(data[0]);
						ret = data[0];
						if(ret.duration > 0){//
							flag = true;
						}
					});
					
					if(flag==true){
						if(callback){
							callback(ret, videoId);
						}else{
							alert("저장에 성공했습니다.");
							parent.LoadingEnd();
						}
						clearInterval(interval);
					}
				}, 5000 );
			}
		}
	})).upload();
}

// vimeo 업로드 진행률 업데이트
//<div id="progress-container" class="upload_wrap" style="display:none">
//	<div class="d_t">
//		<div class="d_c">
//			<div class="progress">
//				<div id="progress" class="progress-bar progress-bar-info progress-bar-striped active" role="progressbar" aria-valuenow="46" aria-valuemin="0" aria-valuemax="100" style="width: 0%">&nbsp;0%</div>
//			</div>	
//		</div>
//	</div>	
//</div>
function updateVimeoUploadProgress(progress) {
	progress = Math.floor(progress * 100);
//	var element = document.getElementById('progress');
//	element.setAttribute('style', 'width:' + progress + '%');
//	element.innerHTML = '&nbsp;' + progress + '%';
	return progress;
}

//vimeo 동영상 삭제
function vimeoDelete(param, callback){
	var loadingMsg = "Vimeo 삭제 중...";
	parent.LoadingStart(loadingMsg);

	(new VimeoUpload({
		vimeoCd: param.vimeoCd,
		token: param.token,
		onError: function(data) {
			alert("삭제에 실패했습니다.["+JSON.parse(data).error+"]");
		},
		onComplete: function(ret) {
			if(callback){
				callback(ret);
			}
		}
	})).delete();
}

//vimeo 동영상 폴더 이동
function vimeoMoveFolder(param, callback){
	(new VimeoUpload({
		vimeoCd: param.vimeoCd,
		token: param.token,
		folderId: param.folderId,
		onError: function(data) {
			alert("폴더 이동에 실패했습니다.["+JSON.parse(data).error+"]");
		},
		onComplete: function(ret) {
			if(callback){
				callback(ret);
			}
		}
	})).moveFolder();
}

//vimeo 동영상 도메인 설정
function vimeoEmbedPrivacy(param, callback){
	(new VimeoUpload({
		vimeoCd: param.vimeoCd,
		token: param.token,
		domain: param.domain,
		onError: function(data) {
			alert("삭제에 실패했습니다.["+JSON.parse(data).error+"]");
		},
		onComplete: function(ret) {
			if(callback){
				callback(ret);
			}
		}
	})).embedPrivacy();
}

//vimeo 동영상 privacy 설정
function vimeoDataPrivacy(param, callback){
	(new VimeoUpload({
		vimeoCd: param.vimeoCd,
		token: param.token,
		onError: function(data) {
			alert("업데이트에 실패했습니다.["+JSON.parse(data).error+"]");
		},
		onComplete: function(ret) {
			if(callback){
				callback(ret);
			}
		}
	})).updateVideoData();
}
