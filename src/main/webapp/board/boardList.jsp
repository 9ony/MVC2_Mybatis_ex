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
	.pageWrap{
		position:relative;
	}
	.paging{
		list-style-type:none;
		position: absolute;
	    top: 50%;
	    left: 50%;
	    transform: translate(-50%, -50%);
	}
	.paging>li{
		float:left;
		padding:5px;
		text-align:center;
		width:2em;
		border: 1px solid #ddd;
		border-radius: 3px;
		margin:1px;
	}
	.paging>li.current{
		background-color:navy;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(function(){
		search_check=function(){
			let key = $('#search_key').val();
			let value = $('#search_value').val()
			if(!key==''){
				if(key=='num'){
					let numcheck=/^[0-9]+$/;
					if(value==''||numcheck.test(value)){
						alert(value);
					}else {
						alert('숫자네요');
					}
				}else if(key=='subject'){
					alert('subject');	
				}else if(key=='content'){
					alert('content');
				}else{
					alert('name');
				}
			}else{
				alert('검색할 항목을 선택하세요')
				return;
			}
		}
	})
</script>
<div class="container">
	<h1>게시글 목록</h1>
	<p>
		<a href="boardWrite.do">글쓰기</a>|<a href="boardList.do">글목록</a>
		<%-- <h3>총 게시글 수 : ${totalCount}</h3> --%>
		<%-- ${listBoard} --%>
	</p>
	<form id="search" name="search" action="boardList.do" method="get">
		<select type="select" name="search_key" id="search_key">
			<option value="">선택하세요</option>
			<option value="num">번호</option>
		    <option value="subject">제목</option>
		    <option value="content">내용</option>
		    <option value="userid">사용자</option>
		</select>
		<input type="search" name="search_value" id="search_value" width="100px">
		<button type="button" onclick="search_check()">검색</button>
	</form>
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
			<li><a href="boardView.do?num=${vo.num }">${vo.subject}</a>
				<c:if test="${vo.filesize >0 }">
					<img src="images/attach.JPG" width="16px">
				</c:if>
			</li>
			<li>${vo.userid }</li>
			<li><fmt:formatDate value="${vo.wdate }" pattern="yyyy-MM-dd"/></li>
			</c:forEach>
			<!--  ------------------------------->
		</ul>
		
		<div style="clear:both"></div>
		<br><br>
		<div class="pageWrap">
			<ul class="paging">
				<li style="width:50px"><a href="boardList.do?cpage=${cpage-1 }">
				Prev</a></li>
				<c:forEach var="i" begin="1" end="${pageCount}">
					<c:if test="${cpage==i }">
						<li class="current"><a href="boardList.do?cpage=${i }">${i }</a></li>
					</c:if>
					<c:if test="${cpage!=i }">
						<li><a href="boardList.do?cpage=${i }">${i }</a></li>
					</c:if>
				</c:forEach>
				<li style="width:50px"><a href="boardList.do?cpage=${cpage+1}">Next</a></li>
			</ul>
		</div>
		<br><br>
		<div>
			총 게시글 수 : ${totalCount }개 , 현재 <span style="color:red">${cpage }</span> / 총 ${pageCount } pages
		</div>
		
	</div>
</div>
<jsp:include page="/foot.jsp"/>