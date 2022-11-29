package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "Cliente")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntidadeAuditavel {

    private static final long serialVersionUID = 4411577995549704667L;

    public static final String LABEL = "Cliente";

    @JsonIgnore
    @NotNull
    @Column(nullable = false)
    private String chaveEmpresa;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<EnderecoCliente> enderecos;

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column
    private String fone;

    @Column
    private String foneAlternativo;

    public void updateFrom(Cliente param) {

	this.setNome(param.getNome());
	this.setCpf(param.getCpf());
	this.setFone(param.getFone());
	this.setFoneAlternativo(param.getFoneAlternativo());
    }
    
}
