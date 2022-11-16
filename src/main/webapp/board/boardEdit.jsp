<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/top.jsp"/>
<!-- boardWrite 스타일 -->
<style>
	ul li{
		list-style-type: none;
	}
	#boardFrm li{
		padding: 10px 5px;
		text-align: left;
	}
	#subject, #content, #filename{
		width: 100%;
	}
	#boardFrm input{
		padding : 5px;
	}
div.container{
	width:98%;
	margin:auto;
}
div.bbs{
	width:90%;
	margin:auto;
}
.btn{
	padding: 4px;
	background-color: #efefef;
	border: 1px solid silver;
	width: 200px;
}
</style>
<!-- jQuery cdn  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- CKE4 에디터 cdn -->
<script src="https://cdn.ckeditor.com/4.20.0/standard/ckeditor.js"></script>
<!-- 유효성검사 스크립트 -->
<script>
	$(function(){
		CKEDITOR.replace("content");
	})

	let board_check=function(){
		if($('#subject').val()==''){
			alert('제목을 입력하세요');
			$('#subject').focus();
			return false;
		}
		if(!CKEDITOR.instances.content.getData()){//CKEDITOR안에 데이터가 있는지 없는지 확인
			alert('글내용을 입력하세요');
			CKEDITOR.instances.content.focus(); //CKEDITOR 에 포커싱
			return false;
		} 
		return true;
	}
	function reset_btn(){
		alert('리셋합니다');
		CKEDITOR.instances.content.setData("");
	}
</script>

<div class="container">
	<h1>Board 글수정</h1>
	
	<c:if test="${board==null }">
		<script>
			alert('해당글은 없습니다.');
			histroy.back();
		</script>
	</c:if>
	<c:if test="${board!=null }"> <!-- else대신 board!=null jstl은 elseif가없음 -->
	<br>
	<div class="bbs">
	<form name="boardf" id="boardFrm" action="boardEdit.do" 
	 enctype="multipart/form-data" method="POST" onsubmit="return board_check()">
		<input type="hidden" name="num" value="${board.num}">
		<input type="hidden" name="userid" value="${board.userid}">
		<ul>
			<li>제목</li>
			<li>
				<input type="text" name="subject" value="${board.subject}" id="subject" placeholder="제목">
			</li>
			<li>글내용</li>
			<li>
				<textarea name="content" id="content" rows="10" cols="50" placeholder="글내용">${board.content}</textarea>
			</li>
			<li>첨부파일</li>
			<li>
			${board.filename} [ ${board.filesize } bytes]<br>
				<input type="file" name="filename" id="filename">
				<!-- 예전에 첨부했던 파일명을 old_file 이란 이름으로 넘기자 기존파일 삭제를위해서 -->
				<input type="hidden" name="old_file" value="${board.filename }">
				
			</li>
			<li>
				<button class="btn">글수정</button>
				<button type="button" class="btn" onclick="reset_btn()">다시쓰기</button>
			</li>
		</ul>
	</form>
	</div>
	</c:if> 
</div>
<jsp:include page="/foot.jsp"/>