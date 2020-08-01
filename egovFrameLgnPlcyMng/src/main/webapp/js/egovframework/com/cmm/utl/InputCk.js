
function chkPwd(str){
	var pw = str;
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
	
	if(pw.length < 8 || pw.length > 20){
		alert("8자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	}
	
	if(pw.search(/₩s/) != -1){
		alert("비밀번호는 공백없이 입력해주세요.");
		return false;
	} 
	
	if(num < 0 || eng < 0 || spe < 0 ){
		alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		return false;
	}
	
	return true;
}


function CheckEmail(str){                                                 
     var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
     if(!reg_email.test(str)) {                            
    	 alert("올바른 이메일 주소가 아닙니다.");
         return false;         
     }else {                       
          return true;         
     }                            
} 

function CheckNumber(str){
    if(event.keyCode<48 || event.keyCode>57){
    	alert("숫자만 입력 가능합니다.");
    	return false;
    }
    return true;
}


//if(!	chkPwd(    $.trim($('#mpassword').val())     )   ){
//	$('#mpassword').val('');
//	$('#mpassword').focus();
//	return false;
//}

