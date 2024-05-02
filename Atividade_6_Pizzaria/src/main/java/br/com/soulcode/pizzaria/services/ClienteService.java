package br.com.soulcode.pizzaria.services;

import br.com.soulcode.pizzaria.models.Cliente;
import br.com.soulcode.pizzaria.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<?> registrarCliente(@RequestBody Cliente cliente) {
        try {
            if (cliente.getIdCliente() != null) {
                throw new IllegalArgumentException("O ID deve ser gerado automaticamente pelo banco de dados.");
            }

            Cliente clienteExistente = clienteRepository.findByLogin(cliente.getLogin());

            if (clienteExistente != null) {
                throw new IllegalArgumentException("Um cliente com este login já foi cadastrado.");
            }

            Cliente clienteCadastrado = clienteRepository.save(cliente);
            return new ResponseEntity<>(clienteCadastrado, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> clientes = this.clienteRepository.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    public ResponseEntity<?> findById(long id){
        Optional<Cliente> resultado = this.clienteRepository.findById(id);

        if (resultado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        } else {
            return ResponseEntity.ok(resultado.get());
        }
    }

    public ResponseEntity<?> deleteById(long id) {
        Optional<Cliente> clienteResultante = clienteRepository.findById(id);

        if (clienteResultante.isPresent()) {
            Cliente cliente = clienteResultante.get();
            clienteRepository.deleteById(id);
            return ResponseEntity.ok("Cliente deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
        }
    }

    public ResponseEntity<?> updateById(Long id, Cliente clienteAtualizado) {
        try {
            Cliente clienteExistente = clienteRepository.findById(id)
                    .orElseThrow(() -> new Exception("Cliente não encontrado com o ID: " + id));

            // Atualiza os campos do cliente existente com os valores do cliente atualizado
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setTelefone(clienteAtualizado.getTelefone());
            clienteExistente.setEndereco(clienteAtualizado.getEndereco());
            clienteExistente.setLogin(clienteAtualizado.getLogin());
            clienteExistente.setSenha(clienteAtualizado.getSenha());

            // Salva o cliente atualizado no banco de dados
            Cliente clienteAtualizadoSalvo = clienteRepository.save(clienteExistente);

            return ResponseEntity.ok(clienteAtualizadoSalvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
