<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>사용자관리</title>
<%@ include file="/WEB-INF/jsp/cmm/head.jsp" %>
<script src="/js/egovframework/com/cmm/utl/EgovCmmUtl.js"></script>
<script src="/js/egovframework/com/cmm/utl/InputCk.js"></script>

<script type="text/javaScript" language="javascript" defer="defer">

var caltype  	= "<%=request.getParameter("callType") %>";
var dpIp		 	= "<%=request.getParameter("dpIp") %>";
var dpIptyp	= "<%=request.getParameter("dpIptyp") %>";
var dpIpType	= "";

/*********************************************************
 * 초기화
 *********************************************************/
$(document).ready(function(){
	if(dpIptyp==null || dpIptyp==""){
		dpIpType = "01";
	}else {
		dpIpType = dpIptyp;
	}
	
	inputCellSet(caltype);
	fn_radio(dpIpType);
});

function inputCellSet(type) {
	//호출타입에 따라 입력환경 설정
	if(type == "c"){ //insert
		$("#btn_Delete").css("display","none");
	}else if(type == "r"){
		$("#btn_Insert").css("display","none");
// 		$("#inDpIp").attr("readonly",true);
		$("#inDpipNm").attr("readonly",true); 
		$("#inDpipDc").attr("readonly",true);
		$("#inDpIp1").attr("readonly",true);
		$("#inDpIp2").attr("readonly",true);
		$("#inDpIp3").attr("readonly",true);
		$("#inDpIp4").attr("readonly",true);
		$("#inDpIpCidr").attr("readonly",true);
		$("#ipTyChk1").attr("disabled",true);
		$("#ipTyChk2").attr("disabled",true);
		
		fn_DetailDpIp();
	}
}

//입력 필수값 체크
function required() {
	if($.trim($("#inDpIp1").val()).length == 0){
		alert("형식에 맞지 않습니다.\nIP정보를 다시 확인해주세요.");$("#inDpIp1").focus();return false;
	}else if($.trim($("#inDpIp2").val()).length == 0){
		alert("형식에 맞지 않습니다.\nIP정보를 다시 확인해주세요.");$("#inDpIp2").focus();return false;
	}else if($.trim($("#inDpIp3").val()).length == 0){
		alert("형식에 맞지 않습니다.\nIP정보를 다시 확인해주세요.");$("#inDpIp3").focus();return false;
	}else if($.trim($("#inDpIp4").val()).length == 0){
		alert("형식에 맞지 않습니다.\nIP정보를 다시 확인해주세요.");$("#inDpIp4").focus();return false;
	}
}
//입력값 길이 체크
function maxlength() { 
	if($.trim($("#inDpipNm").val()).length > 50){
		alert("명칭은(는) 50자 이상 입력할수 없습니다.");$("#inDpipNm").focus();return false;
	}else if($.trim($("#inDpipDc").val()).length > 250){
		alert("설명은(는) 250자 이상 입력할수 없습니다.");$("#inDpipDc").focus();return false;
	}
}

function ipValCk () {
	if(CheckNumber($.trim($('#inDpIp1').val())) == false) {
		$("#inDpIp1").focus();return false;
	} else if(CheckNumber($.trim($('#inDpIp2').val())) == false) {
		$("#inDpIp2").focus();return false;
	} else if(CheckNumber($.trim($('#inDpIp3').val())) == false) {
		$("#inDpIp3").focus();return false;
	} else if(CheckNumber($.trim($('#inDpIp4').val())) == false) {
		$("#inDpIp4").focus();return false;
	}
}

/*********************************************************
 * 정책 정보 조회
 ******************************************************** */
function fn_DetailDpIp(){
	var pDpIp = "";
	if(dpIp == null || dpIp == ""){
		var ip = $("#inDpIp1").val()+"."+$("#inDpIp2").val()+"."+$("#inDpIp3").val()+"."+$("#inDpIp4").val();
		pDpIp = "?dpIp="+ip + "&dpIptyp="+ dpIpType;
	}else {
		pDpIp = "?dpIp="+ dpIp + "&dpIptyp="+ dpIpType;
	}
	var rtnData = new Object();
	var arrlist = new Array();
	rtnData = fn_calApi("GET", "/lgDpIp/detailInfo"+pDpIp, null, false);
	arrlist = rtnData.list;
	const obj2 = arrlist[0]; 
	var ip = obj2.blk_ip.split(".");
	
	$("#inDpIp1").val(ip[0]);
	$("#inDpIp2").val(ip[1]);
	$("#inDpIp3").val(ip[2]);
	$("#inDpIp4").val(ip[3]);
	$("#inDpIpCidr").val(obj2.blk_ip_cidr);
	
	$("#inDpipNm").val(obj2.blk_ipnm);
	$("#inDpipDc").val(obj2.blk_ipdc);
	$("#inAplyUsr").val(obj2.add_usrid);

	if(obj2.blk_ip_ed != "undefined" && obj2.blk_ip_ed != null ){
		var edip = obj2.blk_ip_ed.split(".");
		$("#inDpIp1Ed").val(edip[0]);
		$("#inDpIp2Ed").val(edip[1]);
		$("#inDpIp3Ed").val(edip[2]);
		$("#inDpIp4Ed").val(edip[3]);	
	}
// 	var ipEd = obj2.blk_ip_ed.split(".");
	
}

/*********************************************************
 * 정책등록
 ******************************************************** */
