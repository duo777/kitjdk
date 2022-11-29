package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.entity.GenericService;

/**
 * Classe responsável pelas operações relativos a um {@link Cliente}.
 * 
 * @author Roberto Alencar
 *
 */
@Service
public class ClienteService extends GenericService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente) {

	super.preencherCamposAuditoria(cliente);
	return repository.save(cliente);
    }
    
    @Transactional
    public Cliente obterClientePorID(Long id) {

	return repository.findById(id).get();
    }

    @Transactional
    public List<Cliente> consultarPorChaveEmpresa(String chaveEmpresa) {

	return repository.findByChaveEmpresaOrderByNomeAsc(chaveEmpresa);
    }
    
    @Transactional
    public void update(Long id, Cliente clienteAlterado) {

	 Cliente cliente = this.obterClientePorID(id);
	 cliente.updateFrom(clienteAlterado);
	 super.preencherCamposAuditoria(cliente);

	 repository.save(cliente);
    }
    
    @Transactional
    public void delete(Long id) {

	Cliente cliente = this.obterClientePorID(id);
	cliente.setHabilitado(Boolean.FALSE);
	super.preencherCamposAuditoria(cliente);

	repository.save(cliente);
    }

}
