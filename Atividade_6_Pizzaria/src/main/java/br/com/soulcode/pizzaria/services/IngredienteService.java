package br.com.soulcode.pizzaria.services;

import br.com.soulcode.pizzaria.models.Ingrediente;
import br.com.soulcode.pizzaria.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public ResponseEntity<?> registrarIngrediente(Ingrediente ingrediente) {
        try {
            if (ingrediente.getIdIngrediente() != 0) {
                throw new IllegalArgumentException("O ID deve ser gerado automaticamente pelo banco de dados.");
            }

            Ingrediente ingredienteCadastrado = ingredienteRepository.save(ingrediente);
            return new ResponseEntity<>(ingredienteCadastrado, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<List<Ingrediente>> findAll(){
        List<Ingrediente> ingredientes = this.ingredienteRepository.findAll();
        return new ResponseEntity<>(ingredientes, HttpStatus.OK);
    }

    public ResponseEntity<?> findById(int id){
        Optional<Ingrediente> resultado = this.ingredienteRepository.findById((long) id);

        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ingrediente não encontrado.");
        } else {
            return ResponseEntity.ok(resultado.get());
        }
    }

    public ResponseEntity<?> deleteById(int id) {
        Optional<Ingrediente> ingredienteResultante = ingredienteRepository.findById((long) id);

        if (ingredienteResultante.isPresent()) {
            Ingrediente ingrediente = ingredienteResultante.get();
            ingredienteRepository.deleteById((long) id);
            return ResponseEntity.ok("Fornada deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornada não encontrada.");
        }
    }

    public ResponseEntity<Ingrediente> updateById(Ingrediente ingredienteAtualizado, long id) {
        // verifica se a fornada com o Id fornecido existe
        Optional<Ingrediente> optionalIngredienteExistente = this.ingredienteRepository.findById(id);

        if (optionalIngredienteExistente.isPresent()) {
            Ingrediente ingredienteExistente = optionalIngredienteExistente.get();
            ingredienteExistente.setNome(ingredienteAtualizado.getNome());
            ingredienteExistente.setPreco(ingredienteAtualizado.getPreco());

            Ingrediente ingredienteAtualizadoSalvo = this.ingredienteRepository.save(ingredienteExistente);
            return ResponseEntity.ok(ingredienteAtualizadoSalvo);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrada com o ID: " + id);
        }
    }

}
