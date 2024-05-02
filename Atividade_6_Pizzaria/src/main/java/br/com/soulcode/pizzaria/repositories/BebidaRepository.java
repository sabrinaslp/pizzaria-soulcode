package br.com.soulcode.pizzaria.repositories;

import br.com.soulcode.pizzaria.models.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BebidaRepository extends JpaRepository<Bebida, Long> {
    Bebida findByNome(String nome);

}
