package com.dao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

public class UserDAO{
	private static SqlSessionFactory ssf;
	static{
		try{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//회원가입 - 완료
	public static void insertUser(UserDTO d){
		SqlSession session=ssf.openSession(true);
		session.insert("insertUser",d);
		session.close();
	}
	
	//회원가입 (이메일 중복확인)
	public static int confirmEmail(String email){
		SqlSession session=ssf.openSession();
		 int cnt=session.selectOne("confirmEmail",email);
		 session.close();
		 return cnt;
	}
	
	//로그인 부분 (emailCheck, pwdCheck)
	public static int emailCheck(String email){
		SqlSession session=ssf.openSession();
		int cnt=session.selectOne("emailCheck",email);
		session.close();
		return cnt;
	}
	//로그인 비밀번호 비교
	public static String pwdCheck(String email){
		SqlSession session=ssf.openSession();
		String dbPwd = (String)session.selectOne("pwdCheck",email);
		session.close();
		return dbPwd;
	}
	//로그인 세션 저장용
	public static String loginS(String email){
		SqlSession session=ssf.openSession();
		String list = (String) session.selectOne("loginS", email);
		session.close();
		return list;
	}
	//로그인 세션 저장용
	public static String loginS2(String email){
		SqlSession session=ssf.openSession();
		String list = (String) session.selectOne("loginS", email);
		session.close();
		return list;
	}
	
}