package br.com.ifpe.oxefood.servicos.desconto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.desconto.CupomDesconto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor	
public class CupomDescontoRequest {

    private String chaveEmpresa;
    
    private String codigoDesconto;
    
    private Double percentualDesconto;
    
    private Double valorDesconto;
    
    private Double valorMinimoPedidoPermitido;
    
    private int quantidadeUso;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataInicioVigencia;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataFimVigencia;
    
    public CupomDesconto buildCupomDesconto() {

	return CupomDesconto.builder()
		.chaveEmpresa(chaveEmpresa)
		.codigoDesconto(codigoDesconto)
		.dataInicioVigencia(dataInicioVigencia)
		.dataFimVigencia(dataFimVigencia)
		.percentualDesconto(percentualDesconto)
		.valorDesconto(valorDesconto)
		.valorMinimoPedidoPermitido(valorMinimoPedidoPermitido)
		.quantidadeUso(quantidadeUso)
		.build();
    }
    
}
