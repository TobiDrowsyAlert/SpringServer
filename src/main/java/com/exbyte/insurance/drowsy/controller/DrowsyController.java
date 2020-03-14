package com.exbyte.insurance.drowsy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exbyte.insurance.drowsy.domain.PersonalVO;
import com.exbyte.insurance.drowsy.domain.StatusCode;
import com.exbyte.insurance.drowsy.dto.DrowsyDTO;
import com.exbyte.insurance.drowsy.service.DrowsyService;

@Controller
@RequestMapping(value = "/drowsy")
public class DrowsyController {

	DrowsyService drowsyService;
	
	DrowsyController(DrowsyService drowsyService){
		this.drowsyService = drowsyService;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public DrowsyDTO create(PersonalVO personalVO){
		
		DrowsyDTO drowsyDTO = new DrowsyDTO();
		
		try {
			drowsyDTO.setPersonalVO(null);
		}catch(Exception e) {
			drowsyDTO.setStatusCode(StatusCode.CODE_000);
			return drowsyDTO;
		}
		
		drowsyDTO.setStatusCode(StatusCode.CODE_400);
		return drowsyDTO;
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	@ResponseBody
	public DrowsyDTO read(String userId) {
		DrowsyDTO drowsyDTO = new DrowsyDTO();
		drowsyDTO.setPersonalVO(drowsyService.read(userId));
		
		return drowsyDTO;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public DrowsyDTO update(String userId) {
		
		DrowsyDTO drowsyDTO = new DrowsyDTO();
		
		try {
			drowsyDTO.setPersonalVO(null);
			drowsyService.update(userId);
		}catch(Exception e) {
			drowsyDTO.setStatusCode(StatusCode.CODE_000);
			return drowsyDTO;
		}
		
		drowsyDTO.setStatusCode(StatusCode.CODE_400);
		return drowsyDTO;
	}
	
	
}
