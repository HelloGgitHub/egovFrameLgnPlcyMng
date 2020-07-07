<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<title>로그인</title>
	<%@ include file="/WEB-INF/jsp/cmm/head.jsp" %>
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
			var rtnData = new Object();
			var userData = new Object();
			
			userData.usrId				=	$("#userId").val();
			userData.password		=	$("#password").val();

			//API호출
			rtnData = fn_calApi("POST", "/login/idpw", userData, false);
			
			if(rtnData.RESULTCD == 0){
// 				alert(rtnData.list.userNm);
// 				document.getElementById(tgtEl).style.visibility = "visible";
				parent.parent.topFrame.document.all.infoImg.style.display = "block";
				parent.parent.topFrame.document.all.lgnUserId.value= rtnData.list.userId;
				parent.parent.topFrame.document.all.lgnMnt.value= rtnData.list.userNm+" 님 환영합니다.";
				fn_movelogin();
			}else{
				alert(rtnData.RESULTMSG);
				return;
			}
		}
	
		function fn_movelogin(){
			location.href=baseUrl+"/UserList";
		}

		function fn_arovRequest(){
			location.href=baseUrl+"/UserInfo?callType=c&userId=";
		}









		

		function fn_test(){
			
			window.parent.parent.frames["topFrame"].changePw();

// 			parent.parent.topFrame.document.all.lgnUserId.value= "userid";
			
// 			var userData = new Object();
// 			userData.usrId				=	$("#userId").val();
// 			userData.password		=	$("#password").val();

// 			var p="";
// 			var tUrl="";
// 			$.each(userData, function(key, value){
// 			    p = p + key + "=" + value+"&";
// 			});
// 			tUrl = "?"+p.substring(0,(p.length-1))
// 			alert(tUrl);
			
// 			for (const key in userData) {
// 				 console.log(key);
// 				 console.log(userData.key);
// 			}
		}

	</script>
	</head>
<body>

	<from name="login" id="login">
		<div class="board">
			<h1>로그인</h1>
			<table class="wTable" summary="로그인">
			<colgroup>
				<col style="width: 22%; height:20px">
				<col style="width: 78%; height:20px">
			</colgroup>
			<tbody>
				<tr style="height:30px">
					<th><label for="userId">ID</label></th>
					<td class="left">
						<input id="userId" name="userId" title="사용자ID" type="text" value="TESTUSER" size="50" maxlength="20" style="height:20px;width:100%;"/>
					</td>
				</tr>
				<!-- 비밀번호 -->
				<tr style="height:30px">
					<th><label for="password">PASSWORD</label></th>
					<td class="left">
					   <input id="password" name="password" title="비밀번호 입력" type="password" value="string" size="50" maxlength="20" style="height:20px;width:100%;"/>
					</td>
				</tr>
			</tbody>
			</table>
			<br>
			<table>
				<tr>
					<td style="text-align: -webkit-center;">
			<!-- 			<button title="test" onclick="fn_test()">test</button>  -->
						<button title="로그인" onclick="fn_login()">로그인</button>
						<button title="회원가입" onclick="fn_arovRequest()">회원가입</button>
					
					</td>
				</tr>
			</table>
		</div>
	</from>
</body>
</html>