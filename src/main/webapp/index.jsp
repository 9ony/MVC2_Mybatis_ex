<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<div class="container">
	<h1>Index</h1>
	<p><%=request.getAttribute("msg") %></p><br>
	<div style='color:tomato;font-size:2em'>
		el 표현식을 이용해서 출력할 수 있다.<br>
		${msg}
		<!-- 
		<%=request.getAttribute("msg") %> 와 ${msg} 비슷하다 
		key값이 안맞을때는 null이뜨고 el표현식은 아무것도 출력하지 않음
		-->
	</div>
</div>
<jsp:include page="/foot.jsp"/>