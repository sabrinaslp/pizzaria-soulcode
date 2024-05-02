package br.com.soulcode.pizzaria.services;

import br.com.soulcode.pizzaria.models.Pizza;
import br.com.soulcode.pizzaria.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public ResponseEntity<?> registrarPizza(Pizza pizza) {
        try {
            Pizza pizzaExistente = pizzaRepository.findByNome(pizza.getNome());

            if (pizzaExistente != null) {
                throw new IllegalArgumentException("Uma pizza com este nome já foi cadastrada.");
            }

            Pizza pizzaCadastrada = pizzaRepository.save(pizza);
            return new ResponseEntity<>(pizzaCadastrada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<List<Pizza>> findAllPizzas() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }

    public ResponseEntity<?> findById(int id) {
        Pizza pizza = this.pizzaRepository.findById((long) id).orElse(null);

        if (pizza == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pizza não encontrada");
        } else {
            return ResponseEntity.ok(pizza);
        }
    }

    public ResponseEntity<?> deleteById(int id) {
        Optional<Pizza> pizzaResultante = pizzaRepository.findById((long) id);

        if (pizzaResultante.isPresent()) {
            Pizza pizza = pizzaResultante.get();
            pizzaRepository.deleteById((long) id);
            return ResponseEntity.ok("Pizza deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pizza não encontrada.");
        }
    }

    public ResponseEntity<Pizza> updateById(Pizza pizza, int id) throws Exception {
        // verifica se a pizza com o ID fornecido existe
        Pizza pizzaExistente = this.pizzaRepository.findById((long) id)
                .orElseThrow(() -> new Exception("Pizza não encontrada com o ID: " + id));

        // atualiza os campos da pizza existente com os valores da pizza enviada na requisição
        pizzaExistente.setNome(pizza.getNome());
        pizzaExistente.setPrecoBase(pizza.getPrecoBase());
        pizzaExistente.setPersonalizada(pizza.isPersonalizada());

        // salva a pizza atualizada no banco de dados
        Pizza pizzaAtualizada = this.pizzaRepository.save(pizzaExistente);

        return ResponseEntity.ok(pizzaAtualizada);
    }
}
