<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- foot.jsp -->
<% 
	String myctx=request.getContextPath();
%>
<style>
	.mydiv{
		width:90%;
		margin:auto;
		padding:1em;
		height:130px;
		background-color:#efefef;
		display:table;
	}
	.mysell{
		text-align:center;
		width:100%;
		height:100%;
		display:table-cell;
		vertical-allign:middle;
		
	}
</style>
   </article>
    <!-- 사이드 영역 -------------------------------->
    <aside>
        <!-- 사이드 -->
        <nav>
        <!-- 
        	request에 저장한 값=> requestScope.key
        	session에 저장한 값=> sessionScope.key
        	로 해야되지만
        	${key}로 해도된다
        	${key}로하면 requestScope의 동일한 키가있는지 확인하고 없으면 sessionScope.key에서 찾는다
         -->
        <c:if test="${sessionScope.loginUser!=null }">
        	<div class="mydiv">
		       	<div id="mysell">
			       	<h3>${loginUser.name } [${loginUser.userid }]</h3>
			       	<h3>로그인중.....</h3>
			       	<br>
			       	<h4><a href="${pageContext.request.contextPath}/logout.do">로그아웃</a></h4>
		       	</div>
        	</div>
        </c:if>
            <ul>
                <li><a onClick="history.back()" >뒤로가기</a></li>
                <li><a href="<%=myctx %>/user/myPage.do">마이페이지</a></li>
            </ul>
        </nav>

    </aside>
    <div class="clear"></div>
    <!-- 푸터 영역 ------------------------------>
    <footer>
        <!-- footer ----------------------->
        &copy;Copyright/회사소개
    </footer>
        
</div>
<!-- div#wrap. container end -->
</body>
</html>
