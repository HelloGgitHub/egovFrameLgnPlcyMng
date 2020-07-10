<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<title>로그인</title>
	<%@ include file="/WEB-INF/jsp/cmm/head.jsp" %>
	<link type="text/css" rel="stylesheet" href="/css/egovframework/com/com.css">
	<link type="text/css" rel="stylesheet" href="/css/egovframework/com/uat/uia/login.css">
<!-- 	<script type="text/javascript" src="/js/egovframework/com/cmm/showModalDialog.js" ></script> -->
<!-- 	<script type="text/javascript" src="/js/egovframework/com/cmm/jquery.js" ></script> -->
	
	<script type="text/javaScript" language="javascript" defer="defer">

		/*********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_init(){
			
		}
		
		/*********************************************************
		 * 로그인
		 ******************************************************** */
		function fn_login(){
			var userData = new Object();
			userData.usrId				=	$("#userId").val();
			userData.password		=	$("#password").val();

			//API호출
			var rtnData = new Object();
			var arrlist = new Array();
			rtnData = fn_calApi("POST", "/lgHRgt/detailMsg/", userData, false);
			if(rtnData.RESULTCD == 0){
// 				alert(rtnData.list.userNm);
// 				document.getElementById(tgtEl).style.visibility = "visible";
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
			location.href=baseUrl+"/LoginPolicyList";
		}










		

		function fn_test(){
			window.parent.parent.frames["topFrame"].changePw();
		}

	</script>
	</head>

<body style="/* display: table-row; *//* max-width: min-content; */inline-size: max-content;">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>

<!-- 일반로그인 -->
<div class="login_form">
<!-- 	<form name="loginForm" id="loginForm" action="/egovframework-all-in-one/uat/uia/actionLogin.do" method="post"> -->
	<input type="hidden" id="message" name="message" value="">
	
	<fieldset>
<!-- 		<img src="/images/title.jpg" style="margin:30px 0 0px 60px" alt="login title image"  title="login title image"> -->
		<div class="login_type">
			<ul id="ulLoginType">
<!-- 				<li><a href="javascript:fnLoginTypeSelect('typeGnr');" id="typeGnr" title="">일반</a></li> 일반 -->
<!--  				<li><a href="javascript:fnLoginTypeSelect('typeEnt');" id="typeEnt" title="">기업</a></li> 기업  -->
<!-- 				<li><a href="javascript:fnLoginTypeSelect('typeUsr');" id="typeUsr" title="">업무</a></li> 업무 -->
			</ul>
		</div>
	
		<div class="login_input">
			<ul>
				<!-- 아이디 -->
				<li>
					<label for="id">아이디</label>
					<input type="text" name="userId" id="userId" maxlength="10" title="아이디 " placeholder="아이디 ">
				</li>
				
				<!-- 비밀번호 -->
				<li>
					<label for="password">비밀번호</label>
					<input type="password" name="password" id="password" maxlength="12" title="비밀번호 " placeholder="비밀번호 ">
				</li>

				<li>
					<input type="button" class="btn_login" value="로그인" onclick="fn_login()"> <!-- 로그인  -->
				</li>
<!-- 				<li> -->
<!-- 					<ul class="btn_idpw" > -->
<!-- 						<li><a href="#" onclick="fn_arovRequest()">회원가입</a></li> 회원가입  -->
<!-- 						<li><a href="#" onclick="goFindId(); return false;">아이디/비밀번호 찾기</a></li> 아이디/비밀번호 찾기 -->
<!-- 					</ul> -->
<!-- 				</li> -->
			</ul>
		</div>
	</fieldset>
</div>

</body>

<!-- 	<from name="login" id="login"> -->
<!-- 		<div class="board"> -->
<!-- 			<h1>로그인</h1> -->
<!-- 			<table class="wTable" summary="로그인"> -->
<!-- 			<colgroup> -->
<!-- 				<col style="width: 22%; height:20px"> -->
<!-- 				<col style="width: 78%; height:20px"> -->
<!-- 			</colgroup> -->
<!-- 			<tbody> -->
<!-- 				<tr style="height:30px"> -->
<!-- 					<th><label for="userId">ID</label></th> -->
<!-- 					<td class="left"> -->
<!-- 						<input id="userId" name="userId" title="사용자ID" type="text" value="TESTUSER" size="50" maxlength="20" style="height:20px;width:100%;"/> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				비밀번호 -->
<!-- 				<tr style="height:30px"> -->
<!-- 					<th><label for="password">PASSWORD</label></th> -->
<!-- 					<td class="left"> -->
<!-- 					   <input id="password" name="password" title="비밀번호 입력" type="password" value="string" size="50" maxlength="20" style="height:20px;width:100%;"/> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</tbody> -->
<!-- 			</table> -->
<!-- 			<br> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<td style="text-align: -webkit-center;"> -->
<!-- 			 			<button title="test" onclick="fn_test()">test</button>  --> 
<!-- 						<button title="로그인" onclick="fn_login()">로그인</button> -->
					
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</div> -->
<!-- 	</from> -->
</html>