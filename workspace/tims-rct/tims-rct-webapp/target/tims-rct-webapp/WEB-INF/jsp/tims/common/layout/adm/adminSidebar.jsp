<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div class="admin-container-wrap">	 
	<div class="admin-container"> 
	    <!-- 좌측: lrg-menu -->
	    <div class="lrg-sidebar"> 
		    <ul id="lrg-ul"></ul>
		    <ul id="sidebarMenuList"></ul>
		</div> 
	
	    <!-- 우측: mid | sm-menu -->
	    <div class="mid-sidebar-wrapper">
		    <div class="mid-sidebar"> 
		    	<div class="lrg-content">
		        </div> 
		    </div>
	    </div> 
	</div>
</div>

<script>
const iconMap = {
					RECRUITADM: "../../../images/common/gnb_icon01.png",
					SYSTEMADM: "../../../images/common/gnb_icon02.png"
				};
		
		
$(document).ready(function () {
	
	var sysCd = "${systemId}";
	var url = "${serviceBathPath}/menu/lrgMenu";
	var params = { 'sysMenuCd' : sysCd, 
				   'sysCd' : sysCd }; 
	
	var lrgMenuList = TimsUtil.getObject(url, params, true);  
	const lrgUl = document.getElementById("lrg-ul");   
	
	let html = '';
	lrgMenuList.forEach(menu => {
	    const li = document.createElement("li");
	    const a = document.createElement("a");
	    a.href = "#";
	    a.className = "lrg-menu-toggle";
	    a.dataset.target = menu.sysMenuCd;

	    const iconDiv = document.createElement("div");
	    iconDiv.className = "menu-icon";

	    const img = document.createElement("img");
	    img.src = iconMap[menu.sysMenuCd];
	    img.alt = menu.menuNm;
	    iconDiv.appendChild(img);

	    const labelDiv = document.createElement("div");
	    labelDiv.className = "menu-label";
	    labelDiv.textContent = menu.menuNm;

	    a.appendChild(iconDiv);
	    a.appendChild(labelDiv);
	    li.appendChild(a);
	    lrgUl.appendChild(li);
	}); 
	
	// 처음 로딩될 때 닫혀있는 상태 
	$('.mid-sidebar-wrapper').addClass('closed');
	
	let lastClickedBtn = null;
    // 큰 메뉴 클릭 시   
	  $('.lrg-menu-toggle').on('click', function (e) { 
	    e.preventDefault();
	    const targetId = $(this).data('target');
		var url = "${serviceBathPath}/menu/midMenu";
		var params = { 'sysMenuCd' : targetId, 
					   'sysCd' : sysCd };  
		
		var midMenuList = TimsUtil.getObject(url, params, true);   
		
		const div = document.querySelector(".lrg-content");
	    div.id = targetId;   
	    
	    // 기존 ul 요소가 있으면 삭제
	    const existingUl = div.querySelector("#mid-ul");
	    if (existingUl) {
	        existingUl.remove();  
	    }
	    
	    const midUl = document.createElement("ul");
	    midUl.id= "mid-ul";   
		
	    let html = '';
		midMenuList.forEach((menu) => {
		    const li = document.createElement("li");
		    li.className = "mid-menu";
		    li.id = menu.id;
		    const a = document.createElement("a");
		    a.href = "#";
		    a.className = "mid-menu-toggle";
		    a.dataset.target = menu.sysMenuCd;
		    a.textContent = menu.menuNm; 
		    
		    const smUl = document.createElement("ul"); 
		    smUl.id = "sm-ul";
		    
		    li.appendChild(a);
		    li.appendChild(smUl);
		    midUl.appendChild(li);
		}); 
		
		div.appendChild(midUl);
	    	
	    const $targetContent = $('#' + targetId); 
	    const $targetWrapper = $targetContent.closest('.mid-sidebar-wrapper');
	    const $pageWrapper = $('.page-wrapper'); 
	    const isSame = (lastClickedBtn === targetId);
	
	    if ($targetContent.hasClass('active') && isSame) {   	
	        $targetContent.removeClass('active').fadeOut(300);
	        $targetWrapper.addClass('closed');
	        $pageWrapper.removeClass('shifted');
	        
	        $('.page-header').css({
	            left: '100px',
	            width: 'calc(100% - 100px)'
	        });
	        
	        lastClickedBtn = null;
	        
	    } else {
	        $('.lrg-content').removeClass('active').hide()
	            .closest('.mid-sidebar-wrapper').addClass('closed');
	        $targetContent.addClass('active').fadeIn(300);
	        $targetWrapper.removeClass('closed');
	        $pageWrapper.addClass('shifted');
	         
	     // mid-sidebar 열 때 헤더 위치 변경
	        $('.page-header').css({
	            left: '300px',  
	            width: 'calc(100% - 300px)'
	        });
	     
	        lastClickedBtn = targetId;
	    }
	});

	let lastMidMenu = null;
    // 중간 메뉴 클릭 시 
    $(document).on('click', '.mid-menu-toggle', function (e) {
	    e.preventDefault();
        
        const targetId = $(this).data('target');  
		var url = "${serviceBathPath}/menu/smMenu";
		var params = { 'sysMenuCd' : targetId, 
					   'sysCd' : sysCd };  
		var smMenuList = TimsUtil.getObject(url, params, true);    
		
		 // 기존 ul 요소가 있으면 삭제
		const existingUls = document.querySelectorAll("#sm-ul"); // .sm-ul 클래스를 가진 모든 요소
		existingUls.forEach(ul => ul.remove()); // 각 요소를 하나씩 제거
	
	    const smUl = document.createElement("ul");
	    smUl.id = "sm-ul";
	    smUl.className = "sm-menu";
		
		smMenuList.forEach(menu => {
		    const li = document.createElement("li");
		    li.className = "sm-li";
		    const a = document.createElement("a");
		    a.href = "/service" + menu.menuUrl;
		    a.textContent = menu.menuNm;  
		    a.setAttribute("data-title", menu.menuNm);
		    
		    li.appendChild(a); 
		    smUl.appendChild(li);
		});  
		$(this).closest('.mid-menu').append(smUl);
		
        
		const $parent = $(this).closest('li[id].mid-menu');
	      
        $('li.mid-menu.open').not($parent).each(function () {
           $(this).removeClass('open');
           $(this).find('.sm-menu').stop(true, true).slideUp(300);
        });

        if ($parent.hasClass('open')) {
            $parent.removeClass('open');
            $parent.find('.sm-menu').stop(true, true).slideUp(300);
        } else {
            $parent.addClass('open');
            $parent.find('.sm-menu').stop(true, true).slideDown(300);
        }
    });
});



</script> 