<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html>
<head>
	<title>login</title>
</head>
<body>

<h1>
	Hello login.jsp  
</h1>
<br><br>


<table align=center>
<tr><td align=center>
<h1>로그인</h1></td></tr>
<tr><td>아이디</td><td><input type=text name=userid id=userid></td></tr>

<tr><td>비밀번호</td><td><input type=password name=passcode id=passcode></td></tr>
<tr><td colspan=2 align=center><input type=button id=btn_login value='로그인'>&nbsp;
            <input type=button value='취소' id=btnCancel>
            </td></tr>
<tr><td colspan=2 align=right><a href="signon">회원가입</a></td></tr>
</table>



</body>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<script>
$(document)
.on('click','#btnCancel',function(){
	document.location="/login/";
})
.on('click','#btn_login',function(){
	let flag="";
	
	$.post("/login/login_check",{},function(txt){
		for(i=0;i<txt.length;i++){
			if(txt[i]['userid']==$('#userid').val() && txt[i]['passcode']==$('#passcode').val()){
				flag="good";
				break;
			}else{
				flag="nope";

			}
		}
		if(flag=="good"){
			alert("로그인성공");
			document.location="/login/finish_login?userid="+$('#userid').val();
		}else if(flag=="nope"){
			alert('로그인실패');
		}
		console.log(txt)
	},'json');
	})

</script>
</html>