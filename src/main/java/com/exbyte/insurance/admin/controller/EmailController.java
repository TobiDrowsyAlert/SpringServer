package com.exbyte.insurance.admin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exbyte.insurance.admin.service.AdminService;

@Controller
@RequestMapping("/email")
public class EmailController {
	private final AdminService adminService;
	
	@Inject
	public EmailController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	
	
}
