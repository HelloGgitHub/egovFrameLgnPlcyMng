<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<title>�Ϲ�ȸ������ ���</title>
<!-- <script src="/js/egovframework/com/cmm/jquery-1.4.2.min.js"></script> -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/css/egovframework/com/com.css">

<script type="text/javascript"> 
     var bCancel = false; 

    function validateMberManageVO(form) {
        if (bCancel)
      return true; 
        else 
       return validateRequired(form) && validateMaxLength(form) && validatePassword1(form) && validatePassword2(form) && validatePassword3(form) && validatePassword4(form) && validateInteger(form) && validateEmail(form); 
   }

    function required () { 
     this.aa = new Array("mberId", "�Ϲ�ȸ�����̵���(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='20';  return this[varName];"));
     this.ab = new Array("mberNm", "�Ϲ�ȸ���̸���(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='50';  return this[varName];"));
     this.ac = new Array("password", "��й�ȣ��(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", " return this[varName];"));
     this.ad = new Array("passwordHint", "��й�ȣ��Ʈ��(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", " return this[varName];"));
     this.ae = new Array("passwordCnsr", "��й�ȣ������(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='100';  return this[varName];"));
     this.af = new Array("areaNo", "��������ȣ��(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
     this.ag = new Array("middleTelno", "���߰���ȭ��ȣ��(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
     this.ah = new Array("endTelno", "����������ȭ��ȣ��(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
     this.ai = new Array("moblphonNo", "�ڵ�����ȣ��(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='15';  return this[varName];"));
     this.aj = new Array("mberEmailAdres", "�̸����ּ���(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='50';  return this[varName];"));
     this.ak = new Array("zip", "�����ȣ��(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='6';  return this[varName];"));
     this.al = new Array("adres", "�ּ���(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", "this.maxlength='100';  return this[varName];"));
     this.am = new Array("mberSttus", "�Ϲ�ȸ�������ڵ���(��) �ʼ� �Է°��Դϴ�.", new Function ("varName", " return this[varName];"));
    } 

    function maxlength () { 
     this.aa = new Array("mberId", "�Ϲ�ȸ�����̵���(��) 20�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='20';  return this[varName];"));
     this.ab = new Array("mberNm", "�Ϲ�ȸ���̸���(��) 50�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='50';  return this[varName];"));
     this.ac = new Array("passwordCnsr", "��й�ȣ������(��) 100�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='100';  return this[varName];"));
     this.ad = new Array("areaNo", "��������ȣ��(��) 4�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
     this.ae = new Array("middleTelno", "���߰���ȭ��ȣ��(��) 4�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
     this.af = new Array("endTelno", "����������ȭ��ȣ��(��) 4�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
     this.ag = new Array("mberFxnum", "�ѽ���ȣ��(��) 15�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='15';  return this[varName];"));
     this.ah = new Array("moblphonNo", "�ڵ�����ȣ��(��) 15�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='15';  return this[varName];"));
     this.ai = new Array("mberEmailAdres", "�̸����ּ���(��) 50�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='50';  return this[varName];"));
     this.aj = new Array("adres", "�ּ���(��) 100�� �̻� �Է��Ҽ� �����ϴ�.", new Function ("varName", "this.maxlength='100';  return this[varName];"));
    } 

    function password1 () { 
     this.aa = new Array("password", "��й�ȣ��(��) 8~20�� ������ �Է��ؾ� �մϴ�.", new Function ("varName", " return this[varName];"));
    } 

    function password2 () { 
     this.aa = new Array("password", "��й�ȣ��(��) �ѱ�,Ư������,����� ������ �ʽ��ϴ�.", new Function ("varName", " return this[varName];"));
    } 

    function password3 () { 
     this.aa = new Array("password", "��й�ȣ��(��) �������� ���ڸ� 4���̻� �����ؼ� ����Ҽ� �����ϴ�.", new Function ("varName", " return this[varName];"));
    } 

    function password4 () { 
     this.aa = new Array("password", "��й�ȣ��(��) �ݺ��Ǵ� ���ڳ� ���ڸ� 4���̻� �����ؼ� ����� �� �����ϴ�.", new Function ("varName", " return this[varName];"));
    } 

    function IntegerValidations () { 
     this.aa = new Array("areaNo", "��������ȣ��(��) integer Ÿ���̾�� �մϴ�.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
     this.ab = new Array("middleTelno", "���߰���ȭ��ȣ��(��) integer Ÿ���̾�� �մϴ�.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
     this.ac = new Array("endTelno", "����������ȭ��ȣ��(��) integer Ÿ���̾�� �մϴ�.", new Function ("varName", "this.maxlength='4';  return this[varName];"));
    } 

    function email () { 
     this.aa = new Array("mberEmailAdres", "�̸����ּ���(��) ��ȿ���� ���� �̸��� �ּ��Դϴ�.", new Function ("varName", "this.maxlength='50';  return this[varName];"));
    } 

</script>
<script type="text/javascript" src="/js/egovframework/com/sym/ccm/zip/EgovZipPopup.js" ></script>
<script src="/js/egovframework/com/cmm/jquery.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">
/*********************************************************
 * �ʱ�ȭ
 ******************************************************** */
function fn_egov_init(){

	//��� ����
// 	fn_modal_setting();

}
/*********************************************************
 * ��޼���
 ******************************************************** */
function fn_modal_setting(){
	//��ư�� ��� ����
	$("#btnMbrId").egovModal( "egovModal" );
	
	//Ÿ��Ʋ ����
	$("#egovModal").setEgovModalTitle("���̵� �ߺ� Ȯ��"); //���̵� �ߺ� Ȯ��
	var content = "";
	content = content + "<div class='modal-alignL' style='margin:5px 0 0 0'>"+"����Ҿ��̵� :"+"</div>"; //����Ҿ��̵�
	content = content + "<div class='modal-alignL'>"+"<input type='text' id='checkIdModal' name='checkIdModal' value='' size='20' maxlength='20' />"+"</div>";	
	content += "<div style='clear:both;'></div>";
	content += "<div id='divModalResult' style='margin:10px 0 0 0'>��� : �ߺ�Ȯ���� �����Ͻʽÿ�.</div>"; //��� : �ߺ�Ȯ���� �����Ͻʽÿ�.
	//��� body ����
	$("#egovModal").setEgovModalBody(content);

	var footer = "";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalOk' onclick='fn_id_checkOk()'>Ȯ��</button></div>";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalSelect' onclick='fn_id_check()'>��ȸ</button></div>";
	footer += "<span class='btn_style1 blue' id='btnModalOk' onclick='fn_id_checkOk()'><a href='#'>Ȯ��</a></span>&nbsp;";
	footer += "<span class='btn_style1 blue' id='btnModalSelect' onclick='fn_id_check()'><a href='#'>��ȸ</a></span>&nbsp;";
	//��� footer ����
	$("#egovModal").setEgovModalfooter(footer);
	
	//�����̺�Ʈó��
	$("input[name=checkIdModal]").keydown(function (key) {
		if(key.keyCode == 13){
			fn_id_check();	
		}
	});
	footer = null;
	content = null;
}
/*********************************************************
 * ���̵� üũ AJAX
 ******************************************************** */
function fn_id_check(){	
	$.ajax({
		type:"POST",
		url:"/egovframework-all-in-one/uss/umt/EgovIdDplctCnfirmAjax.do",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				
				if(returnData.usedCnt > 0 ){
					//����Ҽ� ���� ���̵��Դϴ�.
					$("#divModalResult").html("<font color='red'>��� : ["+returnData.checkId+"]�� ����Ҽ� ���� ���̵��Դϴ�.</font>");
				}else{
					//��밡���� ���̵��Դϴ�.
					$("#divModalResult").html("<font color='blue'>��� : ["+returnData.checkId+"]�� ��밡���� ���̵��Դϴ�.</font>");
				}
			}else{ alert("ERROR!");return;} 
		}
		});
}

