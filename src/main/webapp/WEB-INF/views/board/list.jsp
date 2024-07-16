<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
    <h2>게시글 목록</h2>
    <div class="action">
        <a href="${pageContext.request.contextPath}/board/create">새글 작성하기</a>
        <a href="${pageContext.request.contextPath}">홈 화면</a>
    </div>

	<c:forEach var="board" items="${boardList}">
    <div class="board-item">
		<h3><a href="#">${board.title}</a></h3>
		<p>${board.content}</p>
		<!-- 게시글의 작성자가 세션 유저와 동일하다면 수정, 삭제 버튼을 보여주자 -->
        <a class="btn btn-edit" href="#">수정</a>
        <a class="btn btn-delete" href="#">삭제</a>
    </div>
    </c:forEach>
    <br>
    <div class="pagination">
    	
		<c:forEach var="i" begin="1" end="${totalPages}">
			<span><a href="${pageContext.request.contextPath}/board/list?page=${i}">${i}</a></span><br>
		</c:forEach>
    </div>
</body>
</html>