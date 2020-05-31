<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align=center>
		<%-- <form action="req_album_result.jsp"> --%>
			<table border="1" width="700">
				<tr align="center">
					<%-- <td></td> --%>
					<td>앨범 커버</td>
					<td>가수</td>
					<td>앨범 제목</td>
					<td>발매일</td>
				</tr>
				<tr align="center">
					<%-- <td><input type="radio" name="title" value="izone"></td> --%>
					<td align="center">
					<img src="izone.jpg" width="100"
						height="100">
					</td>
					<td>아이즈원</td>
					<td>
					<a href="req_album_result.jsp?title=izone">비올레타</a>
					</td>
					<td>2019-04-01</td>
				</tr>

				<tr align="center">
					<%-- <td><input type="radio" name="title" value="twice"></td> --%>
					<td align="center">
					<a href="req_album_result.jsp?title=twice">
					<img src="twice.jpeg" width="100"
						height="100">
					</a>
					</td>
					<td>트와이스</td>
					<td>
					<a href="req_album_result.jsp?title=twice">What is love</a>
					</td>
					<td>2018-04-09</td>
				</tr>
				<%-- <tr>
					<td colspan="5" align="center"><input type="submit" value="확인"></td>
				</tr> --%>
			</table>
		<%-- </form> --%>
	</div>
<hr>

</body>
</html>









