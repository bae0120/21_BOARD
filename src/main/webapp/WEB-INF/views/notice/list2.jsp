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
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href="notice/noticeList.do?nowPage=${paging.nowPage}&cntPerPage="+sel;
	}
</script>

</head>
<body>
	<input type="button" value="새글작성" onclick="location.href='${contextPath}/notice/btnNoticeWrite.do'">
	
	<div style="float: right;">
		<select id="cntPerPage" name="sel" onchange="selChange()">
			<option value="5"
				<c:if test="${paging.cntPerPage == 5}">selected</c:if>>5줄 보기</option>
			<option value="10"
				<c:if test="${paging.cntPerPage == 10}">selected</c:if>>10줄 보기</option>
			<option value="15"
				<c:if test="${paging.cntPerPage == 15}">selected</c:if>>15줄 보기</option>
			<option value="20"
				<c:if test="${paging.cntPerPage == 20}">selected</c:if>>20줄 보기</option>
		</select>
	</div> <!-- 옵션선택 끝 -->
	
	
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
	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1}">
			<a href="/notice/noticeList.do?nowPage=${paging.startPage - 1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
			
		
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage}">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage}">
					<a href="/notice/noticeList.do?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="/notice/noticeList.do?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
	</div>
</body>
</html>