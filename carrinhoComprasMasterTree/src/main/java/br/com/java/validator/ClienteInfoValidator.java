package br.com.java.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.java.model.ClienteInfo;

@Component
public class ClienteInfoValidator implements Validator{
	
	private EmailValidator emailValidator = EmailValidator.getInstance();

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == ClienteInfo.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ClienteInfo cliInfo = (ClienteInfo) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.clienteForm.nome");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.clienteForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endereco", "NotEmpty.clienteForm.endereco");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefone", "NotEmpty.clienteForm.telefone");
        
        if (!emailValidator.isValid(cliInfo.getEmail())) {
			errors.reject("email", "Pattern.clienteForm.email");
		}
		
	}

}
