package com.nagarro.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {

	private Long id;
	private String accountType;
	private String accountNumber;
	
}
