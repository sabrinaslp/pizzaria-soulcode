package br.com.soulcode.pizzaria.controllers;

import br.com.soulcode.pizzaria.models.Cliente;
import br.com.soulcode.pizzaria.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzaria")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/registrar-cliente")
    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
       return clienteService.registrarCliente(cliente);
    }

    @GetMapping("/visualizar-clientes")
    public ResponseEntity<List<Cliente>> findAll(){
        return clienteService.findAll();
    }

    @GetMapping(value = "/visualizar-clientes/{id}")
    public ResponseEntity<?> findById(@PathVariable long id){
       return clienteService.findById(id);
    }

    @DeleteMapping(value = "/excluir-cliente/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        return clienteService.deleteById(id);
    }

    @PutMapping(value = "/editar-cliente/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return clienteService.updateById(id, clienteAtualizado);
    }

}
