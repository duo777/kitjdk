package br.com.ifpe.oxefood.modelo.entregador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EntregadorRepository extends JpaRepository<Entregador, Long>, JpaSpecificationExecutor<Entregador> {

    List<Entregador> findByChaveEmpresaOrderByNomeAsc(String chaveEmpresa);
    
}
