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
	if('${addNotice}' != '') {
		if('${addNotice}' == '1'){
			alert('공지사항 등록성공');
			location.href='${contextPath}/notice/noticeList.do';
		} else {
			alert('공지사항 등록실패');
			location.href='${contextPath}/notice/noticeList.do';
		}
	}
	
	if('${modifyNotice}' != '') {
		if('${modifyNotice}' == '1'){
			alert('공지사항 수정성공');
			location.href='${contextPath}/notice/noticeList.do';
		} else {
			alert('공지사항 수정실패');
			location.href='${contextPath}/notice/noticeList.do';
		}
	}
	
	if('${deleteNotice}' != '') {
		if('${deleteNotice}' == '1'){
			alert('공지사항 삭제성공');
			location.href='${contextPath}/notice/noticeList.do';
		} else {
			alert('공지사항 삭제실패');
			location.href='${contextPath}/notice/noticeList.do';
		}
	}

</script>
</head>
<body>
	
</body>
</html>