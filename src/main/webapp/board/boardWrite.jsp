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
		alert('dd');
		//CKEDITOR.instances.content = "";
	}
</script>

<div class="container">
	<h1>Board 글쓰기</h1>
	<br>
	<div class="bbs">
	<!-- 첨부파일은 post로 get은 512byte밖에 안된다. 그래서 post를사용 -->
	<%-- 
	#파일 업로드시 주의사항
	[1]파일을 서버에 업로드시키려면 form method방식은 반드시 post로 주어야 함 
	[2]post방식일 경우 인코딩방식(enctype)이 2가지가 있는데
		(1) application/x-www-form-urlencoded (디폴트)
			=> 이 경우는 첨부된 파일이름만 서버에 전송된다.
			
		(2) multipart/form-data 
		이 중 파일업로드를 하려면 (2) multipart/form-data 로 지정해야 한다.
		    => 파일 이름과 함께 파일 데이터가 서버에 전송된다.

	--%>
	<form name="boardf" id="boardFrm" action="boardWriteEnd.do"
	 method="POST" enctype="multipart/form-data" onsubmit="return board_check()">
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
				<button type="button" class="btn" onlick="reset_btn()">다시쓰기</button>
			</li>
		</ul>
	</form>
	</div>
</div>
<jsp:include page="/foot.jsp"/>