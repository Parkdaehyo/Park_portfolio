package com.spring.mvc.user.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.mvc.user.model.UserVO;

public interface IUserMapper {

	//회원 가입 기능
	void register(UserVO user);
	
	//아이디 중복체크 기능
	Integer checkId(String account);
	
	//회원 탈퇴 기능
	void delete(String account);
	
	//회원 (단일)정보 조회기능
	UserVO selectOne(String account);
	
	
	//아이디 찾기 조회
	UserVO findid(UserVO user);
	
	//회원정보 전체 조회 기능
	List<UserVO> selectAll();
	
	//자동 로그인 쿠키값 DB 저장 처리
	//서비스에서 넘어올때는 Map 객체를 받도록 설정.
	void keepLogin(Map<String,Object> datas);
	
	//세션 아이디를 통한 회원정보 조회 기능
	UserVO getUserWithSessionId(String sessionId);
	
	//회원 수정기능
	public void memberupdate(UserVO uservo);
	
	
	
}
