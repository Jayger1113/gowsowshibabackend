package com.gowsow.shiba.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorMessage {

	private Date timeStamp = new Date();
	private String result;

}