function fn_Insert(){

	if(confirm("등록하시겠습니까?")){	
		if(required()==false) return; //필수값 체크
		if(maxlength()==false) return; //최대 길이 체크
		if(ipValCk()==false) return; //IP 숫자 체크
		
		var dpIpData = new Object();
		dpIpData.blkIp				=	$("#inDpIp1").val()+"."+$("#inDpIp2").val()+"."+$("#inDpIp3").val()+"."+$("#inDpIp4").val();
		dpIpData.blkIpEd			=	$("#inDpIp1Ed").val()+"."+$("#inDpIp2Ed").val()+"."+$("#inDpIp3Ed").val()+"."+$("#inDpIp4Ed").val();
		dpIpData.blkIpTyp		=	dpIpType;
		
		dpIpData.blkIpCidr		=	$("#inDpIpCidr").val();
		dpIpData.blkIpNm			=	$("#inDpipNm").val();
		dpIpData.blkIpDc			=	$("#inDpipDc").val();
		dpIpData.addUsrid		=	parent.parent.topFrame.document.all.lgnUserId.value;

		var rtnData = new Object();
		rtnData = fn_calApi("POST", "/lgDpIp/addnew", dpIpData, false);
		alert(rtnData.RESULTMSG);
		if(rtnData.RESULTCD != 0){
			return;
		}

		fn_moveDpIpList();
// 		inputCellSet("r");
	}
}


/*********************************************************
 * 정책정보 삭제
 ******************************************************** */
function fn_Delete(){
	if(confirm("삭제 하시겠습니까?")){
		var ip = $("#inDpIp1").val()+"."+$("#inDpIp2").val()+"."+$("#inDpIp3").val()+"."+$("#inDpIp4").val();
		if(ip == null){
			alert("IP가 등록되어있지 않습니다. \n삭제 할 IP가 없습니다.");
			return;
		}

		var rtnData = new Object();
		rtnData = fn_calApi("DELETE", "/lgDpIp/delete?bkIp="+ip+"&dpIptyp="+dpIpType, null, false);
		
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

function fn_radio(val){
	dpIpType = val;
	if(val=="01"){
		$("input:radio[name='ipTyChk']:radio[value='01']").prop('checked', true);
		$("input:radio[name='ipTyChk']:radio[value='02']").prop('checked', false);
		$("input:radio[name='ipTyChk']:radio[value='03']").prop('checked', false);
		$("#dpIp_cidr").css("visibility","hidden");
		$("#dpIp_ed").css("visibility","hidden");
	}else if(val=="02"){
		$("input:radio[name='ipTyChk']:radio[value='01']").prop('checked', false);
		$("input:radio[name='ipTyChk']:radio[value='02']").prop('checked', true);
		$("input:radio[name='ipTyChk']:radio[value='03']").prop('checked', false);
		$("#dpIp_cidr").css("visibility","visible");
		$("#dpIp_ed").css("visibility","hidden");
// 		$("#dpIp_cidr").css("display","block");
// 		$("#dpIp_ed").css("display","none");
	}else if(val=="03"){
		$("input:radio[name='ipTyChk']:radio[value='01']").prop('checked', false);
		$("input:radio[name='ipTyChk']:radio[value='02']").prop('checked', false);
		$("input:radio[name='ipTyChk']:radio[value='03']").prop('checked', true);
		$("#dpIp_cidr").css("visibility","hidden");
		$("#dpIp_ed").css("visibility","visible");
// 		$("#dpIp_cidr").css("display","none");
// 		$("#dpIp_ed").css("display","block");
	}
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
				<input type="radio" name="ipTyChk" id="ipTyChk1" value="01" checked="checked" onclick="fn_radio(this.value,'con');">&nbsp;IP4&nbsp;&nbsp;
				<input type="radio" name="ipTyChk" id="ipTyChk2" value="02" onclick="fn_radio(this.value,'con');">&nbsp;IP4(서브넷)&nbsp;&nbsp;
				<input type="radio" name="ipTyChk" id="ipTyChk3" value="03" onclick="fn_radio(this.value,'con');">&nbsp;IP4(범위)&nbsp;&nbsp;
				<br><br>
				<span id= "dpIp">
					<input id="inDpIp1" name="inDpIp1" title="IP" type="text" value="" size="20" maxlength="3" style="width:5%;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/> . 
					<input id="inDpIp2" name="inDpIp2" title="IP" type="text" value="" size="20" maxlength="3" style="width:5%;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/> . 
					<input id="inDpIp3" name="inDpIp3" title="IP" type="text" value="" size="20" maxlength="3" style="width:5%;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/> . 
					<input id="inDpIp4" name="inDpIp4" title="IP" type="text" value="" size="20" maxlength="3" style="width:5%;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>
				</span>
				<span id= "dpIp_cidr">	
					&nbsp; / &nbsp;
					<input id="inDpIpCidr" name="inDpIpCidr" title="CIDR" type="text" value="" size="20" maxlength="2" style="width:5%;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>
				 </span>
				<span id= "dpIp_ed">
					&nbsp;~&nbsp;
					<input id="inDpIp1Ed" name="inDpIp1Ed" title="IP" type="text" value="" size="20" maxlength="3" style="width:5%;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/> . 
					<input id="inDpIp2Ed" name="inDpIp2Ed" title="IP" type="text" value="" size="20" maxlength="3" style="width:5%;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/> . 
					<input id="inDpIp3Ed" name="inDpIp3Ed" title="IP" type="text" value="" size="20" maxlength="3" style="width:5%;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/> . 
					<input id="inDpIp4Ed" name="inDpIp4Ed" title="IP" type="text" value="" size="20" maxlength="3" style="width:5%;" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>
				</span>
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