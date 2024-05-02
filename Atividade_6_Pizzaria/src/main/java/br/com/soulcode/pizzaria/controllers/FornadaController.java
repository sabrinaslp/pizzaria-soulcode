package br.com.soulcode.pizzaria.controllers;

import br.com.soulcode.pizzaria.models.Fornada;
import br.com.soulcode.pizzaria.services.FornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzaria")
public class FornadaController {

    @Autowired
    private FornadaService fornadaService;

    @PostMapping("/registrar-fornada")
    public ResponseEntity<?> registrarFornada(@RequestBody Fornada fornada) {
       return fornadaService.registrarFornada(fornada);
    }

    @GetMapping("/visualizar-fornadas")
    public ResponseEntity<List<Fornada>> findAll(){
        return fornadaService.findAll();
    }

    @GetMapping(value = "/visualizar-fornadas/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return fornadaService.findById(id);
    }

    @DeleteMapping(value = "/excluir-fornada/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
       return fornadaService.deleteById(id);
    }

    @PutMapping(value = "/editar-fornada/{id}")
    public ResponseEntity<Fornada> updateById(@RequestBody Fornada fornada, @PathVariable long id) {
        return fornadaService.updateById(fornada, id);
    }

}
