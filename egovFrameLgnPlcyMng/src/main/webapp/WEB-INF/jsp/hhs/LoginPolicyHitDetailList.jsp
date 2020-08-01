<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>적중이력 상세 목록</title>
<%@ include file="/WEB-INF/jsp/cmm/head.jsp" %>

<script type="text/javaScript" language="javascript" defer="defer">

var userId  	= "<%=request.getParameter("userId") %>";
var plcyId	 	= "<%=request.getParameter("plcyId") %>";
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
	rtnData = fn_calApi("GET", "/lgHRgt/detailList?userId="+userId+"&plcyId="+plcyId, null, false);
	var arr = rtnData.list;
	
 	var ihtml = '';
 	ihtml = ihtml + '<table class="board_list" summary="정책별 적중 이력을 출력합니다.">';
 	ihtml = ihtml + '<colgroup><col style="width: 5%;"><col style="width: 15%;"><col style="width: ;"><col style="width: 20%;"><col style="width: 20%;"><col style="width: 10%;"></colgroup>';
 	ihtml = ihtml + '<thead>';
 	ihtml = ihtml + '<tr>';
 	ihtml = ihtml + '<th>번호</th>';
 	ihtml = ihtml + '<th class="board_th_link">사용자ID</th>';
 	ihtml = ihtml + '<th>정책명</th>';
 	ihtml = ihtml + '<th>적중일시</th>';
 	ihtml = ihtml + '<th>접근 IP</th>';
 	ihtml = ihtml + '<th>위치(국가)</th>';
 	ihtml = ihtml + '</tr>';
 	ihtml = ihtml + '</thead>';
 	ihtml = ihtml + '<tbody class="ov">';

	var cnt = 0;
	for(var i =0; arr.length > i; i++){
   	 	ihtml = ihtml + '<tr>';
   	 	ihtml = ihtml + '<td>' + (i+1) ; //+ '</td>';
   	 	ihtml = ihtml + '<input id="id_'+(i+1)+'" name="id_'+(i+1)+'" type="hidden" value="'+arr[i].usr_id+'">';
   	 	ihtml = ihtml + '</td>';
   	 	ihtml = ihtml + '<td>'+arr[i].usr_id+'</td>';
   	 	ihtml = ihtml + '<td style="text-align: left;padding-left: 30px;" id="plcyNm_'+(i+1)+'" name="plcyNm_'+(i+1)+'">'+arr[i].policy_nm+'</td>';
   	 	ihtml = ihtml + '<td id="plcyHtDt_'+(i+1)+'" name="plcyHtDt_'+(i+1)+'">'+arr[i].policy_hit_dt+'</td>';
   		ihtml = ihtml + '<td id="lgnIp_'+(i+1)+'" name="lgnIp_'+(i+1)+'">'+arr[i].login_ip+'</td>';
   		ihtml = ihtml + '<td id="lgnIpCntr_'+(i+1)+'" name="lgnIpCntr_'+(i+1)+'">'+arr[i].login_ip_cntr+'</td>';
	 	ihtml = ihtml + '</tr>';
	 	cnt++;
    }

    if(cnt == 0){
    	ihtml = ihtml + '<tr>';
	 	ihtml = ihtml + '<td colspan=4> 조회 결과가 없습니다</td>';
	 	ihtml = ihtml + '</tr>';
    }
    
 	ihtml = ihtml + '</tbody>';
 	ihtml = ihtml + '</table>';
 	
	var grd = document.getElementById("grd");
	grd.innerHTML = ihtml;
}

</script>
</head>

<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<div class="board">
	<h1>적중이력 상세목록</h1>
	<div id="grd"></div>
</div>
</body>
</html>