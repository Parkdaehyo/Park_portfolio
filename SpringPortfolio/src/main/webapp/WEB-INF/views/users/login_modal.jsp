<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>






<!-- 로그인 Modal-->
<div class="modal fade" id="log-in">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">
					<span style="color: #3232FF;">로그인</span>
				</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">

				<form action="#" name="izone-sign-in" method="post" id="signInForm"
					style="margin-bottom: 0;">
					<table
						style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
						<tr>
							<td style="text-align: left">
								<!--  span태그는 보통 id나 class를 묶는데 사용된다.-->
								<p>
									<strong>아이디</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span>
								</p>
							</td>
						</tr>

						<!-- required="required" aria-required="true" 아무것도 입력안하고 전송하려고 할때 입력이 안되어있다면 메세지를 띄우는 거라고?-->

						<tr>
							<td><input size="13" type="text" name="userId" id="signInId"
								class="form-control tooltipstered" maxlength="10"
								required="required" aria-required="true"
								style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #CD0000"
								placeholder="최대 10자"></td>
						</tr>
						<tr>
							<td style="text-align: left">
								<p>
									<strong>비밀번호</strong>&nbsp;&nbsp;&nbsp;<span id="pwCheck"></span>
								</p>
							</td>
						</tr>
						<tr>
							<td><input type="password" size="17" maxlength="20"
								id="signInPw" name="userPw" class="form-control tooltipstered"
								maxlength="20" required="required" aria-required="true"
								style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #CD0000"
								placeholder="최소 8자"></td>
						</tr>

						<tr>
							<td><input type="checkbox" id="auto-login" name="autoLogin">
								자동 로그인</td>
						</tr>
						
						<br><br>
						<tr>
							<td>
								<!--  td의 역할 -->

								<div class="container">
							
									<!-- Trigger the modal with a button -->
									<button type="button" class="btn btn-info btn-lg"
										data-toggle="modal" data-target="#myModal">ID찾기</button>
							</td>

						</tr>


						<tr>
							<td style="padding-top: 10px; text-align: center">
								<p>
									<strong>▼로그인</strong>
								</p>
							</td>
						</tr>
						<tr>
							<td style="width= 100%; text-align: center; colspan: 2;"><input
								type="button" value="로그인" class="btn form-control tooltipstered"
								id="signIn-btn"
								style="background-color: #3232FF; margin-top: 0; width: 20%; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
							</td>
						</tr>
						<tr>
							<td
								style="width: 100%; text-align: center; colspan: 2; margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">
								<!-- data-toggle="modal" 모델을 열어주세요. --> <!--  href="#sign-up이 그 대상입니다.-->
								<a class="btn form-control tooltipstered" data-toggle="modal"
								href="#sign-up"
								style="width: 100%; cursor: pointer; margin-top: 0; width: 20%; height: 40px; color: white; background-color: orange; border: 0px solid #388E3C; opacity: 0.8">
									회원가입</a>
							</td>
						</tr>
						<tr>
							<td
								style="width: 100%; text-align: center; colspan: 2; margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">

								<a id="custom-login-btn"
								href="https://kauth.kakao.com/oauth/authorize?client_id=1b99bdb2b2d8b2b7b7f7c7ee8f97842b&redirect_uri=http://localhost:8181/ggbro/kakaoLogin&response_type=code">
									<img
									src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg"
									width="200" />


							</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>



<!-- -----------------------------------------ID찾기 Modal------------------------------------------- -->

<div class="container">
	<!-- Trigger the modal with a button -->
	<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
		data-target="#myModal"></button>

	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header text-align: left;">
				<h4>아이디 찾기</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					
				</div>
				<div class="modal-body">
				
				<form action="#" name="name" id="iDUpForm" method="post">
				
				
						
 			 <fieldset name="info">
				<legend> 개인정보 </legend>
				이름:<br>
				<input type="text" name="inputname" id="user_name2" size="13"/> <br> <!--  이부분 안넘어가는거아니에요?? -->
				
				
				휴대폰번호:<br>
				<input type="text" name="inputpn" id="inputpn" maxlength="12" size="13"/> <br> <!--  이부분 안넘어가는거아니에요?? -->
			
				</fieldset>
				
				<br>
				<br>
				<td> 
				<input type="button" value="아이디 찾기" style="WIDTH: 80pt; HEIGHT: 20pt" id="iDup-btn">
				
				</td>
				</form>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

