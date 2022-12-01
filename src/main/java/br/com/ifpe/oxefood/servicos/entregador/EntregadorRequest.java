package br.com.ifpe.oxefood.servicos.entregador;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorRequest {

    private String chaveEmpresa;

    private String cpf;

    private String rg;

    private String nome;

    private String endereco;

    private int qtdEntregasRealizadas;

    private Double valorFrete;

    public Entregador buildEntregador() {

	return Entregador.builder()
		.chaveEmpresa(chaveEmpresa)
		.cpf(cpf)
		.rg(rg)
		.nome(nome)
		.endereco(endereco)
		.qtdEntregasRealizadas(qtdEntregasRealizadas)
		.valorFrete(valorFrete)
		.build();
    }
}
