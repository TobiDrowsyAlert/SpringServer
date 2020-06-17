package com.exbyte.insurance.user.service;

import com.exbyte.insurance.commons.utils.ConnectionRestTemplate;
import com.exbyte.insurance.user.dao.PersonalDAO;
import com.exbyte.insurance.user.dao.UserDAO;
import com.exbyte.insurance.user.domain.PersonalVO;
import com.exbyte.insurance.user.domain.UserVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;

@Service
public class PersonalService {

    PersonalDAO personalDAO;

    @Inject
    PersonalService(PersonalDAO personalDAO){
        this.personalDAO = personalDAO;
    }

    public void create(UserVO userVO) throws Exception {
        personalDAO.create(userVO);
    }

    public ResponseEntity<String> update(UserVO userVO) throws Exception {
        String url = "/personal";
        PersonalVO currenctPersonal = personalDAO.select(userVO);

        PersonalVO personalVO = new PersonalVO();

        if(personalDAO.countLog(userVO) > 1 && personalDAO.countMinuteLog(userVO) > 1){
            personalVO.setEar((currenctPersonal.getEar() * 29 + personalDAO.calculateEar(userVO)) / 30);
            personalVO.setMar((currenctPersonal.getMar() * 29 + personalDAO.calculateMar(userVO)) / 30);
            personalVO.setAvgStage((currenctPersonal.getAvgStage() * 29 + personalDAO.calculateAvgStage(userVO)) / 30);
            personalVO.setFrequencyReason(personalDAO.calculateFrequencyReason(userVO));
            personalVO.setIsWeakTime(false);

            currenctPersonal = personalVO;
        }
        personalDAO.update(currenctPersonal);
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(currenctPersonal);
        System.out.println(json);

        return ConnectionRestTemplate.connect(json, url);
    }

}
