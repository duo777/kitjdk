package br.com.ifpe.oxefood.modelo.desconto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CupomDesconto")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CupomDesconto extends EntidadeAuditavel {
    
    private static final long serialVersionUID = 6858120071199595312L;
    
    @Column
    private String chaveEmpresa;
    
    @Column
    private String codigoDesconto;
    
    @Column
    private Double percentualDesconto;
    
    @Column
    private Double valorDesconto;
    
    @Column
    private Double valorMinimoPedidoPermitido;
    
    @Column
    private int quantidadeUso;
    
    public void updateFrom(CupomDesconto param) {

	this.setChaveEmpresa(param.getChaveEmpresa());
	this.setCodigoDesconto(param.getCodigoDesconto());
	this.setPercentualDesconto(param.getPercentualDesconto());
	this.setValorDesconto(param.getValorDesconto());
	this.setValorMinimoPedidoPermitido(param.getValorMinimoPedidoPermitido());
	this.setQuantidadeUso(param.getQuantidadeUso());
    }

}