/*********************************************************
 * ���̵� üũ Ȯ��
 ******************************************************** */
function fn_id_checkOk(){
	$.ajax({
		type:"POST",
		url:"/egovframework-all-in-one/uss/umt/EgovIdDplctCnfirmAjax.do",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					alert("����� �Ұ����� ���̵� �Դϴ�"); //����� �Ұ����� ���̵� �Դϴ�.
					return;
				}else{
					
					$("input[name=mberId]").val(returnData.checkId);
					$("#egovModal").setEgovModalClose();
				}
			}else{ alert("ERROR!");return;} 
		}
		});
}


function fnIdCheck1(){
    var retVal;
    var url = "/egovframework-all-in-one/uss/umt/EgovIdDplctCnfirmView.do";
    var varParam = new Object();
    varParam.checkId = document.mberManageVO.mberId.value;
    var openParam = "dialogWidth:303px;dialogHeight:250px;scroll:no;status:no;center:yes;resizable:yes;";
        
    return false;
    retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
    	document.mberManageVO.mberId.value = retVal;
    }
}

function showModalDialogCallback(retVal) {
	if(retVal) {
	    document.mberManageVO.mberId.value = retVal;
	}
}

function fnListPage(){
    document.mberManageVO.action = "/egovframework-all-in-one/uss/umt/EgovMberManage.do";
    document.mberManageVO.submit();
}

