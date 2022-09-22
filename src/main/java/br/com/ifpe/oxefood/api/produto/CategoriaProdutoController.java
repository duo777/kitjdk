package br.com.ifpe.oxefood.api.produto;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ifpe.oxefood.servicos.produto.CategoriaProduto;
import br.com.ifpe.oxefood.servicos.produto.CategoriaProdutoService;
import br.com.ifpe.oxefood.util.entity.GenericController;

/**
 * @author Roberto Alencar
 *
 */
@RestController
@RequestMapping("/api/categoriaproduto")
public class CategoriaProdutoController extends GenericController {

    @Autowired
    private CategoriaProdutoService categoriaProdutoService;

    @PostMapping
    public ResponseEntity<CategoriaProduto> save(@RequestBody @Valid CategoriaProdutoRequest request, UriComponentsBuilder uriBuilder) {

	validarPreenchimentoChave(request.getChaveEmpresa());

	CategoriaProduto categoriaProduto = categoriaProdutoService.save(request.buildCategoriaProduto());
	String path = String.format("/api/categoriaproduto/%d", categoriaProduto.getId());
	URI uri = uriBuilder.path(path).build().toUri();

	return ResponseEntity.created(uri).build();
    }

}
