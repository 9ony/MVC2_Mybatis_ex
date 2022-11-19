<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 아래 태그 라이브러리 쓰는법(java인데 html태그처럼 쓸수 있게)
https://tomcat.apache.org/download-taglibs.cgi 아래 jar파일을 받아
WEB-INF/lib에 넣어둔다.
이후 아래 페이지 태그참조 작성, 아래 리스트에서 태그 사용했음.
 -->
 <!-- core태그들 사용반복문 등 들어있음 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 포맷관련 태그.. -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script>
		function find_check(){
			let $keyword = $('#findKeyword');
			alert($keyword.val());
			if(!$keyword.val()){
				alert('검색어를 입력하세요');
				return false;
			}
			return true;
		}
</script>
<div class="container">
	<h1>게시글 목록</h1>
	<p>
		<a href="user/boardWrite.do">글쓰기</a>|<a href="boardList.do">글목록</a>
		<%-- <h3>총 게시글 수 : ${totalCount}</h3> --%>
		<%-- ${listBoard} --%>
	</p>
	<br>
	<div id="boardWrap" class="m2" style="margin:auto">
		<div id="boardSearch" >
			<form name="searchF" id="searchF" action="boardList.do" method="get" onsubmit="return find_check()">
				<select name="findType" style="padding:5px">
					<option value="1">제목</option>
				    <option value="2">작성자</option>
				    <option value="3">글내용</option>
				</select>
				<input type="search" name="findKeyword" id="findKeyword" width="100px">
		<button>검색</button>
			</form>
		</div>
	</div>
		
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
				<li style="width:50px">
				<a href="boardList.do?cpage=${cpage-1 }&${qStr}">
				Prev</a></li>
				<c:forEach var="i" begin="1" end="${pageCount}">
					<c:if test="${cpage==i }">
						<li class="current">
						<a href="boardList.do?cpage=${i }&${qStr}">
						${i }</a>
						</li>
					</c:if>
					<c:if test="${cpage!=i }">
						<li><a href="boardList.do?cpage=${i }&${qStr}">
						${i }</a>
						</li>
					</c:if>
				</c:forEach>
				<li style="width:50px">
				<a href="boardList.do?cpage=${cpage+1}&${qStr}">
				Next</a>
				</li>
			</ul>
		</div>
		<br><br>
		<div>
			총 게시글 수 : ${totalCount }개 , 현재 <span style="color:red">${cpage }</span> / 총 ${pageCount } pages
		</div>
		
	</div>
</div>
<jsp:include page="/foot.jsp"/>