function fnInsert(form){
	
	if(confirm("����Ͻðڽ��ϱ�?")){	
		if(validateMberManageVO(form)){
			if(form.password.value != form.password2.value){
	            alert("��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.");
	            return false;
	        }
			form.submit();
			return true;
	    }
	}
}

/*********************************************************
 * ȸ����� ��û
 ******************************************************** */
function id_insert(){

	var memberData = new Object();
	
		memberData.mberId				=	$("#mberId").val();
		memberData.mberNm				=	$("#mberNm").val();
		memberData.password			=	$("#password").val();
		memberData.passwordHint		=	$("#passwordHint").val();
		memberData.passwordCnsr		=	$("#passwordCnsr").val();
		memberData.sexdstnCode		=	$("#sexdstnCode").val();
		memberData.areaNo 				=	$("#areaNo").val();
		memberData.middleTelno		= 	$("#middleTelno").val();
		memberData.endTelno			=	$("#endTelno").val();
		memberData.mberFxnum		=	$("#mberFxnum").val();
		memberData.moblphonNo		=	$("#moblphonNo").val();
		memberData.mberEmailAdres	=	$("#mberEmailAdres").val();
		memberData.zip					=	$("#zip").val();
		memberData.adres				=	$("#adres").val();
		memberData.detailAdres		=	$("#detailAdres").val();
// 		memberData.mberSttus			=	$("#mberSttus").val();
		memberData.mberSttus			=	"A";

	var jsonData = JSON.stringify(memberData);
	console.log(jsonData);
	
	$.ajax({
		type:"POST",
		url:"http://192.168.56.1:8081/member/AprovRequest",
		contentType: 'application/json; charset=utf-8',
		dataType:'json',
		data:jsonData,
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				alert("success");
			}else{
				 alert("ERROR!");return;
			} 
		}
	});
}

/*********************************************************
 * ȸ������ ����
 ******************************************************** */
function id_update(){

	var memberData = new Object();
	
		memberData.mberId				=	$("#mberId").val();
		memberData.mberNm				=	$("#mberNm").val();
		memberData.password			=	$("#password").val();
		memberData.passwordHint		=	$("#passwordHint").val();
		memberData.passwordCnsr		=	$("#passwordCnsr").val();
		memberData.sexdstnCode		=	$("#sexdstnCode").val();
		memberData.areaNo 				=	$("#areaNo").val();
		memberData.middleTelno		= 	$("#middleTelno").val();
		memberData.endTelno			=	$("#endTelno").val();
		memberData.mberFxnum		=	$("#mberFxnum").val();
		memberData.moblphonNo		=	$("#moblphonNo").val();
		memberData.mberEmailAdres	=	$("#mberEmailAdres").val();
		memberData.zip					=	$("#zip").val();
		memberData.adres				=	$("#adres").val();
		memberData.detailAdres		=	$("#detailAdres").val();
		memberData.mberSttus			=	$("#mberSttus").val();

	var jsonData = JSON.stringify(memberData);
	console.log(jsonData);
	
	$.ajax({
		type:"PUT",
		url:"http://192.168.56.1:8081/member/changeMbrInfo",
		contentType: 'application/json; charset=utf-8',
		dataType:'json',
		data:jsonData,
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				alert("success");
			}else{
				alert("ERROR!");return;
			} 
		}
	});
}

/*********************************************************
 * ȸ������ ����
 ******************************************************** */
function id_delete(){

	var memberData = new Object();
		memberData.mberId				=	$("#mberId").val();

	var jsonData = JSON.stringify(memberData);
	console.log(jsonData);
	
	$.ajax({
		type:"DELETE",
		url:"http://192.168.56.1:8081/member/deleteMbrInfo",
		contentType: 'application/json; charset=utf-8',
		dataType:'json',
		data:jsonData,
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				alert("success");
			}else{
				alert("ERROR!");return;
			} 
		}
	});
}

/*********************************************************
 * ȸ������ ���� ����
 ******************************************************** */
