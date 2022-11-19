<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- top.jsp -->
<% 
	String myctx=request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    
    <link rel="stylesheet" type="text/css" href="<%=myctx%>/css/layout.css"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
    <div id="wrap" class="container">
        <!-- 헤더: 로고 이미지, 검색폼, 목차 -->
        <header>
            <!-- 헤더영역 -->
            <a href="<%=myctx%>/index.jsp"><img src="<%=myctx%>/images/ex43image.JPG"></a>
        </header>
        <div class="clear"></div>
        <!-- 내바게이션바 : 메뉴 -->
        <nav>
       
        <ul>
            <li><a href="<%=myctx%>/index.do">Home</a></li>
            <li><a href="<%=myctx%>/user/boardWrite.do">게시글쓰기</a></li>          
            <li><a href="<%=myctx%>/boardList.do">게시글 목록</a></li>
            <li><a href="<%=myctx%>/joinForm.do">회원가입</a></li>
            <!-- el표현식에서 eq(==와 동일하다), ne(!=와 동일함) -->
            <c:choose>
            	<c:when test="${loginUser==null }">
            	<li><a href="<%=myctx%>/login.do">로그인</a></li>
            	</c:when>
            	<c:otherwise>
            	<li><a href="<%=myctx%>/logout.do">로그아웃</a></li>
            	</c:otherwise>
            </c:choose>
        </ul>
    </nav>
    <div class="clear"></div>
        <!-- 컨텐츠 영역 -------------------------------->
        <article>