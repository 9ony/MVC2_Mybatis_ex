<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<style>
	.container{
		padding: 8px;
	}
	#btnlogin{
		padding: 5px;
		background-color: #abcabc;
		width:20%;
		border: 1px solid silver;
	}
</style>
<script type="text/javascript">
	let login_check=function(){
		if(!loginF.userid.value){
			alert('아이디를 입력하세요');
			loginF.userid.focus();
			return false; /* onsubmit에 false를 보내줘야함 */
		}
		if(!loginF.pwd.value){
			alert('비밀번호를 입력하세요')
			loginF.pwd.select();
			return false;
		}
		return true;
	}
</script>
<%
	/* 쿠키를 요청할땐 배열을 생성해서 요청함
	uid인 키값의 쿠키가 있으면 uid에 value값을 넣어준다
	키값은 getName() , value값은 getValue()*/
	Cookie[] cks=request.getCookies();
	String uid="";
	boolean flag=false;
	if(cks!=null){
		for(Cookie ck:cks){
			String key=ck.getName(); 
			if(key.equals("uid")){
				uid = ck.getValue();
				flag=true;
				break;
			}
		}
	}
%>
<div class="SignUp">
	<h1>Login</h1>
	<div class="container">
		<form name="loginF" action="loginEnd.do" method="post" onsubmit="return login_check()">
		<!-- onsubmit이벤트 핸들러는 form이 전송될때 실행된다.
			onsubmit에서 호출하는 함수의 반환값에 따라 전송 여부를 결정한다.
			true를 반환하면 전송하고, false를 반환하면 전송하지 않는다.
		 -->
			<table border="1" style="width:60%;margin:auto">
				<tr>
					<td width="20%" class="m1"><b>아이디</b></td>
					<td width="80%" class="m2">
						<input type="text" name="userid" id="userid" value="<%=uid%>" placeholder="User ID">
					</td>
				</tr>
				<tr>
					<td width="20%" class="m1"><b>비밀번호</b></td>
					<td width="80%" class="m2">
						<input type="password" name="pwd" id="pwd" placeholder="Password">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="container">
						<label for="saveId">
							<input type="checkbox" name="saveId" id="saveId" <%=(flag)?"checked":"" %>>아이디 저장
						</label>
						<button id="btnlogin"> 로그인 </button>
						<!-- default가 submit버튼 -->
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<jsp:include page="/foot.jsp" />