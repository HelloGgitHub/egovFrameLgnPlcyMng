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
// 		$("#btn_Update").css("display","none");
		$("#btn_Insert").attr("disabled",false);
		$("#btn_Modify").attr("disabled",true);
		$("#btn_Update").attr("disabled",true);
		$("#btn_Delete").attr("disabled",true);

		$("#inPlcyId").val("");
		$("#inPlcyNm").val("");
		$("#inPlcyBgn").val("");
		$("#inPlcyEnd").val("");
		$("#inDpCnt").val("");
		$("#inPlcyYn").val("");
		$("#inPlcyDc").val("");
	}else if(type == "r"){
		$("#btn_Insert").attr("disabled",true);
		$("#btn_Modify").attr("disabled",false);
		$("#btn_Update").attr("disabled",true);
		$("#btn_Delete").attr("disabled",false);

		$("#inPlcyId").attr("readonly",true); 
		$("#inPlcyNm").attr("readonly",true);
		$("#inPlcyBgn").attr("readonly",true);
		$("#inPlcyEnd").attr("readonly",true);
		$("#inDpCnt").attr("readonly",true);
		$("#inPlcyYn").attr("readonly",true);
		$("#inPlcyDc").attr("readonly",true);
		fn_DetailPlcy();
	}else if(type == "u"){ //modify
		$("#btn_Insert").attr("disabled",true);
		$("#btn_Modify").attr("disabled",true);
		$("#btn_Update").attr("disabled",false);
		$("#btn_Delete").attr("disabled",false);

		$("#inPlcyId").attr("readonly",true); 
		$("#inPlcyNm").attr("readonly",false);
		$("#inPlcyBgn").attr("readonly",false);
		$("#inPlcyEnd").attr("readonly",false);
		$("#inDpCnt").attr("readonly",false);
		$("#inPlcyYn").attr("readonly",false);
		$("#inPlcyDc").attr("readonly",false);
	}
}
//입력 필수값 체크
function required() {
	if($.trim($("#inPlcyId").val()).length == 0){
		alert("정책아이디은(는) 필수 입력값입니다.");$("#inPlcyId").focus();return false;
	}else if($.trim($("#inPlcyBgn").val()).length == 0){
		alert("시작일자은(는) 필수 입력값입니다.");$("#inPlcyBgn").focus();return false;
	}else if($.trim($("#inPlcyEnd").val()).length == 0){
		alert("종료일자은(는) 필수 입력값입니다.");$("#inPlcyEnd").focus();return false;
	}else if($.trim($("#inDpCnt").val()).length == 0){
		alert("접근허용 횟수은(는) 필수 입력값입니다.");$("#inDpCnt").focus();return false;
	}else if($.trim($("#inPlcyYn").val()).length == 0){
		alert("적용여부를 선택해 주세요.");$("#inPlcyYn").focus();return false;
	}
}

//입력값 길이 체크
function maxlength() { 
	if($.trim($("#inPlcyId").val()).length >= 20){
		alert("정책아이디은(는) 20자 이상 입력할수 없습니다.");$("#inPlcyId").focus();return false;
	} else if($.trim($("#inPlcyNm").val()).length >= 50){
		alert("정책명은(는) 50자 이상 입력할수 없습니다.");$("#inPlcyNm").focus();return false;
	} else if($.trim($("#inPlcyDc").val()).length >= 250){
		alert("정책설명은(는) 250자 이상 입력할수 없습니다.");$("#inPlcyDc").focus();return false;
	} else if($.trim($("#inPlcyBgn").val()).length != 8){
		alert("시작일자은(는) 입력값이 올바르지 않습니다.");$("#inPlcyBgn").focus();return false;
	} else if($.trim($("#inPlcyEnd").val()).length != 8){
		alert("종료일자은(는) 입력값이 올바르지 않습니다.");$("#inPlcyEnd").focus();return false;
	} else if($.trim($("#inDpCnt").val()).length >= 5){
		alert("접근허용횟수은(는) 5자리 이상 입력할수 없습니다.");$("#inDpCnt").focus();return false;
	}
}
	


/*********************************************************
 * 정책등록
 ******************************************************** */
