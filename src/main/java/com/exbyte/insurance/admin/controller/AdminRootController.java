package com.exbyte.insurance.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.admin.domain.AdminVO;
import com.exbyte.insurance.admin.domain.PointDTO;
import com.exbyte.insurance.admin.service.AdminService;
import com.exbyte.insurance.point.domain.PointVO;

@Controller
@RequestMapping("/admin")
public class AdminRootController {
	
	
	AdminService adminService;
	Logger logger = LoggerFactory.getLogger(AdminRootController.class);
	
	@Inject
	public AdminRootController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model, AdminVO adminVO) throws Exception {
		
		List<AdminVO> adminList = adminService.selectAdmin(adminVO);
		List<PointVO> pointList = adminService.selectAllPoint();
		List<String> nameList = new ArrayList();
		
		List<String> pointNameList = new ArrayList();
		for(AdminVO admin : adminList) {
			String name = pointList.get(admin.getAdminPoint()).getPointName();
			nameList.add(name);
		}
		
		logger.warn("nameList.toString() : " + nameList.toString());
		logger.warn("pointList.toString() : " + pointList.toString());
		
		model.addAttribute("adminList", adminService.selectAdmin(adminVO));
		model.addAttribute("pointList", pointList);
		model.addAttribute("nameList", nameList);
		
		return "/admin/list";
	}
	
	@RequestMapping(value ="/point/list", method = RequestMethod.GET)
	public String pointList(Model model) throws Exception {
		List<PointDTO> list = adminService.selectPointAdmin();
		model.addAttribute("pointList", list);
		logger.info(list.toString());
		return "/point/list";
	}
	
	@RequestMapping(value = "/list/delete", method = RequestMethod.POST)
	@ResponseBody
	public int list(Model model, @RequestParam(value="chkbox[]") List<String> list) throws Exception{
		
		for(String adminId : list) {
			adminService.delete(adminService.read(adminId));
		}
		
		return 1;
	}
	
}
