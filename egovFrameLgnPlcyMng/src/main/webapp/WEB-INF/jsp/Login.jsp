<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<%@ include file="/WEB-INF/jsp/cmm/head.jsp" %>
<link type="text/css" rel="stylesheet" href="/css/egovframework/com/com.css">
<link type="text/css" rel="stylesheet" href="/css/egovframework/com/uat/uia/login.css">
	
<script type="text/javaScript" language="javascript" defer="defer">

	/*********************************************************
	 * 초기화
	 ******************************************************** */
	$(document).ready(function(){
		parent.parent.topFrame.document.all.infoImg.style.display = "none";
		parent.parent.topFrame.document.all.lgnUserId.value= "";
		parent.parent.topFrame.document.all.lgnMnt.value= "";
	});

	//입력 필수값 체크
	function required() {
		if($.trim($("#userId").val()).length == 0){
			alert("아이디를 입력해주세요");$("#userId").focus();return false;
		}else if($.trim($("#password").val()).length == 0){
			alert("패스워드를 입력해 주세요.");$("#password").focus();return false;
		}
	}
	
	/*********************************************************
	 * 로그인
	 ******************************************************** */
	function fn_login(){

		if(required()==false)return;
		
		var userData = new Object();
		userData.usrId				=	$("#userId").val();
		userData.password		=	$("#password").val();

		//API호출
		var rtnData = new Object();
		var arrlist = new Array();
		rtnData = fn_calApi("POST", "/lgHRgt/detailMsg/", userData, false);
		if(rtnData.RESULTCD == 0){
			arrlist = rtnData.list;
			if(arrlist != null){
				const obj2 = arrlist[0]; 
				parent.parent.topFrame.document.all.infoImg.style.display = "block";
				parent.parent.topFrame.document.all.lgnUserId.value= obj2.userId;
				parent.parent.topFrame.document.all.lgnMnt.value= obj2.userNm+" 님 환영합니다.";
			}
			alert(rtnData.MSG);

			if(rtnData.CKCD == 0){
				fn_movelogin();
			}
		}else{
			alert(rtnData.RESULTMSG);
			return;
		}
	}

	function fn_movelogin(){
		location.href="/LoginPolicyList";
	}

</script>
</head>

<body style="inline-size: max-content;">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!-- 일반로그인 -->
<div class="login_form">
	<input type="hidden" id="message" name="message" value="">
	<fieldset>
		<div class="login_input">
			<ul>
				<li>
					<label for="id">아이디</label>
					<input type="text" name="userId" id="userId" maxlength="10" title="아이디 " placeholder="아이디 ">
				</li>
				<li>
					<label for="password">비밀번호</label>
					<input type="password" name="password" id="password" maxlength="12" title="비밀번호 " placeholder="비밀번호 " onKeypress="javascript:if(event.keyCode==13) fn_login()" >
				</li>
				<li>
					<input type="button" class="btn_login" value="로그인" onclick="fn_login()"> <!-- 로그인  -->
				</li>
			</ul>
		</div>
	</fieldset>
</div>
</body>
</html>