package br.com.ifpe.oxefood.modelo.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Roberto Alencar
 *
 */
@Entity
@Table(name = "Produto")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends EntidadeAuditavel {

    public static final String LABEL = "Produto";

    private static final long serialVersionUID = 3884904911622197470L;

    @JsonIgnore
    @Column
    private String chaveEmpresa;

    @ManyToOne
    @JoinColumn
    private CategoriaProduto categoria;

    @Column
    private String codigo;
    
    @Column(length = 300, nullable = false)
    private String titulo;
    
    @Column(length = 100000)
    private String descricao;

    @Column(nullable = false)
    private double valorUnitario;

    @Column(length = 50)
    private String tempoEntrega;
    
    @Column
    private Boolean temComplemento;

    @Column
    private Boolean emDestaque;
    
    public void updateFrom(Produto param) {

	this.setCategoria(param.getCategoria());
	this.setTitulo(param.getTitulo());
	this.setCodigo(param.getCodigo());
	this.setDescricao(param.getDescricao());
	this.setValorUnitario(param.getValorUnitario());
	this.setTempoEntrega(param.getTempoEntrega());
	this.setTemComplemento(param.getTemComplemento());
	this.setEmDestaque(param.getEmDestaque());
    }

}
