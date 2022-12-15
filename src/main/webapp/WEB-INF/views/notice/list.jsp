<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../resources/js/jquery-3.6.0.js"></script>
</head>
<body>
	<input type="button" value="새글작성" onclick="location.href='${contextPath}/notice/btnNoticeWrite.do'">
	<table border=1>
		<thead>
			<tr>
				<td>순번</td>
				<td>제목</td>
				<td>작성날짜</td>
				<td>수정날짜</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty noticeList}">
				<tr>
					<td colspan="5">게시글이 존재하지 않습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty noticeList}">
				<c:forEach items="${noticeList}" var="notice" varStatus="vs">
					<tr>
						<td>${startNo-vs.index}</td>
						<td><a href="${contextPath}/notice/detail.do?noticeSeq=${notice.noticeSeq}">${notice.noticeTitle}</a></td>
						<td>
							<%-- <fmt:formatDate value="${notice.NOTICE_CREATED}" pattern="yyyy-MM-DD" /> --%>
							${notice.noticeCreated}
						</td>
						<td>${notice.noticeModified}</td>
						<td>${notice.noticeHit}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">${paging}</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>