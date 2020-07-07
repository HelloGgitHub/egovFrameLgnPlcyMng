<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>차단 IP 목록</title>
<%@ include file="/WEB-INF/jsp/cmm/head.jsp" %>


<script type="text/javaScript" language="javascript" defer="defer">

var grdVal;

$(document).ready(function(){
	fn_Select();
});


/*********************************************************
 * 정책 목록 조회
 ******************************************************** */
function fn_Select(){
	
	$("#grd").empty();
	//API호출
	var rtnData = new Object();
	rtnData = fn_calApi("GET", "/lgDpIp/list", null, false);
	var arr = rtnData.list;
	
 	var ihtml = '';
 	ihtml = ihtml + '<table class="board_list" summary="그룹목록을 출력합니다.">';
 	ihtml = ihtml + '<colgroup><col style="width: 5%;"><col style="width: 3%;"><col style="width: ;"><col style="width: 30%;"><col style="width: 20%;"><col style="width: 15%;"></colgroup>';
 	ihtml = ihtml + '<thead>';
 	ihtml = ihtml + '<tr>';
 	ihtml = ihtml + '<th>번호</th>';
 	ihtml = ihtml + '<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택체크박스"></th>';
 	ihtml = ihtml + '<th class="board_th_link">IP</th>';
 	ihtml = ihtml + '<th>명칭</th>';
 	ihtml = ihtml + '<th>등록일시</th>';
 	ihtml = ihtml + '<th>등록자ID</th>';
 	ihtml = ihtml + '</tr>';
 	ihtml = ihtml + '</thead>';
 	ihtml = ihtml + '<tbody class="ov">';
	
	for(var i =0; arr.length > i; i++){
   	 	ihtml = ihtml + '<tr>';
   	 	ihtml = ihtml + '<td>' + (i+1) + '</td>';
   	 	ihtml = ihtml + '<td>';
   	 	ihtml = ihtml + '<input id="checkField" name="checkField" title="checkField" type="checkbox"/>';
   	 	ihtml = ihtml + '<input id="id_'+(i+1)+'" name="id_'+(i+1)+'" type="hidden" value="'+arr[i].blk_ip+'">';
   	 	ihtml = ihtml + '</td>';
   	 	ihtml = ihtml + '<td><a onclick="fn_SelectIp(\''+arr[i].blk_ip+'\')">'+arr[i].blk_ip+'</a></td>';
   	 	ihtml = ihtml + '<td id="blkIpNm_'+(i+1)+'" name="blkIpNm_'+(i+1)+'">'+arr[i].blk_ipnm+'</td>';
   	 	ihtml = ihtml + '<td id="addDt_'+(i+1)+'" name="addDt_'+(i+1)+'">'+arr[i].add_dt+'</td>';
   		ihtml = ihtml + '<td id="adduserid_'+(i+1)+'" name="adduserid_'+(i+1)+'">'+arr[i].add_usrid+'</td>';
	 	ihtml = ihtml + '</tr>';
    }

 	ihtml = ihtml + '</tbody>';
 	ihtml = ihtml + '</table>';
 	
	var grd = document.getElementById("grd");
	grd.innerHTML = ihtml;
}

/*********************************************************
 * 등록페이지 이동
 ******************************************************** */
function fn_SelectIp(dpIp){
	var pageType= "r";
	location.href=baseUrl + "/DpIPInfo?callType="+pageType+"&dpIp="+dpIp;
}

/*********************************************************
 * 정책 삭제
 ******************************************************** */
function fn_Delete(){
	var ckId = new Array();
	ckId = checkFieldck();
	
	for(var i=0; ckId.length > i; i++){
		var ckNum = ckId[i];
		
		var rtnData = new Object();
		var paramData = new Object();
		paramData.bkIp = $("#id_"+ckNum).val();

		//API호출
		rtnData = fn_calApi("DELETE", "/lgDpIp/delete", paramData, false);
	}
	fn_Select();
}


/*********************************************************
 * 체크된 row확인
 ******************************************************** */
function checkFieldck(){
	var rowData = new Array();
	var checkbox = $("input[name=checkField]:checked");
	
	checkbox.each(function(i) {
		var tr = checkbox.parent().parent().eq(i);
		var td = tr.children();
		var id = td.eq(0).text();
		rowData.push(id);
	});
	
	return rowData;
}


/*********************************************************
 * 정책 목록 조회
 ******************************************************** */
function fn_Add(){
	location.href=baseUrl+"/DpIPInfo?callType=c&dpIp=";
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////체크박스 전체 선택!!! 더 개발해야함.
function fncCheckAll(){
	var rowData = new Array();
	var checkbox = $("input[name=checkField]:checked");
	console.log(">>>"+checkbox.parent().parent().eq(1).value);
// 	checkbox.parent().parent().eq(i)
}

</script>
</head>

<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<div class="board">
	<h1>로그인 정책 목록</h1>
	<!-- 검색영역 -->
	<div class="search_box" title="이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다.">
		<ul>
<!-- 			<li>조건 -->
<!--                 <select name="searchCondition" id="searchCondition" title="조회조건 선택"> -->
<!--                     <option value="0"  >ID</option>ID  -->
<!--                     <option value="1" selected="selected" >이름</option>Name -->
<!--                 </select> -->
<!-- 			</li> -->
			<!-- 검색키워드 및 조회버튼 -->
			<li>
<!-- 				<input class="s_input" name="searchKeyword" type="text"  size="35" title="검색어 입력" value=''  maxlength="255" > -->
				<input type="button" class="s_btn" onClick="fn_Select();" 	value="조회" title="조회 버튼" />
				<input type="button" class="s_btn" onClick="fn_Delete();" 	value="삭제" title="삭제 버튼" />
				<input type="button" class="s_btn" onClick="fn_Add();" 		value="등록" title="등록 버튼" />
			</li>
		</ul>
	</div>

	<div id="grd"></div>
	<!-- paging navigation -->
	<div class="pagination">
		<ul>
			<li class="current"><a onClick="return false;">1</a></li>
		</ul>
	</div>
</div>
<input name="selectedId" type="hidden" />
<input name="checkedIdForDel" type="hidden" />
<input name="pageIndex" type="hidden" value="1"/>

</body>
</html>