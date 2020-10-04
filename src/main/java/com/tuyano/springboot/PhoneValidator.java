package com.tuyano.springboot;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	private boolean onlyNumber = false;

	public void initialize(Phone phone) {
		onlyNumber = phone.onlyNumber();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) {
			return false;
		}
		if (onlyNumber) {
			return value.matches("[0-9]*");
		}
		return value.matches("[0-9()-]*");
	}

}
