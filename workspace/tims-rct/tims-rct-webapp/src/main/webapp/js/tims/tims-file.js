//-----------------------------------
if(!window["TimsFile"])
{
	
	window["timsFiles"]={};
	window["TimsFile"] = function(){
		return {
			multiFiles        : true,
			autoUpload        : false,
			showControl       : true,
			popupProgress     : false,
			divFile           : '',
			fileIpt           : '',
			uploadUrl         : '',
			downloadUrl       : '',
			deleteUrl         : '',
			deleteCommitUrl   : '',
			autoDelete        : false,
			fileKey           : '',
			fileType          : '',
			fileMaxCnt        : 10,
			fileMaxSize       : 10*1024*1024*1024,
			fileMaxSingleSize : 10*1024*1024,
			fileDiv           : '000',
			divFileSlt        : null,
			fileMap           : {
				timsFile      : null,
				fileList      : [],
				fileArray     : [],
				delArray      : [],
				addFile       : function(data)
				{
					if(!data||!data.files||!data.files[0]||!data.files[0].name)
						return false;
					if(this.findFileByName(data.files[0].name))
						return false;
					this.fileArray.push(data);
					
					this.fileList = data.originalFiles;
					
					return true;
				},
				fundFileByGuid:function(guid)
				{
					for(var i=0;i<this.fileArray.length;i++)
					{
						if(guid == this.fileArray[i].guid)
							return this.fileArray[i];
					}
					return null;
				},
				findFileByName : function(name)
				{
					for(var i=0;i<this.fileArray.length;i++)
					{
						if(name == this.fileArray[i].files[0].name)
							return this.fileArray[i];
					}
					return null;
				},
				find:function(data)
				{
					for(var i=0;i<this.fileArray.length;i++)
					{
						if(data == this.fileArray[i])
							return i
					}
					return -1;
				},
				removeDeleted:function(data)
				{
					for(var i=0;i<this.delArray.length;i++)
					{
						if(data == this.delArray[i])
						{
							this.delArray.splice(i,1);
							return;
						}
					}
				},
				removeFile:function(data)
				{
					var index =this.find(data);
					if(index<0)
						return false;
					if(data.upFile)
						this.delArray.push(data);
					this.fileArray.splice(index,1);
					return true;
					
				},
				getLength:function()
				{
					return this.fileArray.length;
				},
				getUploadedCount:function()
				{
					var nCnt = 0;
					for(var i=0;i<this.fileArray.length;i++)
					{
						var data = this.fileArray[i];
						if(data.uploaded)
							nCnt++;
					}
					return nCnt;
				},
				getUploadCount:function()
				{
					var nCnt = 0;
					for(var i=0;i<this.fileArray.length;i++)
					{
						var data = this.fileArray[i];
						if(data.fromServer)
							continue;
						nCnt++;
					}
					return nCnt;
				},
				getTotalUploadedSize:function(){
					var nSize = 0;
					for(var i=0;i<this.fileArray.length;i++)
					{
						var data = this.fileArray[i];
						if(data.hasError)
							nSize+= 0;
						else if(data._progress)
						{
							var n = Number(data._progress.loaded);
							if(n)
								nSize+= n;
						}
						else
							nSize+= 0;
						//nSize+= Number(this.fileArray[i]);
					}
					return nSize;
				},
				getTotalUploadSize:function(){
					var nSize = 0;
					for(var i=0;i<this.fileArray.length;i++)
					{
						if(this.fileArray[i].fromServer)
							continue;
						nSize+= Number(this.fileArray[i].files[0].size);
					}
					return nSize;
				},
				getTotalFileSize:function()
				{
					var nSize = 0;
					for(var i=0;i<this.fileArray.length;i++)
					{
						nSize+= Number(this.fileArray[i].files[0].size);
					}
					return nSize;
				},
				needUpload:function()
				{
					for(var i=0;i<this.fileArray.length;i++)
					{
						if(!this.fileArray[i].upFile )
							return true;
					}
					return false;
				},
				getDeletedList:function()
				{
					return this.delArray;
				},
				getDeletedFiles:function()
				{
					var arr = [];
					for(var i=0;i<this.delArray.length;i++)
					{
						var obj ={
							fileSeq : this.delArray[i].upFile.fileSeq,
							fileKey : this.delArray[i].upFile.uploadFileCd
						};
						arr.push(obj);
					}
					return arr;
				},
				clearDeletedFiles:function()
				{
					this.delArray=[];
				}
			},
			commitDeletedFiles:function(url)
			{
				url = url  || this.deleteCommitUrl;
				if(!url)
					return;
				var files = this.getDeletedFiles();
				if(!files || !files.length)
					return true;
				var nResult = TimsUtil.getObject(url,files,true,null);
				if(nResult == null)		
					return false;
				this.clearDeletedFiles();
				return true;
			},
			needUpload : function(){
				return this.fileMap.needUpload();
			},
			triggerAddFile : function(){
				this.divFileSlt.find("input[type=file][name=files]").click();
			},
			addFile		: function(data){
				
				if(!data)
					return this.triggerAddFile();
				var ret =  this.fileMap.addFile(data);
				this.enableButtons();
				this.setInfomations();
				return ret;
				
			},
			removeFile :function(guid)
			{
				var data = this.findFile(guid);
				if(!data)
					return;
				this.fileMap.removeFile(data);
				if(this.deleteUrl && (this.autoDelete || !data.fromServer))
				{
					var url = this.deleteUrl+data.upFile.fileSeq
					var nResult = TimsUtil.getObject(url,{_method:"DELETE"});
					if(nResult!=null)
					{
						this.fileMap.removeDeleted(data);
					}
				}
				jQuery("tr[guid="+guid+"]").remove();
				this.enableButtons();
				this.setInfomations();
			},
			getDeletedFiles:function()
			{
				return this.fileMap.getDeletedFiles();
			},
			clearDeletedFiles:function()
			{
				return this.fileMap.clearDeletedFiles();
			},
			findFile	: function(guid)
			{
				return this.fileMap.fundFileByGuid(guid); 
			},
			getTotalUploadedSize:function()
			{
				return this.fileMap.getTotalUploadedSize();
			},
			getTotalUploadSize:function()
			{
				return this.fileMap.getTotalUploadSize();
			},
			getTotalFileSize:function()
			{
				return this.fileMap.getTotalFileSize();
			},
			getUploadedCount:function()
			{
				return this.fileMap.getUploadedCount();
			},
			getUploadCount:function()
			{
				return this.fileMap.getUploadCount();
			},
			
			getFileCount:function()
			{
				return this.fileMap.getLength();
			},
			setPreview:function(data)
			{
				if(data.files[0].type.indexOf("image")<0)
					return;
				var img = this.divFileSlt.find("span.preview[guid="+data.guid+"]");
				var height = 60;
				
				
				if(data.upFile)
				{
					if(data.upFile.uploadFileCd && data.upFile.fileSeq){
						var src = svnmFileUpload+"/thumbnail?fileCode="+data.upFile.uploadFileCd+"&fileSeq="+data.upFile.fileSeq;
						var html = '<img height="'+height+'" src="'+src+'" alt="preview"  height="100%" />';
						img.html(html);
					}

				}
				else
				{
					var html = '<img height="'+height+'" src="#" alt="preview"  height="100%" />';
					img.html(html);
					var reader = new FileReader();
			        reader.onload = function (e) {
			            img.find("img").attr('src', e.target.result);
			        }
	        		reader.readAsDataURL(data.files[0]);
				}
				
			},
			setInfomations:function(){
				var info= this.divFileSlt.find("[data-type=tims-info]");
				var popInfo = $("#dlgTimsFile").find("[data-type=tims-info]");
				var up= this.getTotalUploadedSize();
				var tot = this.getTotalUploadSize();
				var per = !tot || !up ? 0  :  parseInt(up*100/tot);
				var size = this.getTotalFileSize();
				var html = "총 " + TimsUtil.getReadableFileSizeString(size) + " | " 
						 + "총 " + this.getFileCount() + " 파일 | "
						 + TimsUtil.getReadableFileSizeString(up)
						 + " / "
						 + TimsUtil.getReadableFileSizeString(tot)
						 + " | "
						 + this.getUploadedCount() 
						 +" file(s) / "
						 + this.getUploadCount()					 
						 +" file(s) | "
						 + per
						 +" %";
				var orgHtml = info.html();
				if(orgHtml!=html)
				{
					info.html(html);
					popInfo.html(html);
					var gp = this.divFileSlt.find("[data-type=global-progress]");
					var popgp =  $("#dlgTimsFile").find("[data-type=global-progress]");
					if(gp.attr("prevProgress")!=per.toString())
					{
						gp.html(per+"%");
						gp.css("width",per+"%");
						gp.attr("prevProgress",per.toString());
						
						popgp.html(per+"%");
						popgp.css("width",per+"%");
						popgp.attr("prevProgress",per.toString());
						
					}	
					
				}
				
				
			},
			enableButtons : function(bEnabled)
			{
				if(typeof bEnabled ==="undefined")
					bEnabled = this.needUpload();
				if(bEnabled)
				{
					this.divFileSlt.find("button[data-type=submitAll]").removeClass("disabled");
					this.divFileSlt.find("button[data-type=cancelAll]").removeClass("disabled");
					
				}
				else
				{
					this.divFileSlt.find("button[data-type=submitAll]").addClass("disabled");
					this.divFileSlt.find("button[data-type=cancelAll]").addClass("disabled");
				}
				if(this.getFileCount()>0)
				{
					this.divFileSlt.find("button[data-type=deleteAll]").removeClass("disabled");
				}
				else
				{
					this.divFileSlt.find("button[data-type=deleteAll]").addClass("disabled");
				}
			},
			getBodyTemplate : function(data)
			{
				var bEditMode = !this.viewMode;
				var uldFile     = data.files[0];
				var uldFileName = uldFile.name;
				var bTemp = !data.upFile;	
				var html = '';
				html+='<tr class="template-upload" guid="'+data.guid+'">';
				html+='    <td>';
				html+='		<span class="preview" guid="'+data.guid+'" ></span>';
				html+='    </td>';
				html+='    <td>';
				html+='        <p class="name">'+uldFileName+'</p>';
				html+='        <strong data-type="error-report" guid="'+data.guid+'" class="error text-danger"></strong>';
				html+='    </td>';
				html+='    <td>';
				
				html+='        <p class="size">'+TimsUtil.getReadableFileSizeString(Number(uldFile.size))+'</p>';
				if(bTemp){
					html+='		<div class="progress">';
					html+='			<div class="progress-bar progress-bar-striped active" name="fileProgress" guid="'+data.guid+'"  role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width:0%">';
					html+='			</div>';
					html+='		</div>';
				}
				html+='    </td>';
				html+='    <td style="vertical-align: middle;">';
				if(bTemp){
//					html+='		<button guid="'+data.guid+'" file-action = "start" class="btn btn-xs btn-primary start" >';
//					html+='		<i class="glyphicon glyphicon-upload"></i>';
//					html+='		<span>전송</span>';
//					html+='   </button>';
//					html+='   <button guid="'+data.guid+'" file-action = "cancel" class="btn btn-xs btn-warning cancel disabled">';
//					html+='       <i class="glyphicon glyphicon-ban-circle"></i>';
//					html+='       <span>취소</span>';
//					html+='   </button>';
				}
				else if(!bEditMode)
				{
					html+='   <button guid="'+data.guid+'" file-action = "download" class="btn btn-xs btn-default download">';
					html+='       <i class="glyphicon glyphicon-circle-arrow-down"></i>';
					html+='       <span>다운로드</span>';
					html+='   </button>';
				}
				if(bEditMode){
					html+='   <button guid="'+data.guid+'" file-action = "delete" class="btn btn-xs btn-danger delete">';
					html+='       <i class="glyphicon glyphicon-trash"></i>';
					html+='       <span>삭제</span>';
					html+='   </button>';
				}
				html+='    </td>';
				html+='</tr>';
				return html;
			},
			getHeadTemplate : function()
			{
				var bEditMode = !this.viewMode;
				var cntInfo = this.fileMaxCnt? '최대'+this.fileMaxCnt +'개 파일 '  : "";
				var sizeInfo =this.fileMaxSize? TimsUtil.getReadableFileSizeString(this.fileMaxSize ) : "";
				var fileInfo = "";
				if(cntInfo || sizeInfo)
					fileInfo = '('+cntInfo+' '+sizeInfo+')';
				
				var sHtml = '<div class="panel panel-default">';
				sHtml+=		'<div class="panel-heading">';
				sHtml+=		'첨부파일';
				if(bEditMode)
					sHtml+=		fileInfo;
				sHtml+=		'</div>';
				sHtml+=		'<div class="panel-collapse">';
				var mtf = this.multiFiles? 'multiple="multiple"':'';
				if(bEditMode){
					var accept = "";

					var acceptList = this.accpetFileTypes&&this.accpetFileTypes!="*.*"?this.accpetFileTypes.split("|"):null;

					for(var i=0;acceptList&&i<acceptList.length;i++)
					{
						if(accept.length)
							accept+=",";
						accept+="."+acceptList[i];
					}
					
					if(this.fileTypes)
					{
						if(accept.length)
							accept+=",";
						accept += this.fileTypes;
					}
					if(accept.length)
					{
						accept = 'accept="'+accept+'"';
					}
					
					
					
					sHtml+=		'	<div class="panel-body">';
					sHtml+=		'		<div class="row fileupload-buttonbar">';
			        sHtml+=		'  		 <div class="col-lg-7">';
			        sHtml+=		'       		<span class="btn btn-sm btn-success fileinput-button" id="btn_fileAdd">';
			        sHtml+=		'           		 <i class="glyphicon glyphicon-plus"></i>';
			        sHtml+=		'           		 <span>파일추가</span>';
			    	sHtml+=		'					  <input type="file" id="fileInput" name="files" '+accept+' '+mtf+'/>';
			        sHtml+=		'        		</span>';
//			        sHtml+=		'        		<button data-type="submitAll" class="btn btn-sm btn-primary start disabled">';
//			        sHtml+=		'            		<i class="glyphicon glyphicon-upload"></i>';
//			        sHtml+=		'           		 <span>전체전송</span>';
//			        sHtml+=		'        		</button>';
//			        sHtml+=		'        		<button data-type="cancelAll" class="btn btn-sm btn-warning cancel disabled">';
//			        sHtml+=		'            		<i class="glyphicon glyphicon-ban-circle"></i>';
//			        sHtml+=		'           		<span>전체취소</span>';
//			        sHtml+=		'       		</button>';
			        sHtml+=		'        		<button data-type="deleteAll" class="btn btn-sm btn-danger delete disabled">';
			        sHtml+=		'            		<i class="glyphicon glyphicon-trash"></i>';
			        sHtml+=		'            		<span>전체삭제</span>';
			        sHtml+=		'        		</button>';
			        sHtml+=		'        		<span class="fileupload-loading"></span>';
			        sHtml+=		'    		</div>';
			        sHtml+=		'    		<div class="col-lg-5 fileupload-progress" style="float: right;">';
			        sHtml+=		'        		<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">';
			        sHtml+=		'            		<div class="progress-bar progress-bar-success" style="width:0%;" data-type="global-progress"></div>';
			        sHtml+=		'        		</div>';
			        sHtml+=		'        		<div class="progress-extended" data-type="tims-info" >0 KB / 0 KB | 0 file(s) /0 file(s) | 0 % </div>';
			        sHtml+=		'		</div>';
			        sHtml+=		'	</div>';
				}
		        sHtml+=		'	<div class="table-responsive">';
		        sHtml+=		'	<table role="presentation" class="table table-striped" width:100%>';
		        sHtml+=		'	<colgroup><col width="10%"/><col width="30%"/><col width="30%"/><col width="30%"/></colgroup>';
		        sHtml+=		'	<tbody class="files"></tbody>';
		        sHtml+=		'	</table></div>';
				sHtml+=		'</div>';
				return sHtml;
			},
			getPopupTemplate : function(){
				var sHtml = '';
				
					sHtml+=		'		<div class="row fileupload-buttonbar">';
			        sHtml+=		'    		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 fileupload-progress">';
			        sHtml+=		'        		<div class="progress progress-striped active "  role="progressbar" aria-valuemin="0" aria-valuemax="100">';
			        sHtml+=		'            		<div class="progress-bar progress-bar-success" style="width:0%;" data-type="global-progress"></div>';
			        sHtml+=		'        		</div>';
			        sHtml+=		'        		<div class="progress-extended" data-type="tims-info" >0 KB / 0 KB | 0 file(s) /0 file(s) | 0 % </div>';
			        sHtml+=		'			</div>';
					sHtml+=		'		</div>';
				var html = 	 '<div id="dlgTimsFile" class="modal fade " tabindex="-1" role="dialog" aria-hidden="true">'
			                +'    <div class="modal-dialog modal-md">'
			                +'      <div class="modal-content">'
			                +'        <div class="modal-header">'
//			                +'          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"><i class="fas fa-times"></i></span>'
							+'          <button type="button" class="close" data-dismiss="modal"><i class="fas fa-times"></i>'
			                +'          </button>'
			                +'          <h4 class="modal-title" id="myModalLabel">파일전송</h4>'
			                +'        </div>'							
			                +'        <div class="modal-body">'
							+'       	 <div class="px-content modal_content">'							
							+				sHtml
							+'         		<div class="btn_bottom_group"><button type="button" class="n_btn btn_md btn_c04" id="cancelAllUpload">전송취소</button></div>'
							+'      	  </div>'
			                +'        </div>'
			                +'      </div>'
			                +'    </div>'
			                +'  </div>';
				return html;
			},
			showPopupProgress :function(){
				if(this.popupProgress){
					window["currentTimsFile"] = this;
					$("#dlgTimsFile").modal("show");
				}
			},
			hidePopupProgress :function(){
				if(this.popupProgress){
					$("#dlgTimsFile").modal("hide");
				}
			},
			uploadAll : function()
			{
				if(this.popupProgress)
					this.showPopupProgress();
				var timsFile = this;
				var divFileSlt = jQuery('#'+this.divFile);
				var tr = divFileSlt.find("tr[guid]");
				timsFile.results=[];
				tr.each(function(){
					var guid = jQuery(this).attr("guid");
					timsFile.uploadFile(guid);
				});
			},
			cancelAll : function(){
				var timsFile = this;
				timsFile.results=[];
				var divFileSlt = jQuery('#'+this.divFile);
				var tr = divFileSlt.find("tr[guid]");
				tr.each(function(){
					var guid = jQuery(this).attr("guid");
					timsFile.cancelUpload(guid);
				});
			},
			deleteAll : function(){
				var timsFile = this;
				timsFile.results=[];
				var divFileSlt = jQuery('#'+this.divFile);		
				var tr = divFileSlt.find("tr[guid]");
				tr.each(function(){
					var guid = jQuery(this).attr("guid");
					timsFile.deleteUpload(guid);
				});
			},
			downloadFile: function(guid){
				var data = this.findFile(guid);
				if(!data||!data.upFile)
					return;
				var ifm = this.divFileSlt.find("iframe");
				if(ifm.length==0)
				{
					this.divFileSlt.append('<iframe style="display:none" ></iframe>');
					ifm = this.divFileSlt.find("iframe");
				}
				ifm.attr("src",this.downloadUrl+data.upFile.fileSeq);
				
			},
			uploadFile :function(guid)
			{
				var timsFile=this;
				var data = this.findFile(guid);
				if(!data)
					return;
				if(data.uploaded||data.upFile || data.fromServer)
					return;
				data.errorHandled=true;
				var divFileSlt = jQuery('#'+this.divFile);		
				var btn = divFileSlt.find("button[file-action=start][guid="+guid+"]");
				if(btn.attr("uploading"))
					return;
				var btnCancel = divFileSlt.find("button[file-action=cancel][guid="+guid+"]");
				var btnDelete = divFileSlt.find("button[file-action=delete][guid="+guid+"]");
				var prgrsSlt = divFileSlt.find('[name=fileProgress][guid='+guid+']');
				btn.attr("uploading","true");
				btn.addClass("disabled");
				btnDelete.addClass("disabled");
				btnCancel.removeClass("disabled");	
				data.hasError=false;
				data.jqXHR=data.submit()
				.success(function (result, textStatus, jqXHR) {
//					console.log(result.files[0]);
//					result.files[0]
					
					if(timsFile.success)
					{
						if(window[timsFile.success])
						{
							try{window[timsFile.success](timsFile,result);}catch(e){}
						}
					}
					timsFile.results.push(result);
//					console.log("success");
					divFileSlt.find("[data-type=error-report][guid="+guid+"]").html("전송완료");
					prgrsSlt.css('width', '100%');
					prgrsSlt.html("100%");

					data.uploaded=true;
					data.hasError=false;
					if(result.files&&result.files.length)
						data.upFile = result.files[0];
				})
	            .error(function (jqXHR, textStatus, errorThrown) {
//	            	console.log("error");
	            	btn.removeClass("disabled");
	            	
	            	btnCancel.addClass("disabled");
	            	var statusText = textStatus=="abort" ? "전송취소":"전송실패";
	            	prgrsSlt.html(statusText);
	            	prgrsSlt.css('width', '0%');
	            	divFileSlt.find("[data-type=error-report][guid="+guid+"]").html(statusText);
	            	data.hasError=true;
	            	timsFile.results=[];
	            	
	            	if(jqXHR.responseJSON.error){
//	            		alert("업로드 중 문제가 발생했습니다." + jqXHR.responseJSON.message);
	            		alert(jqXHR.responseJSON.message);
	            		timsFile.hidePopupProgress();
	            	}
	            	
				})
	            .complete(function (result, textStatus, jqXHR) {
	            	prgrsSlt.removeClass("active");
					btn.attr("uploading","");	
					data.jqXHR = null;
					btnDelete.removeClass("disabled");
					
					timsFile.setInfomations();
					
					var uCnt = timsFile.getUploadedCount();
					var tCnt = timsFile.getUploadCount();
					var up= timsFile.getTotalUploadedSize();
					var tot = timsFile.getTotalUploadSize();
					if(up&&up==tot){
						timsFile.hidePopupProgress();
					}
					if(up&&uCnt==tCnt&&window[timsFile.successAll]&&timsFile.results.length)
					{
						if(timsFile.successAll){
							window[timsFile.successAll](timsFile,timsFile.results);	
						}
					}
					if(uCnt==tCnt)
						timsFile.results=[];
					
					if(timsFile.autoDelete)
						timsFile.removeFile(data.guid);
	            });
			},
			cancelUpload:function(guid){
				
//				console.log("cancel upload");
				if(jQuery("button[file-action=start][guid="+guid+"]").attr("uploading"))
				{
					var data = this.findFile(guid);
					if(!data)
						return;
					if(data.uploaded)
						return;
					if(data.jqXHR)
						data.jqXHR.abort();
				}
				else
				{
					
				}
			},
			deleteUpload:function(guid){
				if(jQuery("button[file-action=start][guid="+guid+"]").attr("uploading"))
				{
					return;
					
//					var data = this.findFile(guid);
//					if(!data)
//						return;
//					if(data.jqXHR)
//						data.jqXHR.abort();
				}
				else
				{
					this.removeFile(guid);
				}
			},
			addTempFile:function(data){
				var timsFile = this;
				data.guid=TimsUtil.guid();
					if(!this.addFile(data))
					{
						return ;
					}	
					var tbody = this.divFileSlt.find("tbody.files");
					html = this.getBodyTemplate(data);					
					tbody.append(html);
					this.divFileSlt.find("button[file-action=start][guid]").each(function(idx){
						var hasStart = jQuery(this).attr("hasStart");
						if(!hasStart)
						{
							jQuery(this).attr("hasStart","true");
							jQuery(this).click(function(e){
								
								e.preventDefault();
								if(jQuery(this).attr("uploading"))
									return;
								timsFile.uploadFile(jQuery(this).attr("guid"));
							});
						}
					});
					this.divFileSlt.find("button[file-action=cancel][guid]").each(function(idx){
						var hasCancel = jQuery(this).attr("hasCancel");
						if(!hasCancel)
						{
							jQuery(this).attr("hasCancel","true");
							jQuery(this).click(function(e){
								e.preventDefault();
								timsFile.cancelUpload(jQuery(this).attr("guid"));
								
							});
						}
					});
					this.divFileSlt.find("button[file-action=delete][guid]").each(function(idx){
						var hasDelete = jQuery(this).attr("hasDelete");
						if(!hasDelete)
						{
							jQuery(this).attr("hasDelete","true");
							jQuery(this).click(function(e){
								e.preventDefault();
								timsFile.deleteUpload(jQuery(this).attr("guid"));
								
							});
						}
					});
//					this.setPreview(data);
			},
			initUI : function()
			{
				if(!this.showControl || this.showControl.toString() == "false")
					this.divFileSlt.css("display","none");
				
				if(!$("#dlgTimsFile").length){
					var html = this.getPopupTemplate();
					$(document.body).append(html);
					
					//2018.03.14  사용자단에 디자인 없음. showControl = false로 사용할것
					if(!this.showControl || this.showControl.toString() == "false")
						$("#dlgTimsFile").hide();
					
					$("#dlgTimsFile").draggable({
						handle: ".modal-header"
					});
					$("#cancelAllUpload").click(function(){
						if(!window["currentTimsFile"])
							return;
						window["currentTimsFile"].cancelAll();
						window["currentTimsFile"].hidePopupProgress();
					});
				}
				
				var timsFile = this;
				sHtml = this.getHeadTemplate();
				this.divFileSlt.append(sHtml);

				this.enableButtons();
				
				
				this.divFileSlt.find("button[data-type=submitAll]").click(function(e){
					e.preventDefault();
					if(jQuery(this).hasClass("disabled"))
						return;
					timsFile.uploadAll();
				});
				this.divFileSlt.find("button[data-type=cancelAll]").click(function(e){
					e.preventDefault();
					if(jQuery(this).hasClass("disabled"))
						return;
					timsFile.cancelAll();
				});
				this.divFileSlt.find("button[data-type=deleteAll]").click(function(e){
					e.preventDefault();
					if(jQuery(this).hasClass("disabled"))
						return;
					timsFile.deleteAll();
				});
			},
			initUpload:function(){
				var timsFile = this;
				this.divFileSlt.find('#fileInput').fileupload({
				paramName: 'files',
			    formData : [{ name: 'fileKey', value: this.fileKey },
			                { name: 'fileDiv', value: this.fileDiv? this.fileDiv: "000"}],
			    previewMaxWidth: 60,	// default: 80
			    previewMaxHeight: 60, 	// default: 80
			    maxFileSize: this.fileMaxSize,
			    minFileSize: 0,
			    maxNumberOfFiles: this.fileMaxCnt,
			    fileKey : this.fileKey,
			    fileDiv : this.fileDiv? this.fileDiv: "000",
			    prependFiles: false,
    
//					url : url + "/tempUpload",
					url : this.uploadUrl + "",
					dataType : 'json',
				change: function (e, data) {
					var accept = timsFile.accpetFileTypes&&timsFile.accpetFileTypes!="*"?timsFile.accpetFileTypes.split("|"):null;
					var except  = timsFile.exceptFileTypes?timsFile.exceptFileTypes.split("|"):null;
						
					this.error = false;
					var cnt = timsFile.getFileCount()+ data.files.length;
					if(timsFile.fileMaxCnt>0&&cnt>timsFile.fileMaxCnt)
					{
						this.error=true;
						alert("최대 "+timsFile.fileMaxCnt + "개의 파일만 업로드 할 수 있습니다");
					}
					var totalSize = timsFile.getTotalFileSize();
					for(var i=0;i<data.files.length;i++)
					{
						var ext = data.files[i].name.split('.').pop().toLowerCase();
						var fSize = data.files[i].size;
						 if(timsFile.fileMaxSingleSize>0&& fSize>timsFile.fileMaxSingleSize)
						 {
							 this.error=true;
							 var fMsg =TimsUtil.getReadableFileSizeString(timsFile.fileMaxSingleSize);
							 alert("파일 하나당 최대 "+fMsg+ "까지 업로드 가능합니다.");
							 break;
						 }
						totalSize+=fSize;
						if(timsFile.fileMaxSize>0&& totalSize>timsFile.fileMaxSize)
						{
							 this.error=true;
							 var fMsg =TimsUtil.getReadableFileSizeString(timsFile.fileMaxSingleSize);
							 alert("최대 "+fMsg+ "까지 업로드 가능합니다.");
							 break;
						}
						if(accept&&accept.length){
							if(jQuery.inArray(ext, accept) == -1) {
								 this.error=true;
								 alert("허용되지 않은 파일 형식입니다.\n\n");
								 break;
							}

						}
						if(except){
							if(jQuery.inArray(ext, except) != -1) {
								 this.error=true;
								 alert("허용되지 않은 파일 형식입니다.");
								 break;
							}
						}
					}
			    },
				add : function(e, data) {
					if(this.error)
						return;
					data.url= timsFile.uploadUrl;
					timsFile.addTempFile(data);
					if(timsFile.autoUpload)
					{
//						timsFile.uploadFile(data.guid);
						timsFile.uploadAll();
					}

				},

				progress: function(e, data) {
					var guid = data.guid;
					var progress = parseInt(data.loaded / data.total * 100, 10);
					var prgrsSlt = timsFile.divFileSlt.find('[name=fileProgress][guid='+guid+']');
					var width = parseInt( prgrsSlt.css('width'));
					if(typeof this.progressStatus === "undefined")
						this.progressStatus = -1;
					if(progress == this.progressStatus)
						return;

//					console.log(progress + '%');
					this.progressStatus = progress;
					prgrsSlt.css('width', progress + '%');
					prgrsSlt.html(progress + '%');
					if(progress == 100){
						//prgrsSlt.fadeOut( "slow" );
					}
					timsFile.setInfomations();
				}
				}); 
			},
			loadFiles:function(){
//				var url = this.fileUrl;
				var fileKey = $('#'+this.fileIpt).val();
				if(!fileKey)
					return;
				var url = svnmFileUpload +'/selectFileList/'+fileKey;
				if(!url)
					return;
				//url, param, jsonBody,option
				var list = TimsUtil.getObject(url,null,true);
				if(!list || !list.length)
					return;
				this.loadServerFiles(list);
			},
			loadServerFiles : function (list){
				if(!list || !list.length)
					return;
				for(var i=0;i<list.length;i++)
				{
					this.loadServerFile(list[i]);
				}
			},
			loadServerFile : function(file){
//				var data = {files:[{name:file.name,size:file.size,type:file.contentType}],upFile:file};
				var data = {files:[{name:file.fileNm,size:file.fileSize,type:file.fileExtension}],upFile:file};
				data.fromServer=true;
				var timsFile = this;
				data.guid=file.fileSeq;
				if(!this.addFile(data))
				{
					return ;
				}	
				var tbody = this.divFileSlt.find("tbody.files");
				html = this.getBodyTemplate(data);					
				tbody.append(html);
				
				this.divFileSlt.find("button[file-action=delete][guid]").each(function(idx){
					var hasDelete = jQuery(this).attr("hasDelete");
					if(!hasDelete)
					{
						jQuery(this).attr("hasDelete","true");
						jQuery(this).click(function(e){
							e.preventDefault();
							timsFile.deleteUpload(jQuery(this).attr("guid"));
							
						});
					}
				});
				this.divFileSlt.find("button[file-action=download][guid]").each(function(idx){
					var hasDownload = jQuery(this).attr("hasDownload");
					if(!hasDownload)
					{
						jQuery(this).attr("hasDownload","true");
						jQuery(this).click(function(e){
							e.preventDefault();
							timsFile.downloadFile(jQuery(this).attr("guid"));
							
						});
					}
				});
//				this.setPreview(data);
					
					
					
					
				},
				showUI : function(){
					this.divFileSlt.show();
	
				},
				hideUI : function(){
					this.divFileSlt.hide();
	
				},

			init : function(option){
				var timsFile = this;
				this.fileMap.timsFile=timsFile;
				//TimsFile properties 초기화
				if(option)
					jQuery.extend(this, option);
				if(!this.fileMaxCnt)
					this.fileMaxCnt = 0;
				if(!this.fileMaxSize)
					this.fileMaxSize = 0;
				var url         = this.uploadUrl;
				var divFile     = this.divFile;
				var fileIpt     = this.fileIpt;
				var fileType    = this.fileType;
				var fileMaxCnt  = this.fileMaxCnt;
				var fileMaxSize = this.fileMaxSize;
				this.option = option;
				//divFile 체크
				var divFileSlt = jQuery('#'+divFile);
				if(divFile == '' || divFileSlt.length != 1 || divFileSlt.get(0).tagName != 'DIV'){ 
//					console.log('divFile Error! (not defined divFile)');
					return false;
				}
				window["timsFiles"][divFile]= this;
				this.divFileSlt = divFileSlt;
				if(!fileIpt)
				{
					fileIpt = divFile+"Hinput";
				}
				//fileIpt 체크
				var fileIptSlt = divFileSlt.find('#'+fileIpt);
				
				if(fileIptSlt.length != 1 || fileIptSlt.get(0).tagName != 'INPUT'){ 
					fileIptSlt = divFileSlt.append(
							'<input type="hidden" name="'+fileIpt+'" id="'+fileIpt+'" />'
							);
					fileIptSlt = divFileSlt.find('#'+fileIpt);
//					console.log('fileIpt Error! (not defined fileIpt)');
//					return false;
				} 

				//url 체크
				if(url == ''){
//					console.log('uploadUrl Error! (not defined uploadUrl)');
					return false;
				}
				this.id = this.divFile;
				this.initUI();
				this.initUpload();
				if(fileIpt){
					this.loadFiles();
				}
			},
			getFileList : function(){
				var fileList = [];
				var fileArray = this.fileMap.fileArray;
				for(var i=0;i<fileArray.length;i++){
					fileList.push(fileArray[i].upFile);
				}
				return fileList;
			}
		};
	};
	jQuery.fn.timsFile=function(){
		if(!this.length)
			return null;
		if(!window["timsFiles"])
			return null;
		var id= this.attr("id");
		if(id&&window["timsFiles"][id])
			return window["timsFiles"][id];
		var option = this.data();
		if(!option.divFile)
		{
			if(!jQuery(this).attr("id"))
			{
				jQuery(this).attr("id","timsFile"+idx);
			}
			option.divFile = jQuery(this).attr("id");
		}
		var gf = new TimsFile();
		gf.init(option);
		return gf;
		
	};
	jQuery(document).ready(function(){
		jQuery("[data-type='tims-file']").each(
		function(idx)
		{
			jQuery(this).timsFile();
		
		}
	)
		
	});
	
}