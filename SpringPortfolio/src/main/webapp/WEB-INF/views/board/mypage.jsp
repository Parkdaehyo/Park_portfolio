<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<jsp:include page="../include/header.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- 깃허브 6월 1일 수정22 -->
<!-- 1. 텍스트를 오른쪽,왼쪽,중앙 정렬 -->
<!-- 2. 테이블을 가운데로 자동 정렬-->
<style>


#box1 { text-align: right; } 
#box2 { text-align: left; } 
#box3 { text-align: center; } 


table {
margin: auto;
}

div.button

{

   margin: auto;

   width:26%;

}



div.button input

{

   padding: 5px;

   width: 30%;

   font-size: 18px;

}
  </style>
  
 
 




<br>
<br>
<br>
<br>

<div class="box3"> </div>
 
	<div class="row">
		<div class="col-lg-2"></div>
		<div class="col-lg-8">
			<div class="panel-body">
			
			<h2>
			
			<div id="box3"> 
			<span id="box3" style="color: #3232FF;">회원정보</span> 
			</div> 
				 

				</h2>
				
				
				<table class="table table-bordered table-hover">
				
				<div id="box3">
					<thead>
						<tr
							style="background-color: #3232FF; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
							<th>아이디</th>
							<!-- td나 th나 기능은 같음. -->
							<th>이름</th>
							<th>가입일자</th>
							<th>휴대폰번호</th>
							
						</tr>
					</thead>
               </div>
               
               
			 <form role="form" method="post">
				 <c:if test="${not empty login}">
				<tr>
				 <!--  
				 <td>
				 <span id="account" name="account"> span은 name속성값을 못읽는거같음
				 ${userinfo.account}
				 </span>
				 </td>
				  -->
				 
				<td>
				<input id="account" type="hidden" name="account" value="${userinfo.account}"> <!--  value는 지우면 안된다. -->
				${userinfo.account}
				</td>
				
                  <td>
                 <input type="text" name="name" value="${userinfo.name}">
                  </td>
                  <td>
                 
                  <fmt:formatDate value="${userinfo.regDate}"
								pattern="yyyy년 MM월 dd일 a hh:mm" />
                  </td>
                  <td>
                <input type="text" name="phoneNum" value="${userinfo.phoneNum}"><br>
                  </td>
				</tr>
				</c:if>
				</table>
				
				<div class="button">

    
    	<input type="submit" value="수정" onclick="return confirm('수정하시겠습니까?')"/>
  
			</form>
			
  	  <input type="button" id="deleteBtn" value="탈퇴" onclick="return confirm('회원 탈퇴하면 복구 할 수 없습니다. 정말로 탈퇴 하시겠습니까?')"/><br/>

</div>
</div>
</div>
</div>
</div>
</div>


			
				
</body>


<script>


$('#deleteBtn').click(function(e) {
	
	var deleteBtn = $("#deleteBtn");
	//var account = $("#account").val();
	var account = $("#account").val();
	console.log("계정: " + account);
	
	
	$.ajax({
		type: "DELETE", 
		url: "/user/" + account,
		headers: {
			"Content-Type": "application/json"
		}, //요청 헤더 정보
	
		dataType: "text",
			data: account, //서버로 전송할 데이터
			success: function(result) { //함수의 매개변수는 통신성공시의 데이터가 저장될 곳.
				if(result === "delSuccess") {
					alert("회원탈퇴 되었습니다.");
					location.href="/"; // 홈으로 돌려보낸다.
				} else {
					alert("회원탈퇴 실패하였습니다.")
					
				}
			
			}
	
	});
	
});




</script>

		<jsp:include page="../include/footer.jsp" />
		</html>
		