package br.com.soulcode.pizzaria.services;

import br.com.soulcode.pizzaria.models.Fornada;
import br.com.soulcode.pizzaria.repositories.FornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornadaService {

    @Autowired
    private FornadaRepository fornadaRepository;

    public ResponseEntity<?> registrarFornada(Fornada fornada) {
        try {
            if (fornada.getIdFornada() != 0) {
                throw new IllegalArgumentException("O ID deve ser gerado automaticamente pelo banco de dados.");
            }

            Fornada fornadaCadastrada = fornadaRepository.save(fornada);
            return new ResponseEntity<>(fornadaCadastrada, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<List<Fornada>> findAll(){
        List<Fornada> fornadas = this.fornadaRepository.findAll();
        return new ResponseEntity<>(fornadas, HttpStatus.OK);
    }

    public ResponseEntity<?> findById(int id){
        Optional<Fornada> resultado = this.fornadaRepository.findById((long) id);

        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornada não encontrada.");
        } else {
            return ResponseEntity.ok(resultado.get());
        }
    }

    public ResponseEntity<?> deleteById(int id) {
        Optional<Fornada> fornadaResultante = fornadaRepository.findById((long) id);

        if (fornadaResultante.isPresent()) {
            Fornada fornada = fornadaResultante.get();
            fornadaRepository.deleteById((long) id);
            return ResponseEntity.ok("Fornada deletada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornada não encontrada.");
        }
    }

    public ResponseEntity<Fornada> updateById(Fornada fornadaAtualizada, long id) {
        // verifica se a fornada com o Id fornecido existe
        Optional<Fornada> optionalFornadaExistente = this.fornadaRepository.findById(id);

        if (optionalFornadaExistente.isPresent()) {
            Fornada fornadaExistente = optionalFornadaExistente.get();
            fornadaExistente.setNumFornada(fornadaAtualizada.getNumFornada());
            fornadaExistente.setQtdPizzas(fornadaAtualizada.getQtdPizzas());

            Fornada fornadaAtualizadaSalva = this.fornadaRepository.save(fornadaExistente);
            return ResponseEntity.ok(fornadaAtualizadaSalva);
        } else {
            throw new IllegalArgumentException("Fornada não encontrada com o ID: " + id);
        }
    }

}
