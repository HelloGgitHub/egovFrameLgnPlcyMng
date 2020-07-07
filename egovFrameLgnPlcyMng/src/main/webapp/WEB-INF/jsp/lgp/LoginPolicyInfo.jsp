<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>사용자관리</title>
<%@ include file="/WEB-INF/jsp/cmm/head.jsp" %>

<script type="text/javaScript" language="javascript" defer="defer">

var caltype  	= "<%=request.getParameter("callType") %>";
var plcyId 	 	= "<%=request.getParameter("plcyId") %>";

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
		$("#inPlcyId").val(plcyId);
		$("#inPlcyId").attr("readonly",true);
		fn_DetailPlcy();
	}else if(type == "cr"){  //readOnly
		$("#btn_Add").attr("disabled",true);
		$("#btn_Add").css("display","none");
		$("#btn_Modify").attr("disabled",true);
		$("#btn_Modify").css("display","none"); 
		$("#btn_Del").attr("disabled",true);
		$("#btn_Del").css("display","none");
		
		$("#inPlcyId").attr("readonly",true);
	}else if(type == "u"){ //modify
		$("#btn_Add").attr("disabled",true);
		$("#inPlcyId").attr("readonly",true);
	}
}
//입력 필수값 체크
function required() {
	if($.trim($("#inPlcyId").val()).length == 0){//////////////////////////////
		alert("정책아이디은(는) 필수 입력값입니다.");$("#inPlcyId").focus();return;/////////////////////////////////////
	} else if($.trim($("#inPlcyNm").val()).length == 0){
		alert("정책명은(는) 필수 입력값입니다.");$("#inPlcyNm").focus();return;
	}
}
//입력값 길이 체크
function maxlength() { 
	if($.trim($("#inPlcyId").val()).length >= 20){//////////////////////
		alert("정책아이디은(는) 20자 이상 입력할수 없습니다.");$("#inPlcyId").focus();return false;//////////////
	} else if($.trim($("#inPlcyNm").val()).length >= 50){
		alert("정책명은(는) 50자 이상 입력할수 없습니다.");$("#inPlcyNm").focus();return;
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

	var policyData = new Object();
	policyData.policyId			=	$("#inPlcyId").val();
	policyData.policyNm			=	$("#inPlcyNm").val();
	policyData.policyDc			=	$("#inPlcydis").val();
	policyData.policyBgndt		=	$("#inPlcyBgn").val();
	policyData.policyEnddt		=	$("#inPlcyEnd").val();
	policyData.policyAppyYn	=	$("#inPlcyYn").val();
	policyData.policyAppyUsrid	=	$("#inAplyUsr").val();

	var rtnData = new Object();
	rtnData = fn_calApi("POST", "/lgplcy/addnew", policyData, false);
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
	var pPlcyId="";
	if(plcyId == null && plcyId == ""){
		pPlcyId = $("#inPlcyId").val();
	}else{
		pPlcyId = plcyId;
	}

	var rtnData = new Object();
	var arrlist = new Array();
	rtnData = fn_calApi("GET", "/lgplcy/detailInfo/"+pPlcyId, null, false);
	arrlist = rtnData.list;
	const obj2 = arrlist[0]; 
	
	$("#inPlcyId").val(obj2.policy_id);
	$("#inPlcyNm").val(obj2.policy_nm);
	$("#inPlcyBgn").val(obj2.policy_bgndt);
	$("#inPlcyEnd").val(obj2.policy_enddt);
	$("#inPlcyYn").val(obj2.policy_appy_yn);
	$("#inPlcydis").val(obj2.policy_dc);
	$("#inAplyUsr").val(obj2.policy_appy_usrid);
}

/*********************************************************
 * 정책정보 변경
 ******************************************************** */
function fn_update(){
	console.log("===" +$("#inPlcyId").val() );

	if(confirm("저장 하시겠습니까?")){
		required();//필수값 체크
		maxlength();//최대 길이 체크
	}
	
	var policyData = new Object();
	policyData.policyId			=	$("#inPlcyId").val();
	policyData.policyNm			=	$("#inPlcyNm").val();
	policyData.policyDc			=	$("#inPlcydis").val();
	policyData.policyBgndt		=	$("#inPlcyBgn").val();
	policyData.policyEnddt		=	$("#inPlcyEnd").val();
	policyData.policyAppyYn	=	$("#inPlcyYn").val();
	policyData.policyAppyUsrid	=	$("#inAplyUsr").val();

	var rtnData = new Object();
	rtnData = fn_calApi("PUT", "/lgplcy/modifyInfo", policyData, false);
	alert(rtnData.RESULTMSG);
	inputCellSet("cr");
}

/*********************************************************
 * 정책정보 삭제
 ******************************************************** */
function fn_delete(){
	var rtnData = new Object();
	rtnData = fn_calApi("DELETE", "/lgplcy/delete?plcyId="+$("#inPlcyId").val(), null, false);
	fn_movePlcyList();
}

/******************
 * 목록페이지로 돌아가기
 ******************/
function fn_movePlcyList(){
	location.href="/LoginPolicyList";
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
	<h2 >로그인 정책관리</h2>
	<!-- 등록폼 -->
	<table class="wTable" summary="로그인 정책 정보를 출력합니다.">
	<caption>로그인 정책관리</caption>
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 입력/선택 -->
		<!-- 정책아이디 -->
		<tr>
			<th><label for="inPlcyId">정책아이디</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="inPlcyId" name="inPlcyId" title="정책ID" onchange="idmodify()" type="text" value="" size="20" maxlength="20"/>
			</td>
		</tr>
		
		<!-- 정책이름 -->
		<tr>
			<th><label for="inPlcyNm">정책명</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="inPlcyNm" name="inPlcyNm" title="정책명" type="text" value="" size="50" maxlength="50"/>
			</td>
		</tr>
		
		<!-- 시작일자 -->
		<tr>
			<th><label for="inPlcyBgn">시작일자</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="inPlcyBgn" name="inPlcyBgn" class="txaIpUmt" title="로그인 정책 적용 시작일자" type="text" value="" size="50" maxlength="100"/>
			</td>
		</tr>
		
		<!-- 종료일자 -->
		<tr>
			<th><label for="inPlcyEnd">종료일자</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="inPlcyEnd" name="inPlcyEnd" class="txaIpUmt" title="로그인정책 적용 종료일자" type="text" value="" size="50" maxlength="100"/>
			</td>
		</tr>
		
		<!-- 정책적용여부 -->
		<tr>
			<th><label for="inPlcyYn">적용여부</label> <span class="pilsu">*</span></th>
			<td class="left">
				<select id="inPlcyYn" name="inPlcyYn" title="정책 적용여부">
					<option value="" selected="selected">--선택하세요--</option>
					<option value="Y">Y</option><option value="N">N</option>
				</select>
			</td>
		</tr>
		<!-- 정책설명 -->		
		<tr>
			<th><label for="inPlcydis">정책 설명</label> <span class="pilsu">*</span></th>
			<td class="left">
				<textarea id="inPlcydis" name="inPlcydis" title="정책설명" style="resize: none;height: 300px;"></textarea>
			</td>
		</tr>
		
		<!-- 정책설명 -->		
		<tr>
			<th><label for="inAplyUsr">적용자ID</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="inAplyUsr" name="inAplyUsr" class="txaIpUmt" title="정책적용자 ID" type="text" value="" size="50" maxlength="100"/>
			</td>
		</tr>
	</tbody>
	</table>
	<br/>
	<!-- 하단 버튼 -->
	<button title="뒤로가기" 		id="btn_movBak" onclick="fn_movebak();">뒤로가기</button> 
	<button title="등록" 			id="btn_Add" 		onclick="fn_insert();">등록</button>
	<button title="수정" 			id="btn_Modify" 	onclick="fn_update();">수정</button>
	<button title="삭제" 			id="btn_Del" 		onclick="fn_delete();">삭제</button>

</body>
</html>