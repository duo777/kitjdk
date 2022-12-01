package br.com.ifpe.oxefood.servicos.entregador;

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

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import br.com.ifpe.oxefood.modelo.entregador.EntregadorService;
import br.com.ifpe.oxefood.util.entity.GenericController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/entregador")
public class EntregadorController extends GenericController {

    @Autowired
    private EntregadorService entregadorService;

    @ApiOperation(value = "Serviço responsável por salvar um entregador no sistema.")
    @PostMapping
    public ResponseEntity<Entregador> save(@RequestBody @Valid EntregadorRequest request) {

	Entregador entregadorRequisicao = request.buildEntregador();
	Entregador entregadorSalvo = entregadorService.save(entregadorRequisicao);
	return new ResponseEntity<Entregador>(entregadorSalvo, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Serviço responsável por obter um entregador referente ao Id passado na URL.")
    @GetMapping("/{id}")
    public Entregador obterPorID(@PathVariable Long id) {

	return entregadorService.obterPorID(id);
    }

    @ApiOperation(value = "Serviço responsável por obter uma lista de entregadores da empresa passado na URL.")
    @GetMapping("/porempresa/{chaveEmpresa}")
    public List<Entregador> consultarPorChaveEmpresa(@PathVariable String chaveEmpresa) {

	return entregadorService.consultarPorChaveEmpresa(chaveEmpresa);
    }

    @ApiOperation(value = "Serviço responsável por listar todos os entregadores do sistema.")
    @GetMapping
    public List<Entregador> listarTodos() {

	return entregadorService.listarTodos();
    }

    @ApiOperation(value = "Serviço responsável por atualizar as informações do entregador no sistema.")
    @PutMapping("/{id}")
    public ResponseEntity<Entregador> update(@PathVariable("id") Long id, @RequestBody EntregadorRequest request) {

	Entregador entregadorAlterado = entregadorService.update(id, request.buildEntregador());
	return new ResponseEntity<Entregador>(entregadorAlterado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Rota responsável por remover(exclusão lógica) um entregador do sistema.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

	entregadorService.delete(id);
	return ResponseEntity.noContent().build();
    }

}
