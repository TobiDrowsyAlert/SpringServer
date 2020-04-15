package com.exbyte.insurance.api.domain;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Landmark {
	ArrayList landmarks;
	
	Landmark(ArrayList landmarks){
		this.landmarks = landmarks;
	}

}
