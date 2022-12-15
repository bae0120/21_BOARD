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
		fnModifyNotice();
		fnDeleteNotice();
	})
	
	function fnModifyNotice(){
		$('#btnModify').on('click', function(){
			if($('#noticeTitle').val() == '' || $('#noticeContent').val() == ''){
				alert('모든 내용을 입력해주세요.');
				return;
			}
				$('#f').attr('action', '${contextPath}/notice/modify.do').attr('method', 'post').submit();
			
			
		})
	}
	function fnDeleteNotice(){
		$('#btnDelete').on('click', function(e){
			if(!confirm('정말 삭제?')){
				alert('취소하였습니다.');
				return;
			}
			else {
				$('#f').attr('action', '${contextPath}/notice/delete.do').attr('method', 'post').submit();
			}
		})
	}
	
</script>
</head>
<body>
	
	<form id="f">
		<input type="hidden" name="noticeSeq" value="${noticeListOne.noticeSeq}">
		제목 : <input type="text" id="noticeTitle" name="noticeTitle" value="${noticeListOne.noticeTitle}"><br>
		내용<br>
		<textarea rows="5" cols="50" id="noticeContent" name="noticeContent">${noticeListOne.noticeContent}</textarea><br>
		<input type="button" value="수정" id="btnModify">
		<input type="button" value="삭제" id="btnDelete">
		
	</form>
		
		
		
	
</body>
</html>