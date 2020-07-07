<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="EUC-KR" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Ϲ�ȸ������ ���</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="/css/egovframework/com/com.css">

<script src="/js/egovframework/com/cmm/jquery-1.4.2.min.js"></script>
<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function(){
	makeGrid();
    $.ajax({
        type : "GET", //���۹���� �����Ѵ� (POST,GET)
        url : "http://localhost:9085/user/list",//ȣ�� URL�� �����Ѵ�. GET����ϰ�� �ڿ� �Ķ�Ƽ�͸� �ٿ��� ����ص��ȴ�.
        dataType : "text",//ȣ���� �������� �����̴�. xml,json,html,text���� ���� ����� ����� �� �ִ�.
        error : function(){    //error: whenError
            alert("��Ž���!!!!");
        },
        success : function(Parse_data){    //success: whenSuccess,
            $("#Parse_Area").html(Parse_data); //div�� �޾ƿ� ���� �ִ´�.
            
            console.log(Parse_data);
//             alert("��� ������ �� : " + Parse_data);
        }
    });
});


function makeGrid(){

 	var ihtml = 'DDddddddddddd';
 	ihtml = ihtml + '<table class="board_list" summary="�Ϲ�ȸ�������� ������ ���� ����� ����մϴ�.">';
 	ihtml = ihtml + '<colgroup><col style="width: 5%;"><col style="width: 3%;"><col style="width: 15%;"><col style="width: 15%;"><col style="width: 20%;"><col style="width: 13%;"><col style="width: 10%;"><col style="width: ;"></colgroup>';
 	ihtml = ihtml + '<thead>';
 	ihtml = ihtml + '<tr>';
 	ihtml = ihtml + '<th>��ȣ</th>';
 	ihtml = ihtml + '<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="��ü����üũ�ڽ�"></th>';
 	ihtml = ihtml + '<th class="board_th_link">���̵�</th>';
 	ihtml = ihtml + '<th>������̸�</th>';
 	ihtml = ihtml + '<th>������̸���</th>';
 	ihtml = ihtml + '<th>��ȭ��ȣ</th>';
 	ihtml = ihtml + '<th>�����</th>';
 	ihtml = ihtml + '<th>���Ի���</th>';
 	ihtml = ihtml + '</tr>';
 	ihtml = ihtml + '</thead>';

 	ihtml = ihtml + '<tbody class="ov">';
 	ihtml = ihtml + '<tr>';
 	ihtml = ihtml + '<td>1</td>';
 	ihtml = ihtml + '<td>';
 	ihtml = ihtml + '<input name="checkField" title="checkField 1" type="checkbox"/>';
 	ihtml = ihtml + '<input name="checkId" type="hidden" value="USR01:USRCNFRM_00000000023"/>';
 	ihtml = ihtml + '</td>';
 	ihtml = ihtml + '<td><a href="/egovframework-all-in-one/uss/umt/EgovMberSelectUpdtView.do?selectedId=USRCNFRM_00000000023"  onclick="javascript:fnSelectUser(\'USR01:USRCNFRM_00000000023\'); return false;">crossent</a></td>';
 	ihtml = ihtml + '<td>ũ�μ�Ʈ</td>';
 	ihtml = ihtml + '<td>crossent@crossent.co.kr</td>';
 	ihtml = ihtml + '<td>02)115-1155</td>';
 	ihtml = ihtml + '<td>2020-03-05</td>';
 	ihtml = ihtml + '<td>';
 	ihtml = ihtml + 'ȸ�� ���� ���� ����';
 	ihtml = ihtml + '</td>';
 	ihtml = ihtml + '</tr>';
	
	var randValNode = document.getElementById("grd");
	randValNode.innerHTML = "<b><font color='red'>"+Math.random()+"</font></b>" + ihtml;

}


</script>
</head>

<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle">�ڹٽ�ũ��Ʈ�� �������� �ʴ� ������������ �Ϻ� ����� ����Ͻ� �� �����ϴ�.</noscript>

<form name="listForm" action="http://localhost:8081/MemberList" method="post"> 
<div class="board">
	<h1>�Ϲ�ȸ������ ���</h1>
	
	<!-- �˻����� -->
	<div class="search_box" title="�� ���̾ƿ��� �ϴ� ������ ���� �˻� ������ �����Ǿ� �ֽ��ϴ�.">
		<ul>
			<li><!-- ����-->
                <select name="sbscrbSttus" id="sbscrbSttus" title="���Ի������� ����">
                    <option value="0" selected="selected" >����(��ü)</option><!-- ����(��ü) -->
                    <option value="A"  >���Խ�û</option><!-- ���Խ�û -->
                    <option value="D"  >����</option><!-- ���� -->
                    <option value="P"  >����</option><!-- ���� -->
                </select>
			</li>
			<li><!-- ���� -->
                <select name="searchCondition" id="searchCondition" title="��ȸ���� ����"><!--  -->
                    <option value="0"  >ID</option><!-- ID  -->
                    <option value="1" selected="selected" >�̸�</option><!-- Name -->
                </select>
			</li>
			<!-- �˻�Ű���� �� ��ȸ��ư -->
			<li>
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="�˻��� �Է�" value=''  maxlength="255" >
				<input type="submit" class="s_btn" value="��ȸ" title="��ȸ ��ư" />
				<input type="button" class="s_btn" onClick="fnDeleteUser(); return false;" value="����" title="���� ��ư" />
				<span class="btn_b"><a href="/egovframework-all-in-one/uss/umt/EgovMberInsertView.do" onClick="fnAddUserView(); return false;"  title="��� ��ư">���</a></span>
			</li>
		</ul>
	</div>
	