</div>



<!-- ----------------------------------------회원가입 Modal------------------------------------------------------- -->
<!-- 81번 href="#sign-up" 을 id로 받음. -->
<div class="modal fade" id="sign-up" role="dialog">
	<!-- dialog 방식 -->
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">
					<!-- h4로 modal-title을 썼지만 -->
					<span style="color: #3232FF;">회원가입</span>
					<!-- 화면에는 회원가입만 나온다. -->
				</h4>
				<button type="button" class="close" data-dismiss="modal">×</button>

			</div>

			<div class="modal-body">

				<form action="#" name="signup" id="signUpForm" method="post"
					style="margin-bottom: 0;">
					<table
						style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
						<tr>
							<td style="text-align: left">
								<p>
									<strong>아이디</strong>&nbsp;&nbsp;&nbsp; <span id="idChk"></span>
								</p>
							</td>


						</tr>
						<tr>
							<td><input type="text" name="userId" id="user_id"
								class="form-control tooltipstered" maxlength="10"
								required="required" aria-required="true"
								style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
								placeholder="숫자,영문 포함 5-10자"></td>

						</tr>

						<tr>
							<td style="text-align: left">
								<p>
									<strong>비밀번호</strong>&nbsp;&nbsp;&nbsp;<span id="pwChk"></span>
								</p>
							</td>
						</tr>
						<tr>
							<td><input type="password" size="17" maxlength="20"
								id="password" name="userPw" class="form-control tooltipstered"
								maxlength="20" required="required" aria-required="true"
								style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #A814E7"
								placeholder="영문,특수문자 포함 최소 8자"></td>
							<!-- 회원가입 비밀번호 입력창 -->
						</tr>
						<tr>
							<td style="text-align: left">
								<!-- 309 -->
								<p>
									<strong>비밀번호 재확인</strong>&nbsp;&nbsp;&nbsp;<span id="pwChk2"></span>
								</p>
							</td>
						</tr>
						<tr>
							<td><input type="password" size="17" maxlength="20"
								id="password_check" name="pw_check"
								class="form-control tooltipstered" maxlength="20"
								required="required" aria-required="true"
								style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #A814E7"
								placeholder="비밀번호가 일치해야합니다."></td>
						</tr>

						<tr>
							<td style="text-align: left">
								<p>
									<strong>이름</strong>&nbsp;&nbsp;&nbsp;<span id="nameChk"></span>
								</p>
							</td>
						</tr>
						<tr>
							<td><input type="text" name="userName" id="user_name"
								class="form-control tooltipstered" maxlength="10"
								required="required" aria-required="true"
								style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #A814E7"
								placeholder="한글로 최대 10자"></td>
						</tr>
						
						<tr>
							<td style="text-align: left">
								<p>
									<strong>휴대폰 번호</strong>&nbsp;&nbsp;&nbsp;<span id="phoneChk"></span>
									
								</p>
							</td>
						</tr>
						
						<tr>
							<td><input type="text" name="phoneNum" id="phoneNum"
								class="form-control tooltipstered" maxlength="11"
								required="required" aria-required="true"
								style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #A814E7"
								placeholder="-없이 10~11자"></td>
						</tr>

						<!--
						<tr>
							<td style="text-align: left">
								<p><strong>이메일을 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="emailChk"></span></p>
							</td>
						</tr>
						<tr>
							<td><input type="email" name="userEmail" id="user_email"
								class="form-control tooltipstered" 
								required="required" aria-required="true"
								style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
								placeholder="ex) izone@produce.com"></td>
						</tr> 
						 -->

						<tr>
							<td style="padding-top: 10px; text-align: center">
								<p>
									<strong>▼클릭</strong>
								</p>
							</td>
						</tr>
						<tr>
						
						<!-- signup-btn을 눌렀을때 정보를 서버쪽으로 보낼것이다. form을 사용하지 않고 통신함수를 쓸것이다. -->
							<td style="width: 100%; text-align: center; colspan: 2;">
							<input type="button" value="회원가입"
								class="btn form-control tooltipstered" id="signup-btn"
								style="background-color: #3232FF; margin-top: 0; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8">
							</td>
						</tr>

					</table>
				</form>
			</div>
		</div>
	</div>
