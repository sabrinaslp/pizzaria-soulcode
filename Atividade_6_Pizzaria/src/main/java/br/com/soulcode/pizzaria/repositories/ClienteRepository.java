package br.com.soulcode.pizzaria.repositories;

import br.com.soulcode.pizzaria.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Cliente findByLogin(String login);
}
