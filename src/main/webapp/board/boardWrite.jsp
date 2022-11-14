<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		width: 80%;
	}
	#boardFrm input{
		padding : 5px;
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
<!-- 유효성검사 스크립트 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	let board_check=function(){
		if($('#subject').val()==''){
			alert('제목을 입력하세요');
			$('#subject').focus();
			return false;
		}
		return true;
	}
</script>

<div class="container">
	<h1>Board 글쓰기</h1>
	<div class="bbs">
	<form name="boardf" id="boardFrm" action="boardWriteEnd.do" method="POST" onsubmit="return board_check()">
		<ul>
			<li>제목</li>
			<li>
				<input type="text" name="subject" id="subject" placeholder="제목">
			</li>
			<li>글내용</li>
			<li>
				<textarea name="content" id="content" rows="10" cols="50" placeholder="글내용"></textarea>
			</li>
			<li>첨부파일</li>
			<li>
				<input type="file" name="filename" id="filename">
			</li>
			<li>
				<button class="btn">글등록</button>
				<button type="reset" class="btn">다시쓰기</button>
			</li>
		</ul>
	</form>
	</div>
</div>
<jsp:include page="/foot.jsp"/>