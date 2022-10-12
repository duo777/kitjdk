package br.com.ifpe.oxefood.servicos.cliente;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Roberto Alencar
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    private String chaveEmpresa;

    private String nome;

    private String cpf;

    private String fone;

    private String foneAlternativo;
    
    public Cliente buildCliente() {

	return Cliente.builder()
		.chaveEmpresa(chaveEmpresa)
		.nome(nome)
		.cpf(cpf)
		.fone(fone)
		.foneAlternativo(foneAlternativo)
		.build();
    }
    
}
