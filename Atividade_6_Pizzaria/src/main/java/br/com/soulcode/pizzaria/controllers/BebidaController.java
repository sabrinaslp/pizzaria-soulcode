package br.com.soulcode.pizzaria.controllers;

import br.com.soulcode.pizzaria.models.Bebida;
import br.com.soulcode.pizzaria.services.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzaria")
public class BebidaController {

    @Autowired
    private BebidaService bebidaService;

    @PostMapping("/cadastro-bebida")
    public ResponseEntity<?> cadastrarBebida(@RequestBody Bebida bebida) {
        return bebidaService.cadastrarBebida(bebida);
    }

    @GetMapping("/visualizar-bebidas")
    public ResponseEntity<List<Bebida>> findAll() {
        List<Bebida> bebidas = bebidaService.findAll();
        return ResponseEntity.ok(bebidas);
    }

    @GetMapping(value = "/visualizar-bebidas/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return bebidaService.findById(id);
    }

    @DeleteMapping(value = "/excluir-bebida/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        return bebidaService.deleteById(id);
    }

    @PutMapping(value = "/editar-bebida/{id}")
    public ResponseEntity<Bebida> updateById(@RequestBody Bebida bebida, @PathVariable long id) {
        return bebidaService.updateById(bebida, id);
    }

}