<div id="grd"></div>

<table class="board_list" summary="�Ϲ�ȸ�������� ������ ���� ����� ����մϴ�.">
	<caption>�Ϲ�ȸ������ ���</caption>
	<colgroup>
		<col style="width: 5%;">
		<col style="width: 3%;">
		
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 20%;">
		<col style="width: 13%;">
		<col style="width: 10%;">
		<col style="width: ;">
	</colgroup>
	<thead>
	<tr>
		<th>��ȣ</th><!-- ��ȣ -->
		<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="��ü����üũ�ڽ�"></th><!-- ��ü���� -->
		
		<th class="board_th_link">���̵�</th><!--���̵� -->
		<th>������̸�</th><!-- ������̸� -->
		<th>������̸���</th><!-- ������̸��� -->
		<th>��ȭ��ȣ</th><!-- ��ȭ��ȣ -->
		<th>�����</th><!-- ����� -->
		<th>���Ի���</th><!-- ���Ի��� -->

	</tr>
	</thead>
	<tbody class="ov">
	
	
	<tr>
	    <td>1</td>
	    <td>
	        <input name="checkField" title="checkField 1" type="checkbox"/>
	        <input name="checkId" type="hidden" value="USR01:USRCNFRM_00000000023"/>
	    </td>
	    <td><a href="/egovframework-all-in-one/uss/umt/EgovMberSelectUpdtView.do?selectedId=USRCNFRM_00000000023"  onclick="javascript:fnSelectUser('USR01:USRCNFRM_00000000023'); return false;">crossent</a></td>
	    <td>ũ�μ�Ʈ</td>
	    <td>crossent@crossent.co.kr</td>
	    <td>02)115-1155</td>
	    <td>2020-03-05</td>
	    <td>
	        
	            
	        
	            ȸ�� ���� ���� ����
	        
	            
	        
	    </td>
	</tr>
	
	<tr>
	    <td>2</td>
	    <td>
	        <input name="checkField" title="checkField 2" type="checkbox"/>
	        <input name="checkId" type="hidden" value="USR01:USRCNFRM_00000000013"/>
	    </td>
	    <td><a href="/egovframework-all-in-one/uss/umt/EgovMberSelectUpdtView.do?selectedId=USRCNFRM_00000000013"  onclick="javascript:fnSelectUser('USR01:USRCNFRM_00000000013'); return false;">egov2</a></td>
	    <td>egov2</td>
	    <td>egov2</td>
	    <td>1111)111-111</td>
	    <td>2020-03-03</td>
	    <td>
	        
	            ȸ�� ���� ��û ����
	        
	            
	        
	            
	        
	    </td>
	</tr>
	
	<tr>
	    <td>3</td>
	    <td>
	        <input name="checkField" title="checkField 3" type="checkbox"/>
	        <input name="checkId" type="hidden" value="USR01:USRCNFRM_00000000004"/>
	    </td>
	    <td><a href="/egovframework-all-in-one/uss/umt/EgovMberSelectUpdtView.do?selectedId=USRCNFRM_00000000004"  onclick="javascript:fnSelectUser('USR01:USRCNFRM_00000000004'); return false;">egov</a></td>
	    <td>aaaa</td>
	    <td>11111111111</td>
	    <td>1111)1111-1111</td>
	    <td>2020-02-26</td>
	    <td>
	        
	            
	        
	            ȸ�� ���� ���� ����
	        
	            
	        
	    </td>
	</tr>
	
	<tr>
	    <td>4</td>
	    <td>
	        <input name="checkField" title="checkField 4" type="checkbox"/>
	        <input name="checkId" type="hidden" value="USR01:USRCNFRM_00000000003"/>
	    </td>
	    <td><a href="/egovframework-all-in-one/uss/umt/EgovMberSelectUpdtView.do?selectedId=USRCNFRM_00000000003"  onclick="javascript:fnSelectUser('USR01:USRCNFRM_00000000003'); return false;">egovtest</a></td>
	    <td>aaaa</td>
	    <td>11111111111</td>
	    <td>1111)1111-1111</td>
	    <td>2020-02-26</td>
	    <td>
	        
	            ȸ�� ���� ��û ����
	        
	            
	        
	            
	        
	    </td>
	</tr>
	
	<tr>
	    <td>5</td>
	    <td>
	        <input name="checkField" title="checkField 5" type="checkbox"/>
	        <input name="checkId" type="hidden" value="USR01:USRCNFRM_00000000001"/>
	    </td>
	    <td><a href="/egovframework-all-in-one/uss/umt/EgovMberSelectUpdtView.do?selectedId=USRCNFRM_00000000001"  onclick="javascript:fnSelectUser('USR01:USRCNFRM_00000000001'); return false;">USER</a></td>
	    <td>�Ϲ�ȸ��</td>
	    <td>egovframesupport@gmail.com</td>
	    <td>02)1566-2059</td>
	    <td>2020-02-25</td>
	    <td>
	        
	            
	        
	            ȸ�� ���� ���� ����
	        
	            
	        
	    </td>
	</tr>
	
	</tbody>
	</table>
	
	<!-- paging navigation -->
	<div class="pagination">
		<ul><li class="current"><a onClick="return false;">1</a></li>
</ul>
	</div>

<input name="selectedId" type="hidden" />
<input name="checkedIdForDel" type="hidden" />
<input name="pageIndex" type="hidden" value="1"/>
</div>
</form>

</body>
</html>