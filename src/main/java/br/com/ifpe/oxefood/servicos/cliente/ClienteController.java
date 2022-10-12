package br.com.ifpe.oxefood.servicos.cliente;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import br.com.ifpe.oxefood.util.entity.GenericController;

/**
 * @author Roberto Alencar
 *
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteController extends GenericController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest request) {

	validarPreenchimentoChave(request.getChaveEmpresa());
	Cliente cliente = clienteService.save(request.buildCliente());
	return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }
}
