package br.com.ifpe.oxefood.modelo.produto;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.entity.GenericService;
import br.com.ifpe.oxefood.util.exception.produto.ProdutoException;

@Service
public class ProdutoService extends GenericService {

    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public Produto save(Produto produto) {

	if (produto.getValorUnitario() < 10) {
	    throw new ProdutoException(ProdutoException.MSG_VALOR_MINIMO_PRODUTO);
	}

	super.preencherCamposAuditoria(produto);
	return repository.save(produto);
    }

    @Transactional
    public Produto findById(Long id) {

	return repository.findById(id).get();
    }

    @Transactional
    public List<Produto> consultarPorChaveEmpresa(String chaveEmpresa) {

	return repository.findByChaveEmpresaOrderByTituloAsc(chaveEmpresa);
    }

    @Transactional
    public List<Produto> consultarTodos() {

	return repository.findAll();
    }

    @Transactional
    public Produto update(Long id, Produto produtoAlterado) {

	Produto produto = this.findById(id);
	produto.updateFrom(produtoAlterado);
	super.preencherCamposAuditoria(produto);

	return repository.save(produto);
    }

    @Transactional
    public void delete(Long id) {

	Produto produto = this.findById(id);
	produto.setHabilitado(Boolean.FALSE);
	super.preencherCamposAuditoria(produto);

	repository.save(produto);
    }
    
    @Transactional
    public String createImage(Long produtoId, String imagem) {

	Produto produto = this.findById(produtoId);

	ImagemProduto imagemProduto = ImagemProduto.builder().titulo(imagem).produto(produto).build();

	imagemProduto.setHabilitado(Boolean.TRUE);
	imagemProdutoRepository.saveAndFlush(imagemProduto);

	produto.adicionarImagem(imagemProduto);
	update(produto);

	return imagem;
    }
}
