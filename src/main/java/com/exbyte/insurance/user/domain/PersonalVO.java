package com.exbyte.insurance.user.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonalVO {

    String userId;
    double ear;
    double mar;
    double avgStage;
    Boolean isWeakTime;
    String frequencyReason;

}