<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 주요기능 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- fmt:포맷하는기능이 모여있음 -->
<jsp:include page="/top.jsp"/>
<style>
	#boardWrap{
		width:94%;
		margin:auto;
	}
	#boardList>li{
		list-style-type:none;
		float:left;
		height:40px;
		line-height:40px;
		border-bottom:1px solid #ddd;
		width:15%;
	}
	#boardList>li:nth-child(4n+2){
		width:55%;
		white-space: nowrap;
		overflow:hidden;
		text-overflow:ellipsis;
	}
</style>


<div class="container">
	<h1>게시글 목록</h1>
	<p>
	<a href="boardWrite.do">글쓰기</a>|<a href="boardList.do">글목록</a>
	<%-- <h3>총 게시글 수 : ${totalCount}</h3> --%>
	<%-- ${listBoard} --%>
	</p>
	<div id="boardWrap">
		<ul id="boardList" class="boardList">
			<li>번호</li>
			<li>제목</li>
			<li>글쓴이</li>
			<li>등록일</li>
			<!--  ------------------------------->
			<c:forEach var="vo" items="${listBoard}">
				<!-- 
					var: 변수명 지정한다
					items : 자료구조(List , map 등..)
					begin : 시작값
					end   : 반복문 마지막값
					step : 증가치
					varstatus : 반복문의 상태정보를 담아줄 변수명을 지정
				 	출력은 변수명 \. 프로퍼티명 ex)vo.num , vo.subject
				 -->
			<li>${vo.num}</li>
			<li><a href="boardView.do?num=${vo.num }">${vo.subject}</a></li>
			<li>${vo.userid }</li>
			<li><fmt:formatDate value="${vo.wdate }" pattern="yyyy-MM-dd"/></li>
			</c:forEach>
			<!--  ------------------------------->
		</ul>
	</div>
</div>
<jsp:include page="/foot.jsp"/>