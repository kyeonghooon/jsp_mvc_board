<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 보기 화면</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/view.css">
</head>
<body>
	<h2>상세 보기 화면</h2>
	<div class="container">
		<div class="board-content">
			<h2>
				<c:out value="${board.title}" />
			</h2>
			<p>
				<c:out value="${board.content}" />
			</p>
			<p>
				<fmt:formatDate value="${board.createdAt}" pattern="yyyy-MM-dd HH:mm" />
			</p>
		</div>
		<div class="actions">
			<c:if test="${board.userId == userId }">
				<a href="${pageContext.request.contextPath}/board/edit?id=${board.id}" class="btn btn-edit">수정</a>
				<a href="${pageContext.request.contextPath}/board/delete?id=${board.id}" class="btn btn-delete">삭제</a>
			</c:if>
			<a href="${pageContext.request.contextPath}/board/list?page=1" class="btn btn-back">목록으로 돌아가기</a>
		</div>

		<h3>댓글</h3>
		<!-- 댓글 리스트 작성 -->
		<div class="comment-item">
			<c:forEach var="comment" items="${commentList}">
				<p>
					<strong>${comment.username}</strong>
				</p>
				<p>${comment.content}</p>
				<p class="comment-date">
					<fmt:formatDate value="${comment.createdAt}" pattern="yyyy-MM-dd HH:mm" />
				</p>
			</c:forEach>
		</div>
		<!-- 댓글 작성 폼 -->
		<div class="comment-form">
			<form action="${pageContext.request.contextPath}/board/addComment" method="post">
				<textarea name="content" rows="5" placeholder="댓글을 작성해 주세요..."></textarea>
				<input type="hidden" name="boardId" value="${board.id}">
				<button type="submit" class="btn btn-submit">댓글 작성</button>
			</form>
		</div>
	</div>
</body>
</html>