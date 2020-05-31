<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-header text-white"
						style="background-color: #3232FF;">게시글 등록</div>
					<div class="card-body">

						<form role="form" method="post">
							<!--  action이 생략되있고 Post방식이면 자기자신한테 보낸다. -->

							<div class="form-group" hidden="hidden">
								<!--  hidden 태그: 실제 태그구조는 남아있지만  사용자에게는 보이지 않음-->
								<label>작성자</label> <input type="hidden" class="form-control"
									name='writer' value="${login.name}"> <!-- 태그가 숨겨져도 값은 넘어가야하므로 세션에 담은 login을 getName한다. -->
							</div>

							<div class="form-group">
								<label>제목</label> <input type="text" class="form-control"
									name='title'>
							</div>

							<div class="form-group">
								<label>내용</label>
								<textarea class="form-control" rows="5" name='content'
									placeholder="게시글 내용을 입력하세요."></textarea>
							</div>

									<td>
										<input type="file" name="file">
									</td>
		
			
									<td>						
										<button class="write_btn" type="submit">작성</button>	
									</td>
							<input type="submit" value="등록" class="btn form-control"
								style="background-color: #3232FF; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
							&nbsp;&nbsp; <a class="btn form-control" href="#"
								style="cursor: pointer; margin-top: 0; height: 40px; color: white; background-color: orange; border: 0px solid #388E3C; opacity: 0.8">취소</a>
						</form>



					</div>
				</div>
			</div>
		</div>
	</div>
	



</body>
</html>