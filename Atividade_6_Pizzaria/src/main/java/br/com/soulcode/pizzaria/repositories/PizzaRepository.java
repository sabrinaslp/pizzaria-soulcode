package br.com.soulcode.pizzaria.repositories;

import br.com.soulcode.pizzaria.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza findByNome(String nome);
}