</div>

<script>


//start JQuery

$(function() {
	var regex= /[^0-9]/g
	const getIdCheck= RegExp(/^[a-zA-Z0-9]{5,10}$/);
	const getPwCheck= RegExp(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/);
	const getName=  RegExp(/^[가-힣]+$/);
	const getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
	let chk1 = false, chk2 = false, chk3 = false, chk4 = false; chk5 = false;
	
	//회원가입 검증, 127번 라인
	//ID 입력값 검증.
	//아이디 중복확인 키업 이벤트(한자한자 쓸대마다 실시간으로 서버와 통신하여 아이디를 알려줌)
	$('#user_id').on('keyup', function() { //131번 user_id(input="text") 부분
		if($("#user_id").val() === ""){ //만약 입력창이 공백이라면,
			$('#user_id').css("background-color", "#A5D8FA"); //입력창을 분홍색으로 변경하겠다.
			$('#idChk').html('<b style="font-size:14px;color:red;">[아이디를 입력해주세요.]</b>'); //125번 
			//#idchk <span> </span> 사이에 html이하의 내용을 삽입하라.
			chk1 = false; // chk1 = false로 설정하겠다.
		}
		
		//아이디 유효성검사
		else if(!getIdCheck.test($("#user_id").val())){ //user_id를 test하고, getIdcheck에 있는 문자 내용들과 다르다면,
			$('#user_id').css("background-color", "#A5D8FA"); //핑크색으로 변경
			$('#idChk').html('<b style="font-size:14px;color:red;">[영문자,숫자 포함 5~10자만 가능합니다.]</b>'); //idCheck 부분에 이 문자열 삽입
			chk1 = false; // false로 설정.
		} 
		//ID 중복확인 비동기 처리
		else { //상기 if, else if문에 대한 조건이 걸리지 않았다면, 
			//ID 중복확인 비동기 통신
			const id = $(this).val(); // this == #user_id
			console.log(id);
			
			//통신함수 $.ajax()
			$.ajax({
				type: "POST",
				url: "/user/checkId", //어디와 통신하는가?	
				headers: {
	                "Content-Type": "application/json" //서버에 json타입을 보내겠다
	            },
				dataType: "text",  // result값이 ok or No가 들어오므로 text를 써준다.
				data: id,
				success: function(result) { //성공하면 뭐할꺼니?
					if(result === "OK") {
						$("#user_id").css("background-color", "#A5D8FA");
						$("#idChk").html("<b style='font-size:14px; color:green;'>[사용가능한 아이디 입니다.]</b>");						
						chk1 = true;
					} else {
						$("#user_id").css("background-color", "#A5D8FA");
						$("#idChk").html("<b style='font-size:14px; color:red;'>[아이디가 중복었습니다.]</b>");						
						chk1 = false;
					}
				},
				error: function() {
					console.log("통신 실패!");
				}
			});
		}
	});
	
	
	//////////////////////////////회원가입 창 패스워드//////////////////////////////////////
	//패스워드 입력값 검증.
	$('#password').on('keyup', function() {  //패스워드 키업 이벤트 처리
		//비밀번호 공백 확인
		if($("#password").val() === ""){ //공백이라면
		    $('#password').css("background-color", "#A5D8FA"); //레드로 컬러 변경.
			$('#pwChk').html('<b style="font-size:14px;color:red;">[패스워드는 필수입니다.]</b>'); //154번
			chk2 = false;
		}		         
		//비밀번호 유효성검사
		else if(!getPwCheck.test($("#password").val()) || $("#password").val().length < 8){
		    $('#password').css("background-color", "#A5D8FA");
			$('#pwChk').html('<b style="font-size:14px;color:red;">[특수문자 포함 8자이상 입니다.]</b>');
			chk2 = false;
		} else {
			$('#password').css("background-color", "#A5D8FA");
			$('#pwChk').html('<b style="font-size:14px;color:green;">[비밀번호 사용 가능합니다.]</b>');
			chk2 = true;
		}
		
	});
	
	//////////////////////////////회원가입 창 패스워드 확인란//////////////////////////////////////
	//패스워드 확인란 입력값 검증.
	$('#password_check').on('keyup', function() {
		//비밀번호 확인란 공백 확인
		if($("#password_check").val() === ""){ //비밀번호 체크
		    $('#password_check').css("background-color", "#A5D8FA");
			$('#pwChk2').html('<b style="font-size:14px;color:red;">[비밀번호 확인란을 입력해주세요.]</b>');
			chk3 = false;
		}		         
		//비밀번호 확인란 유효성검사
		else if($("#password").val() != $("#password_check").val()){ //비밀번호 와 비밀번호 체크가 맞지 않으면
		    $('#password_check').css("background-color", "#A5D8FA"); // 컬러를 레드로 변경
			$('#pwChk2').html('<b style="font-size:14px;color:red;">[비밀번호를 확인해주세요.]</b>');
			chk3 = false;
		} else {
			$('#password_check').css("background-color", "#A5D8FA"); //공백도 아니고, 
			$('#pwChk2').html('<b style="font-size:14px;color:green;">[사용 가능합니다.]</b>');
			chk3 = true;
		}
		
	});
	
	//이름 입력값 검증.
	$('#user_name').on('keyup', function() {
		//이름값 공백 확인
		if($("#user_name").val() === ""){
		    $('#user_name').css("background-color", "#A5D8FA");
			$('#nameChk').html('<b style="font-size:14px;color:red;">[이름을 입력해주세요.]</b>');
			chk4 = false;
		}		         
		//이름값 유효성검사
		else if(!getName.test($("#user_name").val())){
		    $('#user_name').css("background-color", "#A5D8FA");
			$('#nameChk').html('<b style="font-size:14px;color:red;">[한글만 입력 가능합니다.]]</b>');
			chk4 = false;
		} else { 
			$('#user_name').css("background-color", "#A5D8FA");
			$('#nameChk').html('<b style="font-size:14px;color:green;">[사용 가능합니다.]</b>');
			chk4 = true;
		}
		
	});
	
	//휴대폰 번호 검증
	$('#phoneNum').on('keyup', function() {
	    
		var regexp = /^[0-9]*$/
		
		var phoneNum = $('#phoneNum').val();
		
		console.log(phoneNum);
		
		if($("#phoneNum").val() === "" || $("#phoneNum").val().length < 10){ 
		    $('#phoneNum').css("background-color", "#A5D8FA");
			$('#phoneChk').html('<b style="font-size:14px;color:red;">[휴대폰 번호는 10~11 숫자만 입력 가능합니다.]</b>');
			chk5 = false;
		}		     
			
		else if(!regexp.test(phoneNum)){
		    $('#phoneNum').css("background-color", "#A5D8FA");
			$('#phoneChk').html('<b style="font-size:14px;color:red;">[숫자만 입력 가능합니다.]]</b>');
			chk5 = false;
			
		} else { 
			$("#phoneNum").css("background-color", "#A5D8FA");
			$('#phoneChk').html('<b style="font-size:14px;color:green;">[사용 가능합니다.]</b>');
			chk5 = true;
		}
		});
		
		
	
	
	
	//아이디 찾기 클릭 이벤트
	$('#iDup-btn').click(function(e) { //여기
		
		// var name2 = '${user.account}';
		
		const inputname = $("#user_name2").val();
		const inputpn = $("#inputpn").val();
		
		console.log("이름 : " + inputname);
		console.log("휴대폰번호 : " + inputpn);
		
		const user = {
			
			name : inputname, // key값은 VO필드명하고 똑같이.
			phoneNum : inputpn
			
		
		};
		
		//클라이언트에서 서버와 통신하는 ajax함수(비동기 통신) 
		$.ajax({
			type: "POST", //서버에 전송하는 HTTP요청 방식 회원가입이니까 POST방식.
			url: "/user/Findid",
			headers: {
				"Content-Type": "application/json"
			}, //요청 헤더 정보
			
			//Debug
			dataType: "JSON", //응답받을 데이터의 형태 JSON으로 바꾸니까 됬네??
			//data: JSON.stringify(name), // <-- JSON.stringify를 사용하면 ""가 붙어서 나오므로, 생략
			
			data: JSON.stringify(user), //서버로 전송할 데이터
			success: function(result) { //함수의 매개변수는 통신성공시의 데이터가 저장될 곳.
				console.log("통신 성공!: " + result);

				// Debug 
				//////////////////////////////////////////////////////////////////////////////////////////
				//if(result === "id") {
				//////////////////////////////////////////////////////////////////////////////////////////
				if(result.account != "" && result.account != null) { // === 3개가 아닌, = 1개로 변경
					alert("귀하의 아이디는 " + result.account + "입니다.");
				} else {
					alert("입력하신 정보를 확인해주세요.");
				}

			}, //통신 성공시 처리할 내용들을 함수 내부에 작성.
			error: function() {
				console.log("통신 실패!");
			} //통신 실패 시 처리할 내용들을 함수 내부에 작성.
		});
	
});
		
	
		
	
	//회원가입 버튼 클릭 이벤트
	$('#signup-btn').click(function(e) { //279번 회원가입 처리 버튼
		if(chk1 && chk2 && chk3 && chk4 && chk5) {
			//아이디 정보를 저장할 상수 id
			const id = $("#user_id").val(); //input type="text" 부분 //.val() 값을 빼올때 쓰는 메서드
			console.log("id: " + id);
			//패스워드 정보
			const pw = $("#password").val(); //146번 라인
			console.log("pw: " + pw);
			//이름 정보
			const name = $("#user_name").val();
			console.log("name: " + name);
			
			const phoneNum = $("#phoneNum").val();
			console.log("phoneNum: " + phoneNum);
			
			//Json형태로 객체만들기
			const user = { //자바스크립트의 객체 은 자바스크립트의 표기법을 따른다.
				account: id, //KEY : VALUE 키(왼쪽) 값은 VO의 멤버와 같도록한다.
				password: pw,
				name: name,
				phoneNum : phoneNum
			};
			
			//클라이언트에서 서버와 통신하는 ajax함수(비동기 통신) -- 화면이 고정된 상태에서 작업처리
			$.ajax({
				type: "POST", //서버에 전송하는 HTTP요청 방식 회원가입이니까 POST방식.
				url: "/user/", //서버 요청 URI //UserController의 register 메서드의 PostMapping의 주소값이다. 이 register 메서드는, 이곳 자바스크립트가 적힌곳에서 데이터를 참조해서 결국 Mapper.xml의 SQL문의 id="register" 과 통신하는 것이다.
				headers: {
					"Content-Type": "application/json"
				}, //요청 헤더 정보 , Content-type: 너는 요청할때 어떤 데이터를 보낼건데? 나는 서버에 json 데이터를 보낼거에요.
				dataType: "text", //응답받을 데이터의 형태  
				data: JSON.stringify(user), //서버로 전송할 데이터
				success: function(result) { //result == joinSuccess가 담겨지는 곳, 함수의 매개변수는 통신성공시의 데이터가 저장될 곳.
					console.log("통신 성공!: " + result);
					if(result === "joinSuccess") {
						alert("회원가입에 성공했습니다!");
						location.href="/"; // 홈으로 돌려보낸다.
					} else {
						alert("회원가입에 실패했습니다!");
					}
				}, // success function: 통신 성공시 처리할 내용들을 함수 내부에 작성.
				error: function() {
					console.log("통신 실패!");
				} //통신 실패 시 처리할 내용들을 함수 내부에 작성.
			});
		
		} else {
			alert('입력정보를 다시 확인하세요.');			
		}
	});
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	//로그인 검증~~
	//ID 입력값 검증.
	$('#signInId').on('keyup', function() {
		if($("#signInId").val() == ""){
			$('#signInId').css("background-color", "#A5D8FA");
			$('#idCheck').html('<b style="font-size:14px;color:red;">[아이디를 입력해주세요.]</b>');
			chk1 = false;
		}		
		
		//아이디 유효성검사
		else if(!getIdCheck.test($("#signInId").val())){
			$('#signInId').css("background-color", "#A5D8FA");
			$('#idCheck').html('<b style="font-size:14px;color:red;">[영문자,숫자 4~14자 입력해주세요]</b>');	  
			chk1 = false;
		} else {
			$('#signInId').css("background-color", "#A5D8FA");
			$('#idCheck').html('<b style="font-size:14px;color:green;"></b>');
			chk1 = true;
		}
	});
	
	//패스워드 입력값 검증.
	$('#signInPw').on('keyup', function() {
		//비밀번호 공백 확인
		if($("#signInPw").val() === ""){
		    $('#signInPw').css("background-color", "#A5D8FA");
			$('#pwCheck').html('<b style="font-size:14px;color:red;">[패스워드는 필수입니다.]</b>');
			chk2 = false;
		}		         
		//비밀번호 유효성검사
		else if(!getPwCheck.test($("#signInPw").val()) || $("#signInPw").val().length < 8){
		    $('#signInPw').css("background-color", "#A5D8FA");
			$('#pwCheck').html('<b style="font-size:14px;color:red;">[특수문자 포함 8자이상]</b>');
			chk2 = false;
		} else { //164
			$('#signInPw').css("background-color", "#A5D8FA");
			$('#pwCheck').html('<b style="font-size:14px;color:green;"></b>');
			chk2 = true;
		}
		
	});
	
////////////////////////////////로그인 버튼 클릭이벤트/////////////////////////////////////////

	$("#signIn-btn").click(function() { //93번 로그인 버튼
		if(chk1 && chk2) { //369번라인 ID입력값검증에서 chk1,chk2가 통과 되었다면
			//ajax통신으로 서버에서 값 받아오기 //통신으로 서버에서 값을 받아오겠다.
			const id = $('#signInId').val(); //31번라인 id="signid"값을 id에 담겠다.
			const pw = $('#signInPw').val();
			//is()함수는 상태여부를 판단하여 논리값을 리턴합니다.
			//input 태그인데, name 속성값인 autoLogin인 애가 체크 되었나요? 라고 물어보는것이다.
			const autoLogin = $("input[name=autoLogin]").is(":checked"); //체크를 했다면 true, 안했으면 false를 담을것이다.
			
			
			console.log("id: " + id);
			console.log("pw: " + pw);
			console.log("auto: " + autoLogin); //UserVO에 autoLogin값 추가.
			//기존의 id나,pw값은
			
			//$.ajax에서 url이 /user/와 통신하는 것이다.
			//UserController에서  /user는 UserVO객체를 받는다.
			//key:value == JSON형태로, 이값을 서버에 전송할것이다.
			
			
			const userInfo = { //userInfo라는 자바스크립트 객체에 자바스크립트 객체로 포장(VO에 담길수 있도록)
					
			//UserVO의 account는 id랑 매칭, password는 pw, autoLogin은 autoLogin...
			
					account : id, // KEY(UserVO) : VALUE (자바스크립트 값)
					password : pw,
					autoLogin : autoLogin	// url: "/user/loginCheck" 과 통신
							
			};
			
			$.ajax({
				type: "POST",
				url: "/user/loginCheck", //loginCheck와 통신하겠다.
				headers: {
	                "Content-Type": "application/json"
	            },
				data: JSON.stringify(userInfo),  //해석: 자바스크립트 객체를 JSON 문자열로 변환하겠다.

				dataType : "text", //리턴 dataType은 text이다

				success: function(data) { //성공했을경우 매개변수 값(내맘대로 설정)을 받아서

					console.log("result: " + data);	 //출력

					if(data === "idFail") { //자바스크립트는 " 와 '을 혼용사용한다.
						$('#signInId').css("background-color", "#A5D8FA");
						$('#idCheck').html('<b style="font-size:14px;color:red;">[회원가입 먼저 해주세요!]</b>'); 
						$('#signInPw').val(""); // "" (공백)을 넣어라.
						$('#signInId').focus();  //입력창으로 마우스커서를 옮겨라.
						chk2 = false;
						
				    } else if(data === "pwFail") { //pwFail일 경우,
						$('#signInPw').css("background-color", "#A5D8FA"); 
						$('#signInPw').val(""); //그리고, 패스워드 입력값을 지워버린다.
						$('#signInPw').focus(); //그리고 signInPw에 마우스 커서창을 이동시킨다.
						$('#pwCheck').html('<b style="font-size:14px;color:red;">[비밀번호를 다시 확인 해주세요.]</b>');
						//pwCheck란에 [비밀번호를 다시 확인해주세요]를 삽입시킨다.
						chk2 = false;
						
					} else if(data === "loginSuccess") {
						self.location="/"; //홈으로 돌려보냄
					}
				}
			});
			
		} else {
			alert("입력정보를 다시 확인해주세요!");
		}
	});
	
});//end JQuery

</script>










