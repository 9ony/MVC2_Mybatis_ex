<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<div class="container">
	<h1>게시글 목록</h1>
	<p>
	<a href="boardWrite.do">글쓰기</a>|<a href="boardList.do">글목록</a>
	<h3>총 게시글 수 : ${totalCount}</h3>
	</p>
</div>
<jsp:include page="/foot.jsp"/>