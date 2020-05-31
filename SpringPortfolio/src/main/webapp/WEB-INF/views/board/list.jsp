<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 포맷팅 관련 태그라이브러리(JSTL/fmt) --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/header.jsp" />
<br><br>
<br><br>
<br><br>

<!-- 페이지번호 부분 -->
<style>
.page-active {
	background-color:#3232FF; 
}

.btn-izone {
	background-color:#3232FF;
	color: white;
}

</style>

<div class="container">
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="panel-body">
			
			<h2 class="page-header"><span style="color: #3232FF;">자유 게시판</span> 
				 <span id="count-per-page" style="float: right;"> 
						<input class="btn btn-izone" type="button" value="10"> 
						<input class="btn btn-izone" type="button" value="20"> 
						<input class="btn btn-izone" type="button" value="30">
					</span>

				</h2>
				<table class="table table-bordered table-hover">
					<thead>
						<tr
							style="background-color: #3232FF; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
							<th>#번호</th>
							<!-- td나 th나 기능은 같음. -->
							<th>작성자</th>
							<th>제목</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>

				<c:if test="${articles.size() <=0}">
				
				<tr>
				<td colspan="5" align="center">
					<strong>검색 결과가 없습니다!</strong>
				</td>
				</tr>
				
				</c:if>



					<c:if test="${articles.size()>0}">
					<!-- 게시물이 들어갈 공간 -->
					<c:forEach var="b" items="${articles}">
						<tr style="color: #323232;">
							<td>${b.boardNo}</td>
							<!-- getter메서드 호출 -->
							<td>${b.writer}</td>

							<td>
							<a style="margin-top: 0; height: 40px; color: orange;" href="<c:url value='/board/content/${b.boardNo}${param.page == null ? pc.makeURI(1) : pc.makeURI(param.page)}' />">

									${b.title}
									
									 </a>
									&nbsp;
									<c:if test="${b.newMark}"> <!-- 이 구문 자체가 true이기만 하면 바로 new를 붙게함. -->
									<span class="badge badge-pill badge-danger">new</span>
									</c:if>
									</td>
									

							<td><fmt:formatDate value="${b.regDate}"
									pattern="yyyy년 MM월 dd일 a hh:mm" /></td>
							<td>${b.viewCnt}</td>
						</tr>
					</c:forEach>
					</c:if>
				</table>
				<!-- 페이징 처리 부분  -->
				<ul class="pagination justify-content-center">

					<!--  이전 버튼 --> <!--  조건부로 활성/비활성 여부를 결정. -->
					<c:if test="${pc.prev}"> <!--  이전버튼이 true일때만 등장. -->
					<li class="page-item"><a class="page-link" href="<c:url value='/board/list${pc.makeURI(pc.beginPage - 1)}'/>" 
						style="background-color: #3232FF; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">이전</a>
					</li>
					</c:if>

					<!-- 페이지 버튼 -->
					<c:forEach var="pageNum" begin="${pc.beginPage}" end="${pc.endPage}">
						<!-- 1이 시작값 end가 끝값 -->
						<li class="page-item"><a
							href="<c:url value='/board/list${pc.makeURI(pageNum)}'/>"
							class="page-link ${(pc.paging.page == pageNum) ? 'page-active' : ''}" 
							style="margin-top: 0; height: 40px; color: pink; border: 1px solid pink;">${pageNum}</a>
						</li>
					</c:forEach>

					<!--  다음 버튼 -->
					<c:if test="${pc.next}"> <!-- 이것도, next가 true일때만 등장. -->
					<li class="page-item"><a class="page-link" href="<c:url value='/board/list${pc.makeURI(pc.endPage + 1)}'/>"
						style="background-color: #3232FF; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">다음</a>
					</li>
					</c:if>
				</ul>
				<!-- 페이징 처리 끝 -->
			</div>
		</div>
	</div>
	<!-- 검색 버튼 -->
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="form-group col-sm-2">
			<select id="condition" class="form-control" name="condition">
				<option value="title" ${param.condition == 'title' ? 'selected' : ''}>제목</option> <!--  SearchVO의 정보와 PageVO의 정보가 list.jsp에 함께 파라미터로 보내지기 때문에, 컨트롤러에서 	if(condition.equals("title")) 라면,  -->
				<option value="content" ${param.condition == 'content' ? 'selected' : ''}>내용</option>
				<option value="writer" ${param.condition == 'writer' ? 'selected' : ''}>작성자</option>
				<option value="titleContent" ${param.condition == 'titleContent' ? 'selected' : ''}>제목+내용</option>
			</select>
		</div>
		<div class="form-group col-sm-4">
			<div class="input-group">
				<input type="text" class="form-control" name="keyword" value="${param.keyword}" id="keywordInput" placeholder="검색어"> <!--  검색어 입력하는 부분 -->
				<span class="input-group-btn"> <!--  span 태그: 어떠한 의미도 없지만 그룹화한다. -->
					<!-- 검색창 부분 -->
					 <input type="button" value="검색" class="btn btn-izone btn-flat" id="searchBtn">
				</span>
			</div>
		</div>
		<div class="col-sm-2">
			<a href="<c:url value='/board/write'/>"
				class="btn btn-izone float-right">글쓰기</a>
		</div>
		<div class="col-sm-2"></div>
	</div>
</div>

<script>

//글쓰기 완료 시 띄울 알림창 처리
const result = "${msg}";

if(result === "regSuccess") {
alert("게시글 등록이 완료 되었습니다.");
} else if(result === "delSuccess") {
	alert("게시글 삭제가 완료 되었습니다.");	
}

//start jQuery

$(function() {


//목록 개수가 변동하는 이벤트 처리
$("#count-per-page .btn-izone").click(function() { //클릭시 일어나는 이벤트 처리
	//console.log("목록 버튼이 클릭됨!"); 10,20,30을 할때마다 공통적으로 이 출력문이 실행된다.
	//console.log($(this).val()); //각각의 value값(10,20,30)을 읽게 해준다.
	let count = $(this).val(); //각각의 변수들을 let count로 변수화 시킨다.
	const keyword = "${param.keyword}";
	const condition = "${param.condition}";
	location.href="/board/list?countPerPage=" + count //count에는 10,20,30이 저장되있고 그렇다면 누를때마다(10,20,30) 이므로 countPerPage의 수를 바꿔줄 수 있다.
	+ "&keyword=" + keyword
	+ "&condition=" + condition;
});

//[검색창] 버튼 이벤트 처리 138번 라인
$("#searchBtn").click(function() {
	console.log("검색버튼이 클릭됨!");
	const keyword = $("#keywordInput").val(); //id == keywordInput == input="text"의 주소값 keyword는 mapper.xml에 기록된 파라미터 값. 파라미터에 기록된 값들을 한개씩 읽겠다.
	console.log("검색어: " + keyword)
	
	const condition = $("#condition option:selected").val(); //97번: id가 condition인데, option에 선택된애들의 value값을 읽어라. //SearchVO를 받기 때문에 멤버변수를 쓸 수 있다.
	console.log("검색 조건: " + condition);
	
	location.href="/board/list?keyword=" + keyword
			+"&condition=" + condition; //검색할때  $("#condition option:selected").val(); 에 의해서 각각의 value값들을 묻혀서 전송함.
	
});


//엔터키 입력 이벤트
//키 코드가 13이면 클릭버튼을 활성화 시켜라.
$("#keywordInput").keydown(function (key) {
	if(key.keyCode == 13) { //키가 13이면 실행(엔터는 13)
		$("#searchBtn").click();
	}
	
});

}); //end jQuery

</script>

<jsp:include page="../include/footer.jsp" />
