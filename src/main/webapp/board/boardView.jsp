<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 주요기능 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- fmt:포맷하는기능이 모여있음 -->
<jsp:include page="/top.jsp"/>

<div class="container">
	<h1> ${board.userid}님의 글 </h1>
	<br>
	<a href="boardWrite.do">글쓰기</a>
	<a href="boardList.do">글목록</a>
	<br>
	<table border="1" style="width:90%; margin:auto">
		<tr>
			<td width="20%">글번호</td>
			<td width="30%">${board.num}</td>
			<td width="20%">작성일</td>
			<td width="30%"><fmt:formatDate value="${board.wdate }" pattern="yyyy년MM월dd일 HH시mm분"/></td>
			<!-- pattern 시간설정 hh는 12시간 HH는 24시간으로 표시 -->
		</tr>
		<tr>
			<td width="20%">작성자</td>
			<td width="30%">${board.userid}</td>
			<td width="20%">첨부파일</td>
			<td width="30%">
			
			<a href="Upload/${board.filename }" download>
			<img src="images/attach.JPG" width="20px"">${board.filename} </a> <!-- 다운로드 링크로 설정 -->
			[ ${board.filesize } bytes]</td>
		</tr>
		<tr>
			<td width="20%">제목</td>
			<td width="30%" colspan="3">${board.subject}</td>
		</tr>
		<tr>	
			<td width="20%">글내용</td>
			<td width="30%" colspan="3" style="height:300px">${board.content}</td>
		</tr>
		<tr>
			<td colspan="4">
				<a href="boardList.do">글목록</a>|
				<a href="#" onclick="goEdit()">수정</a>|
				<a href="javascript:goDel()">삭제</a>
			</td>
		</tr>
	</table>
</div>
<!-- 수정 or 삭제를 위한 form -->
<form name="bf" id="bf">
	<input type="hidden" name="num" id="num" value="${board.num }">
</form>
<!--  ---------------------->
<script>
	function goEdit(){
		bf.action="./user/boardEditForm.do";
		bf.method='post'; //메소드 post로변경
		bf.submit();
	}
	function goDel(){
		bf.action="./user/boardDel.do";
		bf.method='post'; //메소드 post로변경
		bf.submit();
	}
</script>
<jsp:include page="/foot.jsp"/>