window["RctUtil"] = {
	renderTable : function(tbodyId, columns, data, onClick, paginationInfo, cdNm) { 
		const tbody = document.getElementById(tbodyId);
		if (!tbody) return;
		
		tbody.innerHTML = ""; // 기존 내용 초기화
		
		if(data.length === 0) {
			const tr = document.createElement("tr");
	        const td = document.createElement("td");
	        td.textContent = "검색조건에 맞는 데이터가 없습니다.";
	        td.colSpan = columns.length; 
	        td.style.textAlign = "center"; 
	        tr.appendChild(td);
	        tbody.appendChild(tr);
		} else {
			data.forEach((row, index) => { 
		 	const tr = document.createElement("tr");
		  
	        const rowId = row[cdNm];
	        if (rowId !== undefined) {
	            tr.setAttribute("data-id", rowId);
	        } 
		
		  	if (typeof onClick === "function") {
				tr.addEventListener("click", (event, param) => onClick(event, param));
		  	}
		
		 	columns.forEach((col, i) => {
				if (col.type === "hidden") {
					return;
				} 
				const td = document.createElement("td");
				if(col.type === "checkbox") {
					if(col.value === null || col.value === undefined) {
						const check = document.createElement("input");
						check.type = "checkbox";
						check.className = "row-checkbox";
						
						td.appendChild(check);
					} else {
						td.textContent = row[col.name] ?? "";
					}
				} else if( col.type === "button") {
					// 버튼 표시 조건 확인
					if (col.showCondition && !col.showCondition(row)) {
						td.textContent = ""; // 조건에 맞지 않으면 빈 셀 표시
					} else {
						const btn = document.createElement("button");
						btn.className = "n_btn btn_md btn_c02";
						btn.textContent = col.btnText; 
					    btn.setAttribute("data-url", col.btnUrl); 
					    btn.setAttribute("data-action", col.dataAction);
					    btn.setAttribute("id", col.id);
					    btn.onclick = col.event;
									
						td.appendChild(btn);
					}
				} else if (col.html != null) {
					td.innerHTML = col.html;
				} else {
				  td.textContent = row[col.name] ?? "";
				  td.setAttribute("data-field", col.dataField);
				}
				tr.appendChild(td);
			  }); 
			  tbody.appendChild(tr);
			}); 
		} 
		
		// 체크박스 처리
		const table = tbody.parentElement;
		this.handleCheckboxes("#" + table.id);
		
		//페이징 처리
		if (paginationInfo) {
			this.renderPagination(paginationInfo);
		}
	},
	insertRow : function(columns, tableSelector) {
		const $tr = $('<tr/>');
	    columns.forEach(col => {
	      const $td = $('<td/>');
	
		  if(col.type === "checkbox" && col.value == null){
				const $check = $('<input/>', {
					type: 'checkbox',
					class: 'row-checkbox'
				});
				$td.append($check);
				$tr.append($td);
				return;
		  }
	      if (col.type === 'select') {
	        const $select = $('<select/>', {
	          type: col.type,
	          name: col.name || '',
	          class: col.class || '',
	          value: col.value || '',	
	        });
	        col.options.forEach(opt => {
	          $select.append($('<option/>').val(opt).text(opt));
	        });
	        $td.append($select);
	      } else if(col.type === "") {
			$td.append('&nbsp;')
		  } else if(col.type === 'textarea') {
			const $textarea = $('<textarea/>').attr('name', col.name);
			$td.append($textarea);
		  } else if(col.button === true) {
			const $button = $('<button />', {
				type: col.type,
	        	name: col.name || '',
	        	class: col.class || '',
	        	value: col.value || '',	
	        	'data-action' : col.action || '',
			})
			const $i = $('<i/>', {
				class: col.iclass || '', 
			});
			$button.append($i);
			$td.append($button);
		  }else {
	        const $input = $('<input/>', {
	          type: col.type,
	          name: col.name || '',
	          class: col.class || '',
	          value: col.value || '',	
	          readonly: col.readonly && true    
	        });
	        $td.append($input);
	        if(col.addButton === true) {
				const $button = $('<button/>', {
				  type: "button",
				  'data-url': col.url
	        	});
				const $i = $('<i/>', {
					class: col.iclass || '', 
				});
				$button.append($i);
				$td.append($button);
			}
	      }
	      $tr.append($td);
	    });
	
	    $(tableSelector + ' tbody').prepend($tr);
	    
	    // 테이블에 체크박스 기능 적용
	    this.handleCheckboxes(tableSelector);
	},
  	deleteSelectedRows: function(tableSelector) {
  	    const selectedRows = $(`${tableSelector} .row-checkbox:checked`).closest('tr');
  	    
  	    if (selectedRows.length === 0) {
  	        alert("삭제할 항목을 선택해주세요.");
  	        return false;
  	    }
  	    
  	    if (!confirm("선택한 항목을 삭제하시겠습니까?")) return false;
  	    
  	    // 선택된 행 삭제
  	    selectedRows.remove();
  	    
  	    // 테이블 체크박스 상태 업데이트
  	   this.handleCheckboxes(tableSelector);
  	    
  	    return true;
  	},
	getTableData: function(tableSelector) {
	 const row = {};
	 const $firstRow = $(tableSelector+ ' tbody tr').first();
	 let isValid = true;
	 
	 $firstRow.find('td').each(function () {
	    const input = $(this).find('input, select');
	    
	    if(input.val() === null || input.val() === '') {
			alert(input.attr('name') + "입력 필요")
			isValid = false;
			return false;
		}
	    let value;
	    if (input.attr('type') === 'checkbox') {
	      value = input.prop('checked') ? 'N' : 'Y';
	    } else {
	      value = input.val();
	    }
	    
	
	    if (input.length > 0) {
	      const name = input.attr('name');
	      if (name) row[name] = value;
	    }
	  });
	
	  if(!isValid){
		return isValid;
	  }
	  return row;
	},
	getTableDataList: function(tableSelector) {
		
	  const data = [];
	
	  $(tableSelector+ ' tbody tr').each(function () {
	    const row = {};
	    $(this).find('input').each(function () {
	      const name = $(this).attr('name');
	      if (!name) return;
	
	      let value;
	      if ($(this).attr('type') === 'checkbox') {
	        value = $(this).prop('checked') ? 'N' : 'Y';
	      } else {
	        value = $(this).val();
	      }
	
	      row[name] = value;
	    });
	
	    data.push(row);
	  });
	
	  return data;
	},
	renderPagination : function(paginationInfo) { 
	    let html = '';  
	    
	    /*처음|이전*/
	    if ( paginationInfo.currentPageNo > 1) {
	        html += `<a href="#" class="page-first" data-page="1">처음</a>`;
	        html += `<a href="#" class="page-prev" data-page="${ paginationInfo.currentPageNo - 1}">이전</a>`;
	    }  
	
		/*페이지번호 링크*/
	    for (let i = paginationInfo.firstPageNoOnPageList; i <= paginationInfo.lastPageNoOnPageList; i++) {
	        html += `<a href="#" class="page-link ${i === paginationInfo.currentPageNo ? 'active' : ''}" data-page="${i}">${i}</a>`;
	    } 
	    
	    /*다음|마지막 항목*/
	    if (paginationInfo.currentPageNo  < paginationInfo.totalPageCount) {
			 html += `<a href="#" class="page-next" data-page="${paginationInfo.currentPageNo + 1}">다음</a>`;
			 html += `<a href="#" class="page-end" data-page="${paginationInfo.totalPageCount}">마지막</a>`;
		}
	
	    $('.x_pagination').html(html);
	},
	handleCheckboxes : function(tableSelector) {
		const table = document.querySelector(tableSelector);
		if (!table) return;
		
		const headerCheckbox = table.querySelector('thead th input[type="checkbox"]');
		
		if (headerCheckbox) {
			// 기존 이벤트 리스너 제거 (중복 방지)
			headerCheckbox.removeEventListener('change', this._headerCheckboxHandler);
			table.removeEventListener('change', this._rowCheckboxHandler);
			
			// 헤더 체크박스 이벤트
			this._headerCheckboxHandler = function() {
				table.querySelectorAll('tbody .row-checkbox').forEach(checkbox => {
					checkbox.checked = this.checked;
				});
			};
			
			// 행 체크박스 이벤트
			this._rowCheckboxHandler = function(e) {
				if (e.target && e.target.classList.contains('row-checkbox')) {
					const checkedCount = table.querySelectorAll('tbody .row-checkbox:checked').length;
					const totalCount = table.querySelectorAll('tbody .row-checkbox').length;
					headerCheckbox.checked = (totalCount > 0 && checkedCount === totalCount);
				}
			};
			
			headerCheckbox.addEventListener('change', this._headerCheckboxHandler);
			table.addEventListener('change', this._rowCheckboxHandler);
			
			// 초기 상태 설정
			const rowCheckboxes = table.querySelectorAll('tbody .row-checkbox');
			if (rowCheckboxes.length > 0) {
				const allChecked = Array.from(rowCheckboxes).every(cb => cb.checked);
				headerCheckbox.checked = allChecked;
			} else {
				headerCheckbox.checked = false;
			}
		}
	},
	loadPageData: function(url, params, cdNm, tbodyId, tbColNms, callback) { 
	    const ajax = TimsUtil.getObject(url, params, true);
	    const data = ajax.list;  
	    const paginationInfo = ajax.paginationInfo;  
	
	    this.renderTable(tbodyId, tbColNms, data, (event), paginationInfo, cdNm); 
	    
	    if (typeof callback === "function") {
	        callback();
	    } 
	},
	urlDialog: function (title, url, width, height) {
	    const $modal = $('#timsdlg');
	    const $dialog = $modal.find('.modal-dialog');
	
	    $dialog.css({
	      width: width + 'px',
	      height: height + 'px',
	      'max-width': '100%',
	    });
	
	    $('#dialogTitle').text(title);
	    $('#dialogBody').html('<p class="text-center">불러오는 중...</p>');
	
	    // Ajax로 JSP 내용 로드
	    $.ajax({
	      url: url,
	      method: 'GET',
	      success: function (data) {
	        $('#dialogBody').html(data);
	      },
	      error: function () {
	        $('#dialogBody').html('<p class="text-danger">내용을 불러오는 데 실패했습니다.</p>');
	      }
	    });
	
	    $modal.modal('show');
  	}
};