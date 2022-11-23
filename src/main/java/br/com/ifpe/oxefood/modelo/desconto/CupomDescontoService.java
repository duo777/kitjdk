package br.com.ifpe.oxefood.modelo.desconto;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.entity.GenericService;

@Service
public class CupomDescontoService extends GenericService {

    @Autowired
    private CupomDescontoRepository repository;

    @Transactional
    public CupomDesconto save(CupomDesconto cupom) {

	super.preencherCamposAuditoria(cupom);
	return repository.save(cupom);
    }

    @Transactional
    public CupomDesconto findById(Long id) {

	return repository.findById(id).get();
    }

    @Transactional
    public List<CupomDesconto> consultarPorChaveEmpresa(String chaveEmpresa) {

	return repository.findByChaveEmpresaOrderByCodigoDescontoAsc(chaveEmpresa);
    }

    @Transactional
    public List<CupomDesconto> consultarTodos() {

	return repository.findAll();
    }
    
    @Transactional
    public void update(Long id, CupomDesconto cupomDescontoAlterado) {

	CupomDesconto cupom = this.findById(id);
	cupom.updateFrom(cupomDescontoAlterado);
	super.preencherCamposAuditoria(cupom);

	repository.save(cupom);
    }
    
    @Transactional
    public void delete(Long id) {

	CupomDesconto cupom = this.findById(id);
	cupom.setHabilitado(Boolean.FALSE);
	super.preencherCamposAuditoria(cupom);

	repository.save(cupom);
    }

}
