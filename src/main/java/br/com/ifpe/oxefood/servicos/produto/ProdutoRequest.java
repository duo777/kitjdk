package br.com.ifpe.oxefood.servicos.produto;

import br.com.ifpe.oxefood.modelo.produto.Produto;
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
public class ProdutoRequest {

    private String chaveEmpresa;

    private Long idCategoria;

    private String codigo;
    
    private String titulo;
    
    private String descricao;

    private double valorUnitario;

    private String tempoEntrega;
    
    private Boolean temComplemento;

    private Boolean emDestaque;
    
    public Produto buildProduto() {
	
	Produto produto = Produto.builder()
		.chaveEmpresa(chaveEmpresa)
		.codigo(codigo)
		.titulo(titulo)
		.descricao(descricao)
		.valorUnitario(valorUnitario)
		.tempoEntrega(tempoEntrega)
		.temComplemento(temComplemento)
		.emDestaque(emDestaque)
		.build();

	return produto;
}
    
}
