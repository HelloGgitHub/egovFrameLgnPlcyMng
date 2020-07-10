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
		$("#btn_Modify").attr("disabled",true);
		$("#btn_Modify").css("display","none"); 
		$("#btn_Del").attr("disabled",true);
		$("#btn_Del").css("display","none");
	}else if(type == "r"){
		$("#btn_Add").attr("disabled",true);
		$("#btn_Add").css("display","none");
		$("#inDpIp").val(dpIp);
		$("#inDpIp").attr("readonly",true);
		$("#inDpipNm").attr("readonly",true);
		$("#inDpipDc").attr("readonly",true);
		$("#inAplyUsr").attr("readonly",true);
		fn_DetailPlcy();
	}else if(type == "cr"){  //readOnly
		$("#btn_Add").attr("disabled",true);
		$("#btn_Add").css("display","none");
		$("#btn_Modify").attr("disabled",true);
		$("#btn_Modify").css("display","none"); 
		$("#btn_Del").attr("disabled",true);
		$("#btn_Del").css("display","none");
		
		$("#inDpIp").attr("readonly",true);
		$("#inDpipNm").attr("readonly",true);
		$("#inDpipDc").attr("readonly",true);
		$("#inAplyUsr").attr("readonly",true);
	}else if(type == "u"){ //modify
		$("#btn_Add").attr("disabled",true);
		$("#inDpIp").attr("readonly",true);
	}
}
//입력 필수값 체크
function required() {
	if($.trim($("#inDpIp").val()).length == 0){//////////////////////////////
		alert("정책아이디은(는) 필수 입력값입니다.");$("#inDpIp").focus();return;/////////////////////////////////////
	}
}
//입력값 길이 체크
function maxlength() { 
	if($.trim($("#inDpIp").val()).length >= 20){//////////////////////
		alert("정책아이디은(는) 20자 이상 입력할수 없습니다.");$("#inDpIp").focus();return false;//////////////
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
 * 정책등록
 ******************************************************** */
function fn_insert(){

	if(confirm("등록하시겠습니까?")){	
		required(); //필수값 체크
		maxlength(); //최대 길이 체크
	}

	var dpIpData = new Object();
	dpIpData.blkIp				=	$("#inDpIp").val();
	dpIpData.blkIpNm			=	$("#inDpipNm").val();
	dpIpData.blkIpDc			=	$("#inDpipDc").val();
	dpIpData.addUsrid		=	$("#inAplyUsr").val();

	var rtnData = new Object();
	rtnData = fn_calApi("POST", "/lgDpIp/addnew", dpIpData, false);
	alert(rtnData.RESULTMSG);
	if(rtnData.RESULTCD != 0){
		return;
	}
	inputCellSet("cr");
}

/*********************************************************
 * 정책 정보 조회
 ******************************************************** */
function fn_DetailPlcy(){
	var pDpIp = "";
	if(dpIp == null && dpIp == ""){
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
 * 정책정보 삭제
 ******************************************************** */
function fn_delete(){
	if(confirm("삭제 하시겠습니까?")){
		if($("#inDpIp").val() == null){
			alert("IP가 등록되어있지 않습니다. \n삭제 할 IP가 없습니다.");
			return;
		}
	}
	
	var rtnData = new Object();
	rtnData = fn_calApi("DELETE", "/lgDpIp/delete?bkIp="+$("#inDpIp").val(), null, false);
	fn_moveDpIpList();
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
	<!-- 타이틀 -->
	<h2 >차단IP 관리</h2>
	<!-- 등록폼 -->
	<table class="wTable" summary="로그인 정책 정보를 출력합니다.">
	<caption>차단IP 관리</caption>
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력/선택 -->
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
				<textarea id="inDpipDc" name="inDpipDc" title="설명" style="resize: none;height: 300px;"></textarea>
			</td>
		</tr>
		
		<!-- 등록자ID -->		
		<tr>
			<th><label for="inAplyUsr">등록자ID</label></th>
			<td class="left">
				<input id="inAplyUsr" name="inAplyUsr" class="txaIpUmt" title="등록한사용자 ID" type="text" value="" size="50" maxlength="100"/>
			</td>
		</tr>
	</tbody>
	</table>
	<br/>
	<!-- 하단 버튼 -->
	<button title="뒤로가기" 		id="btn_movBak" onclick="fn_movebak();" >뒤로가기</button> 
	<button title="등록" 			id="btn_Add" 		onclick="fn_insert();">등록</button>
	<button title="삭제" 			id="btn_Del" 		onclick="fn_delete();">삭제</button>

</body>
</html>