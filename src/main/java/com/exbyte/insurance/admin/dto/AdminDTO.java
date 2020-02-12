package com.exbyte.insurance.admin.dto;

import com.exbyte.insurance.admin.domain.AdminVO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AdminDTO {
	
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SignUpReq {
		private String adminId;
		private String adminPw;
		private String adminName;
		private String adminEmail;
		private String adminPhone;
		private String adminCallNumber;
		private String adminPosition;
		private int adminPoint;
		private String adminAuthKey;
		
		@Builder
		public SignUpReq(String adminId, String adminPw, String adminName, String adminEmail,
				String adminPhone, String adminCallNumber, String adminPosition, int adminPoint, String adminAuthKey) {
			this.adminId = adminId;
			this.adminPw = adminPw;
			this.adminName = adminName;
			this.adminEmail = adminEmail;
			this.adminPhone = adminPhone;
			this.adminCallNumber = adminCallNumber;
			this.adminPosition = adminPosition;
			this.adminPoint = adminPoint;
			this.adminAuthKey = adminAuthKey;
		}
		
		public AdminVO toEntity() {
			return AdminVO.builder()
					.adminId(this.adminId)
					.adminPw(this.adminPw)
					.adminName(this.adminName)
					.adminEmail(this.adminEmail)
					.adminPhone(this.adminPhone)
					.adminCallNumber(this.adminCallNumber)
					.adminPosition(this.adminPosition)
					.adminPoint(this.adminPoint)
					.adminAuthKey(this.adminAuthKey)
					.build();
		}
	}
	
	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SendEmailReq {
		
	}
	
	public static class Res{
		private String adminPw;
	}
}
