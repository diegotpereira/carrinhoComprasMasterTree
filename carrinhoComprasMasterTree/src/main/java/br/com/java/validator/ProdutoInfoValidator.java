package br.com.java.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.java.dao.ProdutoDAO;
import br.com.java.entity.Produto;
import br.com.java.model.ProdutoInfo;

@Component
public class ProdutoInfoValidator implements Validator{
	
	@Autowired
	private ProdutoDAO produtoDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == ProdutoInfo.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ProdutoInfo produtoInfo = (ProdutoInfo) target;

		// Check the fields of ProductInfo class.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codigo", "NotEmpty.produtoForm.codigo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty.produtoForm.nome");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "preco", "NotEmpty.produtoForm.preco");

		String codigo = produtoInfo.getCodigo();

		if (codigo != null && codigo.length() > 0) {
			if (codigo.matches("\\s+")) {
				errors.rejectValue("codigo", "Pattern.produtoForm.codigo");

			} else if (produtoInfo.isNovoProduto()) {
				Produto produto = produtoDAO.descProduto(codigo);
				if (produto != null) {
					errors.rejectValue("codigo", "Duplicate.produtoForm.codigo");
				}
			}

		}

	}

}
