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
		$('#column').val('${column}');
	
		fnSearch(); 
	})
	//검색
	function fnSearch(){
		$('#searchForm').on('submit', function(event){
			if($('#column').val() == '' && $('#query').val() == '' && $('#startDay').val() == '' && $('#endDay').val() == ''){
				event.preventDefault();				
			}
		});
	}
</script>
	<style type="text/css">
		li {list-style: none; float: left; padding: 6px;}
	</style>
</head>
<body>

	<form id="searchForm" action="${contextPath}/notice/noticeSearch.do">
		<select name="column" id="column">
			<option value="">선 택</option>
			<option value="NOTICE_TITLE">제목</option>
			<option value="NOTICE_TITLE_CONTENT">제목+내용</option>
		</select>
		<input type="text" name="query" id="query" value="${query}">
		<button>검색</button><br>
		<input type="date" name="startDay" id="startDay" value="${startDay}"> ~ <input type="date" name="endDay" id="endDay" value="${endDay}">
	</form>
	
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

	
	<div>
	  <ul>
	    <c:if test="${pageMaker.prev}">
	    	<li><a href="list${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a></li>
	    </c:if> 
	
	    <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
	    	<li><a href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>
	    </c:forEach>
	
	    <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
	    	<li><a href="list${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a></li>
	    </c:if> 
	  </ul>
	</div>
	
</body>
</html>