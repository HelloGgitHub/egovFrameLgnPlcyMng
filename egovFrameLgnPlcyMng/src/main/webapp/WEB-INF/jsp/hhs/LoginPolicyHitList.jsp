<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>정책 목록</title>
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

	var userId = "";
	var plcyNm = "";
	if($("#searchCondition").val() == "1"){
		userId = $("#searchKeyword").val();
	}else if($("#searchCondition").val() == "2"){
		plcyNm = $("#searchKeyword").val();
	}
		
	$("#grd").empty();
	//API호출
	var rtnData = new Object();
	rtnData = fn_calApi("GET", "/lgHRgt/list?userId="+userId+"&plcyNm="+plcyNm, null, false);
	var arr = rtnData.list;
	
 	var ihtml = '';
 	ihtml = ihtml + '<table class="board_list" summary="정책별 적중 이력을 출력합니다.">';
 	ihtml = ihtml + '<colgroup><col style="width: 5%;"><col style="width: 3%;"><col style="width: 30%;"><col style="width: ;"><col style="width: 20%;"></colgroup>';
 	ihtml = ihtml + '<thead>';
 	ihtml = ihtml + '<tr>';
 	ihtml = ihtml + '<th>번호</th>';
 	ihtml = ihtml + '<th><input type="checkbox" name="checkAll" id="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택체크박스"></th>';
 	ihtml = ihtml + '<th class="board_th_link">사용자ID</th>';
 	ihtml = ihtml + '<th>정책명</th>';
 	ihtml = ihtml + '<th>적중횟수</th>';
 	ihtml = ihtml + '</tr>';
 	ihtml = ihtml + '</thead>';
 	ihtml = ihtml + '<tbody class="ov">';

	var cnt = 0;
	for(var i =0; arr.length > i; i++){
   	 	ihtml = ihtml + '<tr>';
   	 	ihtml = ihtml + '<td>' + (i+1) + '</td>';
   	 	ihtml = ihtml + '<td>';
   	 	ihtml = ihtml + '<input id="checkField" name="checkField" title="checkField" type="checkbox"/>';
   	 	ihtml = ihtml + '<input id="id_'+(i+1)+'" name="id_'+(i+1)+'" type="hidden" value="'+arr[i].usr_id+'">';
   		ihtml = ihtml + '<input id="plcyid_'+(i+1)+'" name="plcyid_'+(i+1)+'" type="hidden" value="'+arr[i].policy_id+'">';
	 	ihtml = ihtml + '</td>';
   	 	ihtml = ihtml + '<td><a onclick="fn_SelectDetail(\''+arr[i].usr_id+'\',\''+arr[i].policy_id+'\')">'+arr[i].usr_id+'</a></td>';
   	 	ihtml = ihtml + '<td id="plcyNm_'+(i+1)+'" name="plcyNm_'+(i+1)+'">'+arr[i].policy_nm+'</td>';
   	 	ihtml = ihtml + '<td id="plcyHtCnt_'+(i+1)+'" name="plcyHtCnt_'+(i+1)+'">'+arr[i].cnt+'</td>';
	 	ihtml = ihtml + '</tr>';
	 	cnt++;
    }
    
    if(cnt == 0){
    	ihtml = ihtml + '<tr>';
	 	ihtml = ihtml + '<td colspan=5> 조회 결과가 없습니다</td>';
	 	ihtml = ihtml + '</tr>';
    }
    
 	ihtml = ihtml + '</tbody>';
 	ihtml = ihtml + '</table>';
 	
	var grd = document.getElementById("grd");
	grd.innerHTML = ihtml;
}

/*********************************************************
 * 상세 페이지 이동
 ******************************************************** */
function fn_SelectDetail(userId, plcyId){
	location.href="/LoginPolicyHitDetailList?userId="+userId+"&plcyId="+plcyId;
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
		paramData.plcyId = $("#plcyid_"+ckNum).val();
		paramData.userId = $("#id_"+ckNum).val();

		//API호출
		rtnData = fn_calApi("DELETE", "/lgHRgt/clean", paramData, false);
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


function fncCheckAll(){
	if($("#checkAll").prop("checked")){
        $("input[name=checkField]").prop("checked",true);
    }else{
        $("input[name=checkField]").prop("checked",false);
    }
}

</script>
</head>

<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<div class="board">
	<h1>적중이력 목록</h1>
	<!-- 검색영역 -->
	<div class="search_box" title="이 레이아웃은 하단 정보를 대한 검색 정보로 구성되어 있습니다.">
		<ul>
			<li>조건&nbsp;
                <select name="searchCondition" id="searchCondition" title="조회조건 선택">
                	<option value="0" selected="selected" >---전체---</option>
                    <option value="1" >사용자ID</option> 
                    <option value="2" >정책명</option>
                </select>
			</li>
			<!-- 검색키워드 및 조회버튼 -->
			<li>
				<input class="s_input" name="searchKeyword" id="searchKeyword" type="text"  size="35" title="검색어 입력" value=''  maxlength="255" >
				<input type="button" class="s_btn" onClick="fn_Select();" 	value="조회" title="조회 버튼" />
				<input type="button" class="s_btn" onClick="fn_Delete();" 	value="삭제" title="삭제 버튼" />
			</li>
		</ul>
	</div>

	<div id="grd"></div>
</div>

</body>
</html>