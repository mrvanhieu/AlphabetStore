package mum.edu.alphabetstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import mum.edu.alphabetstore.domain.CreditCard;
import mum.edu.alphabetstore.domain.Product;
import mum.edu.alphabetstore.exception.ProductNotFoundException;
import mum.edu.alphabetstore.service.CreditCardService;
import mum.edu.alphabetstore.service.ProductService;


public class CCNumberValidator implements ConstraintValidator<CCNumber, String>{
	
	@Autowired
	private CreditCardService creditCardService;

	public void initialize(CCNumber constraintAnnotation) {
		//  intentionally left blank; this is the place to initialize the constraint annotation for any sensible default values.
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		CreditCard card;
		try {
			card = creditCardService.findCreditCardByNumber(value);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		if(card!= null) {
			return true;
		}
		
		return false;
	}
	
}
