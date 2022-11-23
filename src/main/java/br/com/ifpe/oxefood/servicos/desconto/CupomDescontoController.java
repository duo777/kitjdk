package br.com.ifpe.oxefood.servicos.desconto;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.desconto.CupomDesconto;
import br.com.ifpe.oxefood.modelo.desconto.CupomDescontoService;
import br.com.ifpe.oxefood.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/cupomdesconto")
public class CupomDescontoController extends GenericController {

    @Autowired
    private CupomDescontoService cupomDescontoService;

    @ApiOperation(value = "Serviço responsável por salvar um cupom de desconto no sistema.")
    @PostMapping
    public ResponseEntity<CupomDesconto> save(@RequestBody @Valid CupomDescontoRequest request) {

	CupomDesconto cupom = cupomDescontoService.save(request.buildCupomDesconto());
	return new ResponseEntity<CupomDesconto>(cupom, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por consultar por ID um cupom de desconto no sistema.")
    @GetMapping("/{id}")
    public CupomDesconto get(@PathVariable Long id) {

	return cupomDescontoService.findById(id);
    }

    @ApiOperation(value = "Serviço responsável por obter uma lista de cupons de desconto da empresa passado na URL.")
    @GetMapping("/porempresa/{chaveEmpresa}")
    public List<CupomDesconto> consultarPorChaveEmpresa(@PathVariable String chaveEmpresa) {

	return cupomDescontoService.consultarPorChaveEmpresa(chaveEmpresa);
    }

    @ApiOperation(value = "Serviço responsável por obter todos os cupons de desconto do sistema.")
    @GetMapping
    public List<CupomDesconto> consultarTodos() {

	return cupomDescontoService.consultarTodos();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Serviço responsável por atualizar as informações do cupom de desconto no sistema.")
    public ResponseEntity<CupomDesconto> update(@PathVariable("id") Long id,
	    @RequestBody CupomDescontoRequest request) {

	cupomDescontoService.update(id, request.buildCupomDesconto());
	return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Rota responsável por remover(exclusão lógica) um cupom de desconto do sistema.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

	cupomDescontoService.delete(id);
	return ResponseEntity.noContent().build();
    }

}
