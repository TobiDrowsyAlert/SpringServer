package com.exbyte.insurance.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestSleepAnalyze {
    private int[] rect;
    private Boolean driver;
    private int[][] landmarks;
    private int frame;
    private Boolean isCorrect;
    private String userId;
    private Boolean isWeakTime;
    private double avgStage;
    private int sleep_step;
}
