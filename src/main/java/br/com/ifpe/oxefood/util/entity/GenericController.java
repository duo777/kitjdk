package br.com.ifpe.oxefood.util.entity;

import br.com.ifpe.oxefood.util.exception.PreenchimentoException;

/**
 * @author Roberto Alencar
 *
 */
public class GenericController {

    protected void validarPreenchimentoChave(String chave) {

	if (chave == null || chave.equals("")) {
	    throw new PreenchimentoException(PreenchimentoException.MSG_CHAVE_NAO_INFORMADA);
	}
    }
}
