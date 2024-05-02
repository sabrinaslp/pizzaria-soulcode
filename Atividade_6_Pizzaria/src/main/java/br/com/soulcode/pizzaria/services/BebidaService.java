package br.com.soulcode.pizzaria.services;

import br.com.soulcode.pizzaria.models.Bebida;
import br.com.soulcode.pizzaria.repositories.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BebidaService {

    @Autowired
    private BebidaRepository bebidaRepository;

    public ResponseEntity<?> cadastrarBebida(Bebida bebida) {
        try {
            if (bebida.getIdBebida() != 0) {
                throw new IllegalArgumentException("O ID deve ser gerado automaticamente pelo banco de dados.");
            }

            Bebida bebidaExistente = bebidaRepository.findByNome(bebida.getNome());
            if (bebidaExistente != null) {
                throw new IllegalArgumentException("Uma bebida com este nome já foi cadastrada.");
            }

            Bebida bebidaCadastrada = bebidaRepository.save(bebida);
            return new ResponseEntity<>(bebidaCadastrada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public List<Bebida> findAll() {
        return bebidaRepository.findAll();
    }

    public ResponseEntity<?> findById(int id) {
        Optional<Bebida> resultado = bebidaRepository.findById((long) id);

        if (resultado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bebida não encontrada");
        } else {
            return ResponseEntity.ok(resultado.get());
        }
    }

    public ResponseEntity<?> deleteById(int id) {
        Optional<Bebida> bebidaResultante = bebidaRepository.findById((long) id);

        if (bebidaResultante.isPresent()) {
            Bebida bebida = bebidaResultante.get();
            bebidaRepository.deleteById((long) id);
            return ResponseEntity.ok("Bebida deletada com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bebida não encontrada.");
        }
    }

    public ResponseEntity<Bebida> updateById(Bebida bebidaAtualizada, long id) {
        // verifica se a bebida com o Id fornecido existe
        Optional<Bebida> optionalBebidaExistente = this.bebidaRepository.findById(id);

        if (optionalBebidaExistente.isPresent()) {
            Bebida bebidaExistente = optionalBebidaExistente.get();
            bebidaExistente.setNome(bebidaAtualizada.getNome());
            bebidaExistente.setPreco(bebidaAtualizada.getPreco());

            Bebida bebidaAtualizadaSalva = this.bebidaRepository.save(bebidaExistente);
            return ResponseEntity.ok(bebidaAtualizadaSalva);
        } else {
            throw new IllegalArgumentException("Bebida não encontrada com o ID: " + id);
        }
    }
}
