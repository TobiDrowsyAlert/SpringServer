package com.exbyte.insurance.admin.service;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.persistence.AdminDAO;
import com.exbyte.insurance.commons.mail.TempKey;

@Service
public class AdminMailService {

	private Logger logger = LoggerFactory.getLogger(TempKey.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	static private AdminDAO adminDAO;
	
	@Inject
	public AdminMailService(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	// 이메일 인증키 발송 추후 서비스로 이동
	public void mailSendWithUserKey(AdminVO adminVO, HttpServletRequest request) throws Exception {

		TempKey tempKey = new TempKey();
		String key = tempKey.getKey(20, false);
		
		MimeMessage mail = mailSender.createMimeMessage();
		logger.info(mail.toString());
		String htmlStr = "<h2> 안녕하세요 </>" + "<h4>" + adminVO.getAdminId() + "님</h4>"
				+ "<p> 인증하기 버튼으로 인증키 확인이 가능합니다."
				+ "<a href='http://localhost:8080" + request.getContextPath() + "/admin/auth?"
						+ "adminId="+adminVO.getAdminId() + "&authKey="+key + "'>인증하기</a></p>";
		adminVO.setAdminAuthKey(key);
		adminDAO.updateAuthKey(adminVO);
		mail.setSubject("[본인인증] Com : " + adminVO.getAdminId() + "님의 인증메일입니다.", "utf-8"); // 메일 제목 설정
		mail.setText(htmlStr, "utf-8", "html");
		mail.addRecipient(RecipientType.TO, new InternetAddress(adminVO.getAdminEmail())); // 수신자 설정
		mailSender.send(mail);
	}
	
	// 이메일 인증키 발송 추후 서비스로 이동
	public void mailSendWithUserKey(AdminVO adminVO) throws Exception{

		TempKey tempKey = new TempKey();
		String key = tempKey.getKey(20, false);
		
		MimeMessage mail = mailSender.createMimeMessage();
		logger.info(mail.toString());
		String htmlStr = "<h2> 안녕하세요 </>" + "<h4>" + adminVO.getAdminId() + "님</h4>"
				+ "<p> 인증하기 버튼으로 인증키 확인이 가능합니다."
				+ "<a href='http://localhost:8080" + "/" + "/admin/auth?"
						+ "adminId="+adminVO.getAdminId() + "&authKey="+key + "'>인증하기</a></p>";
		adminVO.setAdminAuthKey(key);
		adminDAO.updateAuthKey(adminVO);
		mail.setSubject("[본인인증] Com : " + adminVO.getAdminId() + "님의 인증메일입니다.", "utf-8"); // 메일 제목 설정
		mail.setText(htmlStr, "utf-8", "html");
		mail.addRecipient(RecipientType.TO, new InternetAddress(adminVO.getAdminEmail())); // 수신자 설정
		mailSender.send(mail);
	
	}
	
	// 이메일 인증키 , 계정정보 전송
	public void mailSendWithAccount(AdminVO adminVO, HttpServletRequest request) throws Exception{

		TempKey tempKey = new TempKey();
		String key = tempKey.getKey(20, false);
		HttpSession httpSession = request.getSession();
		
		MimeMessage mail = mailSender.createMimeMessage(); // root-context에 기록된 정보들을 읽어, mail 객체 생성, Gmail 접근
		logger.info(mail.toString());
		String htmlStr = "<h1>계정찾기</h1>" + "<h2> 안녕하세요 </>" + "<h4>" + adminVO.getAdminName() + "님</h4>"
				+ "<p>아이디 : " + adminVO.getAdminId() + " </p>"
				+ "<p> 비밀번호 변경을 원하시면 아래 링크를 통해 변경하실 수 있습니다.</p>"
				+ "<a href='http://localhost:8080" + request.getContextPath() + "/admin/updatePw?"
						+ "adminId="+adminVO.getAdminId() + "&authKey="+key + "'>인증하기</a></p>";
		adminVO.setAdminAuthKey(key);
		adminDAO.updateAuthKey(adminVO); // 키 등록
		httpSession.setAttribute("adminId", adminVO.getAdminId());
		
		mail.setSubject("[계정찾기] Com : " + adminVO.getAdminId() + "님의 인증메일입니다.", "utf-8"); // 메일 제목 설정
		mail.setText(htmlStr, "utf-8", "html");
		mail.addRecipient(RecipientType.TO, new InternetAddress(adminVO.getAdminEmail())); // 수신자 설정
		mailSender.send(mail);
	
	}
}
