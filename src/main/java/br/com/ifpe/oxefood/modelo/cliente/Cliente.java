package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
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
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @JsonIgnore
    @Column(nullable = false)
    private String chaveEmpresa;

    @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<EnderecoCliente> enderecos;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true)
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
