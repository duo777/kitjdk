package br.com.ifpe.oxefood.modelo.entregador;

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
@Table(name = "Entregador")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entregador extends EntidadeAuditavel {

    private static final long serialVersionUID = -7575490125543141205L;

    @Column
    private String chaveEmpresa;
    
    @Column
    private String cpf;
    
    @Column
    private String rg;
    
    @Column
    private String nome;
    
    @Column
    private String endereco;
    
    @Column
    private int qtdEntregasRealizadas;
    
    @Column
    private Double valorFrete;
    
    public void updateFrom(Entregador param) {

	this.setChaveEmpresa(param.getChaveEmpresa());
	this.setCpf(param.getCpf());
	this.setRg(param.getRg());
	this.setNome(param.getNome());
	this.setEndereco(param.getEndereco());
	this.setQtdEntregasRealizadas(param.getQtdEntregasRealizadas());
	this.setValorFrete(param.getValorFrete());
    }

}
