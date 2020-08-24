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
			url="DpIPInfo?callType=c&dpIp=&dpIptyp=";
		} else if(cd == 6){
			url="LoginPolicyHitList";
		} else if(cd == 7){
			alert("'적중이력 목록 조회'를 통해 접근했을때 정상 동작 합니다.");
			url="LoginPolicyHitDetailList?userId=&plcyId=";
		} else if(cd == 88){
			url="/swagger-ui.html#/";
		} else if(cd == 99){
			url="/db-console";
		} else if(cd == 00){
			url="/download/eGovFrameAuthMng.zip";
		}
		parent.body.location.href=url;
	}
</script>

<body>
	<input type="hidden" id="" name="lngCk" value="9"/>
	<br><span class="btn_b"><a href="" onClick="fn_leftMenu(1);" title="로그인" style="font-size: 15px;font-style: inherit;font-weight: bold;">- 로그인</a></span>
	<br>
	<br><span class="btn_b" style="font-size: 15px;font-style: inherit;font-weight: bold;">- 로그인정책 관리</span>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(3);" title="정책 등록" style="font-size: 15px;font-style: inherit;font-weight: bold;">정책 등록</a></span>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(2);" title="정책 목록 조회" style="font-size: 15px;font-style: inherit;font-weight: bold;">정책 목록 조회</a></span>
	<br>
	<br><span class="btn_b" style="font-size: 15px;font-style: inherit;font-weight: bold;">- 차단IP 목록 관리 </span>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(5);" title="차단IP등록" style="font-size: 15px;font-style: inherit;font-weight: bold;">차단IP 등록</a></span>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(4);" title="차단IP목록 조회" style="font-size: 15px;font-style: inherit;font-weight: bold;">차단IP 목록 조회</a></span>
	<br>
	<br><span class="btn_b" style="font-size: 15px;font-style: inherit;font-weight: bold;">- 적중이력 관리</span>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(6);" title="적중이력 목록조회" style="font-size: 15px;font-style: inherit;font-weight: bold;">적중이력 목록 조회</a></span>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(7);" title="적중이력 상세조회" style="font-size: 15px;font-style: inherit;font-weight: bold;">적중이력 상세 조회</a></span>
	<br><br>
	<br><span class="btn_b" style="font-size: 15px;font-style: inherit;font-weight: bold;">- API명세 및 DB스키마</span>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(88);" title="권한관리 API명세" style="font-size: 15px;font-style: inherit;font-weight: bold;">API 명세</a></span>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(99);" title="권한관리 DB스키마" style="font-size: 15px;font-style: inherit;font-weight: bold;">DB 스키마</a></span>
	<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="btn_b"><a href="" onClick="fn_leftMenu(00);" title="소스 다운로드" style="font-size: 15px;font-style: inherit;font-weight: bold;">소스 다운로드</a></span>
</body>
</html>