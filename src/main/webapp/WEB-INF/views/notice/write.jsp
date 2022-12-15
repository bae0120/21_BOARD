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
	fnAddNotice();
	fnTest();
});

function fnAddNotice(){
	$('#f').on('submit', function(e){
		if($('#noticeTitle').val() == '' || $('#noticeContent').val() == ''){
			alert('제목과 내용 모두 입력해주세요.');
			e.preventDefault();
			return;
		}
		$(this).submit();
	})
}

</script>
</head>
<body>
	<form id="f" method="post" action="${contextPath}/notice/addNotice.do">
		<table>
			<tbody>
				<tr>
					<td>제목</td>
					<td><input type="text" name="noticeTitle" id="noticeTitle"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="5" cols="50" name="noticeContent" id="noticeContent"></textarea></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td><button>등록</button></td>
					
				</tr>
				
			</tfoot>
			
		</table>
		
	</form>
</body>
</html>