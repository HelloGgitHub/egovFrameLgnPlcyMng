<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="/css/egovframework/com/com.css">
	<script src="/js/egovframework/com/cmm/jquery-1.4.2.min.js"></script>
</head>

<script type="text/javascript">
	function fn_leftMenu(cd){
		var url="";
		if(cd == 1){
			url="Login";
		} else if(cd == 2){
			url="LoginPolicyList";
		} else if(cd == 3){
			url="LoginPolicyInfo?callType=c&plcyId=";
		} else if(cd == 4){
			url="DpIPList";
		} else if(cd == 5){
			url="DpIPInfo?callType=c&dpIp=";
		} else if(cd == 6){
			url="LoginPolicyHitList";
		} else if(cd == 7){
			url="LoginPolicyHitDetailList?userId=&plcyId=";
		}
		
		parent.body.location.href=url;
	}

</script>

<body>
	<input type="hidden" id="" name="lngCk" value="9"/>
	<br><span class="btn_b"><a href="" onClick="fn_leftMenu(1);" title="로그인" style="font-size: 15px;font-style: inherit;font-weight: bold;">1- 로그인 >></a></span>
	<br>
	<br><span class="btn_b"><a href="" onClick="fn_leftMenu(2);" title="정책 목록" style="font-size: 15px;font-style: inherit;font-weight: bold;">2- 정책 목록 >></a></span>
	<br>&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(3);" title="정책 정보" style="font-size: 15px;font-style: inherit;font-weight: bold;">2-1 정책 정보 >></a></span>
	<br>
	<br><span class="btn_b"><a href="" onClick="fn_leftMenu(4);" title="차단IP목록" style="font-size: 15px;font-style: inherit;font-weight: bold;">3- 차단IP목록 >></a></span>
	<br>&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(5);" title="차단IP등록" style="font-size: 15px;font-style: inherit;font-weight: bold;">3-1 차단IP등록 >></a></span>
	<br>
	<br><span class="btn_b"><a href="" onClick="fn_leftMenu(6);" title="적중이력 조회" style="font-size: 15px;font-style: inherit;font-weight: bold;">4- 적중이력 조회 >></a></span>
	<br>&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(7);" title="적중이력 상세 조회" style="font-size: 15px;font-style: inherit;font-weight: bold;">4-1 적중이력 상세 조회 >></a></span>


</body>
</html>