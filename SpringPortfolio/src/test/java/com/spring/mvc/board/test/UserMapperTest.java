package com.spring.mvc.board.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/mvc-config.xml"}) // 두줄은 설정 파일을 읽는 코드
public class UserMapperTest {

	@Autowired
	private IUserMapper mapper; //IUserMapper를 사용해야하기 때문에 이것에 대한 필드를 의존성 주입하여 처리해야한다.
	
	@Test
	public void registerTest() {
		
		UserVO user = new UserVO();
		user.setAccount("abc1234");
		user.setPassword("def1234!");
		user.setName("박영희");
		
		mapper.register(user);
		System.out.println("회원가입 성공!");
		
	}

	
	@Test
	public void deleteTest() {
		String account = "abc123";
		mapper.delete(account);
		System.out.println("탈퇴 성공!");
		
		
	}
	
	@Test
	public void loginTest() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String inputId = "ibb910";
		String inputPw = "er9855!!";
		
		//회원정보 조회한 것을 UserVO에 담는다.
		UserVO dbData = mapper.selectOne(inputId);
		String dbPw = dbData.getPassword(); //조회된 UserVO의 패스워드를 dbPw에 담아본다.
		
		//이 테스트에서 자체적으로 입력한 aaa1234@와 
		//DB에 갔다와서 UserVO에 담아진 dbPw의 패스워드가 같은가? 비교
		System.out.println("입력된 비밀번호: " + inputPw);  
		System.out.println("DB에 등록된 비밀번호: " + dbPw);
		
		//System.out.println("비밀번호 일치?? " + inputPw.equals(dbPw));
		System.out.println("비밀번호 일치?? " + encoder.matches(inputPw, dbPw));
		
	}
	
	
	

}
