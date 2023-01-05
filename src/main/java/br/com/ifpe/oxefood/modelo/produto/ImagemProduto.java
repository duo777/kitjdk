package br.com.ifpe.oxefood.modelo.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefood.util.entity.EntidadeNegocio;
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
@Table(name = "ImagemProduto")
@Where(clause = "habilitado = true")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagemProduto extends EntidadeNegocio {
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @Column(nullable = false)
    private String titulo;

}
