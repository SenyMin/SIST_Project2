package com.sist.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.UserDAO;
import com.sist.dao.UserDTO;

@Controller("loginController")
public class LoginController {
	//�α��� �� �׼�
	@RequestMapping("loginOK.do")
	public String loginOK(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session=req.getSession();
		String email = req.getParameter("logEmail");
		String pwd = req.getParameter("logPwd");
		String LogCheck = "";
		int result = UserDAO.emailCheck(email);
		UserDTO d = UserDAO.logCheck(email);
		if (result == 0) {
			LogCheck = "noemail";
		} else {
			if (d.getPwd().equals(pwd)) {
				LogCheck = "ok";
				// �α��� ������ �α����� �������� ������.
				session.setAttribute("logEmailOK", d.getEmail());
				session.setAttribute("logNicknameOK", d.getNickname());
				session.setAttribute("logUsernoOK", d.getNo());
			} else {
				LogCheck = "nopwd";
			}
		}
		// �α��� �� �α��� ������ ������
		res.getWriter().write(String.valueOf(LogCheck));
		return "ajax";
	}
	
	//�α׾ƿ� �ϱ�
	@RequestMapping("logOutOK.do")
	public String logOutOK(HttpServletRequest req, HttpServletResponse res) throws Exception {
		 req.setCharacterEncoding("UTF-8");
		 HttpSession session=req.getSession();
		 session.removeAttribute("logEmailOK");
		 session.removeAttribute("logNicknameOK");
		 session.removeAttribute("logUsernoOK");
		 
		 //username ���� ���� session�� ����
		 res.sendRedirect("intro.jsp");
		 return "ajax";
	}
	
	// ���̵� ���� ��Ű �����
	@RequestMapping("EmailSaveOK.do")
	public String EmailSaveOK(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String email = req.getParameter("logEmail");
		String logSave = req.getParameter("logSave");
		String emailSave = "";
		System.out.println("email:" + email);
		try {
			if (logSave.equals("1")) {
				Cookie logEmailCookie = new Cookie("logEmail_Cookie", email);
				// ��Ű�� �����Ѵ�. �̸�:testCookie, �� : Hello Cookie
				logEmailCookie.setMaxAge(365 * 24 * 60 * 60);
				// ��Ű�� ��ȿ�Ⱓ�� 365�Ϸ� �����Ѵ�.
				logEmailCookie.setPath("/");
				// ��Ű�� ��ȿ�� ���丮�� "/" �� �����Ѵ�.
				res.addCookie(logEmailCookie);
				// Ŭ���̾�Ʈ ���信 ��Ű�� �߰��Ѵ�.

				Cookie[] cookies = req.getCookies(); // ��û�����κ��� ��Ű�� �����´�.
				System.out.println("���� ������ ��Ű�� ���� : " + cookies.length); // ��Ű��
																		  // ����� �迭�� ���̸�	 �����´�.
				for (int i = 0; i < cookies.length; i++) { // ��Ű �迭�� �ݺ������� ������.
					System.out.println(i + "��° ��Ű �̸� : " + cookies[i].getName()); // ��Ű�� �̸��� �����´�.
					System.out.println(i + "��° ��Ű�� ������ �� : " + cookies[i].getValue()); // ��Ű�� ���� �����´�.
				}
			} else {
				Cookie logEmailCookie = new Cookie("logEmail_Cookie", "");
				// ��Ű�� �����Ѵ�. �̸�:testCookie, �� : Hello Cookie
				logEmailCookie.setMaxAge(365 * 24 * 60 * 60);
				// ��Ű�� ��ȿ�Ⱓ�� 365�Ϸ� �����Ѵ�.
				logEmailCookie.setPath("/");
				// ��Ű�� ��ȿ�� ���丮�� "/" �� �����Ѵ�.
				res.addCookie(logEmailCookie);
				// Ŭ���̾�Ʈ ���信 ��Ű�� �߰��Ѵ�.
			}
		} catch (Exception e) {
			System.out.println("catch" + e.getMessage());
		}
		Cookie[] cookies = req.getCookies();
		emailSave = cookies[1].getValue();
		res.getWriter().write(String.valueOf(emailSave));

		return "ajax";
	}
	
	//����
	@RequestMapping("loginOK.do")
	public String loginOK2(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "ajax";
	}
}