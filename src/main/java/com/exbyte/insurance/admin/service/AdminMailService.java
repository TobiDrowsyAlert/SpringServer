package com.exbyte.insurance.admin.service;

import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.persistence.AdminDAO;

@Service
public class AdminMailService {

	private Logger logger = LoggerFactory.getLogger(AdminMailService.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	static private AdminDAO adminDAO;
	
	@Inject
	public AdminMailService(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	// 임의의 인증 키 생성
	private String makeRandomKey(int size, boolean lowerCheck) {
		
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
		
		do {
			num = ran.nextInt(75)+48;
			if((num >= 48 && num <=57) || (num >=65 && num <= 90) || (num >= 97 && num <=122)) {
				sb.append((char)num);
			}else {
				continue;
			}
		} while (sb.length() < size);
		
		if(lowerCheck) {
			return sb.toString().toLowerCase();
		}
		
		return sb.toString();
	}
	
	public void mailSend(AdminVO adminVO, String contextPath, String emailType) throws Exception {

		String key = makeRandomKey(20, false);
		String htmlStr;
		MimeMessage mail = mailSender.createMimeMessage();
		
		if(emailType.equals("auth")) {
			htmlStr = "<h2> 안녕하세요 </>" + "<h4>" + adminVO.getAdminId() + "님</h4>"
					+ "<p> 인증하기 버튼으로 인증키 확인이 가능합니다."
					+ "<a href='http://localhost:8080" + contextPath + "/admin/confirm?"
							+ "adminId="+adminVO.getAdminId() + "&authKey="+key + "'>인증하기</a></p>";
			mail.setSubject("[본인인증] Com : " + adminVO.getAdminId() + "님의 인증메일입니다.", "utf-8");
		}else if(emailType.equals("find")) {
			htmlStr = "<h1>계정찾기</h1>" + "<h2> 안녕하세요 </>" + "<h4>" + adminVO.getAdminName() + "님</h4>"
					+ "<p>아이디 : " + adminVO.getAdminId() + " </p>"
					+ "<p> 비밀번호 변경을 원하시면 아래 링크를 통해 변경하실 수 있습니다.</p>"
					+ "<a href='http://localhost:8080" + contextPath + "/admin/updatePw?"
							+ "adminId="+adminVO.getAdminId() + "&authKey="+key + "'>인증하기</a></p>";
			mail.setSubject("[계정찾기] Com : " + adminVO.getAdminId() + "님의 인증메일입니다.", "utf-8");
		}else {
			htmlStr = null;
			mail.setSubject(null);
		}
		adminVO.setAdminAuthKey(key);
		adminDAO.updateAuthKey(adminVO);
		mail.setText(htmlStr, "utf-8", "html");
		mail.addRecipient(RecipientType.TO, new InternetAddress(adminVO.getAdminEmail())); // 수신자 설정
		mailSender.send(mail);
	}
	
}
