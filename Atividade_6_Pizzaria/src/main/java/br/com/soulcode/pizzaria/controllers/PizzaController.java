package br.com.soulcode.pizzaria.controllers;

import br.com.soulcode.pizzaria.models.Pizza;
import br.com.soulcode.pizzaria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzaria")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping("/registrar-pizza")
    public ResponseEntity<?> registrarPizza(@RequestBody Pizza pizza) {
        return pizzaService.registrarPizza(pizza);
    }

    @GetMapping("/visualizar-pizzas")
    public ResponseEntity<List<Pizza>> findAll() {
        return pizzaService.findAllPizzas();
    }

    @GetMapping("/visualizar-pizzas/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
      return pizzaService.findById(id);
    }

    @DeleteMapping(value = "/excluir-pizza/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
        return pizzaService.deleteById(id);
    }

    @PutMapping(value = "/editar-pizza/{id}")
    public ResponseEntity<Pizza> updateById(@RequestBody Pizza pizza, @PathVariable int id) throws Exception {
        return pizzaService.updateById(pizza, id);
    }

}