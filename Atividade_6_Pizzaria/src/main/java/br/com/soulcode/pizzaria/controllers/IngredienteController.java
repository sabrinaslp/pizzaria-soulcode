package br.com.soulcode.pizzaria.controllers;

import br.com.soulcode.pizzaria.models.Ingrediente;
import br.com.soulcode.pizzaria.services.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzaria")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @PostMapping("/registrar-ingrediente")
    public ResponseEntity<?> registrarFornada(@RequestBody Ingrediente ingrediente) {
        return ingredienteService.registrarIngrediente(ingrediente);
    }

    @GetMapping("/visualizar-ingredientes")
    public ResponseEntity<List<Ingrediente>> findAll(){
        return ingredienteService.findAll();
    }

    @GetMapping(value = "/visualizar-ingredientes/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        return ingredienteService.findById(id);
    }

    @DeleteMapping(value = "/excluir-ingredientes/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        return ingredienteService.deleteById(id);
    }

    @PutMapping(value = "/editar-ingrediente/{id}")
    public ResponseEntity<Ingrediente> updateById(@RequestBody Ingrediente ingrediente, @PathVariable int id) {
        return ingredienteService.updateById(ingrediente, id);
    }

}