function fn_Insert(){

	if(confirm("등록하시겠습니까?")){	
		if(required()==false) return; //필수값 체크
		if(maxlength()==false) return; //최대 길이 체크
		
		var policyData = new Object();
		policyData.policyId			=	$("#inPlcyId").val();
		policyData.policyNm			=	$("#inPlcyNm").val();
		policyData.policyDc			=	$("#inPlcyDc").val();
		policyData.policyBgndt		=	$("#inPlcyBgn").val();
		policyData.policyEnddt		=	$("#inPlcyEnd").val();
		policyData.policyHtmxCnt	=	$("#inDpCnt").val();
		policyData.policyAppyYn	=	$("#inPlcyYn").val();
		policyData.policyInCertKey	=	$("#inCertKey").val();
		policyData.policyAppyUsrid	=	parent.parent.topFrame.document.all.lgnUserId.value;
		
		var rtnData = new Object();
		rtnData = fn_calApi("POST", "/lgplcy/addnew", policyData, false);
		alert(rtnData.RESULTMSG);
		
		if(rtnData.RESULTCD != 0){
			return;
		}
		inputCellSet("r");
	}
}

/*********************************************************
 * 정책 정보 조회
 ******************************************************** */
function fn_DetailPlcy(){
	var pPlcyId="";
	if(plcyId == null || plcyId == ""){
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
	$("#inDpCnt").val(obj2.policy_htmxcnt);
	$("#inPlcyDc").val(obj2.policy_dc);
// 	$("#inAplyUsr").val(obj2.policy_appy_usrid);
}

/*********************************************************
 * 정책정보 변경
 ******************************************************** */
function fn_Update(){

	if(confirm("저장 하시겠습니까?")){
		if(required()==false) return; //필수값 체크
		if(maxlength()==false) return; //최대 길이 체크

		var policyData = new Object();
		policyData.policyId			=	$("#inPlcyId").val();
		policyData.policyNm			=	$("#inPlcyNm").val();
		policyData.policyDc			=	$("#inPlcyDc").val();
		policyData.policyBgndt		=	$("#inPlcyBgn").val();
		policyData.policyEnddt		=	$("#inPlcyEnd").val();
		policyData.policyHtmxCnt	=	$("#inDpCnt").val();
		policyData.policyAppyYn	=	$("#inPlcyYn").val();
		policyData.policyAppyUsrid	=	parent.parent.topFrame.document.all.lgnUserId.value;

		var rtnData = new Object();
		rtnData = fn_calApi("PUT", "/lgplcy/modifyInfo", policyData, false);
		alert(rtnData.RESULTMSG);
		
		inputCellSet("r");
	}
}

/*********************************************************
 * 정책정보 삭제
 ******************************************************** */
function fn_Delete(){
	if(confirm("삭제 하시겠습니까?")){
		if($("#inPlcyId").val() == null || $("#inPlcyId").val() == ""){
			alert("정책ID가 등록되어있지 않습니다. \n삭제 할 정책이 없습니다.");
			return;
		}
	}
	
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

function fn_Modify(){
	inputCellSet("u");
}

</script>
</head>

<body>
	<div class="wTableFrm">
	<h2 >로그인 정책 정보</h2>
	<table class="wTable" summary="로그인 정책 정보를 출력합니다.">
	<caption>로그인 정책 정보</caption>
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- 정책아이디 -->
		<tr>
			<th><label for="inPlcyId">정책아이디</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="inPlcyId" name="inPlcyId" title="정책ID" type="text" value="" size="20" maxlength="20"/>
			</td>
		</tr>
		
		<!-- 정책이름 -->
		<tr>
			<th><label for="inPlcyNm">정책명</label></th>
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
		
		<!-- 접근허용 횟수 -->
		<tr>
			<th><label for="inDpCnt">접근허용 횟수</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="inDpCnt" name="inDpCnt" class="txaIpUmt" title="로그인 접근 허용 횟수" type="text" value="0" size="50" maxlength="5"/>
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
		
		<!-- 인증key -->
		<tr>
			<th><label for="inCertKey">인증 KEY</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="inCertKey" name="inCertKey" class="txaIpUmt" title="인증 key" type="text" value="0" size="50" maxlength="50"/>
			</td>
		</tr>
		
		<!-- 정책설명 -->		
		<tr>
			<th><label for="inPlcyDc">정책 설명</label></th>
			<td class="left">
				<textarea id="inPlcyDc" name="inPlcyDc" title="정책설명" style="resize: none;height: 300px;"></textarea>
			</td>
		</tr>
	</tbody>
	</table>
	<br/>
	<!-- 하단 버튼 -->
	<button title="등록" 		id="btn_Insert" 	onclick="fn_Insert();">등록</button>
	<button title="수정" 		id="btn_Modify" 	onclick="fn_Modify();">수정</button>
	<button title="저장" 		id="btn_Update" 	onclick="fn_Update();">저장</button>
	<button title="삭제" 		id="btn_Delete" 	onclick="fn_Delete();">삭제</button>
</body>
</html>