function id_updateState(){

	var memberData = new Object();
		memberData.mberId				=	$("#mberId").val();
		memberData.mberSttus			=	$("#mberState").val();
		
	var jsonData = JSON.stringify(memberData);
	console.log(jsonData);
	
	$.ajax({
		type:"PUT",
		url:"http://192.168.56.1:8081/member/changeMbrState",
		contentType: 'application/json; charset=utf-8',
		dataType:'json',
		data:jsonData,
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				alert("success");
			}else{
				alert("ERROR!");return;
			} 
		}
	});
}
</script>
<style>
.modal-content {width: 400px;}
</style>
</head>
<body>
<!-- <form id="mberManageVO" name="mberManageVO" >  -->
	<div class="wTableFrm">
	<!-- Ÿ��Ʋ -->
	<h2>�Ϲ�ȸ������ ���</h2>

	<!-- ����� -->
	<table class="wTable" summary="�Ϲ�ȸ�������� ������ ���� ����� ����մϴ�.">
	<caption>�Ϲ�ȸ������ ���</caption>
	<colgroup>
		<col style="width: 22%;"><col style="width: ;">
	</colgroup>
	<tbody>
		<!-- �Է�/���� -->
		<!-- �Ϲ�ȸ�����̵� -->
		<tr>
			<th><label for="mberId">�Ϲ�ȸ�����̵�</label> <span class="pilsu">*</span></th>
			<td class="left">
<!-- 				<input id="mberId" name="mberId" title="�Ϲ�ȸ�����̵� �Է�" style="width:80%;" readonly="readonly" type="text" value="" size="20" maxlength="20"/> -->
				<input id="mberId" name="mberId" title="�Ϲ�ȸ�����̵� �Է�" style="width:80%;" type="text" value="" size="20" maxlength="20"/>
				<button id="btnMbrId" class="btn_s2" onClick="return false;" title="���� ��ư">�ߺ����̵� �˻�</button>
				<div></div>
			</td>
		</tr>
		
		<!-- �Ϲ�ȸ���̸� -->
		<tr>
			<th><label for="mberNm">�Ϲ�ȸ���̸�</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="mberNm" name="mberNm" title="�Ϲ�ȸ���̸� �Է�" type="text" value="" size="50" maxlength="50"/>
				<div></div> 
			</td>
		</tr>
		<!-- ��й�ȣ -->
		
		<tr>
			<th><label for="password">��й�ȣ</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="password" name="password" title="��й�ȣ �Է�" type="password" value="" size="50" maxlength="20"/>
				<div></div> 
			</td>
		</tr>
		<!-- ��й�ȣȮ�� -->
		
		<tr>
			<th><label for="password2">��й�ȣȮ��</label> <span class="pilsu">*</span></th>
			<td class="left">
			<input name="password2" id="password2" title="��й�ȣȮ�� �Է�" type="password" size="50" maxlength="20" />
			</td>
		</tr>
		<!-- ��й�ȣ��Ʈ -->
		
		<tr>
			<th><label for="passwordHint">��й�ȣ��Ʈ</label> <span class="pilsu">*</span></th>
			<td class="left">
				<select id="passwordHint" name="passwordHint" title="��й�ȣ��Ʈ ����">
					<option value="" selected="selected">--�����ϼ���--</option>
					<option value=" P01">���� ��￡ ���� ��Ҵ�?</option><option value=" P02">���� �¿����?</option><option value=" P03">���� ���� ��1ȣ��?</option><option value=" P04">���� ��￡ ���� ������ ������?</option><option value=" P05">�ٸ� ����� �𸣴� ������ ��ü�����?</option><option value=" P06">�������� ����ϰ� ���� ��¥��?</option><option value=" P07">�޾Ҵ� ���� �� ��￡ ���� ��Ư�� ������?</option><option value=" P08">���� �������� ģ�� �̸���?</option><option value=" P09">�λ� ��� ���� å �̸���?</option><option value=" P10">���� �����ϴ� �ι���?</option><option value=" P11">���� �뷡�� ��â����?</option><option value=" P12">���� ������ �� ��ȭ��?</option><option value=" P13">�����ϴ� �������� �̸���?</option>
				</select>
				<div></div>
			</td>
		</tr>
		<!-- ��й�ȣ���� -->
		
		<tr>
			<th><label for="passwordCnsr">��й�ȣ����</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input id="passwordCnsr" name="passwordCnsr" class="txaIpUmt" title="��й�ȣ���� �Է�" type="text" value="" size="50" maxlength="100"/>
				<div></div>
			</td>
		</tr>
		<!-- ���������ڵ� -->
		
		<tr>
			<th><label for="sexdstnCode">���������ڵ�</label></th>
			<td class="left">
				<select id="sexdstnCode" name="sexdstnCode" title="���������ڵ� ����">
					<option value="" selected="selected">--�����ϼ���--</option>
					<option value="M">����</option><option value="F">����</option>
				</select>
			</td>
		</tr>
		<!-- ��ȭ��ȣ -->
		
		<tr>
			<th><label for="areaNo">��ȭ��ȣ</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <input id="areaNo" name="areaNo" class="txaIpUmt" title="��ȭ��ȣ" style="width:40px;" type="text" value="" size="5" maxlength="4"/>
                    - <input id="middleTelno" name="middleTelno" class="txaIpUmt" style="width:40px;" type="text" value="" size="5" maxlength="4"/>
                    - <input id="endTelno" name="endTelno" class="txaIpUmt" style="width:40px;" type="text" value="" size="5" maxlength="4"/>
			</td>
		</tr>
		<!-- �ѽ���ȣ -->
		
		<tr>
			<th><label for="mberFxnum">�ѽ���ȣ</label></th>
			<td class="left">
                    <input id="mberFxnum" name="mberFxnum" class="txaIpUmt" title="�ѽ���ȣ �Է�" type="text" value="" size="20" maxlength="15"/>
                    <div></div>
			</td>
		</tr>
		<!-- �������ȣ -->
		
		<tr>
			<th><label for="moblphonNo">�ڵ�����ȣ</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <input id="moblphonNo" name="moblphonNo" class="txaIpUmt" title="�ڵ�����ȣ �Է�" type="text" value="" size="20" maxlength="15"/>
                    <div></div>
			</td>
		</tr>
		<!-- �̸����ּ� -->
		
		<tr>
			<th><label for="mberEmailAdres">�̸����ּ�</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <input id="mberEmailAdres" name="mberEmailAdres" class="txaIpUmt" title="�̸����ּ� �Է�" type="text" value="" size="30" maxlength="50"/>
                    <div></div>
			</td>
		</tr>
		<!-- �����ȣ -->
		
		<tr>
			<th><label for="zip">�����ȣ</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <input name="zip" id="zip" title="�����ȣ �Է�" type="text" size="20" value="" maxlength="6" style="width:60px;" />
                    <!-- form:hidden path="zip" id="zip" --> 
                    <!-- <button class="btn_s2" onClick="fn_egov_ZipSearch(document.mberManageVO, document.mberManageVO.zip, document.mberManageVO.zip_view, document.mberManageVO.adres);return false;" title="���� ��ư">�����ȣ�˻�</button>  -->
                    <div></div>
			</td>
		</tr>
		<!-- �ּ� -->
		
		<tr>
			<th><label for="adres">�ּ�</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <input id="adres" name="adres" class="txaIpUmt" title="�ּ� �Է�" type="text" value="" size="70" maxlength="100"/>
                    <div></div>
			</td>
		</tr>
		<!-- ���ּ� -->
		
		<tr>
			<th><label for="detailAdres">���ּ�</label></th>
			<td class="left">
                    <input id="detailAdres" name="detailAdres" class="txaIpUmt" title="���ּ� �Է�" type="text" value="" size="70" maxlength="100"/>
                    <div></div>
			</td>
		</tr>
