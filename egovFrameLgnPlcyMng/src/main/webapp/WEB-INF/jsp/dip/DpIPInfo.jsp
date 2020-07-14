<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>사용자관리</title>
<%@ include file="/WEB-INF/jsp/cmm/head.jsp" %>

<script type="text/javaScript" language="javascript" defer="defer">

var caltype  	= "<%=request.getParameter("callType") %>";
var dpIp		 	= "<%=request.getParameter("dpIp") %>";

/*********************************************************
 * 초기화
 *********************************************************/
$(document).ready(function(){
	inputCellSet(caltype);
});

function inputCellSet(type) {
	//호출타입에 따라 입력환경 설정
	if(type == "c"){ //insert
		$("#btn_Delete").css("display","none");
	}else if(type == "r"){
		$("#btn_Insert").css("display","none");
		$("#inDpIp").attr("readonly",true);
		$("#inDpipNm").attr("readonly",true); 
		$("#inDpipDc").attr("readonly",true);
		fn_DetailDpIp();
	}
}

//입력 필수값 체크
function required() {
	if($.trim($("#inDpIp").val()).length == 0){
		alert("IP은(는) 필수 입력값입니다.");$("#inDpIp").focus();return false;
	}
}
//입력값 길이 체크
function maxlength() { 
	if($.trim($("#inDpIp").val()).length >= 20){
		alert("IP은(는) 23자 이상 입력할수 없습니다.");$("#inDpIp").focus();return false;
	}else if($.trim($("#inDpipNm").val()).length > 50){
		alert("명칭은(는) 50자 이상 입력할수 없습니다.");$("#inDpipNm").focus();return false;
	}else if($.trim($("#inDpipDc").val()).length > 250){
		alert("설명은(는) 250자 이상 입력할수 없습니다.");$("#inDpipDc").focus();return false;
	}
}
	
// 	function password1 () { 
// 		this.aa = new Array("password", "비밀번호은(는) 8~20자 내에서 입력해야 합니다.", new Function ("varName", " return this[varName];"));
// 	} 
// 	function password2 () { 
// 		this.aa = new Array("password", "비밀번호은(는) 한글,특수문자,띄어쓰기는 허용되지 않습니다.", new Function ("varName", " return this[varName];"));
// 	}
// 	function password3 () { 
// 		this.aa = new Array("password", "비밀번호은(는) 순차적인 숫자를 4개이상 연속해서 사용할수 없습니다.", new Function ("varName", " return this[varName];"));
// 	} 
// 	function password4 () { 
// 		this.aa = new Array("password", "비밀번호은(는) 반복되는 문자나 숫자를 4개이상 연속해서 사용할 수 없습니다.", new Function ("varName", " return this[varName];"));
// 	}
// 	function IntegerValidations () { 
// 		this.aa = new Array("inAreaNo", "집지역번호은(는) integer 타입이어야 합니다.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
// 		this.ab = new Array("inMiddleTelno", "집중간전화번호은(는) integer 타입이어야 합니다.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
// 		this.ac = new Array("inEndTelno", "집마지막전화번호은(는) integer 타입이어야 합니다.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
// 	}
// 	function email () { 
// 		this.aa = new Array("inUserEmailAdres", "이메일주소은(는) 유효하지 않은 이메일 주소입니다.", new Function ("varName", "this.maxlength='50';  return this[varName];"));
// 	} 


/*********************************************************
 * 정책 정보 조회
 ******************************************************** */
function fn_DetailDpIp(){
	var pDpIp = "";
	if(dpIp == null || dpIp == ""){
		pDpIp = $("#inDpIp").val();
	}else{
		pDpIp = dpIp;
	}

	var rtnData = new Object();
	var arrlist = new Array();
	rtnData = fn_calApi("GET", "/lgDpIp/detailInfo/"+pDpIp, null, false);
	arrlist = rtnData.list;
	const obj2 = arrlist[0]; 
	
	$("#inDpIp").val(obj2.blk_ip);
	$("#inDpipNm").val(obj2.blk_ipnm);
	$("#inDpipDc").val(obj2.blk_ipdc);
	$("#inAplyUsr").val(obj2.add_usrid);
}

/*********************************************************
 * 정책등록
 ******************************************************** */
function fn_Insert(){

	if(confirm("등록하시겠습니까?")){	
		if(required()==false) return; //필수값 체크
		if(maxlength()==false) return; //최대 길이 체크

		var dpIpData = new Object();
		dpIpData.blkIp				=	$("#inDpIp").val();
		dpIpData.blkIpNm			=	$("#inDpipNm").val();
		dpIpData.blkIpDc			=	$("#inDpipDc").val();
		dpIpData.addUsrid		=	parent.parent.topFrame.document.all.lgnUserId.value;

		var rtnData = new Object();
		rtnData = fn_calApi("POST", "/lgDpIp/addnew", dpIpData, false);
		alert(rtnData.RESULTMSG);
		if(rtnData.RESULTCD != 0){
			return;
		}
		inputCellSet("r");
	}
}


/*********************************************************
 * 정책정보 삭제
 ******************************************************** */
function fn_Delete(){
	if(confirm("삭제 하시겠습니까?")){
		if($("#inDpIp").val() == null){
			alert("IP가 등록되어있지 않습니다. \n삭제 할 IP가 없습니다.");
			return;
		}
		
		var rtnData = new Object();
		rtnData = fn_calApi("DELETE", "/lgDpIp/delete?bkIp="+$("#inDpIp").val(), null, false);
		
		alert(rtnData.RESULTMSG);
		fn_moveDpIpList();
	}
}

/******************
 * 목록페이지로 돌아가기
 ******************/
function fn_moveDpIpList(){
	location.href="/DpIPList";
}

/*************
 * 뒤로가기
 *************/
function fn_movebak(){
	window.history.back();	
}

</script>
</head>

<body>

	<div class="wTableFrm">
	<h2 >차단IP 정보</h2>
	<table class="wTable" summary="차단IP 정보를 출력합니다.">
	<caption>차단IP 정보</caption>
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- IP -->
		<tr>
			<th><label for="inDpIp">IP</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="inDpIp" name="inDpIp" title="정책ID" type="text" value="" size="20" maxlength="20"/>
			</td>
		</tr>
		
		<!-- 명칭 -->
		<tr>
			<th><label for="inDpipNm">명칭</label></th>
			<td class="left">
				<input id="inDpipNm" name="inDpipNm" title="명칭" type="text" value="" size="50" maxlength="50"/>
			</td>
		</tr>
		
		<!-- 설명 -->		
		<tr>
			<th><label for="inDpipDc">설명</label></th>
			<td class="left">
				<textarea id="inDpipDc" name="inDpipDc" title="설명" style="resize: none;"></textarea>
			</td>
		</tr>
	</tbody>
	</table>
	<br/>
	<!-- 하단 버튼 -->
	<button title="등록" 			id="btn_Insert" 		onclick="fn_Insert();">등록</button>
	<button title="삭제" 			id="btn_Delete" 		onclick="fn_Delete();">삭제</button>
</body>
</html>