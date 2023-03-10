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

<script>

	$(function(){
		fnSearch();
	})
//검색
	function fnSearch(){
		$('#searchForm').on('submit', function(event){
			if($('#column').val() == '' || $('#query').val() == '' ){
				alert('검색 내용을 입력해주세요.');
				event.preventDefault();
				return;
			}
		});
	}
</script>
</head>
<body>
	
	<form id="searchForm" action="${contextPath}/notice/noticeList.do">
		<select name="column" id="column">
		  <option value="" >선택</option>
		  <option value="NOTICE_TITLE" >제목</option>
		  <option value='NOTICE_CONTENT'>내용</option>
		</select>
		
		<input type="text" name="query" id="query" value="${query}">
		<button>검색</button><br>
		<%-- <input type="date" name="startDay" id="startDay" value="${startDay}"> ~ <input type="date" name="endDay" id="endDay" value="${endDay}">--%>
	</form>
	
	총 개시글 : ${total}<br>
	
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
		
	</table>


	<%--파라미터가 있으면 (검색 시 페이징)--%>
	<c:if test="${not empty param.query && not empty param.column}">
		<c:choose>
		<c:when test="${paging.page == 1}">
			prev
		</c:when>
		<c:when test="${paging.page != 1}">
			<a href="${paging.path}&page=${paging.page-1}">prev</a>
		</c:when>
	</c:choose>
	
	<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="p">
		<c:choose>
			<c:when test="${p == paging.page}">
				<b>${p}</b>
			</c:when>
			<c:when test="${p != paging.page}">
				<a href="${paging.path}&page=${p}">${p}</a>
			</c:when>
		</c:choose>
	</c:forEach>
	
	<c:choose>
		<c:when test="${paging.page == paging.totalPage}">
			next
		</c:when>
		<c:when test="${paging.page != paging.totalPage}">
			<a href="${paging.path}&page=${paging.page+1}">next</a>
		</c:when>
	</c:choose>
	</c:if>
	
	
	<%--파라미터가 없으면 (일반 조회시 페이징)--%>
	<c:if test="${empty param.query && empty param.column}">
		<c:choose>
		<c:when test="${paging.page == 1}">
			prev
		</c:when>
		<c:when test="${paging.page != 1}">
			<a href="${paging.path}?page=${paging.page-1}">prev</a>
		</c:when>
		</c:choose>
		
		<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" var="p">
			<c:choose>
				<c:when test="${p == paging.page}">
					<b>${p}</b>
				</c:when>
				<c:when test="${p != paging.page}">
					<a href="${paging.path}?page=${p}">${p}</a>
				</c:when>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${paging.page == paging.totalPage}">
				next
			</c:when>
			<c:when test="${paging.page != paging.totalPage}">
				<a href="${paging.path}?page=${paging.page+1}">next</a>
			</c:when>
		</c:choose>
	</c:if>

</body>
</html>