<!-- 		<input type="hidden" name="mberSttus" value="DEFAULT" /> -->
	</tbody>
	</table>
	<br>
	<!-- �ϴ� ��ư --> 
	<button title="ȸ�����Խ�û" onclick="id_insert();" >ȸ�����Խ�û</button>
	<button title="ȸ����������" onclick="id_update();" >ȸ����������</button>
	<button title="ȸ����������" onclick="id_delete();" >ȸ����������</button>
	<button title="ȸ������" onclick="id_updateState();" >ȸ������</button>
	<br>
		<tr>
			<th><label for="mberSttus">ȸ�����Ի���</label> <span class="pilsu">*</span></th>
			<td class="left">
				<select id="mberSttus" name="mberSttus" title="ȸ�����Ի���">
					<option value="" selected="selected">--�����ϼ���--</option>
					<option value="A">��û</option>
					<option value="P">����</option>
					<option value="D">����</option>
				</select>
			</td>
		</tr>
		<!-- ��й�ȣ���� -->
 <!-- �����ȣ�˻� -->
 <input type="hidden" name="zip_url" value="/egovframework-all-in-one/sym/ccm/zip/EgovCcmZipSearchPopup.do" />
<!-- </form> -->

<!-- Egov Modal include  -->

</body>
</html>
